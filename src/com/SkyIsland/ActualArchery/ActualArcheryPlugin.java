package com.SkyIsland.ActualArchery;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.SkyIsland.ActualArchery.bows.BowType;
import com.SkyIsland.ActualArchery.bows.CustomBow;
import com.SkyIsland.ActualArchery.config.PluginConfig;
import com.SkyIsland.ActualArchery.listeners.ArrowListener;
import com.SkyIsland.ActualArchery.listeners.BowListener;

public class ActualArcheryPlugin extends JavaPlugin {
	
	public static ActualArcheryPlugin plugin;
	
	private BowListener bowListener;
	
	private ArrowListener arrrowListener;
	
	@Override
	public void onLoad() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		
	}
	
	@Override
	public void onEnable() {
		ActualArcheryPlugin.plugin = this;
		
		PluginConfig conf = PluginConfig.getConfig();
		
		for (BowType type : BowType.values()) {
			if (conf.isEnabled(type)) {
				CustomBow.registerArrow(type, type.getBow());
			}
			if (conf.isCraftable(type)) {
				type.registerRecipe();
			}
		}
		
		this.bowListener = new BowListener();
		this.arrrowListener = new ArrowListener();
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(bowListener);
		HandlerList.unregisterAll(arrrowListener);
	}
}
