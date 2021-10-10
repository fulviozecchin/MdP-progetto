package com.unitelmasapienza.asciiart.asciipanel.view;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * This class, in the <b>MVC (Model View Controller) design pattern</b>, represents the <b>View</b>, 
 * It is responsible for displaying/presenting the information contained in its model.
 * 
 * So the view will then show the updated data of and from the model. 
 * 
 * @see com.unitelmasapienza.asciiart.model.AsciiPanelData that is the <b>Model</b> for this view.
 * @see com.unitelmasapienza.asciiart.imageeditor.ImageEditor that is the <b>Controller</b> between Model and View
 * 
 * @author Fulvio Zecchin
 *
 */
public class AsciiPanelView extends JPanel {
	
	/**
	 * The indefier to serialize/deserialize the object
	 * 
	 */
	private static final long serialVersionUID = -3688823660257394506L;
	
    /**
     * Is the mouse cursor distance from left
     * 
     */
    private int cursorDistanceFromLeft;

    /**
     * Is the mouse cursor distance from top
     * 
     */
    private int cursorDistanceFromTop;
    
    /**
     * The <i>x</i> and <i>y</i> coordinates matrix that identifies the entire drawing space. 
     * At each point [x]-[y] the chosen character can be drawn.
     * 
     */
    private char[][] panelCharsMatrix;

    /**
     * Represents the background color of a specific character identified by its x and y coordinates
     * 
     */
    private Color[][] panelCharsBackgroundColors;

    /**
     * Represents the color of a specific character identified by its x and y coordinates
     * 
     */
    private Color[][] panelCharsForegroundColors;
    
    /**
     * Coordinates <i>x</i>, <i>y</i> of mouse cursor
     * 
     */
    private int mouseCursorX,mouseCursorY;
    
	public int getCursorDistanceFromLeft() {
		return cursorDistanceFromLeft;
	}

	public void setCursorDistanceFromLeft(int cursorDistanceFromLeft) {
		this.cursorDistanceFromLeft = cursorDistanceFromLeft;
	}

	public int getCursorDistanceFromTop() {
		return cursorDistanceFromTop;
	}

	public void setCursorDistanceFromTop(int cursorDistanceFromTop) {
		this.cursorDistanceFromTop = cursorDistanceFromTop;
	}

	public char[][] getPanelCharsMatrix() {
		return panelCharsMatrix;
	}

	public void setPanelCharsMatrix(char[][] panelCharsMatrix) {
		this.panelCharsMatrix = panelCharsMatrix;
	}

	public Color[][] getPanelCharsBackgroundColors() {
		return panelCharsBackgroundColors;
	}

	public void setPanelCharsBackgroundColors(Color[][] panelCharsBackgroundColors) {
		this.panelCharsBackgroundColors = panelCharsBackgroundColors;
	}

	public Color[][] getPanelCharsForegroundColors() {
		return panelCharsForegroundColors;
	}

	public void setPanelCharsForegroundColors(Color[][] panelCharsForegroundColors) {
		this.panelCharsForegroundColors = panelCharsForegroundColors;
	}

	public int getMouseCursorX() {
		return mouseCursorX;
	}

	public void setMouseCursorX(int mouseCursorX) {
		this.mouseCursorX = mouseCursorX;
	}

	public int getMouseCursorY() {
		return mouseCursorY;
	}

	public void setMouseCursorY(int mouseCursorY) {
		this.mouseCursorY = mouseCursorY;
	}

}
