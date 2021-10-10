package com.unitelmasapienza.asciiart.asciipanel.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;

/**
 * This class, in the <b>MVC (Model View Controller) design pattern</b>, represents the <b>Model</b>, 
 * i.e. the information and data about the ascii panel to be displayed.
 * 
 * So it contains the canvas drawing data that will then be organized and displayed by the view. 
 * 
 * @see com.unitelmasapienza.asciiart.view.AsciiPanelView that is the <b>View</b> of this datas
 * @see com.unitelmasapienza.asciiart.asciipanel.controller.AsciiPanelController that is the <b>Controller</b> between Model and View
 * 
 * @author Fulvio Zecchin
 *
 */
public class AsciiPanelData extends JPanel {
	
    /**
     * Represents the drawing canvas width expressed in characters
     * 
     */
    private int panelWidthInCharacters;
    
	/**
     * Represents the drawing canvas height expressed in characters
     * 
     */
    private int panelHeightInCharacters;
    
    /**
     * Is the buffered image of the font table that will be drawn in the font selection frame
     * 
     */
    private BufferedImage glyphSprite;
    
    /**
     * Is the character list of the table character selection frame. 
     * It contains at each index a character that can be selected
     */
    private BufferedImage[] glyphsList;
    
    /**
     * The character width in pixel
     * 
     */
    private int charWidth = 9;

    /**
     * The character height in pixel
     * 
     */
    private int charHeight = 16;
    
    /**
     * Is the used font for drawing
     * 
     */
    private String panelFontFilename = "cp437_9x16.png";
    
    /**
     * Is the selected Font
     * 
     */
    private AsciiFont asciiFont;
	
    /**
     * Is the base for graphic context that allow to draw 
     * 
     */
    private Graphics offscreenGraphics;
    
    /**
     * Is the old character at position [x]-[y]. 
     * 
     */
    private char[][] panelOldChars;
    
    /**
     * Is the old background color at position [x]-[y]. 
     * 
     */
    private Color[][] panelOldCharsBackgroundColors;

    /**
     * Is the old foreground color at position [x]-[y]. 
     * 
     */
    private Color[][] panelOldCharsForegroundColors;
    
    /**
     * Represents the background color of the drawing canvas
     * 
     */
    private Color defaultBackgroundColor;

    /**
     * Represents the color that is used when writing new text in drawing canvas
     * 
     */
    private Color defaultForegroundColor;
    
	/**
	 * The image to be drawn
	 * 
	 */
	private Image offscreenBuffer;
	
    /**
     * Private model constructor. As Builder Pattern best practice, this constructor is only for
     * Builder nested class.
     * @param width
     * @param height
     * @param font
     * @param defaultBackgroundColor
     * @param defaultForegroundColor
     * @param oldCharsBackgroundColors
     * @param oldCharsForegroundColors
     */
    protected AsciiPanelData(int width, int height, AsciiFont font, Color defaultBackgroundColor, Color defaultForegroundColor,
    		Color[][] oldCharsBackgroundColors, Color[][] oldCharsForegroundColors) {
    	setPanelWidthInCharacters(width);
    	setPanelHeightInCharacters(height);
    	setAsciiFont(font);
    	setDefaultBackgroundColor(defaultBackgroundColor);
    	setDefaultForegroundColor(defaultForegroundColor);
    	setPanelOldCharsBackgroundColors(oldCharsBackgroundColors);
    	setPanelOldCharsForegroundColors(oldCharsForegroundColors);
    }
    
    /**
     * Class constructor specifying the width and height in characters and the AsciiFont
     * @param width is the width
     * @param height is the height
     * @param font if passing null, standard font CP437_9x16 will be used
     */
    public AsciiPanelData(int width, int height, AsciiFont font) {
        super();

        if (width < 1) {
            throw new IllegalArgumentException("width " + width + " must be greater than 0." );
        }

        if (height < 1) {
            throw new IllegalArgumentException("height " + height + " must be greater than 0." );
        }

        setPanelWidthInCharacters(width);
        setPanelHeightInCharacters(height);

        setDefaultBackgroundColor(Color.BLACK);
        setDefaultForegroundColor(Color.WHITE);

        setPanelOldCharsBackgroundColors(new Color[getPanelWidthInCharacters()][getPanelHeightInCharacters()]);
        setPanelOldCharsForegroundColors(new Color[getPanelWidthInCharacters()][getPanelHeightInCharacters()]);

        if(font == null) {
        	font = AsciiFont.CP437_9x16;
        }
        setAsciiFont(font);
    }
    
    /**
     * Sets the used font. It is advisable to make sure the parent component is properly sized after setting the font
     * as the panel dimensions will most likely change
     * @param font is the font to use
     */
    public void setAsciiFont(AsciiFont font) {
 
        if(this.asciiFont == font) {
            return;
        }
        this.asciiFont = font;

        this.setCharHeight(font.getFontHeight());
        this.setCharWidth(font.getFontWidth());
        this.setPanelFontFilename(font.getFontFilename());

        Dimension panelSize = new Dimension(getCharWidth() * getPanelWidthInCharacters(), getCharHeight() * getPanelHeightInCharacters());
        setPreferredSize(panelSize);

        setGlyphsList(new BufferedImage[256]);

        setOffscreenBuffer(new BufferedImage(panelSize.width, panelSize.height, BufferedImage.TYPE_INT_RGB));
        setOffscreenGraphics(getOffscreenBuffer().getGraphics());

        loadGlyphsList();

        setPanelOldChars(new char[getPanelWidthInCharacters()][getPanelHeightInCharacters()]);
    }
    
	/**
	 * Load the character list of the table character selection frame.
	 * 
	 */
    private void loadGlyphsList() {
        try {
            setGlyphSprite(ImageIO.read(AsciiPanel.class.getClassLoader().getResource(getPanelFontFilename())));
        } catch (IOException e) {
            System.err.println("loadGlyphs(): " + e.getMessage());
        }

        for (int i = 0; i < 256; i++) {
            int sx = (i % 16) * getCharWidth();
            int sy = (i / 16) * getCharHeight();

            getGlyphsList()[i] = new BufferedImage(getCharWidth(), getCharHeight(), BufferedImage.TYPE_INT_ARGB);
            getGlyphsList()[i].getGraphics().drawImage(getGlyphSprite(), 0, 0, getCharWidth(), getCharHeight(), sx, sy, sx + getCharWidth(), sy + getCharHeight(), null);
        }
    }
    
	/**
	 * The font filename getter
	 * @return the panel font filename  
	 */
	private String getPanelFontFilename() {
		return panelFontFilename;
	}

	/**
	 * The panel font filename setter
	 * @param panelFontFilename is the panel font filename to set
	 */
	private void setPanelFontFilename(String panelFontFilename) {
		this.panelFontFilename = panelFontFilename;
	}

    
    /**
     * Sets the default background color that is used when writing new text.
     * @param defaultBackgroundColor is the default background color to set
     */
    public void setDefaultBackgroundColor(Color defaultBackgroundColor) {
        if (defaultBackgroundColor == null)
            throw new NullPointerException("defaultBackgroundColor must not be null.");

        this.defaultBackgroundColor = defaultBackgroundColor;
    }

    /**
     * Gets the default foreground color that is used when writing new text.
     * @return the default foreground color
     */
    public Color getDefaultForegroundColor() {
        return defaultForegroundColor;
    }

    /**
     * Sets the default foreground color that is used when writing new text.
     * @param defaultForegroundColor is the default foreground color to set
     */
    public void setDefaultForegroundColor(Color defaultForegroundColor) {
        if (defaultForegroundColor == null)
            throw new NullPointerException("defaultForegroundColor must not be null.");

        this.defaultForegroundColor = defaultForegroundColor;
    }
	
	/**
	 * The old panel chars background colors getter
	 * @return the old panel chars background colors
	 */
	private Color[][] getPanelOldCharsBackgroundColors() {
		return panelOldCharsBackgroundColors;
	}

	/**
	 * The old panel chars background colors setter
	 * @param panelNewCharsBackgroundColors is the new panel chars background colors to set
	 */
	private void setPanelOldCharsBackgroundColors(Color[][] panelNewCharsBackgroundColors) {
		this.panelOldCharsBackgroundColors = panelNewCharsBackgroundColors;
	}

	/**
	 * The old panel chars foreground colors getter
	 * @return the old panel chars foreground colors
	 */
	private Color[][] getPanelOldCharsForegroundColors() {
		return panelOldCharsForegroundColors;
	}

	/**
	 * he old panel chars foreground colors setter
	 * @param panelNewCharsForegroundColors is the new panel chars foreground colors to set
	 */
	private void setPanelOldCharsForegroundColors(Color[][] panelNewCharsForegroundColors) {
		this.panelOldCharsForegroundColors = panelNewCharsForegroundColors;
	}
	
	/**
	 * The image to drawn getter
	 * 
	 * @return the offscreenBuffer
	 */
	private Image getOffscreenBuffer() {
		return offscreenBuffer;
	}

	/**
	 * The image to drawn setter
	 * 
	 * @param offscreenBuffer is the image to set
	 */
	private void setOffscreenBuffer(Image offscreenBuffer) {
		this.offscreenBuffer = offscreenBuffer;
	}
	
	/**
	 * The graphic context base getter
	 * 
	 * @return the offscreenGraphics
	 */
	private Graphics getOffscreenGraphics() {
		return offscreenGraphics;
	}

	/**
	 * The graphic context base setter
	 * 
	 * @param offscreenGraphics the graphic context to set
	 */
	private void setOffscreenGraphics(Graphics offscreenGraphics) {
		this.offscreenGraphics = offscreenGraphics;
	}
	
	/**
	 * The old chars getter
	 * @return the old chars
	 */
	private char[][] getPanelOldChars() {
		return panelOldChars;
	}

	/**
	 * The panel old chars setter
	 * @param panelNewChars is the new panel chars to set
	 */
	private void setPanelOldChars(char[][] panelNewChars) {
		this.panelOldChars = panelNewChars;
	}
	
	/**
     * The character list of the table character selection frame getter
     * @return the character list of table character selection
     */
    public BufferedImage[] getGlyphsList() {
    	return glyphsList;
    }
    
	/**
	 * The character list of the table character selection frame setter
	 * @param glyphsList is the glyphsList to set
	 */
	private void setGlyphsList(BufferedImage[] glyphsList) {
		this.glyphsList = glyphsList;
	}
	
	/**
	 * The glyphSprite getter
	 * @return the glyphSprite 
	 */
	private BufferedImage getGlyphSprite() {
		return glyphSprite;
	}

	/**
	 * The glyphSprite setter 
	 * @param glyphSprite is the glyphSprite to set
	 */
	private void setGlyphSprite(BufferedImage glyphSprite) {
		this.glyphSprite = glyphSprite;
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

	public AsciiFont getAsciiFont() {
		return asciiFont;
	}

	public Color getDefaultBackgroundColor() {
		return defaultBackgroundColor;
	}

}
