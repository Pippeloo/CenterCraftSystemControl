package com.pippeloo.centercraftsystemcontrol.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlaceSign implements Listener {

    // Warn the player when a sign is placed
    @EventHandler
    public void onPlaceSign(org.bukkit.event.block.SignChangeEvent event) {
        event.getPlayer().sendMessage("Hey " + event.getPlayer().getName() + ", you just placed a sign!");
    }
}
