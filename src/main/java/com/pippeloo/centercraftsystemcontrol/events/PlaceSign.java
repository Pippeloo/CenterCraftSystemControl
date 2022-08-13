package com.pippeloo.centercraftsystemcontrol.events;

import com.pippeloo.centercraftsystemcontrol.CentercraftSystemControl;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;
import java.util.UUID;

public class PlaceSign implements Listener {

    // Declare variables
    private final CentercraftSystemControl plugin;

    // Constructor
    public PlaceSign(CentercraftSystemControl plugin) {
        this.plugin = plugin;
    }

    // This function gets the plain text on a specific line of a sign
    private String SignMessage(org.bukkit.event.block.SignChangeEvent signEvent, int line) {
        return PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(signEvent.line(line)));
    }

    // This function will add the channel to redstoneSignsData
    private void addChannel(String channel, int type, String owner, int x, int y, int z, boolean isOnWall, String world) {
        String typeString = "Receiver";

        if (type == 1) {
            typeString = "Transmitter";
        }
        this.plugin.redstoneSignsData.getDataConfig().set("channels." + channel + "." + typeString + ".owner", owner);
        this.plugin.redstoneSignsData.getDataConfig().set("channels." + channel + "." + typeString +".x", x);
        this.plugin.redstoneSignsData.getDataConfig().set("channels." + channel + "." + typeString + ".y", y);
        this.plugin.redstoneSignsData.getDataConfig().set("channels." + channel + "." + typeString + ".z", z);
        this.plugin.redstoneSignsData.getDataConfig().set("channels." + channel + "." + typeString + ".isOnWall", isOnWall);
        this.plugin.redstoneSignsData.getDataConfig().set("channels." + channel + "." + typeString + ".world", world);

        // Save the config
        this.plugin.redstoneSignsData.saveData();

    }

    // Execute the code when a sign is placed
    @EventHandler
    public void onSignPlace(org.bukkit.event.block.SignChangeEvent event) {
        final String header = SignMessage(event, 0);

        // Check if the first row is "[RSR]"
        if (header.equals("[RSR]") || header.equals("[RST]")) {
            // Set variable "type" depending on the header
            final int type;
            if (header.equals("[RSR]")) {
                type = 0;
            } else {
                type = 1;
            }

            // Get the sign message from the second row
            final String channel = SignMessage(event, 1);

            // Check if the channel is valid
            if (!channel.equals("")) {
                // Clear the thid and fourth rows
                event.line(2, Component.text(""));
                event.line(3, Component.text(""));
                // Tell the player the information about the sign
                event.getPlayer().sendMessage(Component.text(header + " Sign placed. Channel: " + channel));

                // Save the event.block in a variable
                final org.bukkit.block.Block block = event.getBlock();

                // Add the channel to the list
                addChannel(channel, type, event.getPlayer().getUniqueId().toString(), block.getX(), block.getY(), block.getZ(), true, block.getWorld().getName());
            } else {
                // Tell the player the information about the sign
                event.getPlayer().sendMessage(Component.text(header + " Sign placed. No channel specified."));
            }
        }
    }
}
