package com.pippeloo.centercraftsystemcontrol;

import com.pippeloo.centercraftsystemcontrol.commands.Ping;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CentercraftSystemControl extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Log to console
        getLogger().info("CentercraftSystemControl has been enabled!");

        // Register commands
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // Log to console
        getLogger().info("CentercraftSystemControl has been disabled!");
    }

    // Register all the commands
    private void registerCommands() {
        Objects.requireNonNull(getCommand("ping")).setExecutor(new Ping());
    }
}
