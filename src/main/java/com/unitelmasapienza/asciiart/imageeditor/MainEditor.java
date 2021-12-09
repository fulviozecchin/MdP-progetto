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
	 * by setting setVisible to true on the ImageEditor instance
	 * @param args an array of command-line arguments for the application	 
	 */
	public static void main(String[] args)	{
//		ImageEditor.getInstance().setVisible(true);
		
		//Test per provare le mie modifiche
		ImageEditorController.getInstance();
	}
}

