package com.sanqiu.rustv2.listener;



import com.sanqiu.rustv2.model.LifeBlock;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;



public class LifeBlockListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        LifeBlock lifeBlock = new LifeBlock(block);
        if(!lifeBlock.onBlockBreak(player)) {
            event.setCancelled(true);
        }

    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        LifeBlock lifeBlock = new LifeBlock(block);
        if(!lifeBlock.onBlockPlace(player)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event)
    {
        event.setCancelled(true);
        for(Block block: event.blockList()){
            LifeBlock lifeBlock = new LifeBlock(block);
            lifeBlock.onExplodeInteract();
        }

    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event)
    {
        event.setCancelled(true);
        if(event.getEntityType()!= EntityType.PLAYER) return;
        for(Block block: event.blockList()){
            LifeBlock lifeBlock = new LifeBlock(block);
            lifeBlock.onExplodeInteract();
        }

    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = event.getClickedBlock();
        if(block == null) return;
        LifeBlock lifeBlock = new LifeBlock(block);
        Player player = event.getPlayer();
        if(!lifeBlock.onPlayerInteract(player)) {
            event.setCancelled(true);
        }
    }
}
