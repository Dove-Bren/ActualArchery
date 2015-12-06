package com.SkyIsland.ActualArchery.bows;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.projectiles.ProjectileSource;

/**
 * Details a custom arrow.
 * @author Skyler
 *
 */
public abstract class CustomBow {
	
	private static Map<BowType, CustomBow> arrowMap = new HashMap<BowType, CustomBow>();
	
	/**
	 * Attempts to register the provided arrow prototype to the given type.<br />
	 * This method does not overwrite an existing arrow type if it's there. To do that, see
	 * {@link #registerArrow(BowType, CustomBow, boolean)}
	 * @param type
	 * @param arrow
	 * @return True if the arrow was successfully registered, and false otherwise (like if the arrowtype's taken)
	 */
	public static boolean registerArrow(BowType type, CustomBow arrow) {
		return registerArrow(type, arrow, false);
	}
	
	/**
	 * Registers the arrow to the type. 
	 * @param type
	 * @param arrow
	 * @param overwrite Whether or not to register the arrow
	 * @return True if the arrow was successfully registered, false otherwise (like on !overwrite && collision)
	 */
	public static boolean registerArrow(BowType type, CustomBow arrow, boolean overwrite) {
		if (!arrowMap.containsKey(type) || overwrite) {
			arrowMap.put(type, arrow);
			return true;
		}
		
		return false;
	}
	
	public static CustomBow getArrow(BowType type) {
		return arrowMap.get(type);
	}
	
	/**
	 * Called when an arrow is fired.
	 * @param location The location the projectile is fired from
	 * @param source the source of the projectile
	 */
	public abstract void onFire(Location location, ProjectileSource source);
	
	/**
	 * Called whenever an arrow hits something. This can be <b>either a block or an entity</b>, and is called
	 * even when the {@link #onHitEntity(Location, Entity)} method is also called.<br />
	 * Examples of possible use include some sort of area of effect thing regardless of if it hits an entity or not
	 * @param location
	 */
	public abstract void onHit(Location location);
	
	/**
	 * Called when an arrow hits an entity.<br />
	 * Please note that any time this method is called, a call to the {@link #onFire(Location, ProjectileSource)}
	 * even is also made!
	 * @param location
	 * @param entity
	 */
	public abstract void onHitEntity(Location location, Entity entity);
	
}
