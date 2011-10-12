package com.sunderance.farrago;

/**
 * Represents a direction an Enemy ship can go in
 * 
 * @author Robert Berry
 */
public enum Direction {
	LEFT, RIGHT;
	
	/**
	 * The opposite direction
	 * 
	 * @return The opposite direction
	 */
	public Direction getOpposite() {
		if (this == Direction.LEFT) {
			return Direction.RIGHT;
		} else {
			return Direction.LEFT;
		}
	}
}
