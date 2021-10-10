package com.unitelmasapienza.asciiart.asciipanel.factory;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;
import com.unitelmasapienza.asciiart.asciipanel.model.AsciiPanelData;
import com.unitelmasapienza.asciiart.asciipanel.model.AsciiPanelBuilder;
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
		
		//FIXME: per ora e' solo un new AsciiPanel, sara' da capire come farlo con il builder
		return new AsciiPanel(width, height, font);
	}
	
}
