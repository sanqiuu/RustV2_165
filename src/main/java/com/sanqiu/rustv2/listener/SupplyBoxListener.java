package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.model.Ore;
import com.sanqiu.rustv2.model.SupplyBox;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class SupplyBoxListener implements Listener {

    @EventHandler
    public void onSupplyBoxBreak(BlockBreakEvent event) {
        /*Bukkit.getLogger().info(event.getBlock().getType().toString());
        if(SupplyBox.isSupplyBox(event.getBlock())){

            event.setCancelled(true);
        }*/
        Block block = event.getBlock();
        if(block.getType() == Material.STONE){
            World world = block.getWorld();
            event.setDropItems(false);
            Material material = SupplyBox.changeStone();
            world.dropItemNaturally(block.getLocation(), new ItemStack(material));

        }
    }
    @EventHandler
    public void onSupplyBoxOpen(PlayerInteractEvent event) {
       /* if(event.getHand()== EquipmentSlot.HAND)
        {
            Action action = event.getAction();
            if(action == Action.RIGHT_CLICK_BLOCK) {
                Block block  = event.getClickedBlock();
                if(block!=null && SupplyBox.isSupplyBox(block))
                {
                    SupplyBox.open(block);
                }
            }
        }*/
    }
}
