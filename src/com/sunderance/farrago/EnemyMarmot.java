package com.sunderance.farrago;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;

/**
 * Marmot enemy - darts about pointlessly
 * 
 * @author Robert Berry
 */
class EnemyMarmot extends Enemy {
	public static double SPEED = 3.5;
	public static double LEFT_RIGHT_MARGIN = 10;
	public static double CHANGE_DIRECTION_CHANCE = 0.25;
	public static double CHANGE_DIRECTION_INTERVAL = 300;
	private static double RECHARGE_TIME = 180;
	private static double BULLET_SPEED = 5.0;
	
	private double tillChangeDirection = CHANGE_DIRECTION_INTERVAL;
	private Gun gun;
		
	public EnemyMarmot(double initial_x, double initial_y) {
		super(initial_x, initial_y, "enemy-1.png");
		setDirection(Direction.LEFT);
		setYSpeed(SPEED);
		gun = new Gun(this, RECHARGE_TIME, new EnemyBulletFactory());
		gun.setYOffset(getRadius());
	}

	@Override
	public double getSpeed() {
		return SPEED;
	}
	
	@Override
	protected void stepBehaviour(GameContainer gc, Game game, int delta) {
		double width, height, x, y;
		Farrago fgame = (Farrago) game;
		x = getX(); y = getY();
		width = gc.getWidth(); height = gc.getHeight();
		
		/* fiiiiiiireeee! */
		gun.recharge(delta);
		if (gun.ready() & Math.random() < 0.10) {
			fgame.queueEnemy(gun.shoot(0.0, BULLET_SPEED));
		}
		
		/* stop going too far left or right */
		if (x - LEFT_RIGHT_MARGIN <= 0 || x + LEFT_RIGHT_MARGIN > width) {
			this.swapDirection();
		} 
		
		/* randomly swap direction occasionally */
		tillChangeDirection -= delta;
		if (tillChangeDirection <= 0) {
			if (Math.random() <= CHANGE_DIRECTION_CHANCE) {
				swapDirection();
			}
			tillChangeDirection = CHANGE_DIRECTION_INTERVAL;
		}
		
		/* kill if off screen */
		if (y > height) {
			this.kill();
		}
	}
}