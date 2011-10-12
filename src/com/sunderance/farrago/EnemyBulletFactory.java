package com.sunderance.farrago;

public class EnemyBulletFactory implements BulletFactory {
	@Override
	public Bullet createBullet(double x, double y, double xSpeed, double ySpeed) {
		return new EnemyBullet(x, y, xSpeed, ySpeed);
	}
}
