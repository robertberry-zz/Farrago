package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

public class EnemyTracker extends Enemy {
	double SPEED = 5.0;	
	
	public EnemyTracker(double initial_x, double initial_y) {
		super(initial_x, initial_y, "enemy-2.png");
		setYSpeed(SPEED);
	}

	@Override
	public double getSpeed() {
		return SPEED;
	}

	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		Farrago fgame = (Farrago) game;
		Player player = fgame.getPlayer();
		
		if (player.getX() > this.getX()) {
			setDirection(Direction.RIGHT);
		} else if (player.getX() < this.getX()) {
			setDirection(Direction.LEFT);
		}
		
		/* kill if off screen */
		if (getY() > gc.getHeight()) {
			this.kill();
		}		
	}
}
