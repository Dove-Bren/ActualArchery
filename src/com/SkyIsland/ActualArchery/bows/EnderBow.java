package com.SkyIsland.ActualArchery.bows;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.projectiles.ProjectileSource;

public class EnderBow extends CustomBow {
	
	/**
	 * Constructs an ender bow, which teleports the shooter to the target area<br />
	 */
	public EnderBow() {
	}
	
	@Override
	public void onFire(Location location, ProjectileSource source) {
	}

	@Override
	public void onHit(ProjectileSource source, Location location) {
		if (source instanceof Entity) {
			location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 0);
			((Entity) source).teleport(location);
			location.getWorld().playSound(location, Sound.ENDERMAN_TELEPORT, 1, 1);
		}
	}

	@Override
	public void onHitEntity(ProjectileSource source, Location location, Entity entity) {
		;
	}

}
