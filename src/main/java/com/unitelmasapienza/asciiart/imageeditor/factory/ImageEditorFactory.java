package com.unitelmasapienza.asciiart.imageeditor.factory;

import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

/**
 * This is a factory abstract class to implement the <b>Factory Design Pattern</b>.
 * In <i>Factory Pattern</i> this represent the <i>Creator</i>.
 * 
 * So this class will declare methods for creating View instances.
 * @see ImageEditorFactoryConcrete if you want to see the implementation of this 
 * methods, by <i>Concrete Creator</i>.
 * 
 * @author Fulvio Zecchin
 *
 */
public abstract class ImageEditorFactory {

	public abstract ImageEditorView createView();

}
