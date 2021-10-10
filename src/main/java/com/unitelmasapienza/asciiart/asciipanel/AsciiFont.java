package com.unitelmasapienza.asciiart.asciipanel;

/**
 * This class holds provides <b>all available Fonts for the AsciiPanel</b>.
 * Some graphics are from the Dwarf Fortress Tileset Wiki Page
 * 
 * @author zn80
 *
 */
public class AsciiFont {

	/**
	 * Here is a list of default fonts present in application.
	 * 
	 */
	public static final AsciiFont CP437_8x8 = new AsciiFont("cp437_8x8.png", 8, 8);
	public static final AsciiFont CP437_10x10 = new AsciiFont("cp437_10x10.png", 10, 10);
	public static final AsciiFont CP437_12x12 = new AsciiFont("cp437_12x12.png", 12, 12);
	public static final AsciiFont CP437_16x16 = new AsciiFont("cp437_16x16.png", 16, 16);
	public static final AsciiFont CP437_9x16 = new AsciiFont("cp437_9x16.png", 9, 16);
	public static final AsciiFont DRAKE_10x10 = new AsciiFont("drake_10x10.png", 10, 10);
	public static final AsciiFont TAFFER_10x10 = new AsciiFont("taffer_10x10.png", 10, 10);
	public static final AsciiFont QBICFEET_10x10 = new AsciiFont("qbicfeet_10x10.png", 10, 10);
	public static final AsciiFont TALRYTH_15_15 = new AsciiFont("talryth_square_15x15.png", 15, 15);

	/**
	 * Font filename value
	 * 
	 */
	private String fontFilename;

	/**
	 * Font width size
	 * 
	 */
	private int fontWidth;

	/**
	 * Font height size
	 * 
	 */
	private int fontHeight;

	/**
	 * Public Font constructor
	 * @param filename is the Font filename
	 * @param width is the width of font
	 * @param height is the height of font
	 */
	public AsciiFont(String filename, int width, int height) {
		this.fontFilename = filename;
		this.fontWidth = width;
		this.fontHeight = height;
	}


	/**
	 * Font filename getter
	 * @return font filename
	 */
	public String getFontFilename() {
		return fontFilename;
	}

	/**
	 * Font width getter
	 * @return value of Font width
	 */
	public int getFontWidth() {
		return fontWidth;
	}

	/**
	 * Font height getter
	 * @return value of Font height
	 */
	public int getFontHeight() {
		return fontHeight;
	}
}
