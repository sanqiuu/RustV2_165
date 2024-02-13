package com.sanqiu.rustv2.model;

import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.morepersistentdatatypes.DataType;
import com.sanqiu.rustv2.RustV2;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.List;

public class Radio {
    private  static final String teamID = "Radio";
    private  static final String metaID = "Radio";
    private static void  setRadioValue(Player player, int value){

        Scoreboard board = player.getScoreboard();
        Team team =  board.getTeam(teamID);
        if(team == null) return;
        player.setMetadata(metaID,new FixedMetadataValue(RustV2.getPlugin(),value));
        ChatColor color;
        if(value<=50){
            color = ChatColor.WHITE;
        }else if (value<=100){
            color = ChatColor.YELLOW;
        }else {
            color = ChatColor.RED;
        }
        team.setPrefix(ChatColor.AQUA+"辐射:"+color+value);
    }
    private static int  getRadioValue(Player player){
        if(!player.hasMetadata(metaID)){
            return 0;
        }
        return player.getMetadata(metaID).get(0).asInt();
    }
    private static void setRadioEffect(Player player, int radioValue){
        PotionEffect effect;
        //effect
        if(radioValue>=50){
            //player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE,,2.0f,0.533F);
            effect = new PotionEffect(PotionEffectType.SLOW,
                    199980, 2, true, false);
            player.addPotionEffect(effect);
        }else {
            player.removePotionEffect(PotionEffectType.SLOW);
        }
        if(radioValue>=60){
            effect = new PotionEffect(PotionEffectType.CONFUSION,
                    199980, 2, true, false);
            player.addPotionEffect(effect);
        }
        else {
            player.removePotionEffect(PotionEffectType.CONFUSION);
        }
        if(radioValue>=70){
            player.damage(2);
        }

    }
    public static void  setRadio(Player player,int value){

        setRadioValue(player,value);
        setRadioEffect(player,value);
    }
    public static void ShowGUI(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(
                ChatColor.RED+"RustMC","SANQIU");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = objective.getScore(ChatColor.BLUE+"=-=-=-=-=-=-=-=-=-=");
        score.setScore(2);
        Team team = scoreboard.registerNewTeam(teamID);
        if(!player.hasMetadata(metaID)){
            player.setMetadata(metaID,new FixedMetadataValue(RustV2.getPlugin(),0));
        }
        String team_entry = ChatColor.BLACK + "" + ChatColor.WHITE;
        team.addEntry(team_entry);
        team.setPrefix(ChatColor.AQUA+"辐射:"+ChatColor.WHITE+player.getMetadata(metaID).get(0).asInt());

        objective.getScore(team_entry).setScore(1);

        Score score3 = objective.getScore(ChatColor.AQUA+"Made By "+ChatColor.WHITE+"Sanqiu");
        score3.setScore(0);
        player.setScoreboard(scoreboard);
    }

    private static boolean isRadioZone(Location location){
        World world = location.getWorld();
        String TAG = "RustPlayer";
        Block block = world.getHighestBlockAt(location);
        boolean isBuilding  = false;
        Plugin plugin = RustV2.getPlugin().getServer().getPluginManager().getPlugin("RustBuildSystem");
        if(plugin!=null){
            NamespacedKey key = new NamespacedKey(plugin,TAG);
            PersistentDataContainer data = new CustomBlockData(block,plugin);
            isBuilding = data.has(key,DataType.asList(DataType.LOCATION));
        }
        return block.getY()>=location.getY() && !isBuilding;
    }
    public static void Radiate(Player player){

        int value = getRadioValue(player);
        if(isRadioZone(player.getLocation())){
            int add = 1;
            value+=add;

        }else{
            value-=1;
            if(value<0){
                value = 0;
            }

        }

        setRadio(player,value);
    }
}
