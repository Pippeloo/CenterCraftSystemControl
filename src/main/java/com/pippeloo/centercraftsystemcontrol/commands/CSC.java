package com.pippeloo.centercraftsystemcontrol.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CSC implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        // Check if the args array is not empty
        if (args.length > 0) {
            // Case 1: /csc help
            if (args[0].equalsIgnoreCase("help")) {
                new Help().onCommand(commandSender, command, s, args);
            }
            // Case 2: /csc ping
            if (args[0].equalsIgnoreCase("ping")) {
                new Ping().onCommand(commandSender, command, s, args);
            }
        } else {
            // Tell the sender with a error that they need to specify a sub-command
            commandSender.sendMessage(ChatColor.RED + "You need to specify a sub-command!");
        }

        return true;
    }
}
