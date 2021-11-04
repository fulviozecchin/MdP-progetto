package com.unitelmasapienza.asciiart.asciipanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * This class is an implementation of <b>Builder Design Pattern</b>.
 * We direct call this Builder class for create and initialize an AsciiPanelData object.
 * 
 * @author Fulvio Zecchin
 *
 */
public class AsciiPanelBuilder {
	
	/**
	 * Follow all fields of AsciiPanel
	 * @see AsciiPanel to understand every field
	 * 
	 */
	
    private Graphics offscreenGraphics;
    private int panelWidthInCharacters;
    private int panelHeightInCharacters;
    private int charWidth = 9;
    private int charHeight = 16;
    private String panelFontFilename = "cp437_9x16.png";
    private Color defaultBackgroundColor;
    private Color defaultForegroundColor;
    private int cursorDistanceFromLeft;
    private int cursorDistanceFromTop;
    private BufferedImage glyphSprite;
    private BufferedImage[] glyphsList;
    private char[][] panelCharsMatrix;
    private Color[][] panelCharsBackgroundColors;
    private Color[][] panelCharsForegroundColors;
    private char[][] panelOldChars;
    private Color[][] panelOldCharsBackgroundColors;
    private Color[][] panelOldCharsForegroundColors;
    private AsciiFont asciiFont;
    private int mouseCursorX;
    private int mouseCursorY;
	private Image offscreenBuffer;
	
	/**
	 * This is the public constructor 'starter' for creation of AsciiPanel.
	 * As <i>Builder Pattern</i> implementation wants, we can add a single cascade call for every property/field
	 * that we want to set.
	 * Every set-method of Builder class, will return Builder itself (this), so we can 'cascade' to call all the set
	 * methods we need.
	 * At the end, to create the real AsciiPanel object, we need to call the .build() method of this class.
	 * It will call an AsciiPanel constructor which will return it to caller.
	 * 
	 * @param width is the width in characters for panel
	 * @param height is the height in characters for panel
	 * @param font is the font for panel
	 * @return Builder itself, as <i>Builder Pattern</i>. To get the AsciiPanel object call the build() methods of this class.
	 * 
	 */
	public AsciiPanelBuilder createAsciiPanel(int width, int height, AsciiFont font) {
		
		if (width < 1) {
            throw new IllegalArgumentException("width " + width + " must be greater than 0." );
        }

        if (height < 1) {
            throw new IllegalArgumentException("height " + height + " must be greater than 0." );
        }
        setPanelWidthInCharacters(width);
        setPanelHeightInCharacters(height);
        
        if(font == null) font = AsciiFont.CP437_9x16;
        this.setAsciiFont(font);
        
		return this;
	}
	
    /**
     * Sets the used font. It is advisable to make sure the parent component is properly sized after setting the font
     * as the panel dimensions will most likely change
     * @param font is the font to use
     * 
     */
    public void setAsciiFont(AsciiFont font) {
        if(this.asciiFont == font) {
            return;
        }
        this.asciiFont = font;
    }
    
    /**
     * Sets the default color background for canvas
     * @return Builder itself, as <i>Builder Pattern</i>.
     * 
     */
	public AsciiPanelBuilder defaultBackgroundColor() {
		this.defaultBackgroundColor = Color.BLACK;
		return this;
	}
	
	/**
	 * Sets the default color foreground for canvas
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 * 
	 */
	public AsciiPanelBuilder defaultForegroundColor() {
		this.defaultForegroundColor = Color.WHITE;
		return this;
	}
	
	/**
	 * Sets the panel matrix for entire drawing space.
	 * @param panelCharsMatrix is the matrix to set. If it's null, a new one will be created.
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 * 
	 */
	public AsciiPanelBuilder panelCharsMatrix(char[][] panelCharsMatrix) {
		if(panelCharsMatrix == null || panelCharsMatrix.length == 0) {
			this.panelCharsMatrix = new char[panelWidthInCharacters][panelHeightInCharacters];
		} else this.panelCharsMatrix = panelCharsMatrix;
		return this;
	}
	
	/**
	 * Sets the chars background color from an array of colors which identified a specific char background color by its [x][y] coordinates.
	 * @param panelCharsBackgroundColors is the background color for specific char. If it's null, a new one is created.
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 * 
	 */
	public AsciiPanelBuilder panelCharsBackgroundColors(Color[][] panelCharsBackgroundColors) {
		if(panelCharsBackgroundColors == null || panelCharsBackgroundColors.length == 0) {
			this.panelCharsBackgroundColors = new Color[panelWidthInCharacters][panelHeightInCharacters];
		} else this.panelCharsBackgroundColors = panelCharsBackgroundColors;
		return this;
	}
	
	/**
	 * Sets the chars foreground color from an array of colors which identified a specific char foreground color by its [x][y] coordinates.
	 * @param panelCharsForegroundColors is the foreground color for specific char. If it's null, a new one is created.
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public AsciiPanelBuilder panelCharsForegroundColors(Color[][] panelCharsForegroundColors) {
		if(panelCharsForegroundColors == null || panelCharsForegroundColors.length == 0) {
			this.panelCharsForegroundColors = new Color[panelWidthInCharacters][panelHeightInCharacters];
		} else this.panelCharsForegroundColors = panelCharsForegroundColors;
		return this;
	}
	
	/**
	 * Sets the old chars background color
	 * @param panelOldCharsBackgroundColors
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public AsciiPanelBuilder panelOldCharsBackgroundColors(Color[][] panelOldCharsBackgroundColors) {
		if(panelOldCharsBackgroundColors == null || panelOldCharsBackgroundColors.length == 0) {
			this.panelOldCharsBackgroundColors = new Color[panelWidthInCharacters][panelHeightInCharacters];
		} else this.panelOldCharsBackgroundColors = panelOldCharsBackgroundColors;
		return this;
	}
	
	/**
	 * Sets the old char foreground color
	 * @param panelOldCharsForegroundColors
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public AsciiPanelBuilder panelOldCharsForegroundColors(Color[][] panelOldCharsForegroundColors) {
		if(panelOldCharsForegroundColors == null || panelOldCharsForegroundColors.length == 0) {
			this.panelOldCharsForegroundColors = new Color[panelWidthInCharacters][panelHeightInCharacters];
		} else this.panelOldCharsForegroundColors = panelOldCharsForegroundColors;
		return this;
	}
	
	/**
	 * 
	 * @return The AsciiPanel object
	 */
	public AsciiPanel build() {
		return new AsciiPanel(panelWidthInCharacters, panelHeightInCharacters, asciiFont, defaultBackgroundColor,
				defaultForegroundColor, panelCharsMatrix, panelCharsBackgroundColors, panelCharsForegroundColors,
				panelOldCharsBackgroundColors, panelOldCharsForegroundColors);
	}
	
	
	//GETTERS & SETTERS


	public Graphics getOffscreenGraphics() {
		return offscreenGraphics;
	}

	public void setOffscreenGraphics(Graphics offscreenGraphics) {
		this.offscreenGraphics = offscreenGraphics;
	}

	public int getPanelWidthInCharacters() {
		return panelWidthInCharacters;
	}

	public void setPanelWidthInCharacters(int panelWidthInCharacters) {
		this.panelWidthInCharacters = panelWidthInCharacters;
	}

	public int getPanelHeightInCharacters() {
		return panelHeightInCharacters;
	}

	public void setPanelHeightInCharacters(int panelHeightInCharacters) {
		this.panelHeightInCharacters = panelHeightInCharacters;
	}

	public int getCharWidth() {
		return charWidth;
	}

	public void setCharWidth(int charWidth) {
		this.charWidth = charWidth;
	}

	public int getCharHeight() {
		return charHeight;
	}

	public void setCharHeight(int charHeight) {
		this.charHeight = charHeight;
	}

	public String getPanelFontFilename() {
		return panelFontFilename;
	}

	public void setPanelFontFilename(String panelFontFilename) {
		this.panelFontFilename = panelFontFilename;
	}

	public Color getDefaultBackgroundColor() {
		return defaultBackgroundColor;
	}

	public void setDefaultBackgroundColor(Color defaultBackgroundColor) {
		this.defaultBackgroundColor = defaultBackgroundColor;
	}

	public Color getDefaultForegroundColor() {
		return defaultForegroundColor;
	}

	public void setDefaultForegroundColor(Color defaultForegroundColor) {
		this.defaultForegroundColor = defaultForegroundColor;
	}

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

	public BufferedImage getGlyphSprite() {
		return glyphSprite;
	}

	public void setGlyphSprite(BufferedImage glyphSprite) {
		this.glyphSprite = glyphSprite;
	}

	public BufferedImage[] getGlyphsList() {
		return glyphsList;
	}

	public void setGlyphsList(BufferedImage[] glyphsList) {
		this.glyphsList = glyphsList;
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

	public char[][] getPanelOldChars() {
		return panelOldChars;
	}

	public void setPanelOldChars(char[][] panelOldChars) {
		this.panelOldChars = panelOldChars;
	}

	public Color[][] getPanelOldCharsBackgroundColors() {
		return panelOldCharsBackgroundColors;
	}

	public void setPanelOldCharsBackgroundColors(Color[][] panelOldCharsBackgroundColors) {
		this.panelOldCharsBackgroundColors = panelOldCharsBackgroundColors;
	}

	public Color[][] getPanelOldCharsForegroundColors() {
		return panelOldCharsForegroundColors;
	}

	public void setPanelOldCharsForegroundColors(Color[][] panelOldCharsForegroundColors) {
		this.panelOldCharsForegroundColors = panelOldCharsForegroundColors;
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

	public Image getOffscreenBuffer() {
		return offscreenBuffer;
	}

	public void setOffscreenBuffer(Image offscreenBuffer) {
		this.offscreenBuffer = offscreenBuffer;
	}

}
