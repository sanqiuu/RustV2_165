package com.sanqiu.rustv2.interfaze;

public enum RustItem {
    AK47("AK47","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\"AK-47\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Very reliable automatic assault rifle\n" +
            "      chambered in 7.62mm Soviet.\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Large caliber ammunition requires skill\n" +
            "      to control recoil.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      66.5\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Accuracy\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 68 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 76 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 67 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Fire\n" +
            "      Rate\"}],\"text\":\"? \"}],\"text\":\" 61 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 59 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Control\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 68 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: 5\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -0.0181\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: AK_47\n" +
            "      weaponmechanics:ammo-left: 0i"),
    ZuoLun("左轮","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\".357 Magnum\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Revolver firing .357 Magnum ammunition\n" +
            "      for powerful damage over extended ranges.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      60.7\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Accuracy\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 60 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 63 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 56 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Fire\n" +
            "      Rate\"}],\"text\":\"? \"}],\"text\":\" 44 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 76 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Control\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 65 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: 8\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -2.0E-4\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: 357_Magnum\n" +
            "      weaponmechanics:firearm-action-state: 0i\n" +
            "      weaponmechanics:ammo-left: 0i"),
    SanDan("散弹枪","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\"R9-0\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Pump action shotgun that can shoot twice\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"before having to rechamber.\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Effective at close range.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      62.2\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Accuracy\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 55 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 79 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 42 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Fire\n" +
            "      Rate\"}],\"text\":\"? \"}],\"text\":\" 51 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 71 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Control\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 74 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: 14\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -5.0E-4\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: R9_0\n" +
            "      weaponmechanics:firearm-action-state: 0i\n" +
            "      weaponmechanics:ammo-left: 0i"),
    JuJi("狙击枪","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\"AX-50\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Hard hitting, bolt action sniper rifle\n" +
            "      with .50 cal BMG ammunition.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      66.3\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Accuracy\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 82 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 85 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 79 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Fire\n" +
            "      Rate\"}],\"text\":\"? \"}],\"text\":\" 38 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 44 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Control\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 70 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: 13\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -0.033\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: AX_50\n" +
            "      weaponmechanics:firearm-action-state: 0i\n" +
            "      weaponmechanics:ammo-left: 0i"),
    RPG("RPG","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\"RPG-7\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Unguided, self-propelled rocket launcher\n" +
            "      fires a slower projectile with a high-explosive yield.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      57.7\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Accuracy\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 56 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 86 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 85 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Fire\n" +
            "      Rate\"}],\"text\":\"? \"}],\"text\":\" 30 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 49 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Control\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 40 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: 4\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -0.02855\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: RPG_7\n" +
            "      weaponmechanics:ammo-left: 0i"),
    ChongFeng("冲锋枪","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\"Uzi\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Fully automatic open bolt submachine\n" +
            "      gun. Simple, steady, and effective.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      66.7\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Accuracy\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 61 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 70 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 49 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Fire\n" +
            "      Rate\"}],\"text\":\"? \"}],\"text\":\" 64 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 78 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Control\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 78 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: 1\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -0.0019\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: Uzi\n" +
            "      weaponmechanics:ammo-left: 0i"),
    BiShow("匕首","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\"Combat Knife\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Standard military issue, employed for\n" +
            "      fast, quiet, and deadly wetwork.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      29.2\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 90 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\n" +
            "      in blocks\"}],\"text\":\"? \"}],\"text\":\" 4 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 80 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: -10\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: 0.004\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "      GENERIC_ATTACK_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -3.0\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6ba\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: Combat_Knife"),
    BanZiDong("半自动步枪","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\"FN FAL\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Semi-automatic battle rifle with a high\n" +
            "      rate of fire for faster follow up shots.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      68.3\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Accuracy\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 74 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 79 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 70 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Fire\n" +
            "      Rate\"}],\"text\":\"? \"}],\"text\":\" 59 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 60 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Control\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 68 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: 6\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -0.017\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: FN_FAL\n" +
            "      weaponmechanics:ammo-left: 0i"),
    JiQiang("机枪","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: FEATHER\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"gold\",\"text\":\"MG34\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"Fully automatic weapon with a high rate\n" +
            "      of fire and punishing 7.92 Mauser ammunition.\"}'\n" +
            "    - '{\"italic\":false,\"text\":\"\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"\n" +
            "      69.4\"}],\"text\":\"-\"}],\"text\":\"Weapon Statistics \"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Accuracy\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 72 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Damage\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 77 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Range\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 78 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Fire\n" +
            "      Rate\"}],\"text\":\"? \"}],\"text\":\" 70 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Mobility\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 48 \"}],\"text\":\"?\"}'\n" +
            "    - '{\"italic\":false,\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"extra\":[{\"color\":\"gold\",\"extra\":[{\"color\":\"gray\",\"text\":\"Control\"}],\"text\":\"?\n" +
            "      \"}],\"text\":\" 72 \"}],\"text\":\"?\"}'\n" +
            "    custom-model-data: 11\n" +
            "    attribute-modifiers:\n" +
            "      GENERIC_MOVEMENT_SPEED:\n" +
            "      - ==: org.bukkit.attribute.AttributeModifier\n" +
            "        amount: -0.0296\n" +
            "        name: MechanicsCoreAttribute\n" +
            "        uuid: 00000000-0000-0b38-0000-0000000da6bd\n" +
            "        operation: 0\n" +
            "    ItemFlags:\n" +
            "    - HIDE_ENCHANTS\n" +
            "    - HIDE_ATTRIBUTES\n" +
            "    - HIDE_UNBREAKABLE\n" +
            "    - HIDE_DESTROYS\n" +
            "    - HIDE_PLACED_ON\n" +
            "    - HIDE_POTION_EFFECTS\n" +
            "    - HIDE_DYE\n" +
            "    Unbreakable: true\n" +
            "    PublicBukkitValues:\n" +
            "      mechanicscore:deny-crafting: 1i\n" +
            "      weaponmechanics:weapon-title: MG34\n" +
            "      weaponmechanics:ammo-left: 0i"),
    ammo("子弹","Item:\n" +
            "  ==: org.bukkit.inventory.ItemStack\n" +
            "  v: 2586\n" +
            "  type: ARROW\n" +
            "  amount: 11\n" +
            "  meta:\n" +
            "    ==: ItemMeta\n" +
            "    meta-type: UNSPECIFIC\n" +
            "    display-name: '{\"italic\":false,\"color\":\"red\",\"text\":\"ammo\"}'\n" +
            "    lore:\n" +
            "    - '{\"italic\":false,\"color\":\"gray\",\"text\":\"ammo\"}'\n" +
            "    PublicBukkitValues:\n" +
            "      weaponmechanics:ammo-name: Rocket");
    private final String name;
    public final String itemstackString;
    RustItem(String name,String itemstackString) {
        this.name = name;
        this.itemstackString = itemstackString;
    }
}
