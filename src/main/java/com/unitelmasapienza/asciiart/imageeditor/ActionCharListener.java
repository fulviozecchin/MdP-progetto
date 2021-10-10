package com.unitelmasapienza.asciiart.imageeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class manages the <b>Action Listener for each character selected from the character palette frame</b>
 * @see CharacterSelector that represents the character palette frame
 * @author Fulvio Zecchin
 *
 */
public class ActionCharListener implements ActionListener {

	/**
	 * Represents the index of the selected character
	 * 
	 */
	int selectedCharIndex;
	
	/**
	 * The public constructor of the class.
	 * As input it expects the index of the selected character
	 * @param index is the index of selected character
	 */
	public ActionCharListener(int index) {
		this.selectedCharIndex = index;
	}
	
	/**
	 * Describes the behavior when a character is selected from the palette frame
	 * 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ImageEditor.getInstance().onSelectChar(selectedCharIndex);
	}

}
