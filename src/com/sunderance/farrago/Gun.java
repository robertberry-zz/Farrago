package com.sunderance.farrago;

public class Gun {
	private Entity owner;
	private BulletFactory bulletFactory;
	private double untilCharged = 0.0, rechargeTime = 0.0, angle = 0.0;

	public Gun(Entity owner, double rechargeTime, BulletFactory bulletFactory) {
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

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}
		
	public Bullet shoot() {
		if (!this.ready()) {
			// throw error
			return null;
		}
		this.untilCharged = this.rechargeTime;
		Bullet bullet = this.bulletFactory.createBullet(getX(), getY(), getAngle());
		return bullet;
	}
}
