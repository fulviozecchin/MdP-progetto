package com.unitelmasapienza.asciiart.imageeditor.factory;

import com.unitelmasapienza.asciiart.asciipanel.model.AsciiPanelBuilder;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

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
public class ImageEditorFactoryConcrete extends ImageEditorFactory {
	
	AsciiPanelBuilder builder = new AsciiPanelBuilder();

	@Override
	public ImageEditorView createView() {
		
		
		return null;
	}
	
}
