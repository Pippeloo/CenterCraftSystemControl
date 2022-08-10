package com.pippeloo.centercraftsystemcontrol.commands;

import com.pippeloo.centercraftsystemcontrol.CentercraftSystemControl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Disable implements CommandExecutor {

    private final CentercraftSystemControl plugin;

    public Disable(CentercraftSystemControl plugin) {
        this.plugin = plugin;
    }

    // This command is used to disable the plugin
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // Inform the player that the plugin is disabled
        commandSender.sendMessage(ChatColor.RED + "CentercraftSystemControl has been disabled!");

        // Disable the plugin
        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);

        return true;
    }
}
