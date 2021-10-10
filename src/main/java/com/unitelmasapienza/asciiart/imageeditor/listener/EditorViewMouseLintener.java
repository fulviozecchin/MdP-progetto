package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

/**
 * The class represents the mouse listener for <b>mouse action</b> (click) functionality for the drawing canvas panel.
 * 
 * @author Fulvio Zecchin
 *
 */
public class EditorViewMouseLintener implements MouseListener {

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
	public EditorViewMouseLintener(ImageEditorView imageEditorView) {
		this.setImageEditorView(imageEditorView);
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
		switch (getImageEditorView().getSelectedToolIndex()) {
		case 0:
			getImageEditorView().onClick(e.getButton());
			break;
		case 1:
			getImageEditorView().onPick(e.getButton());
			break;
		case 2:
			getImageEditorView().onFill(e.getButton());
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
	ImageEditorView getImageEditorView() {
		return imageEditorView;
	}

	/**
	 * The image editor setter
	 * @param imageEditor the imageEditor to set
	 */
	void setImageEditorView(ImageEditorView imageEditorView) {
		this.imageEditorView = imageEditorView;
	}

}
