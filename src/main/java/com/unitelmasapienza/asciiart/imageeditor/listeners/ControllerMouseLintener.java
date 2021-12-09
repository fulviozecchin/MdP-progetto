package com.unitelmasapienza.asciiart.imageeditor.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.unitelmasapienza.asciiart.imageeditor.controllers.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.views.ImageEditorView;

/**
 * The class represents the mouse listener for <b>mouse action</b> (click) functionality for the drawing canvas panel.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ControllerMouseLintener implements MouseListener {

	/**
	 * This field is the controller instance that is responsible of binding the view (and her listeners) with the panel.
	 * 
	 * @see ImageEditorController wich represent the main controller of application.
	 * @see ImageEditorView which represent the main View of application.
	 */
	private ImageEditorController imageEditorController;

	/**
	 * Public constructor. Receives as input the controller that acts as a 'bridge' between the view and the panel (model). 
	 * It will add this mouse listener to the panel, which in turn is bound to the view.
	 * 
	 * @param imageEditorController is the controller instance.
	 */
	public ControllerMouseLintener(ImageEditorController imageEditorController) {
		this.setImageEditorController(imageEditorController);
	}

	/**
	 * Implementation of MouseListener interface method.
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * It is called when the mouse is clicked.
	 * depending on the functionality, so based on the selected button, 
	 * it calls the relative action (click, pick or fill).
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		switch (getImageEditorController().getView().getSelectedToolIndex()) {
		case 0:
			getImageEditorController().onClick(e.getButton());
			break;
		case 1:
			getImageEditorController().onFill(e.getButton());
			break;
		case 2:
			getImageEditorController().onPick(e.getButton());
			break;
		default:
			break;
		}
	}

	/**
	 * Implementation of MouseListener interface method
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * Implementation of MouseListener interface method
	 * 
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Implementation of MouseListener interface method
	 * 
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

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
	 * @param imageEditorController is the controller to set.
	 */
	public void setImageEditorController(ImageEditorController imageEditorController) {
		this.imageEditorController = imageEditorController;
	}

}
