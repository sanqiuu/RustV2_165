package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.model.LuckyBox;
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

public class LuckyBoxListener implements Listener {
    @EventHandler
    public void onLuckyBoxClick(PlayerInteractEvent event) {
        Player player =  event.getPlayer();
        if(event.getHand()== EquipmentSlot.HAND)
        {
            Action action = event.getAction();
            if(action == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if( block!=null && LuckyBox.isLuckBox(block))
                {
                    LuckyBox.OpenMenu(player);
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onLuckyBoxInventoryClick(InventoryClickEvent event) {
        Entity entity =  event.getWhoClicked();
        if (!LuckyBox.isLuckBox(event.getView())) return;
        if(!(entity instanceof Player))return;
        LuckyBox.OperateMenu(event);
    }

}
