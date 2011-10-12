package com.sunderance.farrago;

public class PlayerBulletFactory implements BulletFactory {

	@Override
	public Bullet createBullet(double x, double y, double xSpeed, double ySpeed) {
		return new PlayerBullet(x, y, xSpeed, ySpeed);
	}
	
}
