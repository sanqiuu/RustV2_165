package com.sanqiu.rustv2.model;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.sanqiu.rustv2.RustV2;
import com.sanqiu.rustv2.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Blueprint {
    static private final String TAG = "Blueprint";
    private final Player player;
    private final ItemStack itemStack;
    NamespacedKey key;
    PersistentDataContainer container;
    public Blueprint(Player player, ItemStack itemStack){
        this.player  = player;
        this.itemStack  = itemStack;
        key = new NamespacedKey(RustV2.getPlugin(), TAG);
        container = player.getPersistentDataContainer();
    }
    public static  boolean isBlueprint(ItemStack item){
        boolean result = false;
        if(item.getType() == Material.PAPER){
            String displayName = Objects.requireNonNull(item.getItemMeta()).getDisplayName();
            if(displayName.split("-")[0].equals("蓝图")){
                result = true;
            }
        }
        return result;
    }
    public  boolean hasBlueprint(){
        boolean result = false;
       String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        List<String> list = container.get(key,DataType.asList(DataType.STRING));
        if(list != null){
            for (String string : list){
                if (string.equals(name)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public  void addBlueprint(){
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        name = name.split("-")[1];
        List<String> list = container.get(key,DataType.asList(DataType.STRING));
        if(list == null){
            list = new ArrayList<>();
            container.set(key,DataType.asList(DataType.STRING),list);
        }else if(!list.contains(name)){
            ItemUtil.subPlayerItemAmount(player,itemStack,1);
            list.add(name);
            container.set(key,DataType.asList(DataType.STRING),list);
        }

    }
}