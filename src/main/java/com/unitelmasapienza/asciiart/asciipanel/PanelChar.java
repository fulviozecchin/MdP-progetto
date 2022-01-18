package com.unitelmasapienza.asciiart.asciipanel;
import java.awt.*;

/**
 * This class represents a single character of panel.
 * It contains all important data of character such as position and colors.
 * 
 * This class is used to implementation of <b>Undo</b> and <b>Redo</b> function of application.
 * 
 * @author Fulvio Zecchin
 *
 */
public class PanelChar {

	/**
	 * Is the value of <i>X</i> position for character.
	 * 
	 */
	private int x;
	
	/**
	 * Is the value of <i>Y</i> position for character.
	 * 
	 */
	private int y;
	
	/**
	 * Is the character.
	 * 
	 */
	private char selectedChar;
	
	/**
	 * Is <b>Foreground Color</b> of character.
	 * 
	 */
	private Color foregroundColor;
	
	/**
	 * Is <b>Background Color</b> of character.
	 * 
	 */
	private Color backgroundColor;
	
	/**
	 * Panel char constructor
	 * @param x is the x value to set
	 * @param y is the y value to set
	 * @param selectedChar is the character to set
	 * @param foregroundColor is the foreground color to set
	 * @param backgroundColor is the background color to set
	 */
	public PanelChar(int x, int y, char selectedChar, Color foregroundColor, Color backgroundColor) {
		this.x = x;
		this.y = y;
		this.selectedChar = selectedChar;
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
	}
	
	/**
	 * The X value getter
	 * 
	 * @return the x value of character
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * The X value to set
	 * 
	 * @param x the X to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * The Y value getter
	 * 
	 * @return the y value of character
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * The Y value to set
	 * 
	 * @param y the Y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * The character getter
	 * 
	 * @return selected character
	 */
	public char getSelectedChar() {
		return selectedChar;
	}
	
	/**
	 * The character setter
	 * 
	 * @param selectedChar is the character to set
	 */
	public void setSelectedChar(char selectedChar) {
		this.selectedChar = selectedChar;
	}
	
	/**
	 * Foreground color getter
	 * 
	 * @return foreground color of character
	 */
	public Color getForegroundColor() {
		return foregroundColor;
	}
	
	/**
	 * Foreground color setter
	 * 
	 * @param foregroundColor is the foreground color to set
	 */
	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}
	
	/**
	 * Background color getter
	 * 
	 * @return background color of character
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	/**
	 * Background color setter
	 * 
	 * @param backgroundColor is the background color to set
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
