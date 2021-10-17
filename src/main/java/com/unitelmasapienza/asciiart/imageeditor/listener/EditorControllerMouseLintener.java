package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.unitelmasapienza.asciiart.imageeditor.ImageEditor;
import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

/**
 * The class represents the mouse listener for <b>mouse action</b> (click) functionality for the drawing canvas panel.
 * 
 * @author Fulvio Zecchin
 *
 */
public class EditorControllerMouseLintener implements MouseListener {

	/**
	 * Represents the instance of the main application window on which the drawing panel is attached.
	 * 
	 * @see ImageEditor which represent the main frame of application.
	 */
	private ImageEditorController imageEditorController;

	/**
	 * Public constructor. Receives as input the ImageEditor to which will be attached the panel of which this class is the mouse listener.
	 * @param ie is the ImageEditor instance
	 */
	public EditorControllerMouseLintener(ImageEditorController imageEditorController) {
		this.setImageEditorController(imageEditorController);
	}

	/**
	 * Implementation of MouseListener interface method
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * It is called when the mouse is clicked.
	 * depending on the functionality, so based on the selected button, 
	 * it calls the relative action (click, pick or fill)
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		switch (getImageEditorController().getView().getSelectedToolIndex()) {
		case 0:
			getImageEditorController().onClick(e.getButton());
			break;
		case 1:
			getImageEditorController().onPick(e.getButton());
			break;
		case 2:
			getImageEditorController().onFill(e.getButton());
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
	 * The image editor getter
	 * @return the imageEditor
	 */
	ImageEditorController getImageEditorController() {
		return imageEditorController;
	}

	/**
	 * The image editor setter
	 * @param imageEditor the imageEditor to set
	 */
	void setImageEditorController(ImageEditorController imageEditorController) {
		this.imageEditorController = imageEditorController;
	}

}
