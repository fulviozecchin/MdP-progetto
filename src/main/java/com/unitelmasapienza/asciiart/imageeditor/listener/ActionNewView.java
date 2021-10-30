package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.unitelmasapienza.asciiart.imageeditor.ImageEditor;
import com.unitelmasapienza.asciiart.imageeditor.ImageNew;
import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.view.NewView;

/**
 * The class represents the action listener for the <b>reset functionality</b> of the main canvas panel.
 * 
 * @see ImageEditor which represents the main frame class for the application.
 * @see ImageNew which represents the dialog for confirm action and insert width and height for new canvas.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ActionNewView implements ActionListener {

	/**
	 * Describes the behavior when a is clicked the <i>Create New</i> button from 
	 * <i>ImageNewView</i> frame.
	 * 
	 * Resets the main canvas setting as new dimensions (width and height) those indicated in the ImageNewView dialog frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		ImageEditorController controller = ImageEditorController.getInstance();
		NewView newView = NewView.getInstance();
		try {
			int widthValue = Integer.parseInt(newView.getWidthTextbox().getText());
			int heightValue = Integer.parseInt(newView.getHeightTextbox().getText());
			controller.reset(widthValue, heightValue);
			controller.getView().repaint();
			newView.setVisible(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
