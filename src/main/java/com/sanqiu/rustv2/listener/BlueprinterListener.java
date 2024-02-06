package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.model.Blueprinter;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

public class BlueprinterListener implements Listener {
    @EventHandler
    public void onBlueprinterClick(PlayerInteractEvent event) {
        Player player =  event.getPlayer();
        if(event.getHand()== EquipmentSlot.HAND)
        {
            Action action = event.getAction();
            if(action == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if( block!=null && Blueprinter.isBlueprinter(block))
                {
                    Blueprinter.OpenMenu(player);
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onBlueprinterInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (!Blueprinter.isBlueprinter(event.getView())) return;
        Blueprinter.CloseMenu(inventory,(Player) event.getPlayer());
    }
    @EventHandler
    public void onBlueprinterInventoryClick(InventoryClickEvent event) {
        Entity entity =  event.getWhoClicked();
        if (!Blueprinter.isBlueprinter(event.getView())) return;
        if(!(entity instanceof Player))return;
        Blueprinter.OperateMenu(event);
    }
    @EventHandler
    public void onBlueprinterInventoryDrag(InventoryDragEvent event) {
        Entity entity =  event.getWhoClicked();
        if (!Blueprinter.isBlueprinter(event.getView())) return;
        if(!(entity instanceof Player))return;
        if(!Blueprinter.canDrag(event.getRawSlots())){
            event.setCancelled(true);
        }

    }

}
