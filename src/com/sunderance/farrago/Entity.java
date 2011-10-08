package com.sunderance.farrago;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

/**
 * Base class for in game objects
 * 
 * @author Robert Berry
 */
public class Entity {
	private double x, y, xSpeed, ySpeed, xAcceleration, yAcceleration, radius;
	private Image sprite;
	private Behaviour behaviour;
	
	public Entity(float initial_x, float initial_y, float _radius, Image _sprite,
			Behaviour _behaviour) {
		x = initial_x;
		y = initial_y;
		radius = _radius;
		sprite = _sprite;
		behaviour = _behaviour;
	}
	
	/**
	 * Updates position of the entity given the amount of time passed
	 * 
	 * @param time The amount of time elapsed
	 */
	public void step(GameContainer gc, float delta) {
		behaviour.step(gc, delta);
		x += xSpeed + Math.pow(delta, 2) * xAcceleration / 2;
		y += ySpeed + Math.pow(delta, 2) * yAcceleration / 2;
	}
	
	/**
	 * Draws the image on the screen
	 */
	public void draw() {
		sprite.draw((int) (x - radius), (int) (y - radius));
	}

	/**
	 * Returns the radius of the Entity in pixels
	 * 
	 * @return The radius
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Returns the x coordinate of the Entity
	 * 
	 * @return X coordinate in pixels
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate of the Entity
	 * 
	 * @return Y coordinate in pixels
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returns the distance in pixels from another Entity
	 * 
	 * @param other The other entity
	 * @return The distance
	 */
	public double getDistanceFrom(Entity other) {
		return (float) Math.sqrt(Math.pow(other.getX() - x, 2) + 
			Math.pow(other.getY() - y, 2));
	}
	
	/**
	 * Returns whether the Entity is overlapping another given Entity
	 * 
	 * @param other The other entity
	 * @return Whether overlapping
	 */
	public boolean overlaps(Entity other) {
		return radius + other.getRadius() <= getDistanceFrom(other);
	}
}
