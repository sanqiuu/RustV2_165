package com.sanqiu.rustv2.model;


import com.sanqiu.rustv2.RustV2;
import com.sanqiu.rustv2.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


public class LuckyBox {
    private static final String menuTitle = "研究台";
    private static final Material LuckyBoxType = Material.LECTERN;
    public static void OpenMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null , InventoryType.DISPENSER,menuTitle);
        for(int i=0;i<inventory.getSize();i++){
            inventory.setItem(i,new ItemStack(RustData.waste,i+1));
        }
        player.openInventory(inventory);
    }
    private static void randomAward(Player player,Inventory inventory){
       ItemStack[] itemArray = {new  ItemStack(Material.BREAD),
               new  ItemStack(Material.BREAD),
               new  ItemStack(Material.APPLE),
               new  ItemStack(Material.PAPER),
               new  ItemStack(Material.BRICK_WALL),
               new  ItemStack(Material.YELLOW_BED),
               new  ItemStack(Material.WHITE_WOOL),
               new  ItemStack(Material.RED_BED),
               new  ItemStack(Material.GRAY_BED),
               new  ItemStack(Material.COAL),
               new  ItemStack(Material.OAK_LOG),
               new  ItemStack(Material.DARK_OAK_DOOR),
               new  ItemStack(Material.IRON_DOOR),
               new  ItemStack(Material.GREEN_BED),
               new  ItemStack(Material.STRING),
               new  ItemStack(Material.GOLD_BLOCK),
               new  ItemStack(Material.OAK_PRESSURE_PLATE),
               new  ItemStack(Material.INFESTED_CRACKED_STONE_BRICKS),
               new  ItemStack(Material.IRON_SWORD),
               new  ItemStack(Material.CROSSBOW),
               new  ItemStack(Material.GOLDEN_AXE),
               new  ItemStack(Material.WHITE_WOOL),
               new  ItemStack(Material.OAK_SLAB),
               new  ItemStack(Material.CHISELED_STONE_BRICKS),
               new  ItemStack(Material.EMERALD_BLOCK),
               new  ItemStack(Material.DIAMOND_HELMET),
               new  ItemStack(Material.CRIMSON_BUTTON),
               new  ItemStack(Material.ACACIA_WOOD),
               new  ItemStack(Material.GOLDEN_AXE),
               new  ItemStack(Material.DARK_OAK_WOOD),
               new  ItemStack(Material.DIAMOND_SWORD),
               new  ItemStack(Material.FLETCHING_TABLE),
               new  ItemStack(Material.SAND),
               new  ItemStack(Material.STONE_SWORD),
               new  ItemStack(Material.IRON_SWORD),
               new  ItemStack(Material.CHAIN),
               new  ItemStack(Material.BLACK_WOOL),
               new  ItemStack(Material.HORN_CORAL_BLOCK),
               new  ItemStack(Material.DIAMOND),
               new  ItemStack(Material.GRAY_BED),
               new  ItemStack(Material.GOLDEN_AXE),
               new  ItemStack(Material.GOLDEN_HOE),
               new  ItemStack(Material.GOLD_NUGGET),
               new  ItemStack(Material.DARK_OAK_BOAT),
               new  ItemStack(Material.DIAMOND_LEGGINGS)};
        List<ItemStack> list = new LinkedList<ItemStack>(Arrays.asList(itemArray));
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
