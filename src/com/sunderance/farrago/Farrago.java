package com.sunderance.farrago;

import java.util.LinkedList;
import java.util.ListIterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	private static double ENEMY_SPAWN_TIME = 700.0;
	
	private Player player;
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private Image background;
	private LinkedList<Entity> enemies = new LinkedList<Entity>();
	private LinkedList<Entity> queuedEntities = new LinkedList<Entity>();
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
	 * Returns the Player entity
	 * 
	 * @return The player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Adds an enemy entity into the game
	 * 
	 * @param e The entity
	 */
	public void queueEnemy(Entity e) {
		queueEntity(e);
		enemies.add(e);
	}
	
	/**
	 * Queues an entity for creation. This should be used by subclass entities that want
	 * to create new entities in their step code. You can't just add to the list of entities
	 * as it throws an error related to concurrency & thread access.
	 * 
	 * @param e The entity
	 */
	public void queueEntity(Entity e) {
		queuedEntities.add(e);
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
		// add queued entities
		Entity queuedEntity = queuedEntities.poll();
		while (queuedEntity != null) {
			entities.add(queuedEntity);
			queuedEntity = queuedEntities.poll();
		}
		
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
		
		/* spawn an enemy? */
		untilEnemySpawn -= delta;
		if (untilEnemySpawn <= 0) {
			Enemy enemy = enemyFactory.createRandomEnemy();
			addEntity(enemy);
			enemies.add(enemy);
			untilEnemySpawn = ENEMY_SPAWN_TIME;
		}
		
		/* if player collides with enemy, kill player */
		for (Entity enemy : enemies) {
			if (player.overlaps(enemy)) {
				player.kill();
			}
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
