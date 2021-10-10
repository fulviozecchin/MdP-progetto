package com.unitelmasapienza.asciiart.asciipanel;

import java.awt.Color;

/**
 * The class represents the <b>information that a single ascii character contains</b> in the application.
 * Any ascii character in the application can be accessed by identifying it by its <i>x</i> and <i>y</i> coordinates.
 *  
 * @author Fulvio Zecchin
 *
 */
public class AsciiCharacterData {

	/**
	 * It represents a specific character
	 * 
	 */
	private char character;

	/**
	 * It represents the foreground color of the character
	 * 
	 */
	private Color characterForegroundColor;

	/**
	 * It represents the background color of the character
	 * 
	 */
	private Color characterBackgroundColor;

	/**
	 * A specifi character getter
	 * 
	 * @return the character
	 */
	public char getCharacter() {
		return character;
	}

	/**
	 * A specific character setter
	 * 
	 * @param character the character to set
	 */
	public void setCharacter(char character) {
		this.character = character;
	}

	/**
	 * The character foreground color getter
	 * 
	 * @return the character foreground color
	 */
	public Color getCharacterForegroundColor() {
		return characterForegroundColor;
	}

	/**
	 * The character foreground setter
	 * 
	 * @param foregroundColor the character foreground color to set
	 */
	public void setCharacterForegroundColor(Color foregroundColor) {
		this.characterForegroundColor = foregroundColor;
	}

	/**
	 * The character background color getter
	 * 
	 * @return the character background color
	 */
	public Color getCharacterBackgroundColor() {
		return characterBackgroundColor;
	}

	/**
	 * The character background color setter
	 * 
	 * @param backgroundColor the character background color to set
	 */
	public void setCharacterBackgroundColor(Color backgroundColor) {
		this.characterBackgroundColor = backgroundColor;
	}
}