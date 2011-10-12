package com.sunderance.farrago;

import org.newdawn.slick.Image;

public class ResourceManager {

	private static String RESOURCES_FOLDER = "res";
	
	private static ResourceManager instance;
	
	private CachedImageFactory imageFactory;
	
	private ResourceManager() {
		imageFactory = new CachedImageFactory(RESOURCES_FOLDER);
	}
	
	public static ResourceManager getInstance() {
		if (instance == null) {
			instance = new ResourceManager();
		}
		return instance;
	}
	
	public Image getImage(String path) {
		return imageFactory.createImage(path);
	}
}
