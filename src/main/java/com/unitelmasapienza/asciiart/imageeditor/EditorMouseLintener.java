package com.unitelmasapienza.asciiart.imageeditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The class represents the mouse listener for <b>mouse action</b> (click) functionality for the drawing canvas panel.
 * 
 * @author Fulvio Zecchin
 *
 */
public class EditorMouseLintener implements MouseListener {

	/**
	 * Represents the instance of the main application window on which the drawing panel is attached.
	 * 
	 * @see ImageEditor which represent the main frame of application.
	 */
	private ImageEditor imageEditor;

	/**
	 * Public constructor. Receives as input the ImageEditor to which will be attached the panel of which this class is the mouse listener.
	 * @param ie is the ImageEditor instance
	 */
	public EditorMouseLintener(ImageEditor ie) {
		this.setImageEditor(ie);
	}

	/**
	 * Implementation of MouseListener interface method
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * It is called when the mouse is clicked.
	 * Depending on the functionality, so based on the selected button, 
	 * it calls the relative action (click, pick or fill)
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		switch (getImageEditor().getSelectedToolIndex()) {
		case 0:
			getImageEditor().onClick(e.getButton());
			break;
		case 1:
			getImageEditor().onPick(e.getButton());
			break;
		case 2:
			getImageEditor().onFill(e.getButton());
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
	ImageEditor getImageEditor() {
		return imageEditor;
	}

	/**
	 * The image editor setter
	 * @param imageEditor the imageEditor to set
	 */
	void setImageEditor(ImageEditor imageEditor) {
		this.imageEditor = imageEditor;
	}

}
