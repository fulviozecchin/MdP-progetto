package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

/**
 * The class represents the listener for the <b>save functionality</b> of a drawn canvas.
 * 
 * @see ImageEditorView which represents the main View class for the application 
 *      and calls the saving action at the click of the <b>Save...</b> button under <i>File</i> menu.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ActionSaveListener implements ActionListener {

	/**
	 * Public default constructor
	 * 
	 */
	public ActionSaveListener() {}

	/**
	 * Describes the behavior when a is clicked the <i>Save...</i> button from <i>File</i> menu.
	 * 
	 * Opens a file system dialog that allows you to choose the location where you want to export the canvas as a file.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser("resources/");
		int returnVal = fileChooser.showSaveDialog(ImageEditorController.getInstance().getView());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			ImageEditorController.getInstance().getView().getPanel().save(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}

}
