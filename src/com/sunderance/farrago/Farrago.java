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
 * @author Robert Berry
 */
public class Farrago extends BasicGame {
	private static int WIDTH = 800, HEIGHT = 600;
	private static boolean FULL_SCREEN = false;
	private static String RESOURCE_FOLDER = "res";
	
	private CachedImageFactory imageFactory;
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private Image background;
	
	public Farrago() {
		super("Farrago");
		imageFactory = new CachedImageFactory(RESOURCE_FOLDER);
	}
		
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		background.draw(0f, 0f);
		for (Entity entity: entities) {
			entity.draw();
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// Load entities, passing them the CachedImageFactory
		background = imageFactory.createImage("background.png");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// unset references to any dead entities
		for (ListIterator<Entity> it = entities.listIterator(); it.hasNext();) {
			if (it.next().isDead()) {
				it.remove();
			}
		}
		// call step code for all entities
		for (Entity entity : entities) {
			entity.step(gc, delta);
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
