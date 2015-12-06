package com.SkyIsland.ActualArchery.bows;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.projectiles.ProjectileSource;

public class FireBow extends CustomBow {

	private double baseDamage;
	
	/**
	 * Constructs a firebow prototype with the given extra damage
	 * @param baseDamage
	 */
	public FireBow(double extraDamage) {
		this.baseDamage = extraDamage;
	}
	
	@Override
	public void onFire(Location location, ProjectileSource source) {
		; //nothing on fire
	}

	@Override
	public void onHit(Location location) {
		location.getWorld().playEffect(location, Effect.BLAZE_SHOOT, 1, 4);
	}

	@Override
	public void onHitEntity(Location location, Entity entity) {
		if (!(entity instanceof LivingEntity)) {
			return;
		}
		
		((LivingEntity) entity).damage(baseDamage);
	}

}
