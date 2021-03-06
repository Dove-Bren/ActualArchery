package com.SkyIsland.ActualArchery.bows;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.SkyIsland.ActualArchery.listeners.BowListener;
import com.google.common.collect.Lists;

public enum BowType {

	FIRE(FireBow.class, Material.BLAZE_POWDER, Material.BLAZE_POWDER, Material.SULPHUR),
	ICE(IceBow.class, Material.SNOW_BALL, Material.SNOW_BALL, Material.ICE),
	SLOW(SlowBow.class, Material.WATER_BUCKET, Material.SOUL_SAND, Material.SLIME_BALL),
	ENDER(EnderBow.class, Material.ENDER_PEARL, Material.ENDER_PEARL, Material.DIAMOND);
	
	private Class<? extends CustomBow> bowClass;
	
	private Recipe recipe;
	
	private BowType(Class<? extends CustomBow> bowClass, Material ... materials) {
		this.bowClass = bowClass;
				
		
		ShapelessRecipe rec = new ShapelessRecipe(getBowItem());
		rec.addIngredient(Material.BOW);
		for (Material mat : materials) {
			rec.addIngredient(mat);
		}
		
		this.recipe = rec;
	}
	
	public CustomBow getBow() {
		try {
			return bowClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void registerRecipe() {
		Bukkit.getServer().addRecipe(recipe);
	}
	
	protected ItemStack getBowItem() {
		ItemStack bow = new ItemStack(Material.BOW, 1);
		ItemMeta meta = bow.getItemMeta();
		
		meta.setDisplayName("Enchanted Bow");
		meta.setLore(Lists.newArrayList(ChatColor.DARK_PURPLE + BowListener.bowPrefix + name()));
		bow.setItemMeta(meta);
		
		return bow;
	}
	
}
