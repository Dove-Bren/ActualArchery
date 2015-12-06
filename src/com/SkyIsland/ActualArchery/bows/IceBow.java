package com.SkyIsland.ActualArchery.bows;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class IceBow extends CustomBow {

	public static double extraDamage = 1.0;
	
	public static int xdiameter = 3;
	
	public static int maxdepth = 5;
	
	/**
	 * Constructs a ice prototype<br />
	 * Ice bows slow entities, or freeze water
	 */
	public IceBow() {
	}
	
	@Override
	public void onFire(Location location, ProjectileSource source) {
			location.getWorld().playSound(location, Sound.GLASS, 1, (float) .7);
	}

	@Override
	public void onHit(Location location) {
		location.getWorld().playSound(location, Sound.GLASS, 1, (float) .7);
		
		if (location.getBlock().getType() != Material.WATER && location.getBlock().getType() != Material.STATIONARY_WATER) {
			return;
		}
		
		for (int i = 0; i < maxdepth; i++) {
			if (location.getBlock().getType() == Material.AIR) {
				break;
			}
			location.add(0, 1, 0);
		}
		
		//check if block is air, else do nothing
		if (location.getBlock().getType() != Material.AIR) {
			return;
		}
		
		location.add(0, -1, 0); //go down to the water
		
		
		for (int i = 0; i < xdiameter; i++)
		for (int j = 0; j < xdiameter; j++) {
			Location l = location.clone().add(i - 1, 0, j - 1);
			System.out.println(l.getBlock().getType());
			if (l.getBlock().getType() == Material.WATER || l.getBlock().getType() == Material.STATIONARY_WATER) {
				l.getBlock().setType(Material.ICE);
			}
		}
	}

	@Override
	public void onHitEntity(Location location, Entity entity) {
		
		if (!(entity instanceof LivingEntity)) {
			return;
		}
		
		((LivingEntity) entity).damage(extraDamage);
		
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 150, 1));
	}

}
