package com.pippeloo.centercraftsystemcontrol.dataManagers;

import com.pippeloo.centercraftsystemcontrol.CentercraftSystemControl;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class RedstoneSignsData {

    private CentercraftSystemControl plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public RedstoneSignsData(CentercraftSystemControl plugin) {
        this.plugin = plugin;
    }

    public void reloadData() {
        if (configFile == null) {
            configFile = new File(this.plugin.getDataFolder(), "redstoneSigns.yml");
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultConfigStream = this.plugin.getResource("redstoneSigns.yml");
        if (defaultConfigStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultConfigStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getDataConfig() {
        if (this.dataConfig == null) {
            this.reloadData();
        }
        return this.dataConfig;
    }

    public void saveData() {
        if (this.dataConfig == null || this.configFile == null) {
            return;
        }
        try {
            this.getDataConfig().save(this.configFile);
        } catch (Exception e) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save the config to" + this.configFile, e);
        }
    }

    public void saveDefaultData() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "redstoneSigns.yml");
        }
        if (!this.configFile.exists()) {
            this.plugin.saveResource("redstoneSigns.yml", false);
        }
    }
}
