package com.unitelmasapienza.asciiart.imageeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class represents the action listener for the <b>reset functionality</b> of the main canvas panel.
 * 
 * @see ImageEditor which represents the main frame class for the application.
 * @see ImageNew which represents the dialog for confirm action and insert width and height for new canvas.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ActionNew implements ActionListener {

	/**
	 * Describes the behavior when a is clicked the <i>Create New</i> button from 
	 * <i>ImageNew</i> frame.
	 * 
	 * Resets the main canvas setting as new dimensions (width and height) those indicated in the ImageNew dialog frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		ImageEditor imageEditor = ImageEditor.getInstance();
		ImageNew imageNew = ImageNew.getInstance();
		try {
			int widthValue = Integer.parseInt(imageNew.getWidthTextbox().getText());
			int heightValue = Integer.parseInt(imageNew.getHeightTextbox().getText());
			imageEditor.reset(widthValue, heightValue);
			imageEditor.repaint();
			imageNew.setVisible(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
