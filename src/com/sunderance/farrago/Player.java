package com.sunderance.farrago;

import org.newdawn.slick.GameContainer;

public class Player extends Entity {

	public Player(double initial_x, double initial_y, ImageFactory imageFactory) {
		super(initial_x, initial_y, imageFactory);
	}

	@Override
	protected String getSpritePath() {
		return "player.png";
	}

	@Override
	protected void stepBehaviour(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

}
