package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Player extends Entity {
	private static double DECELERATION_PER_STEP = 0.005;
	private static double ACCELERATION_PER_STEP = 0.010;
	private static double MAXIMUM_ACCELERATION = 0.05;
	private static double GUN_RECHARGE_TIME = 100.0;
	
	private Gun gun;
	
	public Player(double initial_x, double initial_y) {
		super(initial_x, initial_y, "player.png");
		gun = new Gun(this, GUN_RECHARGE_TIME, new PlayerBulletFactory());
	}

	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		Input input = gc.getInput();
		Farrago fgame = (Farrago) game;
		
		/* shoot a bullet? */
		if (input.isKeyDown(Input.KEY_SPACE) && gun.ready()) {
			try {
				fgame.queueEntity(gun.shoot(0.0, -5.0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			gun.recharge(delta);
		}
		
		/* move the player */
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
		
		/* prevent from going off left or right side of screen */
		double radius, x, width;
		radius = getRadius(); x = getX(); width = gc.getWidth();
		
		if (x + radius > width) {
			setXAcceleration(0);
			setXSpeed(0);
			setX(width - radius);
		} else if (x - radius < 0) {
			setXAcceleration(0);
			setXSpeed(0);
			setX(0 + radius);
		}
	}
}
