package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

class EnemyMarmot extends Enemy {

	public static double SPEED = 3.5;
	
	public static double LEFT_RIGHT_MARGIN = 10;
	
	public static double CHANGE_DIRECTION_CHANCE = 0.25;
	public static double CHANGE_DIRECTION_INTERVAL = 300;
	
	private double tillChangeDirection = CHANGE_DIRECTION_INTERVAL;
	
	Direction direction;
	
	public EnemyMarmot(double initial_x, double initial_y) {
		super(initial_x, initial_y, "enemy-1.png");
		setDirection(Direction.LEFT);
		setYSpeed(SPEED);
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
		
		if (direction == Direction.LEFT) {
			setXSpeed(-SPEED);
		} else {
			setXSpeed(SPEED);
		}
	}
	
	public Direction getDirection() {
		return direction;
	}
	
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