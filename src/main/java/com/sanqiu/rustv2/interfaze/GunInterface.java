package com.sanqiu.rustv2.interfaze;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GunInterface {
    public enum GUN_TYPE{
        Pistol("格洛克 18","CGM_HANDGUN"),
        ShotGun("S686","CGM_SHOTGUN"),
        Rifle("AK-47","CGM_ASSAULT_RIFLE"),
        Sniper("Karabiner 98k","CGM_RIFLE"),
        Submachine("MP5","CGM_MACHINE_PISTOL"),

        ShotGunAmmo("12号霰弹","CGM_SHELL"),
        RifleAmmo("7.62mm子弹","CGM_ADVANCED_AMMO"),
        PistolAmmo(".45子弹","CGM_BASIC_AMMO"),

        GlitterBomb("闪光弹","CGM_STUN_GRENADE"),
        Grenade("手雷","CGM_GRENADE"),
        Rocket("火箭炮","CGM_BAZOOKA"),
        RocketAmmo("火箭炮子弹","CGM_MISSILE");

        private final String value;
        private final String displayName;
        GUN_TYPE(String displayName,String value) {
            this.value = value;
            this.displayName = displayName;
        }

        public String getValue() {
            return value;
        }
    }

    public static ItemStack create(GUN_TYPE type){
        ItemStack item = null;
        try{
            item =  new ItemStack(Material.valueOf(type.getValue()));
        }catch (Exception e){
        };
        if(item == null){
            item = new ItemStack(Material.RED_BANNER);
            ItemMeta itemMeta = item.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName("ITEM加载失败");
            item.setItemMeta(itemMeta);
        }else {
            ItemMeta itemMeta = item.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName(type.displayName);
            item.setItemMeta(itemMeta);
        }
        return item;
    }
}
