package com.sunderance.farrago;

/**
 * Base class for Enemy ships
 * 
 * @author Robert Berry
 */
abstract public class Enemy extends Entity {
	Direction direction;	
	
	/**
	 * Sets whether the ship is moving left or right
	 * 
	 * @param direction The direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
		
		if (direction == Direction.LEFT) {
			setXSpeed(-getSpeed());
		} else {
			setXSpeed(getSpeed());
		}
	}
	
	/**
	 * Returns the speed of the ship
	 * 
	 * @return The speed
	 */
	abstract public double getSpeed();
	
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
	
	
	/**
	 * Creates an enemy at a given position with a given path to a sprite image
	 * 
	 * @param initial_x X position
	 * @param initial_y Y position
	 * @param spritePath Path to image
	 */
	public Enemy(double initial_x, double initial_y, String spritePath) {
		super(initial_x, initial_y, spritePath);
	}
}
