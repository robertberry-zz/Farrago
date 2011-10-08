package com.sunderance.farrago;

import java.util.HashMap;
import java.util.LinkedList;

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
	
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private HashMap<String,Image> imageCache = new HashMap<String,Image>();
	
	public Farrago() {
		super("Farrago");
	}
	
	/**
	 * Given the path from the 'res' folder, returns the requested Image. Caches
	 * on first load.
	 * 
	 * @param path The file path
	 * @return The image
	 */
	private Image getImage(String path) {
		path = Utils.pathJoin(RESOURCE_FOLDER, path);
		if (!imageCache.containsKey(path)) {
			try {
				imageCache.put(path, new Image(path));
			} catch (SlickException e) {
				e.printStackTrace();
				// force quit here? could result in a null pointer error
			}
		}
		return imageCache.get(path);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (Entity entity: entities) {
			entity.draw();
		}

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// Preload entitites, storing their images in the HashMap?

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		for (Entity entity : entities) {
			entity.step(delta);
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
