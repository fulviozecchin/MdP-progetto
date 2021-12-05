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
	 * <i>Builder</i> version of field offscreenGraphics in AsciiPanel
	 * 
	 */
    private Graphics offscreenGraphics;
    
	/**
	 * <i>Builder</i> version of field panelWidthInCharacters in AsciiPanel
	 * 
	 */
    private int panelWidthInCharacters;
    
	/**
	 * <i>Builder</i> version of field panelHeightInCharacters in AsciiPanel
	 * 
	 */
    private int panelHeightInCharacters;
    
	/**
	 * <i>Builder</i> version of field charWidth in AsciiPanel
	 * 
	 */
    private int charWidth = 9;
    
	/**
	 * <i>Builder</i> version of field charHeight in AsciiPanel
	 * 
	 */
    private int charHeight = 16;
    
	/**
	 * <i>Builder</i> version of field panelFontFilename in AsciiPanel
	 * 
	 */
    private String panelFontFilename = "cp437_9x16.png";
    
	/**
	 * <i>Builder</i> version of field defaultBackgroundColor in AsciiPanel
	 * 
	 */
    private Color defaultBackgroundColor;
    
	/**
	 * <i>Builder</i> version of field defaultForegroundColor in AsciiPanel
	 * 
	 */
    private Color defaultForegroundColor;
    
	/**
	 * <i>Builder</i> version of field cursorDistanceFromLeft in AsciiPanel
	 * 
	 */
    private int cursorDistanceFromLeft;
    
	/**
	 * <i>Builder</i> version of field cursorDistanceFromTop in AsciiPanel
	 * 
	 */
    private int cursorDistanceFromTop;
    
	/**
	 * <i>Builder</i> version of field glyphSprite in AsciiPanel
	 * 
	 */
    private BufferedImage glyphSprite;
    
	/**
	 * <i>Builder</i> version of field glyphsList in AsciiPanel
	 * 
	 */
    private BufferedImage[] glyphsList;
    
	/**
	 * <i>Builder</i> version of field panelCharsMatrix in AsciiPanel
	 * 
	 */
    private char[][] panelCharsMatrix;
    
	/**
	 * <i>Builder</i> version of field panelCharsBackgroundColors in AsciiPanel
	 * 
	 */
    private Color[][] panelCharsBackgroundColors;
    
	/**
	 * <i>Builder</i> version of field panelCharsForegroundColors in AsciiPanel
	 * 
	 */
    private Color[][] panelCharsForegroundColors;
    
	/**
	 * <i>Builder</i> version of field panelOldChars in AsciiPanel
	 * 
	 */
    private char[][] panelOldChars;
    
	/**
	 * <i>Builder</i> version of field panelOldCharsBackgroundColors in AsciiPanel
	 * 
	 */
    private Color[][] panelOldCharsBackgroundColors;
    
	/**
	 * <i>Builder</i> version of field panelOldCharsForegroundColors in AsciiPanel
	 * 
	 */
    private Color[][] panelOldCharsForegroundColors;
    
	/**
	 * <i>Builder</i> version of field asciiFont in AsciiPanel
	 * 
	 */
    private AsciiFont asciiFont;
    
	/**
	 * <i>Builder</i> version of field mouseCursorX in AsciiPanel
	 * 
	 */
    private int mouseCursorX;

	/**
	 * <i>Builder</i> version of field mouseCursorY in AsciiPanel
	 * 
	 */
    private int mouseCursorY;
    
	/**
	 * <i>Builder</i> version of field offscreenBuffer in AsciiPanel
	 * 
	 */
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
     * as the panel dimensions will most likely change.
     * 
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
     * Sets the default color background for canvas.
     * 
     * @return Builder itself, as <i>Builder Pattern</i>.
     * 
     */
	public AsciiPanelBuilder defaultBackgroundColor() {
		this.defaultBackgroundColor = Color.BLACK;
		return this;
	}
	
	/**
	 * Sets the default color foreground for canvas.
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * This is the method used at the end of 'cascade' calls to Builder for create the AsciiPanel object.
	 * It calls the AsciiPanel constructor passing all fields already populated with values.
	 * Then receives from AsciiPanel the concrete object to return.
	 * 
	 * @return The AsciiPanel object is the concrete built panel object.
	 */
	public AsciiPanel build() {
		return new AsciiPanel(panelWidthInCharacters, panelHeightInCharacters, asciiFont, defaultBackgroundColor,
				defaultForegroundColor, panelCharsMatrix, panelCharsBackgroundColors, panelCharsForegroundColors,
				panelOldCharsBackgroundColors, panelOldCharsForegroundColors);
	}
	
	
	//GETTERS & SETTERS

	/**
	 * <b>Builder</b> Version of the graphic context base getter
	 * 
	 * @return the offscreenGraphics
	 */
	public Graphics getOffscreenGraphics() {
		return offscreenGraphics;
	}

	/**
	 * <b>Builder</b> Version of the image to drawn setter
	 * 
	 * @param offscreenBuffer is the image to set
	 */
	public void setOffscreenGraphics(Graphics offscreenGraphics) {
		this.offscreenGraphics = offscreenGraphics;
	}

    /**
     * <b>Builder</b> Version of the panel width in characters getter.
     * A standard terminal is 80 characters wide.
     * 
     * @return the width in characters
     */
	public int getPanelWidthInCharacters() {
		return panelWidthInCharacters;
	}

	/**
	 * <b>Builder</b> Version of the panel with in characters setter.
	 * 
	 * @param panelWidthInCharacters is the panel width in characters to set
	 */
	public void setPanelWidthInCharacters(int panelWidthInCharacters) {
		this.panelWidthInCharacters = panelWidthInCharacters;
	}

    /**
     * <b>Builder</b> Version of the panel height in characters getter.
     * A standard terminal is 24 characters high.
     * 
     * @return the height in characters
     */
	public int getPanelHeightInCharacters() {
		return panelHeightInCharacters;
	}

	/**
	 * <b>Builder</b> Version of the panel height in characters setter.
	 * 
	 * @param panelHeightInCharacters is the the panel height in characters to set
	 */
	public void setPanelHeightInCharacters(int panelHeightInCharacters) {
		this.panelHeightInCharacters = panelHeightInCharacters;
	}

    /**
     * <b>Builder</b> Version for get the width, in pixels, of a character.
     * 
     * @return characters width
     */
	public int getCharWidth() {
		return charWidth;
	}

	/**
	 * <b>Builder</b> Version for the char width setter.
	 * 
	 * @param charWidth is the char width to set
	 */
	public void setCharWidth(int charWidth) {
		this.charWidth = charWidth;
	}

    /**
     * <b>Builder</b> Version for get the height, in pixels, of a character.
     * 
     * @return characters height
     */
	public int getCharHeight() {
		return charHeight;
	}

	/**
	 * <b>Builder</b> Version for the char height setter.
	 * 
	 * @param charHeight is the char width to set
	 */
	public void setCharHeight(int charHeight) {
		this.charHeight = charHeight;
	}

	/**
	 * <b>Builder</b> Version for the font filename getter.
	 * 
	 * @return the panel font filename  
	 */
	public String getPanelFontFilename() {
		return panelFontFilename;
	}

	/**
	 * <b>Builder</b> Version for the panel font filename setter.
	 * 
	 * @param panelFontFilename is the panel font filename to set
	 */
	public void setPanelFontFilename(String panelFontFilename) {
		this.panelFontFilename = panelFontFilename;
	}

    /**
     * <b>Builder</b> Version for gets the default background color that is used when writing new text.
     * 
     * @return the default background color
     */
	public Color getDefaultBackgroundColor() {
		return defaultBackgroundColor;
	}

    /**
     * <b>Builder</b> Version for sets the default background color that is used when writing new text.
     * 
     * @param defaultBackgroundColor is the default background color to set
     */
	public void setDefaultBackgroundColor(Color defaultBackgroundColor) {
		this.defaultBackgroundColor = defaultBackgroundColor;
	}

    /**
     * <b>Builder</b> Version for gets the default foreground color that is used when writing new text.
     * 
     * @return the default foreground color
     */
	public Color getDefaultForegroundColor() {
		return defaultForegroundColor;
	}

    /**
     * <b>Builder</b> Version for sets the default foreground color that is used when writing new text.
     * 
     * @param defaultForegroundColor is the default foreground color to set
     */
	public void setDefaultForegroundColor(Color defaultForegroundColor) {
		this.defaultForegroundColor = defaultForegroundColor;
	}

    /**
     * <b>Builder</b> Version for gets the distance from the left new text will be written to.
     * 
     * @return the distance from the left
     */
	public int getCursorDistanceFromLeft() {
		return cursorDistanceFromLeft;
	}

    /**
     * <b>Builder</b> Version for sets the distance from the left new text will be written to.
     * This should be equal to or greater than 0 and less than the the width in characters.
     * 
     * @param distance the distance from the left new text should be written to
     */
	public void setCursorDistanceFromLeft(int cursorDistanceFromLeft) {
		this.cursorDistanceFromLeft = cursorDistanceFromLeft;
	}

    /**
     * <b>Builder</b> Version to get the distance from the top new text will be written to.
     * 
     * @return the distance from top
     */
	public int getCursorDistanceFromTop() {
		return cursorDistanceFromTop;
	}

    /**
     * <b>Builder</b> Version to set the distance from the top new text will be written to.
     * This should be equal to or greater than 0 and less than the the height in characters.
     * 
     * @param distance the distance from the top new text should be written to
     */
	public void setCursorDistanceFromTop(int cursorDistanceFromTop) {
		this.cursorDistanceFromTop = cursorDistanceFromTop;
	}

	/**
	 * <b>Builder</b> Version of the glyphSprite getter.
	 * 
	 * @return the glyphSprite 
	 */
	public BufferedImage getGlyphSprite() {
		return glyphSprite;
	}

	/**
	 * <b>Builder</b> Version of the glyphSprite setter 
	 * @param glyphSprite is the glyphSprite to set
	 */
	public void setGlyphSprite(BufferedImage glyphSprite) {
		this.glyphSprite = glyphSprite;
	}

	/**
     * <b>Builder</b> Version of the character list of the table character selection frame getter.
     * 
     * @return the character list of table character selection
     */
	public BufferedImage[] getGlyphsList() {
		return glyphsList;
	}

	/**
	 * <b>Builder</b> Version of the character list of the table character selection frame setter.
	 * 
	 * @param glyphsList is the glyphsList to set
	 */
	public void setGlyphsList(BufferedImage[] glyphsList) {
		this.glyphsList = glyphsList;
	}

	/**
	 * <b>Builder</b> Version of the chars matrix [x][y] of entire drawing space getter.
	 * 
	 * @return the chars matrix
	 */
	public char[][] getPanelCharsMatrix() {
		return panelCharsMatrix;
	}

	/**
	 * <b>Builder</b> Version of the chars matrix [x][y] of entire drawing space setter.
	 * 
	 * @param chars is the char matrix to set
	 */
	public void setPanelCharsMatrix(char[][] panelCharsMatrix) {
		this.panelCharsMatrix = panelCharsMatrix;
	}

	/**
	 * <b>Builder</b> Version of the panel chars background colors getter.
	 * 
	 * @return the panel chars background color
	 */
	public Color[][] getPanelCharsBackgroundColors() {
		return panelCharsBackgroundColors;
	}

	/**
	 * <b>Builder</b> Version of the panel chars background colors setter.
	 * 
	 * @param panelCharsBackgroundColors is the background colors to set
	 */
	public void setPanelCharsBackgroundColors(Color[][] panelCharsBackgroundColors) {
		this.panelCharsBackgroundColors = panelCharsBackgroundColors;
	}

	/**
	 * <b>Builder</b> Version of the panel chars foreground colors getter.
	 * 
	 * @return the panel chars foreground colors
	 */
	public Color[][] getPanelCharsForegroundColors() {
		return panelCharsForegroundColors;
	}

	/**
	 * <b>Builder</b> Version of the panel chars foreground colors setter.
	 * 
	 * @param panelCharsForegroundColors is the foreground colors to set
	 */
	public void setPanelCharsForegroundColors(Color[][] panelCharsForegroundColors) {
		this.panelCharsForegroundColors = panelCharsForegroundColors;
	}

	/**
	 * <b>Builder</b> Version of the old chars getter.
	 * 
	 * @return the old chars
	 */
	public char[][] getPanelOldChars() {
		return panelOldChars;
	}

	/**
	 * <b>Builder</b> Version of the panel old chars setter.
	 * 
	 * @param panelNewChars is the new panel chars to set
	 */
	public void setPanelOldChars(char[][] panelOldChars) {
		this.panelOldChars = panelOldChars;
	}

	/**
	 * <b>Builder</b> Version of the old panel chars background colors getter.
	 * @return the old panel chars background colors
	 */
	public Color[][] getPanelOldCharsBackgroundColors() {
		return panelOldCharsBackgroundColors;
	}

	/**
	 * <b>Builder</b> Version of the old panel chars background colors setter.
	 * 
	 * @param panelNewCharsBackgroundColors is the new panel chars background colors to set
	 */
	public void setPanelOldCharsBackgroundColors(Color[][] panelOldCharsBackgroundColors) {
		this.panelOldCharsBackgroundColors = panelOldCharsBackgroundColors;
	}

	/**
	 * <b>Builder</b> Version of the old panel chars foreground colors getter.
	 * 
	 * @return the old panel chars foreground colors
	 */
	public Color[][] getPanelOldCharsForegroundColors() {
		return panelOldCharsForegroundColors;
	}

	/**
	 * <b>Builder</b> Version of the old panel chars foreground colors setter.
	 * 
	 * @param panelNewCharsForegroundColors is the new panel chars foreground colors to set
	 */
	public void setPanelOldCharsForegroundColors(Color[][] panelOldCharsForegroundColors) {
		this.panelOldCharsForegroundColors = panelOldCharsForegroundColors;
	}

    /**
     * <b>Builder</b> Version for coordinate <i>x</i> of mouse cursor getter.
     * 
     * @return the mouse cursor<i>x</i>-coordinate
     */
	public int getMouseCursorX() {
		return mouseCursorX;
	}

    /**
     * <b>Builder</b> Version for coordinate <i>x</i> of mouse cursor setter.
     * 
     * @param x is the mouse cursor for <i>x</i>-coordinate
     */
	public void setMouseCursorX(int mouseCursorX) {
		this.mouseCursorX = mouseCursorX;
	}

    /**
     * <b>Builder</b> Version for coordinate <i>y</i> of mouse cursor getter.
     * 
     * @return the mouse cursor<i>y</i>-coordinate
     */
	public int getMouseCursorY() {
		return mouseCursorY;
	}

    /**
     * <b>Builder</b> Version for coordinate <i>y</i> of mouse cursor setter.
     * 
     * @param y is the mouse cursor for <i>y</i>-coordinate
     */
	public void setMouseCursorY(int mouseCursorY) {
		this.mouseCursorY = mouseCursorY;
	}

	/**
	 * <b>Builder</b> Version of the image to drawn getter.
	 * 
	 * @return the offscreenBuffer
	 */
	public Image getOffscreenBuffer() {
		return offscreenBuffer;
	}

	/**
	 * <b>Builder</b> Version of the image to drawn setter.
	 * 
	 * @param offscreenBuffer is the image to set
	 */
	public void setOffscreenBuffer(Image offscreenBuffer) {
		this.offscreenBuffer = offscreenBuffer;
	}

}
