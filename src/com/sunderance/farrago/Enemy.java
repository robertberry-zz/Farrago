package com.sunderance.farrago;

/**
 * Base class for Enemy ships
 * 
 * @author Robert Berry
 */
abstract public class Enemy extends Entity {
	private boolean dead = false;

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

	/**
	 * Kills the enemy, removing from the game next step
	 */
	public void kill() {
		dead = true;
	}
	
	@Override
	public boolean isDead() {
		return dead;
	}
}
