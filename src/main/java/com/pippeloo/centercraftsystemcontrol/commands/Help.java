package com.pippeloo.centercraftsystemcontrol.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Help implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        commandSender.sendMessage("===== Help =====\n" +
                "/ping - Check if the server is online\n" +
                "/csc ping - Check if the server is online\n" +
                "===== Help =====");

        return true;
    }
}
