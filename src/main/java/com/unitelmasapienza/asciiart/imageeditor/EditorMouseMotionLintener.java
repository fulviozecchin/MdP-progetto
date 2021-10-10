package com.unitelmasapienza.asciiart.imageeditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * The class represents the mouse listener for <b>mouse movement</b> functionality for the drawing canvas panel.
 * 
 * @author Fulvio Zecchin
 *
 */
public class EditorMouseMotionLintener implements MouseMotionListener {

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
	public EditorMouseMotionLintener(ImageEditor ie) {
		this.setImageEditor(ie);
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
		// System.out.println("Mouse moved:"+e.getButton()+" "+e.getX()+" "+e.getY());
		getImageEditor().onCursorMove(e.getButton(), e.getX(), e.getY());
	}

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
