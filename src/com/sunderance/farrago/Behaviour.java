package com.sunderance.farrago;

import org.newdawn.slick.GameContainer;

/**
 * The behaviour of an entity - controls what it does in each step.
 * 
 * @author Robert Berry
 */
abstract public class Behaviour {
	protected Entity entity;
	
	public Behaviour(Entity _entity) {
		entity = _entity;
	}
	
	abstract public void step(GameContainer gc, float delta);
}
