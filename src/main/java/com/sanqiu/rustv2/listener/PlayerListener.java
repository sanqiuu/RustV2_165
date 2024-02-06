package com.sanqiu.rustv2.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player player=evt.getPlayer();
        player.sendMessage("Welcome to play RustMc, made by Sanqiu");
    }
}
