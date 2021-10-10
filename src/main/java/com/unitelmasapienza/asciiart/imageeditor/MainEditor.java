package com.unitelmasapienza.asciiart.imageeditor;

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
		ImageEditor.getInstance().setVisible(true);
	}
}

