package com.SkyIsland.ActualArchery.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;

import com.SkyIsland.ActualArchery.ActualArcheryPlugin;
import com.SkyIsland.ActualArchery.bows.BowType;
import com.SkyIsland.ActualArchery.bows.CustomBow;

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
	
	@EventHandler
	public void onArrowHit(ProjectileHitEvent e) {
		if (!(e.getEntity() instanceof Arrow)) {
			return;
		}
		
		List<MetadataValue> meta = e.getEntity().getMetadata(metaKey);
		
		if (meta == null || meta.isEmpty()) {
			return;
		}
		
		BowType type = BowType.valueOf(meta.get(0).asString());
		
		if (type == null) {
			return;
		}
		
		CustomBow.getArrow(type).onHit(e.getEntity().getShooter(), e.getEntity().getLocation());
	}
	
	@EventHandler
	public void onArrowHitEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled() || (!(e.getDamager() instanceof Projectile))) {
			return;
		}
		
		List<MetadataValue> meta = e.getDamager().getMetadata(metaKey);
		
		if (meta == null || meta.isEmpty()) {
			return;
		}
		
		BowType type = BowType.valueOf(meta.get(0).asString());
		
		if (type == null) {
			return;
		}

		CustomBow.getArrow(type).onHitEntity(((Projectile) e.getDamager()).getShooter(), e.getEntity().getLocation(), e.getEntity());
	}
	
	
}
