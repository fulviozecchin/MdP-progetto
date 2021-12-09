package com.unitelmasapienza.asciiart.imageeditor.factories;

import com.unitelmasapienza.asciiart.imageeditor.views.ImageEditorView;
import com.unitelmasapienza.asciiart.imageeditor.views.ImageEditorViewBuilder;

/**
 * This is a factory class to implement the <b>Factory Design Pattern</b>.
 * In <i>Factory Pattern</i> this represent the <i>Concrete Creator</i>.
 * 
 * So this class will implement and return the creation of Views instances.
 * @see ImageEditorViewFactory if you want to see what methods are declared by the 
 * <i>Creator</i> abstract class.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ImageEditorViewFactoryConcrete extends ImageEditorViewFactory {
	
	/**
	 * This is a <b>Builder Design Pattern</b> implementation.
	 * This fields represents the real builder for this factory.
	 * 
	 * Every time that we will have need of an object of type ImageEditorViewBuilder, 
	 * we can demand it therefore to the builder.
	 * 
	 */
	ImageEditorViewBuilder builder = new ImageEditorViewBuilder();
	
	/**
	 * Thanks to the implementation of the <b>Builder Pattern</b>, in this method to create the ImageEditorViewBuilder,
	 * the concrete factory will only care about calling the cascade set methods of the builder, 
	 * so here we will choose which fields we want to set for the creation of ImageEditorViewBuilder object.
	 * 
	 */
	@Override
	public ImageEditorView createView() {
		
		ImageEditorView view = builder.selectedCharPreview()
				.charColorPreview()
				.charBackgroundColorPreview()
				.minusButton()
				.plusButton()
				.charIndexButton()
				.pickToolButton()
				.paintToolButton()
				.fillToolButton()
				.createControlTool()
				.menuBar()
				.newFileMenuItem()
				.loadFileMenuItem()
				.saveFileMenuItem()
				.importFileMenuItem()
				.addFileToMenuBar()
				.build();
		return view;
	}
	
}
