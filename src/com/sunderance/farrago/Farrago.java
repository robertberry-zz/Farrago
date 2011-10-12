package com.sunderance.farrago;

import java.util.LinkedList;
import java.util.ListIterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Farrago
 * 
 * "A farrago is a mixed medley, a varied assortment. It's a slightly disparaging word 
 *  that implies a haphazard, disorganized collection."
 *  
 * - What I imagine my code for this is going to end up like.
 * 
 * This is my homework for week 2 of SquashMonster's Learn Game Programming ureddit class.
 * It's a simple shooter in the vein of Galaga, Galaxian or Xevious.
 * 
 * -- Re the Farrago thing, as I am now approaching the deadline I can confirm this is
 * true. I first tried to avoid all global state, passing through objects like the
 * CachedImageFactory to load sprites into entities. Then deciding that made the code
 * messy I tried to implement a system for building entities based on components. Then
 * I realized it was Wednesday so scrapped it all and just tried to finish the thing!!
 * 
 * @author Robert Berry
 */
public class Farrago extends BasicGame {
	private static int WIDTH = 800, HEIGHT = 600;
	private static int PLAYER_SPAWN_X = WIDTH / 2, PLAYER_SPAWN_Y = HEIGHT - 45;
	private static boolean FULL_SCREEN = false;
	private static double RECHARGE_SHOT_TIME = 100.0;
	
	private static double ENEMY_SPAWN_TIME = 1000.0;
	
	private double untilRecharge = 0.0;	
	private Player player;
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private Image background;
	
	private LinkedList<Entity> enemies = new LinkedList<Entity>();
	
	private double untilEnemySpawn = ENEMY_SPAWN_TIME;
	
	private EnemyShipFactory enemyFactory;
	
	public Farrago() {
		super("Farrago");
	}
		
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		background.draw(0f, 0f);
		for (Entity entity: entities) {
			entity.draw();
		}
	}
	
	/**
	 * Adds an entity into the game
	 * 
	 * @param e The entity
	 */
	public void addEntity(Entity e) {
		entities.add(e);
	}

	/**
	 * Returns a list of enemies in the game
	 * 
	 * @return The enemies
	 */
	public LinkedList<Entity> getEnemies() {
		return enemies;
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		// Create background and player
		background = ResourceManager.getInstance().getImage("background.png");
		addEntity(player = new Player(PLAYER_SPAWN_X, PLAYER_SPAWN_Y));

		// Create enemy factory
		enemyFactory = new EnemyShipFactory(gc.getWidth());		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
		// unset references to any dead entities
		for (ListIterator<Entity> it = entities.listIterator(); it.hasNext();) {
			if (it.next().isDead()) {
				it.remove();
			}
		}

		// do for dead enemies too
		for (ListIterator<Entity> it = enemies.listIterator(); it.hasNext();) {
			if (it.next().isDead()) {
				it.remove();
			}
		}
		
		/* shoot bullet if is time */
		if (untilRecharge > 0.0) {
			if (delta > untilRecharge) {
				untilRecharge = 0.0;
			} else {
				untilRecharge -= delta;
			}
		} else if (input.isKeyDown(Input.KEY_SPACE)) {
			// i need to create the entity :[
			addEntity(new PlayerBullet(player.getX(), player.getY() - player.getRadius() - 5));
			untilRecharge = RECHARGE_SHOT_TIME;
		}
		
		/* spawn an enemy? */
		untilEnemySpawn -= delta;
		if (untilEnemySpawn <= 0) {
			Enemy enemy = enemyFactory.createRandomEnemy();
			addEntity(enemy);
			enemies.add(enemy);
			untilEnemySpawn = ENEMY_SPAWN_TIME;
		}
		
		// call step code for all entities
		for (Entity entity : entities) {
			entity.step(gc, this, delta);
		}
	}

	/**
	 * Entry point for the game
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Farrago());
			app.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
