package com.sunderance.farrago;

public class EnemyShipFactory {

	public static double Y_START = -10;
	
	private double maximumX;
	
	public EnemyShipFactory(double maximumX) {
		this.maximumX = maximumX;
	}
	
	public double randomX() {
		return Math.random() * maximumX;
	}
	
	public Enemy createRandomEnemy() {
		double rand = Math.random();
		
		if (rand <= 0.33) {
			return createMarmot();
		} else if (rand <= 0.66) {
			return createMarmot();
		} else {
			return createMarmot();
		}
	}
	
	public EnemyMarmot createMarmot() {
		return new EnemyMarmot(randomX(), Y_START);
	}
}
