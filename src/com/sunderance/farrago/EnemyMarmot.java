package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

class EnemyMarmot extends Enemy {
	/**
	 * Speed of the ship
	 */
	public static double SPEED = 3.5;
	
	/**
	 * How close the ship will get to the left and right sides of the screen
	 */
	public static double LEFT_RIGHT_MARGIN = 10;
	
	/**
	 * The chance the ship will change direction randomly
	 */
	public static double CHANGE_DIRECTION_CHANCE = 0.25;
	
	/**
	 * The interval between the ship deciding to change randomly
	 */
	public static double CHANGE_DIRECTION_INTERVAL = 300;
	
	private double tillChangeDirection = CHANGE_DIRECTION_INTERVAL;
	
	Direction direction;
	
	public EnemyMarmot(double initial_x, double initial_y) {
		super(initial_x, initial_y, "enemy-1.png");
		setDirection(Direction.LEFT);
		setYSpeed(SPEED);
	}
	
	/**
	 * Sets whether the ship is moving left or right
	 * 
	 * @param direction The direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
		
		if (direction == Direction.LEFT) {
			setXSpeed(-SPEED);
		} else {
			setXSpeed(SPEED);
		}
	}
	
	/**
	 * The horizontal direction the ship is moving in
	 * 
	 * @return The direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Swaps the horizontal direction the ship is moving in
	 */
	public void swapDirection() {
		setDirection(getDirection().getOpposite());
	}

	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		double width, height, x, y;
		x = getX(); y = getY();
		width = gc.getWidth(); height = gc.getHeight();
		
		/* stop going too far left or right */
		if (x - LEFT_RIGHT_MARGIN <= 0 || x + LEFT_RIGHT_MARGIN > width) {
			this.swapDirection();
		} 
		
		/* randomly swap direction occasionally */
		tillChangeDirection -= delta;
		if (tillChangeDirection <= 0) {
			if (Math.random() <= CHANGE_DIRECTION_CHANCE) {
				swapDirection();
			}
			tillChangeDirection = CHANGE_DIRECTION_INTERVAL;
		}
		
		/* kill if off screen */
		if (y > height) {
			this.kill();
		}
	}
}