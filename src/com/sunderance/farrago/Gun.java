package com.sunderance.farrago;

public class Gun {
	private Entity owner;
	private BulletFactory bulletFactory;
	private double untilCharged = 0.0, rechargeTime = 0.0;

	public Gun(Entity owner, double rechargeTime, BulletFactory bulletFactory) {
		this.owner = owner;
		if (rechargeTime < 0) {
			// throw error
		}
		this.bulletFactory = bulletFactory;
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
	
	public void recharge(double delta) {
		this.untilCharged -= delta;
	}
		
	public Bullet shoot(double xSpeed, double ySpeed) throws Exception {
		if (!this.ready()) {
			throw new Exception("Gun is not ready.");
		}
		this.untilCharged = this.rechargeTime;
		Bullet bullet = this.bulletFactory.createBullet(getX(), getY(), xSpeed, ySpeed);
		return bullet;
	}
}
