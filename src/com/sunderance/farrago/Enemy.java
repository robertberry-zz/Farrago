package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

abstract public class Enemy extends Entity {
	
	private boolean dead = false;

	public Enemy(double initial_x, double initial_y, String spritePath) {
		super(initial_x, initial_y, spritePath);
	}

	public void kill() {
		dead = true;
	}
	
	@Override
	public boolean isDead() {
		return dead;
	}

}
