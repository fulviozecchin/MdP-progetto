package com.unitelmasapienza.asciiart.asciipanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The class consists of an array of cells (or pixels) organized in rows and columns (or a grid) 
 * where <b>each cell contains a value representing information</b>, such as character, foreground color and background color.
 * It is used for parsing a drawing to be loaded into the application, 
 * from which it is then copied/written to the canvas.
 * 
 * @see com.unitelmasapienza.asciiart.imageeditor.ActionLoad#actionPerformed(ActionEvent e) which use it to load an Ascii file into canvas.
 * 
 * @author Fulvio Zecchin
 *
 */
public class AsciiRaster {

	/**
	 * Is the chars matrix. 
	 * Can access to character by [x][y] coordinates
	 * 
	 */
	private char[][] rasterCharsMatrix;

	/**
	 * Is the foreground color information for the character in [x][y] coordinates.
	 * 
	 */
	private Color[][] rasterCharsForegroundColors;

	/**
	 * Is the background color information for the character in [x][y] coordinates.
	 * 
	 */
	private Color[][] rasterCharsBackgroundColors;

	/**
	 * Is the maximum <i>x</i>-coordinate of the raster.
	 * It represents the horizontal limit of raster.
	 * 
	 */
	private int rasterLimitX;

	/**
	 * Is the maximum <i>y</i>-coordinate of the raster.
	 * It represents the vertical limit of raster.
	 * 
	 */
	private int rasterLimitY;

	/**
	 * Public raster constructor.
	 * This is only to set raster matrix dimensions and foreground and backgroung colors.
	 * @param limitX is maximum <i>x</i>-coordinate (horizontal dimension of raster)
	 * @param limitY is maximum <i>y</i>-coordinate (vertical dimension of raster)
	 */
	public AsciiRaster(int limitX, int limitY) {
		setRasterLimitX(limitX);
		setRasterLimitY(limitY);
		setRasterCharsMatrix(new char[limitX][limitY]);
		setRasterCharsForegroundColors(new Color[limitX][limitY]);
		setRasterCharsBackgroundColors(new Color[limitX][limitY]);
	}

	/**
	 * This method can be seen as a "constructor" which is called from other classes. 
	 * It creates and returns an AsciiRaster from the absolute path of a file (this filename) to be loaded.
	 * 
	 * @see com.unitelmasapienza.asciiart.imageeditor.ActionLoad#actionPerformed(ActionEvent e) which call to load an Ascii image into drawing canvas.
	 * @param filename is the filename to be loaded (its absolute path in the file system will be retrieved)
	 * @return an AsciiRaster object containing all the information of the image to be uploaded.
	 */
	public static AsciiRaster createRasterFromFile(String filename) {
		BufferedReader br;
		AsciiRaster raster = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			
			int limitX = Integer.parseInt(br.readLine());
			int limitY = Integer.parseInt(br.readLine());
			raster = new AsciiRaster(limitX,limitY);
			int x = 0;
			int y = 0;
			while (br.ready()) {
				//System.out.println("Loading x:"+x+"/sx:"+sx+" y:"+y+"/ sy:"+sy);
				String line = br.readLine();
				String[] lines = line.split("\t");
				Color foregroundColor = new Color(Integer.parseInt(lines[1]));
				Color backgroundColor = new Color(Integer.parseInt(lines[2]));
				char character = (char)Integer.parseInt(lines[0]);
				raster.getRasterCharsMatrix()[y][x] = character;
				raster.getRasterCharsForegroundColors()[y][x] = foregroundColor;
				raster.getRasterCharsBackgroundColors()[y][x] = backgroundColor;
				x++;
				if (x == limitY) {
					x = 0;
					y++;
					if (y == limitX) break;
				}
			}
			br.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return raster;
	}

	/**
	 * The last <i>x</i>-coordinate of raster getter
	 * @return an int which represent maximum <i>x</i>-coordinate of the raster
	 */
	public int getRasterLimitX() {
		return rasterLimitX;
	}

	/**
	 * The last <i>x</i>-coordinate of raster setter
	 * @param limitX is an int which represent maximum <i>x</i>-coordinate of the raster
	 */
	public void setRasterLimitX(int limitX) {
		this.rasterLimitX = limitX;
	}
	
	/**
	 * The last <i>y</i>-coordinate of raster getter
	 * @return an int which represent maximum <i>y</i>-coordinate of the raster
	 */
	public int getRasterLimitY() {
		return rasterLimitY;
	}

	/**
	 * The last <i>y</i>-coordinate of raster setter
	 * @param limitY is an int which represent maximum <i>y</i>-coordinate of the raster
	 */
	public void setRasterLimitY(int limitY) {
		this.rasterLimitY = limitY;
	}

	/**
	 * The matrix of raster characters getter.
	 * Can access to specific character with [x][y] values coordinates
	 * @return the matrix of raster characters
	 */
	public char[][] getRasterCharsMatrix() {
		return rasterCharsMatrix;
	}

	/**
	 * The matrix of raster characters setter.
	 * @param the matrix of raster characters
	 */
	private void setRasterCharsMatrix(char[][] chars) {
		this.rasterCharsMatrix = chars;
	}

	/**
	 * The matrix of raster foreground colors getter.
	 * Can access to specific foreground colors with [x][y] values coordinates
	 * @return the matrix of foreground colors
	 */
	public Color[][] getRasterCharsForegroundColors() {
		return rasterCharsForegroundColors;
	}

	/**
	 * The matrix of raster foreground colors setter.
	 * Can access to specific foreground colors with [x][y] values coordinates
	 * @param the matrix of foreground colors
	 */
	private void setRasterCharsForegroundColors(Color[][] forecolors) {
		this.rasterCharsForegroundColors = forecolors;
	}
	
	/**
	 * The matrix of raster background colors getter.
	 * Can access to specific background colors with [x][y] values coordinates
	 * @return the matrix of foreground colors
	 */
	public Color[][] getRasterCharsBackgroundColors() {
		return rasterCharsBackgroundColors;
	}

	/**
	 * The matrix of raster background colors setter.
	 * Can access to specific foreground colors with [x][y] values coordinates
	 * @param the matrix of background colors
	 */
	private void setRasterCharsBackgroundColors(Color[][] backcolors) {
		this.rasterCharsBackgroundColors = backcolors;
	}

}
