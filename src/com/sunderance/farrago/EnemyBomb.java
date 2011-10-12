package com.sunderance.farrago;

public class EnemyBomb extends Bullet {
	public EnemyBomb(double initialX, double initialY, double xSpeed,
			double ySpeed) {
		super(initialX, initialY, xSpeed, ySpeed, "explosive-bullet.png");
	}
}
