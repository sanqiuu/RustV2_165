package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.interfaze.RustRecipe;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RustRecipeListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player player=evt.getPlayer();
        RustRecipe.RegisterRustRecipeBook(player);
    }
}
