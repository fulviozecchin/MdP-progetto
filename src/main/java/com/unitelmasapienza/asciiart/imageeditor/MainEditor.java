package com.unitelmasapienza.asciiart.imageeditor;

import com.unitelmasapienza.asciiart.imageeditor.controllers.ImageEditorController;

/**
 * The <b>application's entry point</b>
 * @author Fulvio Zecchin
 *
 */
public class MainEditor {
	
	/**
	 * Start up the application 
	 * by getting the instance of application's controller.
	 * @param args an array of command-line arguments for the application	 
	 */
	public static void main(String[] args)	{
		ImageEditorController.getInstance();
	}
}

