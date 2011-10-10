package com.sunderance.farrago;

import org.newdawn.slick.GameContainer;

public class PlayerBullet extends Bullet {
	public static double SPEED = 5.0;
	
	public PlayerBullet(double initialX, double initialY,
			ImageFactory imageFactory) {
		super(initialX, initialY, 0.0, -SPEED, imageFactory);
	}

	@Override
	protected String getSpritePath() {
		return "plasma-bullet.png";
	}

	@Override
	protected void stepBehaviour(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		
	}

}
