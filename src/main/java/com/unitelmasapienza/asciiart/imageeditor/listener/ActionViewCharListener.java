package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.view.SelectCharView;

/**
 * The class manages the <b>Action Listener for each character selected from the character palette frame</b>.
 * 
 * @see SelectCharView that represents the character palette view frame of application.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ActionViewCharListener implements ActionListener {

	/**
	 * Represents the index of the selected character
	 * 
	 */
	private int selectedCharIndex;
	
	/**
	 * The public constructor of the class.
	 * As input it expects the index of the selected character.
	 * 
	 * @param index is the index of selected character.
	 */
	public ActionViewCharListener(int index) {
		this.selectedCharIndex = index;
	}
	
	/**
	 * Describes the behavior when a character is selected from the palette frame.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ImageEditorController.getInstance().onSelectChar(selectedCharIndex);
	}

}
