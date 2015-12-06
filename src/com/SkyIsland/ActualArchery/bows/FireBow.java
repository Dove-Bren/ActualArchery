package com.SkyIsland.ActualArchery.bows;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.projectiles.ProjectileSource;

public class FireBow extends CustomBow {

	public static double extraDamage = 1.0;
	
	/**
	 * Constructs a firebow prototype with the given extra damage
	 * @param baseDamage
	 */
	public FireBow() {
	}
	
	@Override
	public void onFire(Location location, ProjectileSource source) {
		location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
		if (source instanceof Player) {
			((Player) source).playEffect(location, Effect.BLAZE_SHOOT, null);
		}
	}

	@Override
	public void onHit(ProjectileSource source, Location location) {
		location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0, 20);
		if (location.getBlock().getType() == Material.AIR) {
			location.getBlock().setType(Material.FIRE);
		}
	}

	@Override
	public void onHitEntity(ProjectileSource source, Location location, Entity entity) {
		
		if (!(entity instanceof LivingEntity)) {
			return;
		}
		
		((LivingEntity) entity).damage(extraDamage);
		
		((LivingEntity) entity).setFireTicks(60);
	}

}
