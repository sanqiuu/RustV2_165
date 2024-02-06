package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.RustV2;
import com.sanqiu.rustv2.model.RespawnBed;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;




public class RespawnBedListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        player.setBedSpawnLocation(null);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        Location respawnLoc = e.getRespawnLocation().clone();
        RespawnBed.onPlayerRespawn(p,respawnLoc);
        if (RespawnBed.spawn_on_sky) {
            respawnLoc.setY(300);
            e.setRespawnLocation(respawnLoc);
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        RespawnBed.onPlayerJoinEvent(p);
    }

    @EventHandler
    public void onPlayerGetOnBed(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
        Block bed = e.getBed();
        RespawnBed.onPlayerGetOnBed(player,bed);
        e.setCancelled(RustV2.getPlugin().getConfig().getBoolean("disable-sleeping"));

    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("床")){
            RespawnBed.operateRespawnMenu(e);
        }
    }

    @EventHandler
    public void onMenuClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("床")){

            Player p = (Player) e.getPlayer();
            if (!p.getCanPickupItems()){
                RespawnBed.openRespawnMenu(p);
            }

        }
    }
}