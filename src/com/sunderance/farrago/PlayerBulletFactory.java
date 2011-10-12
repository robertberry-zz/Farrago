package com.sunderance.farrago;

public class PlayerBulletFactory implements BulletFactory {

	@Override
	public Bullet createBullet(double x, double y, double angle) {
		
		return new PlayerBullet(x, y);
	}
	
}
