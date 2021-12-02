package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

/**
 * The class represents the mouse listener for <b>mouse movement</b> functionality for the drawing canvas panel.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ControllerMouseMotionLintener implements MouseMotionListener {

	/**
	 * Represents the instance of the main application window on which the drawing panel is attached.
	 * 
	 * @see ImageEditorController which represent the main Controller of application.
	 * @see ImageEditorView which represent the main View of application.
	 */
	private ImageEditorController imageEditorController;

	/**
	 * Public constructor. Receives as input the controller that acts as a 'bridge' between the view and the panel (model). 
	 * It will add this mouse motion listener to the panel, which in turn is bound to the view.
	 * 
	 * @param ie is the ImageEditor instance
	 */
	public ControllerMouseMotionLintener(ImageEditorController imageEditorController) {
		this.setImageEditorController(imageEditorController);
	}

	/**
	 * Implementation of MouseListener interface method
	 * 
	 */
	@Override
	public void mouseDragged(MouseEvent e) {}

	/**
	 * It gets the <i>x</i> and <i>y</i> coordinates of the cursor and communicates them to the main ImageEditor frame.
	 * 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		getImageEditorController().onCursorMove(e.getButton(), e.getX(), e.getY());
	}

	/**
	 * The controller field getter.
	 * 
	 * @return the controller field.
	 */
	public ImageEditorController getImageEditorController() {
		return imageEditorController;
	}

	/**
	 * The controller field setter.
	 * 
	 * @param imageEditorController the controller to set.
	 */
	public void setImageEditorController(ImageEditorController imageEditorController) {
		this.imageEditorController = imageEditorController;
	}

}
