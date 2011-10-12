package com.sunderance.farrago;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Gun {
	private Entity owner;
	private Class bulletClass;
	private double untilCharged = 0.0, rechargeTime = 0.0;

	public Gun(Entity owner, Class bulletClass, double rechargeTime) {
		this.owner = owner;
		if (rechargeTime < 0) {
			// throw error
		}
		this.rechargeTime = rechargeTime;
	}
	
	public boolean ready() {
		return untilCharged <= 0.0;
	}
	
	public double getX() {
		return owner.getX();
	}
	
	public double getY() {
		return owner.getY();
	}
	
	private Entity createBullet() {
		try {
			Constructor bulletConstructor = 
					bulletClass.getConstructor(double.class, double.class);
			return bulletConstructor.newInstance(getX(), getY());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public Entity shoot() {
		if (!this.ready()) {
			// throw error
			return null;
		}
		this.untilCharged = this.rechargeTime;
		return this.createBullet();
	}
}
