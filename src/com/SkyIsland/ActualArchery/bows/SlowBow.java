package com.SkyIsland.ActualArchery.bows;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class SlowBow extends CustomBow {

	public static double extraDamage = 0.0;
	
	/**
	 * Constructs a slow bow prototype with the given extra damage
	 * @param baseDamage
	 */
	public SlowBow() {
	}
	
	@Override
	public void onFire(Location location, ProjectileSource source) {
			location.getWorld().playSound(location, Sound.SLIME_WALK, 1, 1);
	}

	@Override
	public void onHit(Location location) {
		location.getWorld().playSound(location, Sound.SLIME_WALK2, 1, 1);
	}

	@Override
	public void onHitEntity(Location location, Entity entity) {
		
		if (!(entity instanceof LivingEntity)) {
			return;
		}
		
		((LivingEntity) entity).damage(extraDamage);
		
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
	}

}
