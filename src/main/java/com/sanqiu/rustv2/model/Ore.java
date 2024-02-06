package com.sanqiu.rustv2.model;

import com.sanqiu.rustv2.RustV2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Ore {
    public static int OreDropRate = 5;
    public static int AnimalDropRate = 5;
    private static int OreRecoverTicks = 20*60;
    public static boolean isOre(Block block) {
        boolean result = false;
        switch (block.getType()){
            case REDSTONE_ORE:
            case COAL_ORE:
            case IRON_ORE:
            case COBBLESTONE:
            case OAK_LOG:
            case SPRUCE_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case DARK_OAK_LOG:
            case BIRCH_LOG:
                result = true;
                break;

        }
        return  result;
    }
    public  static  ItemStack createCloth(int amount){
        ItemStack itemStack = new ItemStack(Material.PAPER,amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("布料");
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public  static  ItemStack createBlueprint(String type,int amount){
        ItemStack itemStack = new ItemStack(Material.PAPER,amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("蓝图-"+type);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static void OreRecover(Block block){
        Bukkit.getScheduler().scheduleSyncDelayedTask(RustV2.getPlugin(), new Runnable() {
            Material material = block.getType();
            byte data = block.getData();
            Location location = block.getLocation();
            @Override
            public void run() {
                Block block = location.getBlock();
                block.setType(material);
                block.setBlockData(block.getBlockData());
            }
        }, Ore.OreRecoverTicks); //20 Tick (1 Second) delay before run() is called
    }

}
