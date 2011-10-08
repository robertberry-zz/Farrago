package com.sunderance.farrago;

import java.io.File;

/**
 * Utility functions
 * 
 * @author Robert Berry
 */
public class Utils {
	/**
	 * Joins two directory paths
	 * 
	 * @param path1 First path
	 * @param path2 Second path
	 * @return The combined path
	 */
	public static String pathJoin(String path1, String path2) {
		File file1 = new File(path1);
		File file2 = new File(file1, path2);
		return file2.getPath();
	}
}
