package com.pippeloo.centercraftsystemcontrol;

import com.pippeloo.centercraftsystemcontrol.commands.Ping;
import com.pippeloo.centercraftsystemcontrol.events.Join;
import com.pippeloo.centercraftsystemcontrol.events.Leave;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

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

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("scs")) {
            if (args.length == 0) {
                // The command was executed without any arguments
                sender.sendMessage(ChatColor.RED + "Please specify a command!");
                return true;
            }
            if (args.length >= 1) {
                // The command was executed with arguments
                if (args[0].equalsIgnoreCase("ping")) {
                    // The command was executed with the ping argument
                    new Ping();
                }
            }
        }
        return false;
    }

    // Check the args for the command
    private void getCommandArgs(String[] args) {
        // Check the args for the command
        if (args.length == 1) {
            // Check the args for the command
            if (args[0].equalsIgnoreCase("ping")) {
                // Register the command
                Objects.requireNonNull(getCommand("ping")).setExecutor(new Ping());
            }
        }
    }
}
