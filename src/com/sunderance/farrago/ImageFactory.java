package com.sunderance.farrago;

import org.newdawn.slick.Image;

/**
 * Abstract factory for creating Slick images.
 * 
 * @author Robert Berry
 */
public interface ImageFactory {
	/**
	 * Given a path relative to the game's resources folder, returns a Slick
	 * Image object
	 * 
	 * @param path The path
	 * @return The image
	 */
	public Image createImage(String path);
}
