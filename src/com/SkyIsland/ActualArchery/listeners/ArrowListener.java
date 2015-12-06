package com.SkyIsland.ActualArchery.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.SkyIsland.ActualArchery.ActualArcheryPlugin;

/**
 * Class dedicated to listening for arrow events and changing their actions to match the specified
 * @author Skyler
 *
 */
public class ArrowListener implements Listener {
	
	public static String metaKey = "arrowtype";
	
	public ArrowListener() {
		Bukkit.getPluginManager().registerEvents(this, ActualArcheryPlugin.plugin);
	}
	
	
	
}
