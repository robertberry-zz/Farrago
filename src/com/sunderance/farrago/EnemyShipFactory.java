package com.sunderance.farrago;

/**
 * Factory for creating random enemy ships
 * 
 * @author Robert Berry
 */
public class EnemyShipFactory {

	public static double Y_START = -10;
	
	private double maximumX;
	
	/**
	 * Creates the factory with a given maximum starting position in x
	 * 
	 * @param maximumX Maximum x
	 */
	public EnemyShipFactory(double maximumX) {
		this.maximumX = maximumX;
	}
	
	/**
	 * Returns a random valid x position
	 * 
	 * @return The position
	 */
	public double randomX() {
		return Math.random() * maximumX;
	}
	
	/**
	 * Creates a random enemy
	 * 
	 * @return The enemy
	 */
	public Enemy createRandomEnemy() {
		double rand = Math.random();
		
		if (rand <= 0.33) {
			return createMarmot();
		} else if (rand <= 0.66) {
			return createTracker();
		} else {
			return createVirgo();
		}
	}
	
	/**
	 * Creates a Marmot in a random position
	 * 
	 * @return The Marmot
	 */
	public EnemyMarmot createMarmot() {
		return new EnemyMarmot(randomX(), Y_START);
	}
	
	/**
	 * Creates a Virgo in a random position
	 * 
	 * @return The Virgo
	 */
	public EnemyVirgo createVirgo() {
		return new EnemyVirgo(randomX(), Y_START);
	}
	
	/**
	 * Creates a Tracker in a random position
	 * 
	 * @return The Tracker
	 */
	public EnemyTracker createTracker() {
		return new EnemyTracker(randomX(), Y_START);
	}
}
