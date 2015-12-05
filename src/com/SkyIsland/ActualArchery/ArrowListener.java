package com.SkyIsland.ActualArchery;

import org.bukkit.Bukkit;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

/**
 * Class dedicated to listening for arrow events and changing their actions to match the specified
 * @author Skyler
 *
 */
public class ArrowListener implements Listener {
	
	public ArrowListener() {
		Bukkit.getPluginManager().registerEvents(this, ActualArcheryPlugin.plugin);
	}
	
	@EventHandler
	public void onArrowFire(ProjectileLaunchEvent e) {
		if (e.isCancelled()) {
			return;
		}
		
		Projectile proj = e.getEntity();
		
		if (proj.getM)
	}
	
}
