package com.sanqiu.rustv2.util;

import com.sanqiu.rustv2.RustV2;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {
    private File configFile;
    private YamlConfiguration configYaml;
    private String configName;
    private Plugin plugin;
    public ConfigUtil(String configName){
        this.configName = configName;
        plugin = RustV2.getPlugin();
    }
    public void save(){
        try {
            configYaml.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public   YamlConfiguration load() {
        File pluginDataFolder=plugin.getDataFolder();
        File configFile = new File(pluginDataFolder, configName);
        if (!pluginDataFolder.exists())pluginDataFolder.mkdir();
        if (!configFile.exists())plugin.saveResource(configName, true);
        this.configFile = configFile;
        this.configYaml = YamlConfiguration.loadConfiguration(configFile);
        return configYaml;
    }
}