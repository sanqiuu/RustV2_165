package com.sanqiu.rustv2.interfaze;

import com.sanqiu.rustv2.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VehicleInterface {
    public enum VEHICLE_TYPE{
        Car("汽车","Item:\n" +
                "  ==: org.bukkit.inventory.ItemStack\n" +
                "  type: VEHICLE_VEHICLE_CRATE\n" +
                "  meta:\n" +
                "    ==: ItemMeta\n" +
                "    meta-type: UNSPECIFIC\n" +
                "    internal: H4sIAAAAAAAAAONiYOBi4HPKyU/Ods0rySypDElMZ2bgSs1Lz8xLDclMLWIAAmYGzvKM1NSckMqCVBCfg4G9LDUjMzknlUEQyrAqzk0sKolPTgRqAACdf2X6VAAAAA=="),
        Bike("自行车","Item:\n" +
                "  ==: org.bukkit.inventory.ItemStack\n" +
                "  type: VEHICLE_VEHICLE_CRATE\n" +
                "  meta:\n" +
                "    ==: ItemMeta\n" +
                "    meta-type: UNSPECIFIC\n" +
                "    internal: H4sIAAAAAAAAAONiYOBi4HPKyU/Ods0rySypDElMZ2bgSs1Lz8xLDclMLWIAAmYGzvKM1NSckMqCVBCfg4G9LDUjMzknlUEQyrDKzczLjE/KzAYqAACj5ly4VAAAAA=="),
        Plane("飞机","Item:\n" +
                "  ==: org.bukkit.inventory.ItemStack\n" +
                "  type: VEHICLE_VEHICLE_CRATE\n" +
                "  meta:\n" +
                "    ==: ItemMeta\n" +
                "    meta-type: UNSPECIFIC\n" +
                "    internal: H4sIAAAAAAAAAONiYOBi4HPKyU/Ods0rySypDElMZ2bgSs1Lz8xLDclMLWIAAmYGzvKM1NSckMqCVBCfg4G9LDUjMzknlUEEyrAqLsgvKimOL8hJzAOqAQBKSKVhVwAAAA=="),
        OilContent("简便油桶","Item:\n" +
                "  ==: org.bukkit.inventory.ItemStack\n" +
                "  type: VEHICLE_JERRY_CAN\n" +
                "  meta:\n" +
                "    ==: ItemMeta\n" +
                "    meta-type: UNSPECIFIC\n" +
                "    internal: H4sIAAAAAAAAAONiYGBlYEkrTc1xnePAwAAAx2b6Ww8AAAA="),

        SpeedBoat("游艇","Item:\n" +
                "  ==: org.bukkit.inventory.ItemStack\n" +
                "  type: VEHICLE_VEHICLE_CRATE\n" +
                "  meta:\n" +
                "    ==: ItemMeta\n" +
                "    meta-type: UNSPECIFIC\n" +
                "    internal: H4sIAAAAAAAAAONiYOBi4HPKyU/Ods0rySypDElMZ2bgSs1Lz8xLDclMLWIAAmYGzvKM1NSckMqCVBCfg4G9LDUjMzknlUEIyrAqLkhNTYlPyk8sYWAAAPSF85pVAAAA");
        private final String name;
        private final String itemstackString;
        VEHICLE_TYPE(String name,String itemstackString) {
            this.name = name;
            this.itemstackString = itemstackString;
        }
    }
    public static ItemStack create(VEHICLE_TYPE type){

        ItemStack item = null;
        try{
            item = ItemUtil.stringToItem(type.itemstackString);
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
            itemMeta.setDisplayName(type.name);
            item.setItemMeta(itemMeta);
        }


        return item;
    }

}
