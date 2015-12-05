package com.SkyIsland.ActualArchery;

import org.bukkit.plugin.java.JavaPlugin;

public class ActualArcheryPlugin extends JavaPlugin {
	
	public static ActualArcheryPlugin plugin;
	
	@Override
	public void onLoad() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		
	}
	
	@Override
	public void onEnable() {
		ActualArcheryPlugin.plugin = this;
		
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
