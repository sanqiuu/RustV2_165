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
}
class SupplyBoxUtil{

    private static boolean isInventoryEmpty(final Inventory inventory) {
        return Arrays.stream(inventory.getContents()).noneMatch(Objects::nonNull);

    }
    public static void addSupply(Inventory inventory,SUPPLY_ELEMENT[] SupplyList){
        if(!isInventoryEmpty(inventory)) return;
        int totalWeight = 0;
        for (SUPPLY_ELEMENT element : SupplyList){
            totalWeight += element.weight;
        }
        for(int num=0;num<3;num++){
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
            inventory.addItem(SupplyList[randomIndex].itemStack);
        }
    }
}
public class SupplyBox {
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
                SupplyBoxUtil.addSupply(inventory,SupplyData.SupplyList);

            }
        }
    }
    public static void open(Block block){
        Barrel barrel = (Barrel)block.getState();
        Inventory inventory = barrel.getInventory();
        SupplyBoxUtil.addSupply(inventory,SupplyData.SupplyList);
        list.add(block.getLocation());
    }
    public static boolean isSupplyBox(Inventory inventory){
        return  inventory.getType() ==InventoryType.BARREL;
    }
}
