package com.SkyIsland.ActualArchery.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.SkyIsland.ActualArchery.ActualArcheryPlugin;
import com.SkyIsland.ActualArchery.bows.BowType;

public class PluginConfig {
	
	public static enum MsgLevel {
		NONE,
		LOW,
		HIGH,
		ALL;
	}
	
	/**
	 * Creates the default configuration file.
	 * @param outFile
	 * @throws FileNotFoundException 
	 */
	private void createDefaultFile(File outFile) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(outFile);
		
		final String tab = "    "; //four spaces
		
		writer.println("###############################");
		writer.println("# ActualArchery Plugin configuration");
		writer.println("# Values below can be changed, but should not be deleted.");
		writer.println("# Only modify the values, not the keys (modify to the right");
		writer.println("# of the colon!) Possible values will follow the description.");
		writer.println("###############################");
		writer.println();
		writer.println();
		writer.println();
		writer.println("# How frequently should the plugin print messages?");
		writer.println("Values: 0 | 1 | 2 | 3 | NONE | LOW | HIGH | ALL");
		writer.println("msglvl: ALL");
		writer.println();
		writer.println();
		writer.println();
		writer.println("# Is this arrow allowed? (Values: true | false)");
		writer.println("enabled:");
		for (BowType type : BowType.values()) {
			writer.println(tab + type.name() + ": true");
		}
		writer.println();
		writer.println();
		writer.println();
		writer.println("# Is this arrow craftable? (Values: true | false)");
		writer.println("craftable:");
		for (BowType type : BowType.values()) {
			writer.println(tab + type.name() + ": true");
		}
		
		writer.close();
	}
	
	private static PluginConfig config = null;
	
	public static final String configFileName = "config.yml";
	
	private PluginConfig() {
		File file = new File(ActualArcheryPlugin.plugin.getDataFolder(), configFileName);
		
		if (!file.exists()) {
			try {
				createDefaultFile(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		YamlConfiguration yaml = new YamlConfiguration();
		try {
			yaml.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		load(yaml);
	}
	
	public static PluginConfig getConfig() {
		if (config == null) {
			config = new PluginConfig();
		}
		
		return config;
	}
	
	private Map<BowType, Boolean> accessMap;
	
	private Map<BowType, Boolean> craftMap;
	
	private MsgLevel msgLevel;
	
	
	public MsgLevel getMessageLevel() {
		return msgLevel;
	}
	
	public boolean isEnabled(BowType type) {
		if (accessMap.containsKey(type)) {
			return accessMap.get(type);
		}
		
		return false;
	}
	
	public boolean isCraftable(BowType type) {
		if (craftMap.containsKey(type)) {
			return craftMap.get(type);
		}
		
		return false;
	}
	
	
	
	private void load(YamlConfiguration config) {
		
		accessMap = new HashMap<BowType, Boolean>();
		craftMap = new HashMap<BowType, Boolean>();
		
		if (config.contains("enabled")) {
			ConfigurationSection accessSection = config.getConfigurationSection("enabled");
			for (BowType type : BowType.values()) {
				//for all arrow types, try to look up. If not there, put in map as false
				accessMap.put(type, accessSection.getBoolean(type.toString(), false));
			}
		} else {
			ActualArcheryPlugin.plugin.getLogger().warning("Missing section 'enabled' in configuration!\n"
					+ "It's recommended you remove the config and let a new one be generated!");
		}
		
		if (config.contains("craftable")) {
			ConfigurationSection accessSection = config.getConfigurationSection("craftable");
			for (BowType type : BowType.values()) {
				//for all arrow types, try to look up. If not there, put in map as false
				craftMap.put(type, accessSection.getBoolean(type.toString(), false));
			}
		} else {
			ActualArcheryPlugin.plugin.getLogger().warning("Missing section 'craftable' in configuration!\n"
					+ "It's recommended you remove the config and let a new one be generated!");
		}
		
		if (config.contains("msglvl")) {
			Object o = config.get("msglvl");
			
			if (o instanceof Integer) {
				switch ((Integer) o) {
				case 0: msgLevel = MsgLevel.NONE; break;
				case 1: msgLevel = MsgLevel.LOW; break;
				case 2: msgLevel = MsgLevel.HIGH; break;
				default: msgLevel = MsgLevel.ALL; break;
				}
			} else {
				//try and pull from string
				try {
					msgLevel = MsgLevel.valueOf(o.toString());
				} catch (Exception e) {
					//doesn't exist!
					ActualArcheryPlugin.plugin.getLogger().warning("Unable to get warning level for '" 
							+ o.toString() + "' ! Defaulting to ALL");
					msgLevel = MsgLevel.ALL;
				}
			}
			
		} else {
			msgLevel = MsgLevel.ALL;
			ActualArcheryPlugin.plugin.getLogger().warning("Missing section 'msglvl' in configuration!\n"
				+ "It's recommended you remove the config and let a new one be generated!");
		}
	}
	
}
