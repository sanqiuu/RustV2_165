package com.sanqiu.rustv2.model;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class LuckyBox {
    private static final String menuTitle = "研究台";
    private static int interPos = 3;
    private static int outerPos = 5;
    private static int material_num = 25;
    private static final Material LuckyBoxType = Material.LECTERN;
    public static void OpenMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null , InventoryType.DISPENSER,menuTitle);
        for(int i=0;i<inventory.getSize();i++){
            if(i==outerPos) continue;
            else if(i == interPos) {
                ItemStack button  = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                ItemMeta itemMeta = button.getItemMeta();
                itemMeta.setDisplayName("开启");
                button.setItemMeta(itemMeta);
                inventory.setItem(i,button);
            }
            else if( i == 4) inventory.setItem(i,new ItemStack(Material.NETHERITE_SCRAP,material_num));
            else inventory.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        player.openInventory(inventory);
    }

    public static  void OperateMenu(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();
        if(item == null) return;
        Material material = item.getType();
        if(material == Material.BLACK_STAINED_GLASS_PANE ||
                material == Material.NETHERITE_SCRAP ){
            event.setCancelled(true);
            return;
        }
        int RawSlot = event.getRawSlot();
        Inventory inventory = event.getInventory();
        if(RawSlot == interPos) {
            inventory.setItem(outerPos,new ItemStack(Material.PAPER));
            event.setCancelled(true);
        }
    }
    public static boolean isLuckBox(InventoryView view){

        return view.getTitle().equals(menuTitle) ;
    }
    public  static  boolean isLuckBox(Block block){
        return block.getType() == LuckyBoxType;
    }
    public static void CloseMenu(Inventory inventory, Player player){
        ItemStack item = inventory.getItem(outerPos);
        if(item!= null) {
            player.getInventory().addItem(item);
        }

    }
}
