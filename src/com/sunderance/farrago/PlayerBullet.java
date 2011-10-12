package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

public class PlayerBullet extends Bullet {
	public static double SPEED = 5.0;
	
	public PlayerBullet(double initialX, double initialY) {
		super(initialX, initialY, 0.0, -SPEED, "plasma-bullet.png");
	}

	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		// TODO Auto-generated method stub
		
	}

}
