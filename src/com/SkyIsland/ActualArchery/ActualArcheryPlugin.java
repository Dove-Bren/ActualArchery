package com.SkyIsland.ActualArchery;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.SkyIsland.ActualArchery.config.PluginConfig;

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
