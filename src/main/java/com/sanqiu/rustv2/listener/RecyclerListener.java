package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.model.Blueprinter;
import com.sanqiu.rustv2.model.Recycler;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.DragType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

public class RecyclerListener implements Listener {
    @EventHandler
    public void onRecyclerClick(PlayerInteractEvent event) {
        Player player =  event.getPlayer();
        if(event.getHand()== EquipmentSlot.HAND)
        {
            Action action = event.getAction();
            if(action == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if( block!=null && Recycler.isRecycler(block))
                {
                    Recycler.OpenMenu(player,block);
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onRecyclerBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!Recycler.isRecycler(block)) return;
        Recycler.breakRecycler(block);
    }
    @EventHandler
    public void onRecyclerInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (!Recycler.isRecycler(inventory)) return;
        Recycler.CloseMenu(inventory);
    }
    @EventHandler
    public void onRecyclerInventoryClick(InventoryClickEvent event) {
        Entity entity =  event.getWhoClicked();
        if (!Recycler.isRecycler(event.getInventory())) return;
        if(!(entity instanceof Player))return;
        Recycler.OperateMenu(event);
    }
    @EventHandler
    public void onRecyclerInventoryDrag(InventoryDragEvent event) {
        Entity entity =  event.getWhoClicked();
        if (!Recycler.isRecycler(event.getInventory())) return;
        if(!(entity instanceof Player))return;
        Recycler.drag(event);

    }
}
