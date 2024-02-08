package com.sanqiu.rustv2.model;

import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.morepersistentdatatypes.DataType;
import com.sanqiu.rustv2.RustV2;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

class RecyclerHolder implements InventoryHolder {
    public Block block;
    public RecyclerHolder(Block block){
        this.block = block;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
public class Recycler {
    private  static final String menuTitle = "分解机";
    private  static  final String TAG = "Recycler";
    private  static  final int button_slot = 17;
    private  static  final int inv_size = 27;
    private  static  final List<Integer> enterPos = Arrays.asList(10,11,12,13,14,15);
    private  static  final Material RecyclerType = Material.STONECUTTER;

    private static  boolean isWorking(Block block){
        return block.hasMetadata(TAG);
    }
    private  static void markWorking(Block block){
        block.setMetadata(TAG,new FixedMetadataValue(RustV2.getPlugin(),1));
    }
    private  static  void clearWorking(Block block){
        block.removeMetadata(TAG,RustV2.getPlugin());
    }
    private static ItemStack[] getArray(Block block){
        RustV2 plugin = RustV2.getPlugin();
        NamespacedKey key =  new NamespacedKey(plugin, TAG);
        PersistentDataContainer container = new CustomBlockData(block, plugin);
        return container.get(key,DataType.ITEM_STACK_ARRAY);
    }
    private static void setArray(Block block ,ItemStack[] array ){
        RustV2 plugin = RustV2.getPlugin();
        NamespacedKey key =  new NamespacedKey(plugin, TAG);
        PersistentDataContainer container = new CustomBlockData(block, plugin);
        container.set(key,DataType.ITEM_STACK_ARRAY, array);
    }
    public  static boolean isRecycler(Block block){
        return block.getType() ==RecyclerType;
    }


    public static void breakRecycler(Block block){
        block.removeMetadata(TAG,RustV2.getPlugin());
    }
    private static boolean isMaterialOut(ItemStack itemStack){
        return itemStack.getType() == Material.NETHERITE_SCRAP;
    }
    private static void setMaterialOut(Inventory inventory,ItemStack itemStack){
        inventory.addItem(new ItemStack(Material.NETHERITE_SCRAP));
    }
    private static void work(Block block){
        markWorking(block);
        new BukkitRunnable(){
            int delay = 10;
            boolean needStop = true;
            @Override
            public void run() {

                if(delay > 0) {
                    ItemStack[] array = getArray(block);
                    Inventory inventory  = Bukkit.createInventory(null ,inv_size,menuTitle);
                    inventory.setContents(array);
                    for(int i:enterPos){
                        ItemStack item = inventory.getItem(i);
                        if(item == null ||isMaterialOut(item)) continue;
                        int amount = item.getAmount();
                        if(amount == 1){
                            inventory.clear(i);
                        }else {
                            item.setAmount(amount-1);
                        }
                        setMaterialOut(inventory,item);
                        needStop = false;
                        break;
                    }
                    setArray(block,inventory.getContents());
                    if(needStop){
                        cancel();
                    }
                    delay --;
                } else{
                    clearWorking(block);
                    this.cancel();
                }

            }
        }.runTaskTimer(RustV2.getPlugin(), 0,5*20);
    }


    public static boolean isRecycler(Inventory inventory){

        return inventory.getHolder() instanceof RecyclerHolder;
    }
    public  static void OpenMenu(Player player,Block block){

        ItemStack[] array =   getArray(block);

        Inventory inventory = Bukkit.createInventory(new RecyclerHolder(block) ,inv_size,menuTitle);

        for(int i =0;i<inv_size;i++){
            if(enterPos.contains(i)) {
                if(array!=null)  inventory.setItem(i,array[i]);
            }
            else if(i == button_slot) {
                ItemStack button= new ItemStack(Material.LIME_STAINED_GLASS_PANE);

                ItemMeta itemMeta = button.getItemMeta();
                itemMeta.setDisplayName("开启");
                button.setItemMeta(itemMeta);
                inventory.setItem(i,button);
            }
            else inventory.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }

        player.openInventory(inventory);
        new BukkitRunnable(){
            @Override
            public void run() {
                if(inventory.getViewers().isEmpty()) cancel();
                ItemStack[] array = getArray(block);
                if(array == null){
                    setArray(block,inventory.getContents());
                }else {
                    inventory.setContents(array);
                }
            }
        }.runTaskTimerAsynchronously(RustV2.getPlugin(), 0,20);
    }

    public static void CloseMenu(Inventory inventory){
        RecyclerHolder  holder = (RecyclerHolder)inventory.getHolder();
        ItemStack[] array = inventory.getContents();
        assert holder != null;
        setArray(holder.block,array);
    }
    public static  void OperateMenu(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();
        if(item!=null){
            Material material = item.getType();
            if(material == Material.BLACK_STAINED_GLASS_PANE){
                event.setCancelled(true);
                return;
            }
        }
        int slot = event.getRawSlot();

        RecyclerHolder holder = (RecyclerHolder)event.getInventory().getHolder();
        assert holder != null;
        Block block = holder.block;

        if(enterPos.contains(slot)) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    setArray(block, event.getInventory().getContents());
                }
            }.runTaskLaterAsynchronously(RustV2.getPlugin(), 1);

        }else if(button_slot == slot){


            if(!isWorking(block)) {
                work(block);
            }
            event.setCancelled(true);
        }


    }
    public static void drag(InventoryDragEvent event){

        for(int slot :event.getRawSlots()){
            if(enterPos.contains(slot)){
                RecyclerHolder holder = (RecyclerHolder)event.getInventory().getHolder();
                assert holder != null;
                Block block = holder.block;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        setArray(block, event.getInventory().getContents());
                    }
                }.runTaskLaterAsynchronously(RustV2.getPlugin(), 1);
            }
        }
    }
}
