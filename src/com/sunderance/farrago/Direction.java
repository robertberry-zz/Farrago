package com.sunderance.farrago;

public enum Direction {
	LEFT, RIGHT;
	
	public Direction getOpposite() {
		if (this == Direction.LEFT) {
			return Direction.RIGHT;
		} else {
			return Direction.LEFT;
		}
	}
}
