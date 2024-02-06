package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.model.CraftingTable;
import com.sanqiu.rustv2.model.Decomposer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

public class DecomposerListener implements Listener {
    @EventHandler
    public void onDecomposerClick(PlayerInteractEvent event) {
        Player player =  event.getPlayer();
        if(event.getHand()== EquipmentSlot.HAND)
        {
            Action action = event.getAction();
            if(action == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if( block!=null && Decomposer.isDecomposer(block))
                {
                    Decomposer.OpenMenu(player,block);
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onDecomposerPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (!Decomposer.isDecomposer(block)) return;
        Decomposer.placeDecomposer(block);
    }
    @EventHandler
    public void onDecomposerInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (!Decomposer.isDecomposer(inventory)) return;
        Decomposer.CloseMenu(inventory);
    }
    @EventHandler
    public void onDecomposerInventoryClick(InventoryClickEvent event) {
        Entity entity =  event.getWhoClicked();
        if (!Decomposer.isDecomposer(event.getInventory())) return;
        if(!(entity instanceof Player))return;
        Decomposer.OperateMenu(event);
    }
}
