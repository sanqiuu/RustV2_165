package com.sanqiu.rustv2.model;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

class SUPPLY_ELEMENT{
    public ItemStack itemStack;
    public int weight;
    public SUPPLY_ELEMENT (ItemStack itemStack,int weight){
        this.itemStack = itemStack;
        this.weight = weight;
    }
}
class SupplyData{
    public static SUPPLY_ELEMENT[] SupplyList = {
            new SUPPLY_ELEMENT(new ItemStack(Material.IRON_INGOT,10),100),
            new SUPPLY_ELEMENT(new ItemStack(Material.REDSTONE,10),100),
            new SUPPLY_ELEMENT(new ItemStack(Material.FEATHER,10),100),
            new SUPPLY_ELEMENT(new ItemStack(Material.LEATHER,10),100),
            new SUPPLY_ELEMENT(new ItemStack(Material.STRING,10),100),
            new SUPPLY_ELEMENT(Ore.createCloth(10),100),
            new SUPPLY_ELEMENT(Ore.createBlueprint("铁靴子",1),50),
            new SUPPLY_ELEMENT(Ore.createBlueprint("铁胸甲",1),50),
            new SUPPLY_ELEMENT(Ore.createBlueprint("铁头盔",1),50),
            new SUPPLY_ELEMENT(Ore.createBlueprint("铁护腿",1),50),
            new SUPPLY_ELEMENT(Ore.createBlueprint("TNT",1),50),
            new SUPPLY_ELEMENT(Ore.createBlueprint("AK-47",1),1),
            new SUPPLY_ELEMENT(Ore.createBlueprint("Karabiner 98k",1),1),
            new SUPPLY_ELEMENT(Ore.createBlueprint("MP5",1),5),
            new SUPPLY_ELEMENT(Ore.createBlueprint("火箭炮",1),1),
            new SUPPLY_ELEMENT(Ore.createBlueprint("飞机",1),1),
            new SUPPLY_ELEMENT(Ore.createBlueprint("自行车",1),50),
            new SUPPLY_ELEMENT(Ore.createBlueprint("汽车",1),1)

    };
    public static SUPPLY_ELEMENT[] OreList = {
            new SUPPLY_ELEMENT(new ItemStack(Material.COAL,1),100),
            new SUPPLY_ELEMENT(new ItemStack(Material.REDSTONE,1),100),
            new SUPPLY_ELEMENT(new ItemStack(Material.COBBLESTONE,1),650),
            new SUPPLY_ELEMENT(new ItemStack(Material.GOLD_ORE,1),35),
            new SUPPLY_ELEMENT(new ItemStack(Material.GUNPOWDER,1),5),
            new SUPPLY_ELEMENT(new ItemStack(Material.IRON_ORE,1),100),
            new SUPPLY_ELEMENT(new ItemStack(Material.NETHERITE_SCRAP,1),5),
            new SUPPLY_ELEMENT(new ItemStack(Material.DIAMOND,1),5),
    };
}
class SupplyBoxUtil{


    public static ItemStack  getSupply(SUPPLY_ELEMENT[] SupplyList){

        int totalWeight = 0;
        for (SUPPLY_ELEMENT element : SupplyList){
            totalWeight += element.weight;
        }
        Random random = new Random();
        int randomIndex = -1;
        int randomWeight = random.nextInt(totalWeight);
        for (int i = 0; i < SupplyList.length; i++){
            if (randomWeight < SupplyList[i].weight){
                randomIndex = i;
                break;
            }
            randomWeight -= SupplyList[i].weight;
        }
        return SupplyList[randomIndex].itemStack;
    }
}
public class SupplyBox {
    private static boolean isInventoryEmpty(final Inventory inventory) {
        return Arrays.stream(inventory.getContents()).noneMatch(Objects::nonNull);

    }
    private static List<Location> list = new ArrayList<>();
    public static boolean isSupplyBox(Block block){
        return block.getType() == Material.BARREL;
    }
    public static void update(){
        for(Location location : list){
            Block block = location.getBlock();
            if(isSupplyBox(block)){
                Barrel barrel = (Barrel)block.getState();
                Inventory inventory = barrel.getInventory();
                if(!isInventoryEmpty(inventory)) return;
                for(int num=0;num<3;num++){
                    ItemStack item = SupplyBoxUtil.getSupply(SupplyData.SupplyList);
                    inventory.addItem(item);
                }


            }
        }
    }
    public static void open(Block block){
        Barrel barrel = (Barrel)block.getState();
        Inventory inventory = barrel.getInventory();
        if(!isInventoryEmpty(inventory)) return;
        for(int num=0;num<3;num++){
            ItemStack item = SupplyBoxUtil.getSupply(SupplyData.SupplyList);
            inventory.addItem(item);
        }
        list.add(block.getLocation());
    }
    public static Material changeStone(){
        ItemStack item = SupplyBoxUtil.getSupply(SupplyData.OreList);
        return item.getType();
    }
    public static boolean isSupplyBox(Inventory inventory){
        return  inventory.getType() ==InventoryType.BARREL;
    }
}
