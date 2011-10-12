package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

abstract public class Bullet extends Entity {
	public Bullet(double initialX, double initialY, double xSpeed, double ySpeed,
			String spritePath) {
		super(initialX, initialY, spritePath);
		setXSpeed(xSpeed);
		setYSpeed(ySpeed);
	}

	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		// check if off screen
		double x, y;
		x = getX(); y = getY();
		if (x > gc.getWidth() || x < 0 || y > gc.getHeight() || y < 0) {
			this.kill();
		}
	}
}
