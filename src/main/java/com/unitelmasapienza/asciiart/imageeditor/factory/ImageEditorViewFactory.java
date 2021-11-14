package com.unitelmasapienza.asciiart.imageeditor.factory;

import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

/**
 * This is a factory abstract class to implement the <b>Factory Design Pattern</b>.
 * In <i>Factory Pattern</i> this represent the <i>Creator</i>.
 * 
 * So this class will declare methods for creating View instances.
 * @see ImageEditorViewFactoryConcrete if you want to see the implementation of this 
 * methods, by <i>Concrete Creator</i>.
 * 
 * @author Fulvio Zecchin
 *
 */
public abstract class ImageEditorViewFactory {

	/**
	 * This method is an abstract method for creation of ImageEditorView.
	 * It returns an ImageEditorView object.
	 * 
	 * @return a new instance of ImageEditorView object.
	 */
	public abstract ImageEditorView createView();

}
