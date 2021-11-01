package com.unitelmasapienza.asciiart.asciipanel.factory;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanelBuilder;
import com.unitelmasapienza.asciiart.imageeditor.factory.ImageEditorFactory;

/**
 * This is a factory class to implement the <b>Factory Design Pattern</b>.
 * In <i>Factory Pattern</i> this represent the <i>Concrete Creator</i>.
 * 
 * So this class will implement and return the creation of AsciiPanel instances.
 * @see ImageEditorFactory if you want to see what methods are declared by the 
 * <i>Creator</i> abstract class.
 * 
 * @author Fulvio Zecchin
 *
 */
public class AsciiPanelFactoryConcrete extends AsciiPanelFactory {
	
	AsciiPanelBuilder builder = new AsciiPanelBuilder();
	
	@Override
	public AsciiPanel createAsciiPanel(int width, int height, AsciiFont font) {
		
		AsciiPanel panel = builder.createAsciiPanel(width, height, font)
				.defaultBackgroundColor()
				.defaultForegroundColor()
				
				.panelCharsMatrix(null)
				.panelCharsBackgroundColors(null)
				.panelCharsForegroundColors(null)
				
				.panelOldCharsBackgroundColors(null)
				.panelOldCharsForegroundColors(null)
				
				.build();
		return panel;
	}
	
}
