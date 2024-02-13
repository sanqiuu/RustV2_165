package com.sanqiu.rustv2.interfaze;

import com.sanqiu.rustv2.RustV2;
import com.sanqiu.rustv2.util.ItemUtil;
import jdk.nashorn.internal.ir.Block;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class RustRecipe {
    public static  void RegisterRustRecipeBook(Player player){
        for(NamespacedKey key : list){
            player.discoverRecipe(key);
        }
        player.discoverRecipe(Material.LECTERN.getKey());
    }
    private static final List<NamespacedKey> list = new ArrayList<>();
    public  static  void RegisterRustRecipe(){
        Plugin plugin = RustV2.getPlugin();
        //ak47
        //111
        //001
        ItemStack item = ItemUtil.stringToItem(RustItem.AK47.itemstackString);
        NamespacedKey key = new NamespacedKey(plugin, "AK47");

        list.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "AAA", "XXA");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //手枪
        //01B
        //00B
        item = ItemUtil.stringToItem(RustItem.ZuoLun.itemstackString);
        key = new NamespacedKey(plugin, "ZuoLun");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "XAB", "XXB");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        recipe.setIngredient('B', Material.IRON_INGOT);
        plugin.getServer().addRecipe(recipe);
        //散弹
        //1BB
        item = ItemUtil.stringToItem(RustItem.SanDan.itemstackString);
        key = new NamespacedKey(plugin, "SanDan");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "ABB", "XXX");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        recipe.setIngredient('B', Material.IRON_INGOT);
        plugin.getServer().addRecipe(recipe);
        //狙击枪
        //010
        //111
        //001
        item = ItemUtil.stringToItem(RustItem.JuJi.itemstackString);
        key = new NamespacedKey(plugin, "JuJi");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XAX", "AAA", "XXA");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //RPG
        //111
        //111
        //111
        item = ItemUtil.stringToItem(RustItem.RPG.itemstackString);
        key = new NamespacedKey(plugin, "RPG");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("AAA", "AAA", "AAA");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //冲锋枪
        //11B
        //00B
        item = ItemUtil.stringToItem(RustItem.ChongFeng.itemstackString);
        key = new NamespacedKey(plugin, "ChongFeng");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "AAB", "XXB");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        recipe.setIngredient('B', Material.IRON_INGOT);
        plugin.getServer().addRecipe(recipe);
        //匕首
        //0B0
        //BBB
        //0B0
        item = ItemUtil.stringToItem(RustItem.BiShow.itemstackString);
        key = new NamespacedKey(plugin, "BiShow");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XBX", "BBB", "XBX");
        recipe.setIngredient('B', Material.IRON_INGOT);
        plugin.getServer().addRecipe(recipe);
        //半自动步枪
        //111
        //00B
        item = ItemUtil.stringToItem(RustItem.BanZiDong.itemstackString);
        key = new NamespacedKey(plugin, "BanZiDong");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "AAA", "XXB");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        recipe.setIngredient('B', Material.IRON_INGOT);
        plugin.getServer().addRecipe(recipe);
        //机枪
        //111
        //011
        item = ItemUtil.stringToItem(RustItem.JiQiang.itemstackString);
        key = new NamespacedKey(plugin, "JiQiang");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "AAA", "XAA");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //子弹
        item = ItemUtil.stringToItem(RustItem.ammo.itemstackString);
        key = new NamespacedKey(plugin, "ammo");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "ABC", "XXX");
        recipe.setIngredient('A', Material.REDSTONE);
        recipe.setIngredient('B', Material.COAL);
        recipe.setIngredient('C', Material.IRON_NUGGET);
        plugin.getServer().addRecipe(recipe);
        //领地柜
        item = new ItemStack(Material.BEACON);
        key = new NamespacedKey(plugin, "BEACON");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("AAA", "ABA", "CCC");
        recipe.setIngredient('A', Material.GLASS);
        recipe.setIngredient('B', Material.IRON_BLOCK);
        recipe.setIngredient('C', Material.GOLD_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //Burst炮台
        item = ItemUtil.stringToItem(RustItem.BurstTower.itemstackString);
        key = new NamespacedKey(plugin, "BurstTower");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("AAX", "AAX", "XXX");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //Burst子弹
        item = ItemUtil.stringToItem(RustItem.BurstAmmo.itemstackString);
        key = new NamespacedKey(plugin, "BurstAmmo");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("AAX", "AAX", "XXX");
        recipe.setIngredient('A', Material.COBBLESTONE);
        plugin.getServer().addRecipe(recipe);
        //siege炮台
        item = ItemUtil.stringToItem(RustItem.siegeTower.itemstackString);
        key = new NamespacedKey(plugin, "siegeTower");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XAA", "XAA", "XXX");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //siege子弹
        item = ItemUtil.stringToItem(RustItem.siegeAmmo.itemstackString);
        key = new NamespacedKey(plugin, "siegeAmmo");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XAA", "XAA", "XXX");
        recipe.setIngredient('A', Material.COBBLESTONE);
        plugin.getServer().addRecipe(recipe);
        //slow炮台
        item = ItemUtil.stringToItem(RustItem.slowTower.itemstackString);
        key = new NamespacedKey(plugin, "slowTower");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "AAX", "AAX");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //slow子弹
        item = ItemUtil.stringToItem(RustItem.slowAmmo.itemstackString);
        key = new NamespacedKey(plugin, "slowAmmo");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "AAX", "AAX");
        recipe.setIngredient('A', Material.COBBLESTONE);
        plugin.getServer().addRecipe(recipe);
        //heal炮台
        item = ItemUtil.stringToItem(RustItem.healTower.itemstackString);
        key = new NamespacedKey(plugin, "healTower");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "XAA", "XAA");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        plugin.getServer().addRecipe(recipe);
        //heal子弹
        item = ItemUtil.stringToItem(RustItem.healAmmo.itemstackString);
        key = new NamespacedKey(plugin, "healAmmo");
        list.add(key);
        recipe = new ShapedRecipe(key,item);
        recipe.shape("XXX", "XAA", "XAA");
        recipe.setIngredient('A', Material.COBBLESTONE);
        plugin.getServer().addRecipe(recipe);
    }
}
