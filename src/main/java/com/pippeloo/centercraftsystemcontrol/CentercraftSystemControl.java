package com.pippeloo.centercraftsystemcontrol;

import com.pippeloo.centercraftsystemcontrol.commands.CSC;
import com.pippeloo.centercraftsystemcontrol.commands.Ping;
import com.pippeloo.centercraftsystemcontrol.events.Join;
import com.pippeloo.centercraftsystemcontrol.events.Leave;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CentercraftSystemControl extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Log to console
        getLogger().info("CentercraftSystemControl has been enabled!");

        // Load the config file
        this.getConfig().options().copyDefaults(true);
        // Save the config file with comments (if it doesn't exist)
        this.saveDefaultConfig();

        // Register events
        this.registerEvents();
        // Register commands
        this.registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // Log to console
        getLogger().info("CentercraftSystemControl has been disabled!");
    }

    // Register all the events
    private void registerEvents() {
        // Register event from Join class
        getServer().getPluginManager().registerEvents(new Join(), this);
        // Register event from Leave class
        getServer().getPluginManager().registerEvents(new Leave(), this);
    }

    // Register all the commands
    private void registerCommands() {
        // Register command from Ping class
        Objects.requireNonNull(getCommand("ping")).setExecutor(new Ping());
        // Register command from CSC class
        Objects.requireNonNull(getCommand("csc")).setExecutor(new CSC());
    }
}
