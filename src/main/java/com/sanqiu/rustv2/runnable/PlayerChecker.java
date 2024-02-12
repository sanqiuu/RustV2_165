package com.sanqiu.rustv2.runnable;

import com.sanqiu.rustv2.RustV2;
import com.sanqiu.rustv2.model.Radio;
import com.sanqiu.rustv2.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerChecker extends BukkitRunnable {
    @Override
    public void run() {
        for(Player player : RustV2.getPlugin().getServer().getOnlinePlayers()){
            //Bukkit.getLogger().info(ItemUtil.itemToString(player.getItemInHand()));
            /*Block body = player.getLocation().getBlock();
            Block feet = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
            if(body.getType() == Material.WATER || feet.getType() == Material.WATER){
                player.damage(2);
            }else if(body.getType() == Material.COBWEB || feet.getType() == Material.COBWEB)
            {
                player.damage(999);
            }*/
            Radio.Radiate(player);

        }
    }
}
