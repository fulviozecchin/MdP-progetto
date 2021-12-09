package com.unitelmasapienza.asciiart.asciipanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.unitelmasapienza.asciiart.asciipanel.factories.AsciiPanelFactoryConcrete;

/**
 * The class represents the <b>drawing space (canvas) of the application</b>.
 * @see asciiart.imageeditor.ImageEditor which instantiates one when it is created
 *      and use it to draw, import images or load drawings on it.
 * 
 * @author Fulvio Zecchin
 * MODEL
 */
public class AsciiPanel extends JPanel {
	
	/**
	 * The indefier to serialize/deserialize the object
	 * 
	 */	
	private static final long serialVersionUID = -4167851861147593092L;

    /**
     * The color black (pure black).
     * 
     */
    public static Color black = new Color(0, 0, 0);

    /**
     * The color red.
     * 
     */
    public static Color red = new Color(128, 0, 0);

    /**
     * The color green.
     * 
     */
    public static Color green = new Color(0, 128, 0);

    /**
     * The color yellow.
     * 
     */
    public static Color yellow = new Color(128, 128, 0);

    /**
     * The color blue.
     * 
     */
    public static Color blue = new Color(0, 0, 128);

    /**
     * The color magenta.
     * 
     */
    public static Color magenta = new Color(128, 0, 128);

    /**
     * The color cyan.
     * 
     */
    public static Color cyan = new Color(0, 128, 128);

    /**
     * The color white (light gray).
     * 
     */
    public static Color white = new Color(192, 192, 192);

    /**
     * A brighter black (dark gray).
     * 
     */
    public static Color brightBlack = new Color(128, 128, 128);

    /**
     * A brighter red.
     * 
     */
    public static Color brightRed = new Color(255, 0, 0);

    /**
     * A brighter green.
     * 
     */
    public static Color brightGreen = new Color(0, 255, 0);

    /**
     * A brighter yellow.
     * 
     */
    public static Color brightYellow = new Color(255, 255, 0);

    /**
     * A brighter blue.
     * 
     */
    public static Color brightBlue = new Color(0, 0, 255);

    /**
     * A brighter magenta.
     * 
     */
    public static Color brightMagenta = new Color(255, 0, 255);

    /**
     * A brighter cyan.
     * 
     */
    public static Color brightCyan = new Color(0, 255, 255);
    
    /**
     * A brighter white (pure white).
     * 
     */
    public static Color brightWhite = new Color(255, 255, 255);

    /**
     * Is the base for graphic context that allow to draw 
     * 
     */
    private Graphics offscreenGraphics;
    
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
     * Is the buffered image of the font table that will be drawn in the font selection frame
     * 
     */
    private BufferedImage glyphSprite;

    /**
     * Is the character list of the table character selection frame. 
     * It contains at each index a character that can be selected
     * 
     */
    private BufferedImage[] glyphsList;

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
     * Is the selected Font
     * 
     */
    private AsciiFont asciiFont;

    /**
     * Coordinates <i>x</i>, <i>y</i> of mouse cursor
     * 
     */
    private int mouseCursorX,mouseCursorY;

	/**
	 * The image to be drawn
	 * 
	 */
	private Image offscreenBuffer;

    /**
     * Class constructor.
     * Default size is 80x24.
     */
    public AsciiPanel() {
        this(80, 24);
    }

    /**
     * Class constructor specifying the width and height in characters.
     * @param width is the width
     * @param height is the height
     */
    public AsciiPanel(int width, int height) {
    	this(width, height, null);
    }
    
    /**
     * Class constructor specifying the width and height in characters and the AsciiFont
     * @param width is the width
     * @param height is the height
     * @param font if passing null, standard font CP437_9x16 will be used
     */
    public AsciiPanel(int width, int height, AsciiFont font) {
        super();

        if (width < 1) {
            throw new IllegalArgumentException("width " + width + " must be greater than 0." );
        }

        if (height < 1) {
            throw new IllegalArgumentException("height " + height + " must be greater than 0." );
        }

        setPanelWidthInCharacters(width);
        setPanelHeightInCharacters(height);

        setDefaultBackgroundColor(getBlack());
        setDefaultForegroundColor(getWhite());

        setPanelCharsMatrix(new char[getPanelWidthInCharacters()][getPanelHeightInCharacters()]);
        setPanelCharsBackgroundColors(new Color[getPanelWidthInCharacters()][getPanelHeightInCharacters()]);
        setPanelCharsForegroundColors(new Color[getPanelWidthInCharacters()][getPanelHeightInCharacters()]);

        setPanelOldCharsBackgroundColors(new Color[getPanelWidthInCharacters()][getPanelHeightInCharacters()]);
        setPanelOldCharsForegroundColors(new Color[getPanelWidthInCharacters()][getPanelHeightInCharacters()]);

        if(font == null) {
        	font = AsciiFont.CP437_9x16;
        }
        setAsciiFont(font);
    }
    
    /**
     * Class constructor specifying all fields. This is called directly from panel builder to create the final objet.
     * In the specific case it's an implementation of <i>Builder Pattern</i>.
     * @see AsciiPanelBuilder to check all value control (nullable, default value etc.) and see the modular construction
     * of object by cascading methods calls.
     * @see AsciiPanelFactoryConcrete check the cascading methods calls to the Builder Class.
     * 
     * @param width is the panel width in characters
     * @param height is the panel height in characters
     * @param font is the Ascii Font
     * @param defaultBackgroundColor is the default color for background
     * @param defaultForegroundColor is the default color for foreground
     * @param panelCharsMatrix is matrix [x][y]
     * @param panelCharsBackgroundColors is the color for panel chars background
     * @param panelCharsForegroundColors is the color for panel chars foreground
     * @param panelOldCharsBackgroundColors is the old color for panel chars background
     * @param panelOldCharsForegroundColors is the old color for panel chars foreground
     * 
     */
    public AsciiPanel(int width, int height, AsciiFont font, Color defaultBackgroundColor, Color defaultForegroundColor, char[][] panelCharsMatrix,
    		Color[][] panelCharsBackgroundColors, Color[][] panelCharsForegroundColors, Color[][] panelOldCharsBackgroundColors, Color[][] panelOldCharsForegroundColors) {
    	super();
    	setPanelWidthInCharacters(width);
    	setPanelHeightInCharacters(height);
    	setAsciiFont(font);
    	
    	setDefaultBackgroundColor(defaultBackgroundColor);
    	setDefaultForegroundColor(defaultForegroundColor);
    	
    	setPanelCharsMatrix(panelCharsMatrix);
    	setPanelCharsBackgroundColors(panelCharsBackgroundColors);
    	setPanelCharsForegroundColors(panelCharsForegroundColors);
    	
    	setPanelOldCharsBackgroundColors(panelOldCharsBackgroundColors);
    	setPanelOldCharsForegroundColors(panelOldCharsForegroundColors);
    }

    /**
     * The method is called every time some event (even the moving mouse cursor) happens on the canvas.
     * 
     * It simply calls {@link #paint(Graphics g)} method.
     * 
     */
    @Override
    public void update(Graphics g) {
         paint(g); 
    } 

    /**
     * The method is called every time some event (even the moving mouse cursor) happens on the canvas. 
     * 
     * If there are no changes to be made (characters to be drawn or replaced) it does nothing, otherwise it updates the drawing canvas.
     * 
     */
    @Override
    public void paint(Graphics g) {
        if (g == null)
            throw new NullPointerException();

        for (int x = 0; x < getPanelWidthInCharacters(); x++) {
            for (int y = 0; y < getPanelHeightInCharacters(); y++) {
            	if (getPanelOldCharsBackgroundColors()[x][y] == getPanelCharsBackgroundColors()[x][y]
            	 && getPanelOldCharsForegroundColors()[x][y] == getPanelCharsForegroundColors()[x][y]
            	 && getPanelOldChars()[x][y] == getPanelCharsMatrix()[x][y])
            		continue;
            	
                Color bg = getPanelCharsBackgroundColors()[x][y];
                Color fg = getPanelCharsForegroundColors()[x][y];
                if (bg==null) bg=Color.BLACK;
                if (fg==null) fg=Color.BLACK;
                
                LookupOp op = setColors(bg, fg);
                BufferedImage img = op.filter(getGlyphsList()[getPanelCharsMatrix()[x][y]], null);
                getOffscreenGraphics().drawImage(img, x * getCharWidth(), y * getCharHeight(), null);
                
                getPanelOldCharsBackgroundColors()[x][y] = getPanelCharsBackgroundColors()[x][y];
        	    getPanelOldCharsForegroundColors()[x][y] = getPanelCharsForegroundColors()[x][y];
        	    getPanelOldChars()[x][y] = getPanelCharsMatrix()[x][y];
            }
        }
        g.drawImage(getOffscreenBuffer(), 0, 0, this);
    }
    /**
     * The method is called when you want to save a drawing in a file. 
     * It takes the file name as an absolute path and saves it in the desired path with the chosen name.
     * 
     * @see asciiart.imageeditor.ActionSave which representing the save action.
     * 
     * @param filename is the absolute path, contains file system directory in which file will be saved and filename
     */
    public void save(String filename) {
    	BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(filename));
			bw.write(getPanelWidthInCharacters()+"\n");
			bw.write(getPanelHeightInCharacters()+"\n");
			
			for (int x = 0; x < getPanelWidthInCharacters(); x++) {
		            for (int y = 0; y < getPanelHeightInCharacters(); y++) {
		            	Color fg=getPanelCharsForegroundColors()[x][y];
		            	Color bg=getPanelCharsBackgroundColors()[x][y];
		            	
		            	if (fg==null) fg=Color.black;
		            	if (bg==null) bg=Color.black;
		            	
		            	
		            	bw.write((int)getPanelCharsMatrix()[x][y]
		            			+"\t"+fg.getRGB()
		            			+"\t"+bg.getRGB()+"\n");
		            	}
		            }
			bw.close();		
		} catch (Exception e) {
			System.out.println("Error saving:");
			System.exit(1);
		}
    }

    /**
     * This method seems a method to load some image/drawing from file system by the filename.
     * However it is never called in the application.
     * 
     * @param filename is the filename of resources to load
     */
	public void load(String filename) {
		AsciiRaster img=AsciiRaster.createRasterFromFile(filename);
		this.paintRaster(img, 0, 0, false);
		repaint();
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
     * Create a <code>LookupOp</code> object (lookup table) mapping the original 
     * pixels to the background and foreground colors, respectively. 
     * @param bgColor the background color
     * @param fgColor the foreground color
     * @return the <code>LookupOp</code> object (lookup table)
     */
    private LookupOp setColors(Color bgColor, Color fgColor) {
        short[] a = new short[256];
        short[] r = new short[256];
        short[] g = new short[256];
        short[] b = new short[256];

        byte bga = (byte) (bgColor.getAlpha());
        byte bgr = (byte) (bgColor.getRed());
        byte bgg = (byte) (bgColor.getGreen());
        byte bgb = (byte) (bgColor.getBlue());

        byte fga = (byte) (fgColor.getAlpha());
        byte fgr = (byte) (fgColor.getRed());
        byte fgg = (byte) (fgColor.getGreen());
        byte fgb = (byte) (fgColor.getBlue());

        for (int i = 0; i < 256; i++) {
            if (i == 0) {
                a[i] = bga;
                r[i] = bgr;
                g[i] = bgg;
                b[i] = bgb;
            } else {
                a[i] = fga;
                r[i] = fgr;
                g[i] = fgg;
                b[i] = fgb;
            }
        }

        short[][] table = {r, g, b, a};
        return new LookupOp(new ShortLookupTable(0, table), null);
    }

    /**
     * Clear the entire screen to whatever the default background color is.
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel clear() {
        return clear(' ', 0, 0, getPanelWidthInCharacters(), getPanelHeightInCharacters(), getDefaultForegroundColor(), getDefaultBackgroundColor());
    }

    /**
     * Clear the entire screen with the specified character and whatever the default foreground and background colors are.
     * @param character  the character to write
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel clear(char character) {
        if (character < 0 || character >= getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        return clear(character, 0, 0, getPanelWidthInCharacters(), getPanelHeightInCharacters(), getDefaultForegroundColor(), getDefaultBackgroundColor());
    }

    /**
     * Clear the entire screen with the specified character and whatever the specified foreground and background colors are.
     * @param character  the character to write
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel clear(char character, Color foreground, Color background) {
        if (character < 0 || character >= getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        return clear(character, 0, 0, getPanelWidthInCharacters(), getPanelHeightInCharacters(), foreground, background);
    }

    /**
     * Clear the section of the screen with the specified character and whatever the default foreground and background colors are.
     * The cursor position will not be modified.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param width      the height of the section to clear
     * @param height     the width of the section to clear
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel clear(char character, int x, int y, int width, int height) {
        if (character < 0 || character >= getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        if (x < 0 || x >= getPanelWidthInCharacters())
            throw new IllegalArgumentException("x " + x + " must be within range [0," + getPanelWidthInCharacters() + ")." );

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")." );

        if (width < 1)
            throw new IllegalArgumentException("width " + width + " must be greater than 0." );

        if (height < 1)
            throw new IllegalArgumentException("height " + height + " must be greater than 0." );

        if (x + width > getPanelWidthInCharacters())
            throw new IllegalArgumentException("x + width " + (x + width) + " must be less than " + (getPanelWidthInCharacters() + 1) + "." );

        if (y + height > getPanelHeightInCharacters())
            throw new IllegalArgumentException("y + height " + (y + height) + " must be less than " + (getPanelHeightInCharacters() + 1) + "." );


        return clear(character, x, y, width, height, getDefaultForegroundColor(), getDefaultBackgroundColor());
    }

    /**
     * Clear the section of the screen with the specified character and whatever the specified foreground and background colors are.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param width      the height of the section to clear
     * @param height     the width of the section to clear
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel clear(char character, int x, int y, int width, int height, Color foreground, Color background) {
        if (character < 0 || character >= getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        if (x < 0 || x >= getPanelWidthInCharacters())
            throw new IllegalArgumentException("x " + x + " must be within range [0," + getPanelWidthInCharacters() + ")" );

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")" );

        if (width < 1)
            throw new IllegalArgumentException("width " + width + " must be greater than 0." );

        if (height < 1)
            throw new IllegalArgumentException("height " + height + " must be greater than 0." );

        if (x + width > getPanelWidthInCharacters())
            throw new IllegalArgumentException("x + width " + (x + width) + " must be less than " + (getPanelWidthInCharacters() + 1) + "." );

        if (y + height > getPanelHeightInCharacters())
            throw new IllegalArgumentException("y + height " + (y + height) + " must be less than " + (getPanelHeightInCharacters() + 1) + "." );

        int originalCursorX = getCursorDistanceFromLeft();
        int originalCursorY = getCursorDistanceFromTop();
        for (int xo = x; xo < x + width; xo++) {
            for (int yo = y; yo < y + height; yo++) {
                write(character, xo, yo, foreground, background);
            }
        }
        setCursorDistanceFromLeft(originalCursorX);
        setCursorDistanceFromTop(originalCursorY);

        return this;
    }

    /**
     * Write a character to the cursor's position.
     * This updates the cursor's position.
     * @param character  the character to write
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(char character) {
        if (character < 0 || character > getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        return write(character, getCursorDistanceFromLeft(), getCursorDistanceFromTop(), getDefaultForegroundColor(), getDefaultBackgroundColor());
    }

    /**
     * Write a character to the cursor's position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param character  the character to write
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(char character, Color foreground) {
        if (character < 0 || character >= getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        return write(character, getCursorDistanceFromLeft(), getCursorDistanceFromTop(), foreground, getDefaultBackgroundColor());
    }

    /**
     * Write a character to the cursor's position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param character  the character to write
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(char character, Color foreground, Color background) {
        if (character < 0 || character >= getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        return write(character, getCursorDistanceFromLeft(), getCursorDistanceFromTop(), foreground, background);
    }

    /**
     * Write a character to the specified position.
     * This updates the cursor's position.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(char character, int x, int y) {
        if (character < 0 || character >= getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        if (x < 0 || x >= getPanelWidthInCharacters())
            throw new IllegalArgumentException("x " + x + " must be within range [0," + getPanelWidthInCharacters() + ")" );

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")" );

        return write(character, x, y, getDefaultForegroundColor(), getDefaultBackgroundColor());
    }

    /**
     * Write a character to the specified position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(char character, int x, int y, Color foreground) {
        if (character < 0 || character >= getGlyphsList().length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + getGlyphsList().length + "]." );

        if (x < 0 || x >= getPanelWidthInCharacters())
            throw new IllegalArgumentException("x " + x + " must be within range [0," + getPanelWidthInCharacters() + ")" );

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")" );

        return write(character, x, y, foreground, getDefaultBackgroundColor());
    }

    /**
     * Write a character to the specified position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(char character, int x, int y, Color foreground, Color background) {
        
    	if (foreground == null) foreground = getDefaultForegroundColor();
        if (background == null) background = getDefaultBackgroundColor();

    	if (character >= 0 && character < getGlyphsList().length)
      //      throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        if (x >= 0 && x < getPanelWidthInCharacters())
        //    throw new IllegalArgumentException("x " + x + " must be within range [0," + getPanelWidthInCharacters() + ")" );

        if (y >= 0 && y < getPanelHeightInCharacters())
          //  throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")" );
        {
        
        getPanelCharsMatrix()[x][y] = character;
        getPanelCharsForegroundColors()[x][y] = foreground;
        getPanelCharsBackgroundColors()[x][y] = background;
        setCursorDistanceFromLeft(x + 1);
        setCursorDistanceFromTop(y);
        }
        return this;
    }
    
    /**
     * The method is used for the filler functionality of the application. It fills the drawing canvas with the selected character.
	 * @see asciiart.imageeditor.ImageEditor#onFill(int button) class that calls this method when the fill functionality is chosen.
     * @param character chosen
     * @param x coordinate from mouse cursor
     * @param y coordinate from mouse cursor
     * @param fc is the character color
     * @param bc is the background character color
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel fill(char character,int x,int y,Color fc,Color bc) {
    	char oldchar = getPanelCharsMatrix()[x][y];
    	if (oldchar != character) {
	        getPanelCharsMatrix()[x][y] = character;
	        getPanelCharsForegroundColors()[x][y] = fc;
	        getPanelCharsBackgroundColors()[x][y] = bc;
	        int diff = 0;
	        if (x < getPanelWidthInCharacters() -1) {
	        	diff = Math.abs(getPanelCharsMatrix()[x+1][y] - oldchar);
	        	if (diff >= 0 && diff <= 3)
	        		fill(character, x+1, y, fc, bc);
	        }
	        if (x > 0) {
	        	diff = Math.abs(getPanelCharsMatrix()[x-1][y] - oldchar);
	        	if (diff >= 0 && diff <= 3)
	        		fill(character, x-1, y, fc, bc);
	        }
	        if (y > 0) {
	        	diff = Math.abs(getPanelCharsMatrix()[x][y-1] - oldchar);
	        	if (diff >= 0 && diff <= 3)
	        		fill(character, x, y-1, fc, bc);
	        }
	        if (y < getPanelHeightInCharacters()-1) {
	        	diff = Math.abs(getPanelCharsMatrix()[x][y+1] - oldchar);
	        	if (diff >= 0 && diff <= 3)
	        		fill(character, x, y+1, fc, bc);
	        }
    	}
        return this;
    }
    /**
     * Write a string to the cursor's position.
     * This updates the cursor's position.
     * @param string     the string to write
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(String string) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (getCursorDistanceFromLeft() + string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("cursorX + string.length() " + (getCursorDistanceFromLeft() + string.length()) + " must be less than " + getPanelWidthInCharacters() + ".");

        return write(string, getCursorDistanceFromLeft(), getCursorDistanceFromTop(), getDefaultForegroundColor(), getDefaultBackgroundColor());
    }

    /**
     * Write a string to the cursor's position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param string     the string to write
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(String string, Color foreground) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (getCursorDistanceFromLeft() + string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("cursorX + string.length() " + (getCursorDistanceFromLeft() + string.length()) + " must be less than " + getPanelWidthInCharacters() + "." );

        return write(string, getCursorDistanceFromLeft(), getCursorDistanceFromTop(), foreground, getDefaultBackgroundColor());
    }

    /**
     * Write a string to the cursor's position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param string     the string to write
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(String string, Color foreground, Color background) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (getCursorDistanceFromLeft() + string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("cursorX + string.length() " + (getCursorDistanceFromLeft() + string.length()) + " must be less than " + getPanelWidthInCharacters() + "." );

        return write(string, getCursorDistanceFromLeft(), getCursorDistanceFromTop(), foreground, background);
    }

    /**
     * Write a string to the specified position.
     * This updates the cursor's position.
     * @param string     the string to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(String string, int x, int y) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (x + string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("x + string.length() " + (x + string.length()) + " must be less than " + getPanelWidthInCharacters() + "." );

        if (x < 0 || x >= getPanelWidthInCharacters())
            throw new IllegalArgumentException("x " + x + " must be within range [0," + getPanelWidthInCharacters() + ")" );

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")" );

        return write(string, x, y, getDefaultForegroundColor(), getDefaultBackgroundColor());
    }

    /**
     * Write a string to the specified position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param string     the string to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(String string, int x, int y, Color foreground) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (x + string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("x + string.length() " + (x + string.length()) + " must be less than " + getPanelWidthInCharacters() + "." );

        if (x < 0 || x >= getPanelWidthInCharacters())
            throw new IllegalArgumentException("x " + x + " must be within range [0," + getPanelWidthInCharacters() + ")" );

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")" );

        return write(string, x, y, foreground, getDefaultBackgroundColor());
    }

    /**
     * Write a string to the specified position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param string     the string to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel write(String string, int x, int y, Color foreground, Color background) {
        if (string == null)
            throw new NullPointerException("string must not be null." );
        
        if (x + string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("x + string.length() " + (x + string.length()) + " must be less than " + getPanelWidthInCharacters() + "." );

        if (x < 0 || x >= getPanelWidthInCharacters())
            throw new IllegalArgumentException("x " + x + " must be within range [0," + getPanelWidthInCharacters() + ")." );

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")." );

        if (foreground == null)
            foreground = getDefaultForegroundColor();

        if (background == null)
            background = getDefaultBackgroundColor();

        for (int i = 0; i < string.length(); i++) {
            write(string.charAt(i), x + i, y, foreground, background);
        }
        return this;
    }

    /**
     * Write a string to the center of the panel at the specified y position.
     * This updates the cursor's position.
     * @param string     the string to write
     * @param y          the distance from the top to begin writing from
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel writeCenter(String string, int y) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("string.length() " + string.length() + " must be less than " + getPanelWidthInCharacters() + "." );

        int x = (getPanelWidthInCharacters() - string.length()) / 2;

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")" );

        return write(string, x, y, getDefaultForegroundColor(), getDefaultBackgroundColor());
    }

    /**
     * Write a string to the center of the panel at the specified y position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param string     the string to write
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel writeCenter(String string, int y, Color foreground) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("string.length() " + string.length() + " must be less than " + getPanelWidthInCharacters() + "." );

        int x = (getPanelWidthInCharacters() - string.length()) / 2;

        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")" );

        return write(string, x, y, foreground, getDefaultBackgroundColor());
    }

    /**
     * Write a string to the center of the panel at the specified y position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param string     the string to write
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public AsciiPanel writeCenter(String string, int y, Color foreground, Color background) {
        if (string == null)
            throw new NullPointerException("string must not be null." );

        if (string.length() > getPanelWidthInCharacters())
            throw new IllegalArgumentException("string.length() " + string.length() + " must be less than " + getPanelWidthInCharacters() + "." );

        int x = (getPanelWidthInCharacters() - string.length()) / 2;
        
        if (y < 0 || y >= getPanelHeightInCharacters())
            throw new IllegalArgumentException("y " + y + " must be within range [0," + getPanelHeightInCharacters() + ")." );

        if (foreground == null)
            foreground = getDefaultForegroundColor();

        if (background == null)
            background = getDefaultBackgroundColor();

        for (int i = 0; i < string.length(); i++) {
            write(string.charAt(i), x + i, y, foreground, background);
        }
        return this;
    }

    /**
     * This method seems to be a sort of "wrapper method" to perform some tile transformation in the panel using the interface asciiart.asciipanel.TileTransformer.
	 * However, it is never called or used in the application.
	 * 
     * @param transformer is the interface containing the method with the behaviour for the transformation
     */
    public void withEachTile(TileTransformer transformer){
		withEachTile(0, 0, getPanelWidthInCharacters(), getPanelHeightInCharacters(), transformer);
    }

    /**
     * This method seems to be the effective implementation of some tile transformation in the application. 
     * However, it is never called or used within the application.
     * 
     * @param left is the left distance(?)
     * @param top is the top distance(?)
     * @param width is the width(?)
     * @param height is the height(?)
     * @param transformer is the interface containing the method with the behaviour for the transformation
     */
    public void withEachTile(int left, int top, int width, int height, TileTransformer transformer) {
		AsciiCharacterData data = new AsciiCharacterData();
		
    	for (int x0 = 0; x0 < width; x0++)
    	for (int y0 = 0; y0 < height; y0++) {
    		int x = left + x0;
    		int y = top + y0;
    		
    		if (x < 0 || y < 0 || x >= getPanelWidthInCharacters() || y >= getPanelHeightInCharacters())
    			continue;
    		
    		data.setCharacter(getPanelCharsMatrix()[x][y]);
    		data.setCharacterForegroundColor(getPanelCharsForegroundColors()[x][y]);
    		data.setCharacterBackgroundColor(getPanelCharsBackgroundColors()[x][y]);
    		
    		transformer.transformTile(x, y, data);
    		
    		getPanelCharsMatrix()[x][y] = data.getCharacter();
    		getPanelCharsForegroundColors()[x][y] = data.getCharacterForegroundColor();
    		getPanelCharsBackgroundColors()[x][y] = data.getCharacterBackgroundColor();
    	}
    }
    
    /**
	 * Acquires a character from the drawing canvas by <i>x</i> and <i>y</i> char coordinates
	 * @param px is the character <i>x</i>-coordinate
	 * @param py is the character <i>y</i>-coordinate
	 * @return the index of character selected by [x][y]
	 */
	public Integer pickPanelCharIndex(int px, int py) {
		return new Integer(getPanelCharsMatrix()[px][py]);
	}

	/**
	 * Acquires the character foreground color from the drawing canvas by <i>x</i> and <i>y</i> char coordinates
	 * @param px is the character <i>x</i>-coordinate
	 * @param py is the character <i>y</i>-coordinate
	 * @return the foreground color of character selected by [x][y]
	 */
	public Color pickPanelCharsForegroundColors(int px, int py) {
		return getPanelCharsForegroundColors()[px][py];
	}

	/**
	 * Acquires the character background color from the drawing canvas by <i>x</i> and <i>y</i> char coordinates
	 * @param px is the character <i>x</i>-coordinate
	 * @param py is the character <i>y</i>-coordinate
	 * @return the background color of character selected by [x][y]
	 */
	public Color pickPanelCharsBackgroundColors(int px, int py) {
		return getPanelCharsBackgroundColors()[px][py];
	}

	/**
	 * Draws the raster of the image to be loaded into the drawing canvas.
	 * 
	 * @see asciiart.imageeditor.ActionLoad#actionPerformed(ActionEvent e) which is the method that call this with image to load.
	 * 
	 * @param raster is the image raster to draw
	 * @param x is the <i>x</i>-coordinate to start to draw
	 * @param y is the <i>y</i>-coordinate to start to draw
	 * @param transparent is a boolean to understand if you want to draw using transparency 
	 * @return this for convenient chaining of method calls
	 */
	public AsciiPanel paintRaster(AsciiRaster raster,int x,int y,boolean transparent) {
		
		int dx=0;
		int dy=0;
		if (x<0) dx=-x;
		if (y<0) dy=-y;
		int sx=dx;
		int sy=dy;
		for (int xi=x+dx;xi<getPanelWidthInCharacters();xi++) {
			sy=dy;
			for (int yi=y+dy;yi<getPanelHeightInCharacters();yi++) {
				
				char nc=raster.getRasterCharsMatrix()[sx][sy];
					
				if (nc!=0||!transparent) {
					getPanelCharsMatrix()[xi][yi]=nc;
					getPanelCharsForegroundColors()[xi][yi]=raster.getRasterCharsForegroundColors()[sx][sy];
					getPanelCharsBackgroundColors()[xi][yi]=raster.getRasterCharsBackgroundColors()[sx][sy];
				}
				sy++;
				if (sy==raster.getRasterLimitY()) break;
			}
		sx++;
		if (sx==raster.getRasterLimitX()) break;
			
		}
		return this;
	}

	/**
	 * The black color getter
	 * @return the black color
	 */
	public static Color getBlack() {
		return black;
	}

	/**
	 * The white color getter
	 * @return the white color
	 */
	public static Color getWhite()	{
		return white;
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
     * Gets the width, in pixels, of a character.
     * @return characters width
     */
    public int getCharWidth() {
        return charWidth;
    }
	/**
	 * The char width setter
	 * @param charWidth is the char width to set
	 */
	private void setCharWidth(int charWidth) {
		this.charWidth = charWidth;
	}

    /**
     * Gets the height, in pixels, of a character.
     * @return characters height
     */
    public int getCharHeight() {
        return charHeight;
    }

	/**
	 * The char height setter
	 * @param charHeight is the char height to set
	 */
	private void setCharHeight(int charHeight) {
		this.charHeight = charHeight;
	}

    /**
     * Gets the width in characters.
     * A standard terminal is 80 characters wide.
     * @return the width in characters
     */
    public int getPanelWidthInCharacters() {
        return panelWidthInCharacters;
    }

	/**
	 * The panel with in characters setter
	 * @param panelWidthInCharacters is the panel width in characters to set
	 */
	private void setPanelWidthInCharacters(int panelWidthInCharacters)	{
		this.panelWidthInCharacters = panelWidthInCharacters;
	}

    /**
     * Gets the height in characters.
     * A standard terminal is 24 characters high.
     * @return the height in characters
     */
    public int getPanelHeightInCharacters() {
        return panelHeightInCharacters;
    }

	/**
	 * The panel height in characters setter
	 * @param panelHeightInCharacters is the the panel height in characters to set
	 */
	private void setPanelHeightInCharacters(int panelHeightInCharacters) {
		this.panelHeightInCharacters = panelHeightInCharacters;
	}

    /**
     * Gets the distance from the left new text will be written to.
     * @return the distance from the left
     */
    public int getCursorDistanceFromLeft() {
        return cursorDistanceFromLeft;
    }

    /**
     * Sets the distance from the left new text will be written to.
     * This should be equal to or greater than 0 and less than the the width in characters.
     * @param distance the distance from the left new text should be written to
     */
    public void setCursorDistanceFromLeft(int distance) {
        if (distance >= 0 && distance < getPanelWidthInCharacters())
      //      throw new IllegalArgumentException("cursorX " + cursorX + " must be within range [0," + widthInCharacters + ")." );

        this.cursorDistanceFromLeft = distance;
    }

    /**
     * Gets the distance from the top new text will be written to.
     * @return the distance from top
     */
    public int getCursorDistanceFromTop() {
        return cursorDistanceFromTop;
    }

    /**
     * Sets the distance from the top new text will be written to.
     * This should be equal to or greater than 0 and less than the the height in characters.
     * @param distance the distance from the top new text should be written to
     */
    public void setCursorDistanceFromTop(int distance) {
        if (distance >= 0 && distance < getPanelHeightInCharacters())
           
        this.cursorDistanceFromTop = distance;
    }

    /**
     * Sets the x and y position of where new text will be written to. The origin (0,0) is the upper left corner.
     * The x should be equal to or greater than 0 and less than the the width in characters.
     * The y should be equal to or greater than 0 and less than the the height in characters.
     * @param x the distance from the left new text should be written to
     * @param y the distance from the top new text should be written to
     */
    public void setCursorPosition(int x, int y) {
        setCursorDistanceFromLeft(x);
        setCursorDistanceFromTop(y);
    }

    /**
     * Gets the default background color that is used when writing new text.
     * @return the default background color
     */
    public Color getDefaultBackgroundColor() {
        return defaultBackgroundColor;
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
     * Gets the currently selected font
     * @return the font
     */
    public AsciiFont getAsciiFont() {
        return asciiFont;
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
     * Coordinate <i>x</i> of mouse cursor getter
     * 
     * @return the mouse cursor<i>x</i>-coordinate
     */
	public int getMouseCursorX() {
		return mouseCursorX;
	}

    /**
     * Coordinate <i>x</i> of mouse cursor setter
     * 
     * @param x is the mouse cursor for <i>x</i>-coordinate
     */
	public void setMouseCursorX(int x) {
		mouseCursorX=x;
	}

    /**
     * Coordinate <i>y</i> of mouse cursor getter
     * 
     * @return the mouse cursor<i>y</i>-coordinate
     */
	public int getMouseCursorY() {
		return mouseCursorY;
	}

    /**
     * Coordinate <i>y</i> of mouse cursor setter
     * 
     * @param y is the mouse cursor for <i>y</i>-coordinate
     */
	public void setMouseCursorY(int y) {
		mouseCursorY=y;
	}

	/**
	 * The chars matrix [x][y] of entire drawing space getter
	 * @return the chars matrix
	 */
	public char[][] getPanelCharsMatrix() {
		return panelCharsMatrix;
	}

	/**
	 * The chars matrix [x][y] of entire drawing space setter
	 * @param chars is the char matrix to set
	 */
	public void setPanelCharsMatrix(char[][] chars) {
		this.panelCharsMatrix = chars;
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

	/**
	 * The panel chars background colors getter
	 * @return the panel chars background color
	 */
	private Color[][] getPanelCharsBackgroundColors() {
		return panelCharsBackgroundColors;
	}

	/**
	 * The panel chars background colors setter
	 * @param panelCharsBackgroundColors is the background colors to set
	 */
	private void setPanelCharsBackgroundColors(Color[][] panelCharsBackgroundColors) {
		this.panelCharsBackgroundColors = panelCharsBackgroundColors;
	}

	/**
	 * The panel chars foreground colors getter
	 * @return the panel chars foreground colors
	 */
	private Color[][] getPanelCharsForegroundColors() {
		return panelCharsForegroundColors;
	}

	/**
	 * The panel chars foreground colors setter
	 * @param panelCharsForegroundColors is the foreground colors to set
	 */
	private void setPanelCharsForegroundColors(Color[][] panelCharsForegroundColors) {
		this.panelCharsForegroundColors = panelCharsForegroundColors;
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
	 * The old panel chars foreground colors setter
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

}
