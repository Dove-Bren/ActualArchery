package com.SkyIsland.ActualArchery.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import com.SkyIsland.ActualArchery.ActualArcheryPlugin;
import com.SkyIsland.ActualArchery.bows.BowType;
import com.SkyIsland.ActualArchery.bows.CustomBow;
import com.SkyIsland.ActualArchery.config.PluginConfig;

public class BowListener implements Listener {
	
	public static String bowPrefix = "Bow Type: ";
	
	public BowListener() {
		Bukkit.getPluginManager().registerEvents(this, ActualArcheryPlugin.plugin);
	}
	
	@EventHandler
	public void onArrowFire(EntityShootBowEvent e) {
		if (e.isCancelled()) {
			return;
		}
		
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		
		ItemStack bow = e.getBow();
		List<String> lore = bow.getItemMeta().getLore();
		
		if (lore == null || lore.isEmpty()) {
			return; //do nothing, regular bow
		}
		
		BowType type = null;
		
		//search through lore for a bow type
		for (String line : lore) {
			line = ChatColor.stripColor(line.trim());
			if (line.startsWith(BowListener.bowPrefix)) {
				type = BowType.valueOf(line.substring((BowListener.bowPrefix).length()));
				break;
			}
		}
		
		if (type == null) {
			return;
		}
		
		if (!PluginConfig.getConfig().isEnabled(type)) {
			return;
		}
		
		e.getProjectile().setMetadata(ArrowListener.metaKey, new FixedMetadataValue
				(ActualArcheryPlugin.plugin, type.name()));
		
		CustomBow.getArrow(type).onFire(e.getEntity().getLocation(), e.getEntity());
		
	}
}
