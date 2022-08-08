package com.pippeloo.centercraftsystemcontrol;

import org.bukkit.plugin.java.JavaPlugin;

public final class CentercraftSystemControl extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Log to console
        getLogger().info("CentercraftSystemControl has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // Log to console
        getLogger().info("CentercraftSystemControl has been disabled!");
    }
}
