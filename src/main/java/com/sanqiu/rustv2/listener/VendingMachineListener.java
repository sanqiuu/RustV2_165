package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.model.Blueprinter;
import com.sanqiu.rustv2.model.VendingMachine;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

public class VendingMachineListener implements Listener {
    @EventHandler
    public void onVendingMachineClick(PlayerInteractEvent event) {
        Player player =  event.getPlayer();
        if(event.getHand()== EquipmentSlot.HAND)
        {
            Action action = event.getAction();
            if(action == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if( block!=null && VendingMachine.isVendingMachine(block))
                {
                    event.setCancelled(true);
                    VendingMachine.OpenMenu(player);

                }
            }
        }
    }

    @EventHandler
    public void onVendingMachineInventoryClick(InventoryClickEvent event) {
        Entity entity =  event.getWhoClicked();
        if (!VendingMachine.isVendingMachine(event.getView())) return;
        if(!(entity instanceof Player))return;
        VendingMachine.OperateMenu(event);
    }

}
