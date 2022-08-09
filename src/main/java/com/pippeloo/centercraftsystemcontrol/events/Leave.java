package com.pippeloo.centercraftsystemcontrol.events;

import com.pippeloo.centercraftsystemcontrol.CentercraftSystemControl;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {

    // Declare variables
    CentercraftSystemControl plugin;
    String message;

    // Constructor
    public Leave() {
        plugin = CentercraftSystemControl.getPlugin(CentercraftSystemControl.class);
        message = this.plugin.getConfig().getString("leave-message");
    }

    // Check the leave message for placeholders
    private String checkPlaceholders(String message, String playerName) {
        message = message.replace("{player}", playerName);
        return message;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        // Check the join message for placeholders
        this.message = checkPlaceholders(this.message, event.getPlayer().getName());
        // Create quitMessage component
        Component quitMessage = Component.text(this.message);
        // Send the message to all online players
        event.quitMessage(quitMessage);

    }
}
