package com.sunderance.farrago;

public class EnemyBombFactory implements BulletFactory {

	@Override
	public Bullet createBullet(double x, double y, double xSpeed, double ySpeed) {
		return new EnemyBomb(x, y, xSpeed, ySpeed);
	}

}
