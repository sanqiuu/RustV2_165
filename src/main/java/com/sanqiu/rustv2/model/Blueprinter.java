package com.sanqiu.rustv2.model;

import com.sanqiu.rustv2.RustV2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public class Blueprinter {
    private static final String menuTitle = "蓝图制图台";
    private static int interPos = 3;
    private static int outerPos = 5;
    private static int material_num = 25;
    private static final Material BlueprinterType = Material.CARTOGRAPHY_TABLE;
    public static void OpenMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null , InventoryType.DISPENSER,menuTitle);
        for(int i=0;i<inventory.getSize();i++){
            if(i == interPos||i==outerPos) continue;
            else if( i == 4) inventory.setItem(i,new ItemStack(Material.NETHERITE_SCRAP,material_num));
            else inventory.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        player.openInventory(inventory);
    }

    public static  void OperateMenu(InventoryClickEvent event){
        HumanEntity entity =  event.getWhoClicked();
        if(!(entity instanceof Player)) return;
        ItemStack itemCurrent = event.getCurrentItem();
        if(itemCurrent == null) return;
        Material material = itemCurrent.getType();
        if(material == Material.BLACK_STAINED_GLASS_PANE ||
                material == Material.NETHERITE_SCRAP ){
            event.setCancelled(true);
        }

        new BukkitRunnable(){
            @Override
            public void run() {
                int RawSlot = event.getRawSlot();
                if(RawSlot!=interPos && RawSlot!= outerPos) return;
                Inventory inventory = event.getInventory();
                ItemStack itemCursor = event.getCursor();
                ItemStack itemCurrent = event.getCurrentItem();
                ItemStack itemIn = inventory.getItem(interPos);
                ItemStack itemOut = inventory.getItem(outerPos);


                //Bukkit.getLogger().info("itemCursor"+event.getCursor());
                //Bukkit.getLogger().info("getCurrentItem"+event.getCurrentItem());
                if(RawSlot == interPos) {
                    //放入
                    if(itemCurrent.getType() != Material.AIR && itemCursor.getType()==Material.AIR){

                        if(itemOut == null){
                            if(itemIn != null && itemIn.getType() != Material.PAPER){
                                inventory.setItem(outerPos,new ItemStack(Material.PAPER));
                            }

                        }
                    }else if(itemCursor != null && itemCurrent.getType() == Material.AIR ){
                        //拿出
                        if(itemOut != null && itemOut.getType() == Material.PAPER){
                            inventory.clear(outerPos);
                        }
                    }
                }else if  (RawSlot == outerPos){
                        //拿出
                    if(itemCurrent.getType() == Material.AIR && itemCursor.getType()!=Material.AIR){
                        if(itemCursor.getType() == Material.PAPER){
                            inventory.clear(interPos);
                        }
                    }
                }
            }
        }.runTaskLaterAsynchronously(RustV2.getPlugin(), 1);

    }
    public static boolean canDrag(Set<Integer> slot){

        return !slot.contains(outerPos)  &&  !slot.contains(interPos);
    }
    public static boolean isBlueprinter(InventoryView view){

        return view.getTitle().equals(menuTitle) ;
    }
    public  static  boolean isBlueprinter(Block block){
        return block.getType() == BlueprinterType;
    }
    public static void CloseMenu(Inventory inventory, Player player){
        ItemStack item = inventory.getItem(outerPos);
        if(item!= null) {
            player.getInventory().addItem(item);
        }

    }
}
