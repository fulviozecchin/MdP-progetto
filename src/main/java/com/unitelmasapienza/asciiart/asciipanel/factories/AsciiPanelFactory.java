package com.unitelmasapienza.asciiart.asciipanel.factories;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;

/**
 * This is a factory abstract class to implement the <b>Factory Design Pattern</b>.
 * In <i>Factory Pattern</i> this represent the <i>Creator</i>.
 * 
 * So this class will declare methods for creating AsciiPanel instances.
 * @see ImageEditorFactoryConcrete if you want to see the implementation of this 
 * methods, by <i>Concrete Creator</i>.
 * 
 * @author Fulvio Zecchin
 *
 */
public abstract class AsciiPanelFactory {
	
	/**
	 * This method is an abstract method for creation of AsciiPanel.
	 * It returns an AsciiPanel object.
	 * 
	 * @param width is the panel width in characters
	 * @param height is the panel height in characters
	 * @param font is the font to set for AsciiPanel
	 * @return a new instance of AsciiPanel object
	 */
	public abstract AsciiPanel createAsciiPanel(int width, int height, AsciiFont font);
}
