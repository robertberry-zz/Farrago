package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

public class EnemyVirgo extends Enemy {
	double SPEED = 5.0;
	double RECHARGE_TIME = 400.0;
	double BULLET_SPEED = 8.0;
	Gun leftGun, rightGun;
	
	public EnemyVirgo(double initial_x, double initial_y) {
		super(initial_x, initial_y, "enemy-3.png");
		
		setYSpeed(SPEED);
		leftGun = new Gun(this, RECHARGE_TIME, new EnemyBulletFactory());
		leftGun.setYOffset(getRadius());
		rightGun = new Gun(this, RECHARGE_TIME, new EnemyBulletFactory());
		rightGun.setYOffset(getRadius());
	}

	@Override
	public double getSpeed() {
		return SPEED;
	}

	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		double height, y;
		Farrago fgame = (Farrago) game;
		y = getY();
		height = gc.getHeight();

		// fire cannons!
		leftGun.recharge(delta);
		rightGun.recharge(delta);
		if (leftGun.ready()) {
			fgame.queueEnemy(leftGun.shoot(-1.0, BULLET_SPEED));
		} else if (rightGun.ready()) {
			fgame.queueEnemy(rightGun.shoot(1.0, BULLET_SPEED));			
		}
		
		/* kill if off screen */
		if (y > height) {
			this.kill();
		}		
	}

}
