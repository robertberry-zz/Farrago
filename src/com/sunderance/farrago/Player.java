package com.sunderance.farrago;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Player extends Entity {
	private static double DECELERATION_PER_STEP = 0.005;
	
	private static double ACCELERATION_PER_STEP = 0.010;
	
	private static double MAXIMUM_ACCELERATION = 0.05;
	
	private int lives = 3;

	public Player(double initial_x, double initial_y, ImageFactory imageFactory) {
		super(initial_x, initial_y, imageFactory);
	}

	@Override
	protected String getSpritePath() {
		return "player.png";
	}

	@Override
	protected void stepBehaviour(GameContainer gc, int delta) {
		/* move the player */
		Input input = gc.getInput();
		double accelerationChange = 0;
		
		if (input.isKeyDown(Input.KEY_LEFT) ||
				input.isKeyDown(Input.KEY_A)) {
			accelerationChange = -ACCELERATION_PER_STEP;
		} else if (input.isKeyDown(Input.KEY_RIGHT) ||
				input.isKeyDown(Input.KEY_D)) {
			accelerationChange = ACCELERATION_PER_STEP;
		}
		
		double newAcceleration = getXAcceleration() + accelerationChange;
		if (Math.abs(newAcceleration) > MAXIMUM_ACCELERATION) {
			newAcceleration = (newAcceleration > 0) ? MAXIMUM_ACCELERATION :
				-MAXIMUM_ACCELERATION;
		}
		
		setXAcceleration(newAcceleration);
		
		/* deal with deceleration */
		double deceleration;
		if (getXAcceleration() > 0) {
			deceleration = -DECELERATION_PER_STEP;
		} else {
			deceleration = DECELERATION_PER_STEP;
		}

		if (Math.abs(deceleration) > Math.abs(getXAcceleration())) {
			setXAcceleration(0);
		} else {
			setXAcceleration(getXAcceleration() + deceleration);
		}
	}

	@Override
	public boolean isDead() {
		return lives <= 0;
	}
}
