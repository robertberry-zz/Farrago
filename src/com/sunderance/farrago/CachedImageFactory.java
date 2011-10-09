package com.sunderance.farrago;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Creates instances of Slick Images from a given working directory. If
 * the Image has previously been created returns a reference to the same
 * object.
 * 
 * @author Robert Berry
 */
public class CachedImageFactory implements ImageFactory {
	private String directory;
	private HashMap<String,Image> cache = new HashMap<String,Image>();

	/**
	 * Constructs a CachedImageFactory that looks for images in the given 
	 * working directory.
	 * 
	 * @param _directory The working directory
	 */
	public CachedImageFactory(String _directory) {
		directory = _directory;
	}
	
	/**
	 * Given the path from the 'res' folder, returns the requested Image. Caches
	 * on first load.
	 * 
	 * @param path The file path
	 * @return The image
	 */
	public Image createImage(String path) {
		path = Utils.pathJoin(directory, path);
		if (!cache.containsKey(path)) {
			try {
				cache.put(path, new Image(path));
			} catch (SlickException e) {
				e.printStackTrace();
				// force quit here? could result in a null pointer error.
				// probably best way is to throw a custom exception, which is
				// caught in the sprite, which throws its own exception, which
				// is caught in the main game class, which then quits. Need to
				// look up Java exceptions ...
			}
		}
		return cache.get(path);
	}
}
