package com.sunderance.farrago;

import java.util.LinkedList;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

public class PlayerBullet extends Bullet {	
	public PlayerBullet(double initialX, double initialY, double xSpeed, double ySpeed) {
		super(initialX, initialY, xSpeed, ySpeed, "plasma-bullet.png");
	}

	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		Farrago fgame = (Farrago) game;
		LinkedList<Entity> enemies = fgame.getEnemies();
		
		for (Entity enemy : enemies) {
			if (this.overlaps(enemy)) {
				enemy.kill();
				this.kill();
			}
		}
	}

}
