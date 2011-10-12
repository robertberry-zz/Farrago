package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

public class EnemyTracker extends Enemy {
	private static double SPEED = 5.0;	
	private static double RECHARGE_TIME = 200.0;
	private static double BULLET_SPEED = 10;
	
	Gun gun;
	
	public EnemyTracker(double initial_x, double initial_y) {
		super(initial_x, initial_y, "enemy-2.png");
		setYSpeed(SPEED);
		gun = new Gun(this, RECHARGE_TIME, new EnemyBombFactory());
	}

	@Override
	public double getSpeed() {
		return SPEED;
	}

	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		Farrago fgame = (Farrago) game;
		Player player = fgame.getPlayer();
		
		/* follow player */
		if (player.getX() > this.getX()) {
			setDirection(Direction.RIGHT);
		} else if (player.getX() < this.getX()) {
			setDirection(Direction.LEFT);
		}
		
		/* fire bomb */
		gun.recharge(delta);
		if (gun.ready()) {
			fgame.queueEnemy(gun.shoot(0.0, BULLET_SPEED));
		}
		
		/* kill if off screen */
		if (getY() > gc.getHeight()) {
			this.kill();
		}		
	}
}
