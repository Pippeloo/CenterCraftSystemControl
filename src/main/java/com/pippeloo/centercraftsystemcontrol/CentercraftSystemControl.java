package com.pippeloo.centercraftsystemcontrol;

import com.pippeloo.centercraftsystemcontrol.commands.Ping;
import com.pippeloo.centercraftsystemcontrol.dataManagers.RedstoneSignsData;
import com.pippeloo.centercraftsystemcontrol.events.Join;
import com.pippeloo.centercraftsystemcontrol.events.Leave;
import com.pippeloo.centercraftsystemcontrol.events.PlaceSign;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CentercraftSystemControl extends JavaPlugin {

    public RedstoneSignsData redstoneSignsData;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Log to console
        getLogger().info("CentercraftSystemControl has been enabled!");

        // Load the database
        this.redstoneSignsData = new RedstoneSignsData(this);

        // Load the config file
        this.getConfig().options().copyDefaults(true);
        // Save the config file with comments (if it doesn't exist)
        this.saveDefaultConfig();

        // Register commands
        this.registerCommands();
        // Register events
        this.registerEvents();
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

    // Register all the events
    private void registerEvents() {
        // Register event from Join
        getServer().getPluginManager().registerEvents(new Join(), this);
        // Register event from Leave
        getServer().getPluginManager().registerEvents(new Leave(), this);
        // Register event from Sign placement
        getServer().getPluginManager().registerEvents(new PlaceSign(this), this);
    }
}
