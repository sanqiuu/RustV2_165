package com.sanqiu.rustv2.listener;

import com.sanqiu.rustv2.model.Blueprint;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class BlueprintListener implements Listener {
    @EventHandler
    public void onBlueprintUse(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(event.getHand()== EquipmentSlot.HAND)
        {
            if(action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)
            {
                ItemStack item = player.getInventory().getItemInMainHand();
                Blueprint blueprint = new Blueprint(player,item);
                if(Blueprint.isBlueprint(item)){
                    if(blueprint.hasBlueprint()){
                        player.sendMessage( "该蓝图已被学习过");
                    }else {
                        blueprint.addBlueprint();
                        player.sendMessage( "学习成功");
                    }

                }
            }
        }
    }

}