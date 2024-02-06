package com.sanqiu.rustv2.model;

import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.morepersistentdatatypes.DataType;
import com.sanqiu.rustv2.RustV2;
import com.sanqiu.rustv2.util.ItemUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

class DecomposerHolder implements InventoryHolder {
    public Block block;
    public DecomposerHolder(Block block){
        this.block = block;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
public class Decomposer {
    private static final String menuTitle = "分解机";
    private static final String TAG = "Decomposer";
    private static final int button_slot = 17;
    private static final List<Integer> enterPos = Arrays.asList(10,11,12,13,14,15);
    private static final Material DecomposerType = Material.STONECUTTER;

    public static void OpenMenu(Player player, Block block){
        RustV2 plugin = RustV2.getPlugin();
        NamespacedKey key =  new NamespacedKey(plugin, TAG);
        PersistentDataContainer container = new CustomBlockData(block, plugin);
        List<ItemStack> list =   container.get(key,DataType.asList(DataType.ITEM_STACK));
        int size = 27;
        Inventory inventory = Bukkit.createInventory(new DecomposerHolder(block) ,size,menuTitle);
        if(list == null){
            for(int i =0;i<size;i++){
                if(enterPos.contains(i)) continue;
                if(i == button_slot) {
                    ItemStack button;
                    if(block.hasMetadata(TAG)){
                        button = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                    }else {
                        button = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                    }
                    ItemMeta itemMeta = button.getItemMeta();
                    itemMeta.setDisplayName("开启");
                    button.setItemMeta(itemMeta);
                    inventory.setItem(i,button);
                }
                else inventory.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
        }else {
            for(int i =0;i<size;i++){
                inventory.setItem(i,list.get(i));
                if(i == button_slot){
                    ItemStack itemStack = inventory.getItem(i);
                    if(block.hasMetadata(TAG)){
                        itemStack.setType(Material.RED_STAINED_GLASS_PANE);
                    }else {
                        itemStack.setType(Material.LIME_STAINED_GLASS_PANE);
                    }
                }
            }
        }

        player.openInventory(inventory);
    }
    public static boolean isDecomposer(Block block){
        return block.getType() ==DecomposerType;
    }
    public static boolean isDecomposer(Inventory inventory){

        return inventory.getHolder() instanceof DecomposerHolder;
    }
    public static void placeDecomposer(Block block){
        block.removeMetadata(TAG,RustV2.getPlugin());
    }
    public static void CloseMenu(Inventory inventory){

        DecomposerHolder holder = (DecomposerHolder)inventory.getHolder();
        assert holder != null;
        RustV2 plugin = RustV2.getPlugin();
        List<ItemStack> list = Arrays.asList(inventory.getContents());
        PersistentDataContainer container = new CustomBlockData(holder.block, plugin);
        NamespacedKey key =  new NamespacedKey(plugin, TAG);
        container.set(key,DataType.asList(DataType.ITEM_STACK), list);
    }
    private static void decompose(Inventory inventory){
        RustV2 plugin = RustV2.getPlugin();
        DecomposerHolder holder = (DecomposerHolder)inventory.getHolder();
        Block block = holder.block;
        if(block.hasMetadata(TAG)) return;
        List<ItemStack>  list = Arrays.asList(inventory.getContents());

        boolean needStart = false ;
        for(int i:enterPos){
            ItemStack item = list.get(i);
            if(item != null && item.getType() != Material.NETHERITE_SCRAP) {
                needStart = true;
                break;
            }
        }
        if(!needStart) return;

        block.setMetadata(TAG,new FixedMetadataValue(plugin,1));


        ItemStack button = inventory.getItem(button_slot);
        button.setType(Material.RED_STAINED_GLASS_PANE);


        new BukkitRunnable(){
            int delay = 10;
            @Override
            public void run() {
                boolean needStop = true;
                if(delay > 0) {

                    for(int i:enterPos){
                        ItemStack item = list.get(i);
                        if(item == null ||item.getType() == Material.NETHERITE_SCRAP) continue;
                        int amount = item.getAmount();
                        if(amount == 1){
                            item.setType(Material.NETHERITE_SCRAP);
                        }else {
                            item.setAmount(amount-1);
                            inventory.addItem(new ItemStack(Material.NETHERITE_SCRAP));
                        }

                        needStop = false;
                        break;
                    }
                    delay --;
                }

                if(delay<=0 || needStop){
                    block.removeMetadata(TAG,plugin);
                    button.setType(Material.LIME_STAINED_GLASS_PANE);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0,20);
    }
    public static  void OperateMenu(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();
        if(item == null) return;
        Material material = item.getType();
        if(material == Material.BLACK_STAINED_GLASS_PANE){
            event.setCancelled(true);
            return;
        }
        if(button_slot == event.getRawSlot()){
            decompose(event.getInventory());
            event.setCancelled(true);
        }
    }
}
