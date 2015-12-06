package com.SkyIsland.ActualArchery.bows;

import java.lang.reflect.InvocationTargetException;

public enum BowType {

	FIRE(FireBow.class),
	ICE(FireBow.class),
	SLOW(FireBow.class),
	ENDER(FireBow.class);
	
	private Class<? extends CustomBow> bowClass;
	
	private BowType(Class<? extends CustomBow> bowClass) {
		this.bowClass = bowClass;
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
	
}
