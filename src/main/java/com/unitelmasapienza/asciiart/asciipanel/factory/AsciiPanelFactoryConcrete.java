package com.unitelmasapienza.asciiart.asciipanel.factory;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanelBuilder;

/**
 * This is a factory class to implement the <b>Factory Design Pattern</b>.
 * In <i>Factory Pattern</i> this represent the <i>Concrete Creator</i>.
 * 
 * So this class will implement and return the creation of AsciiPanel instances.
 * 
 * @see ImageEditorFactory if you want to see what methods are declared by the 
 * <i>Creator</i> abstract class.
 * 
 * @author Fulvio Zecchin
 *
 */
public class AsciiPanelFactoryConcrete extends AsciiPanelFactory {
	
	/**
	 * This is a <b>Builder Design Pattern</b> implementation.
	 * This fields represents the real builder for this factory.
	 * 
	 * Every time that we will have need of an object of type AsciiPanel, 
	 * we can demand it therefore to the builder.
	 * 
	 */
	AsciiPanelBuilder builder = new AsciiPanelBuilder();
	
	/**
	 * Thanks to the implementation of the <b>Builder Pattern</b>, in this method to create the AsciiPanel,
	 * the concrete factory will only care about calling the cascade set methods of the builder, 
	 * so here we will choose which fields we want to set for the creation of AsciiPanel object.
	 * 
	 */
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
