package com.pippeloo.centercraftsystemcontrol.events;

import com.pippeloo.centercraftsystemcontrol.CentercraftSystemControl;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

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
    private void addChannel(String channel) {
        // Check if the channel is already in the list
        if (!this.plugin.redstoneSignsData.getDataConfig().contains("channels." + channel)) {
            // If not, add it to the list
            this.plugin.redstoneSignsData.getDataConfig().set("channels." + channel, (true));
            this.plugin.redstoneSignsData.saveData();
        }
    }

    // Execute the code when a sign is placed
    @EventHandler
    public void onSignPlace(org.bukkit.event.block.SignChangeEvent event) {
        final String header = SignMessage(event, 0);

        // Check if the first row is "[RSR]"
        if (header.equals("[RSR]")) {
            // Get the sign message from the second row
            final String channel = SignMessage(event, 1);

            // Check if the channel is valid
            if (!channel.equals("")) {
                // Clear the thid and fourth rows
                event.line(2, Component.text(""));
                event.line(3, Component.text(""));
                // Tell the player the information about the sign
                event.getPlayer().sendMessage(Component.text(header + " Sign placed. Channel: " + channel));
                // Add the channel to the list
                addChannel(channel);
            } else {
                // Tell the player the information about the sign
                event.getPlayer().sendMessage(Component.text(header + " Sign placed. No channel specified."));
            }
        }
    }
}
