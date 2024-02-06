package com.sanqiu.rustv2.model;

import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.morepersistentdatatypes.DataType;
import com.sanqiu.rustv2.RustV2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.*;

public class LifeBlock {
    static private final String TAG = "LifeBlock";
    NamespacedKey key;
    PersistentDataContainer container;
    Block block;
    public LifeBlock(Block block){
        RustV2 plugin = RustV2.getPlugin();
        this.block = block;
        key = new NamespacedKey(plugin, TAG);
        container = new CustomBlockData(block,plugin);
    }

    public  boolean onBlockPlace(Player player){
        boolean result = true;
        if(Ore.isOre(block)) {
            result = false;
            return result;
        };
        if(block.getBlockData() instanceof Door){
            PersistentDataContainer container2 = new CustomBlockData(block.getRelative(BlockFace.UP),RustV2.getPlugin());
            container2.set(key,DataType.UUID,player.getUniqueId());
        }else if(block.getBlockData() instanceof Bed){
            BlockFace facing = player.getFacing();
            PersistentDataContainer container2 = new CustomBlockData(block.getRelative(facing),RustV2.getPlugin());
            container2.set(key,DataType.UUID,player.getUniqueId());
        }

        container.set(key,DataType.UUID,player.getUniqueId());

        return result;
    }
    public  boolean onPlayerInteract(Player player){
        boolean result = true;
        BlockData data = block.getBlockData();
        if(data instanceof Door || data instanceof TrapDoor){
            UUID uuid = container.get(key,DataType.UUID);
            if(uuid!=null && isLifeBlock()  && !player.getUniqueId().equals(uuid)){;
                result = false;
                player.sendMessage("操作失败，该装置由["+ Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName()+"]放置");
            }
        }
        return result;
    }
    public  boolean isLifeBlock(){
        boolean result =false;
        switch (block.getType()){
            case OAK_PLANKS:
            case STONE_BRICKS:
            case CRACKED_STONE_BRICKS:
            case CHISELED_STONE_BRICKS:
            case OAK_DOOR:
            case IRON_DOOR:
            case CHEST:
            case GLASS:
            case OAK_TRAPDOOR:
            case STONE_BRICK_STAIRS:
            case COBBLESTONE_STAIRS:
            case OAK_STAIRS:
                result = true;
                break;
        }
        return result;
    }

    public  void onExplodeInteract(){
        Material material = block.getType();
        boolean result = false;
        switch (material){
            case STONE_BRICKS:
                block.setType(Material.CRACKED_STONE_BRICKS);
                break;
            case CRACKED_STONE_BRICKS:
                result = true;
                break;
            case CHISELED_STONE_BRICKS:
                block.setType(Material.STONE_BRICKS);
                break;
            case STONE_BRICK_STAIRS:
                block.setType(Material.COBBLESTONE_STAIRS);
                break;
            case IRON_DOOR:
                break;
            case OAK_PLANKS:
            case OAK_DOOR:
            case CHEST:
            case GLASS:
            case OAK_TRAPDOOR:
            case OAK_STAIRS:
            case COBBLESTONE_STAIRS:
            default:
                result = true;
                break;
        }
        if(result){
            block.setType(Material.AIR);
            container.remove(key);
        }
    }
    public  boolean onBlockBreak(Player player){

        boolean can_break = false;
        UUID uuid = container.get(key,DataType.UUID);
        if(uuid!=null){
            if(!player.getUniqueId().equals(uuid)){
                player.sendMessage("你不能破坏["+ Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName()+"]放置的方块");
            }else {
                can_break = true;
            }
        }else{
            if(Ore.isOre(block)){
                can_break = true;
            }
        }
        return can_break;
    }
}