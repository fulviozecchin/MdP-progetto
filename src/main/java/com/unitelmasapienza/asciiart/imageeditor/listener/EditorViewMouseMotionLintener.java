package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.unitelmasapienza.asciiart.imageeditor.ImageEditor;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

/**
 * The class represents the mouse listener for <b>mouse movement</b> functionality for the drawing canvas panel.
 * 
 * @author Fulvio Zecchin
 *
 */
public class EditorViewMouseMotionLintener implements MouseMotionListener {

	/**
	 * Represents the instance of the main application window on which the drawing panel is attached.
	 * 
	 * @see ImageEditor which represent the main frame of application.
	 */
	private ImageEditorView imageEditorView;

	/**
	 * Public constructor. Receives as input the ImageEditor to which will be attached the panel of which this class is the mouse listener.
	 * @param ie is the ImageEditor instance
	 */
	public EditorViewMouseMotionLintener(ImageEditorView imageEditorView) {
		this.setImageEditorView(imageEditorView);
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
		getImageEditorView().onCursorMove(e.getButton(), e.getX(), e.getY());
	}

	/**
	 * The image editor getter
	 * @return the imageEditor
	 */
	ImageEditorView getImageEditorView() {
		return imageEditorView;
	}

	/**
	 * The image editor setter
	 * @param imageEditorView the imageEditorView to set
	 */
	void setImageEditorView(ImageEditorView imageEditorView) {
		this.imageEditorView = imageEditorView;
	}

}
