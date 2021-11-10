package com.unitelmasapienza.asciiart.imageeditor.factory;

import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorViewBuilder;

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
	
	ImageEditorViewBuilder builder = new ImageEditorViewBuilder();
	
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
