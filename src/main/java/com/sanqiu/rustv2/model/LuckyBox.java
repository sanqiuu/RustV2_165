package com.sanqiu.rustv2.model;


import com.sanqiu.rustv2.RustV2;
import com.sanqiu.rustv2.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


public class LuckyBox {
    private static final String menuTitle = "研究台";
    private static final Material LuckyBoxType = Material.LECTERN;
    public static void OpenMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null , InventoryType.DISPENSER,menuTitle);
        inventory.setItem(0,new ItemStack(Material.OAK_LOG,64));
        inventory.setItem(1,new ItemStack(Material.STONE,32));
        inventory.setItem(2,new ItemStack(Material.COAL,32));
        inventory.setItem(3,new ItemStack(Material.IRON_INGOT,4));
        inventory.setItem(4,new ItemStack(Material.COBBLESTONE,64));
        inventory.setItem(5,new ItemStack(Material.DIRT,64));
        inventory.setItem(6,new ItemStack(Material.SAND,64));
        inventory.setItem(7,new ItemStack(Material.DIAMOND,1));
        inventory.setItem(8,new ItemStack(Material.GOLD_INGOT,2));
        player.openInventory(inventory);
    }
    private static void randomAward(Player player,Inventory inventory){
        List<ItemStack> list = new LinkedList<ItemStack>();
        Random random = new Random();
        for(int i=0;i<inventory.getSize();i++){

            Enchantment[] effect_array = Enchantment.values();
            Enchantment effect = effect_array[random.nextInt(effect_array.length)];
            int level = random.nextInt(effect.getMaxLevel())+effect.getStartLevel();
            ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);

            EnchantmentStorageMeta esm = (EnchantmentStorageMeta) itemStack.getItemMeta();

            esm.addStoredEnchant(effect, level, true);
            itemStack.setItemMeta(esm);
            list.add(itemStack);
        }
        Collections.shuffle(list);
        inventory.setContents(list.toArray(new ItemStack[0]));

       new BukkitRunnable(){
            int loop_num  = 0;
           @Override
           public void run() {
               if(list.size()<=2){
                   player.closeInventory();
                   for(ItemStack item:list){
                       player.getInventory().addItem(item);
                   }
                   cancel();
                   return;
               }
               Collections.shuffle(list);
               for(int i =0;i<2;i++){
                   Random random = new Random();
                   int random_index = random.nextInt(list.size());
                   list.remove(random_index);
               }
               loop_num ++ ;
               int list_size = list.size();
               for(int i =0 ;i<inventory.getSize();i++){
                   if(i<loop_num){
                       inventory.setItem(i,new ItemStack(Material.LIME_STAINED_GLASS_PANE));
                   }else if(i-loop_num<list_size){
                       inventory.setItem(i,list.get(i-loop_num));
                   }else {
                       inventory.setItem(i,new ItemStack(Material.LIME_STAINED_GLASS_PANE));
                   }
               }
           }
       }.runTaskTimer(RustV2.getPlugin(), 20,20);


     }
    public static  void OperateMenu(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)) return;
        Inventory inventory = event.getInventory();
        int size = inventory.getSize();
        if(size == 45) {
            event.setCancelled(true);
            return;
        };
        int RawSlot = event.getRawSlot();
        if(RawSlot> size) return;
        ItemStack item = inventory.getItem(RawSlot);
        int item_amount = item.getAmount();
        Player player = (Player) event.getWhoClicked();
        if(!ItemUtil.isPlayerItemEnough(player,item,item_amount)){
            player.sendMessage("材料不足");
        }else{
            ItemUtil.subPlayerItemAmount(player,item,item_amount);
            player.sendMessage("购买成功");
            player.closeInventory();
            Inventory new_inv = Bukkit.createInventory(null,45,"研究台");
            player.openInventory(new_inv);
            randomAward(player,new_inv);
        }
        event.setCancelled(true);
    }
    public static boolean isLuckBox(InventoryView view){

        return view.getTitle().equals(menuTitle) ;
    }
    public  static  boolean isLuckBox(Block block){
        return block.getType() == LuckyBoxType;
    }

}
