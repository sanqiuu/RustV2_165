package com.sanqiu.rustv2;

import com.jeff_media.customblockdata.CustomBlockData;
import com.sanqiu.rustv2.command.MainCmd;
import com.sanqiu.rustv2.listener.*;
import com.sanqiu.rustv2.model.CraftingTable;
import com.sanqiu.rustv2.runnable.PlayerChecker;
import com.sanqiu.rustv2.runnable.SupplyBoxUpdater;
import org.bukkit.plugin.java.JavaPlugin;

public final class RustV2 extends JavaPlugin {
    private static RustV2 plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        setPlugin(this);
        CustomBlockData.registerListener(this);
        getServer().getPluginCommand("rustmc").setExecutor(new MainCmd());
        CraftingTable.RegisterCraftingTableRecipe();
        new PlayerChecker().runTaskTimer(this, 0,20);
        new SupplyBoxUpdater().runTaskTimer(this, 0,10*20);
        getServer().getPluginManager().registerEvents(new BlueprintListener(), this);
        getServer().getPluginManager().registerEvents(new CraftingTableListener(), this);
        getServer().getPluginManager().registerEvents(new OreListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new TNTListener(), this);
        getServer().getPluginManager().registerEvents(new LifeBlockListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnBedListener(), this);
        getServer().getPluginManager().registerEvents(new DecomposerListener(), this);
        getServer().getPluginManager().registerEvents(new SupplyBoxListener(), this);
        getServer().getPluginManager().registerEvents(new LuckyBoxListener(), this);
        getServer().getPluginManager().registerEvents(new VendingMachineListener(), this);
        getServer().getPluginManager().registerEvents(new BlueprinterListener(), this);
        getLogger().info("已成功加载rustmc插件.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("已成功卸载rustmc插件.");
    }
    public static RustV2 getPlugin() {
        return plugin;
    }

    public static void setPlugin(RustV2 plugin) {
        RustV2.plugin = plugin;
    }
}
