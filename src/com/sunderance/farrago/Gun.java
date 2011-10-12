package com.sunderance.farrago;

public class Gun {
	private Entity owner;
	private BulletFactory bulletFactory;
	private double untilCharged = 0.0, rechargeTime = 0.0, yOffset = 0.0, xOffset = 0.0;
	
	public Gun(Entity owner, double rechargeTime, BulletFactory bulletFactory) {
		this.owner = owner;
		if (rechargeTime < 0) {
			// throw error
		}
		this.bulletFactory = bulletFactory;
		this.rechargeTime = rechargeTime;
	}
	
	/**
	 * Returns whether the gun is ready to fire
	 * 
	 * @return Whether ready
	 */
	public boolean ready() {
		return untilCharged <= 0.0;
	}
	
	/**
	 * Returns the x position of the gun
	 * 
	 * @return The x position
	 */
	public double getX() {
		return owner.getX() + xOffset;
	}
	
	/**
	 * Returns the y position of the gun
	 * 
	 * @return The y position
	 */
	public double getY() {
		return owner.getY() + yOffset;
	}
	
	/**
	 * Recharges the gun by the given delta
	 * 
	 * @param delta Recharge time
	 */
	public void recharge(double delta) {
		this.untilCharged -= delta;
	}
		
	/**
	 * Creates a new bullet with the given speed
	 * 
	 * @param xSpeed The X speed
	 * @param ySpeed The Y speed
	 * @return The bullet
	 */
	public Bullet shoot(double xSpeed, double ySpeed) {
		this.untilCharged = this.rechargeTime;
		Bullet bullet = this.bulletFactory.createBullet(getX(), getY(),
				xSpeed, ySpeed);
		return bullet;
	}

	/**
	 * Sets Y offset of the Gun from the centre of the Entity
	 * 
	 * @param xOffset The offset
	 */
	public void setYOffset(double yOffset) {
		this.yOffset = yOffset;
	}
	
	/**
	 * Sets X offset of the Gun from the centre of the Entity
	 * 
	 * @param xOffset The offset
	 */
	public void setXOffset(double xOffset) {
		this.xOffset = yOffset;
	}	
}
