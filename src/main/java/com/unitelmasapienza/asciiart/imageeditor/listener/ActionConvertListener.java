package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.Color;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;
import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;
import com.unitelmasapienza.asciiart.imageeditor.view.ImporterView;

/**
 * The class represents the action listener for the <b>image conversion functionality</b>, after the image has been imported.
 * 
 * @see ImageEditorView which represents the main view class for the application 
 *      and calls the importing action at the click of the <b>Import...</b> button under <i>File</i> menu.
 * @see ImporterView which represents the frame for image importing that containing 
 *      the <b>Convert</b> button to which this Listener is linked.
 *      It also provides to create the present class 
 *      passing him the values that come inserted in the relative importing frame.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ActionConvertListener implements ActionListener {

	/**
	 * The canvas/panel into which the imported image will be converted and drawn
	 * 
	 */
	private AsciiPanel asciiPanel;
	
	/**
	 * The textbox object representing the <b>threshold</b> in the imported image 
	 * 
	 */
	private JTextField thresholdTextbox;
	
	/**
	 * The checkbox object for enabling/disabling <b>all colors</b> in the imported image
	 * 
	 */
	private JToggleButton allColorsCheckbox;

	/**
	 * The public constructor of the class. 
	 * It is called by passing the values read from the image importing frame.
	 * @param asciiPanel is the main canvas/panel into which the chosen image will be converted
	 * @param thresholdTextbox is the threshold object read by the importing frame
	 * @param allColorsCheckbox is the enable/disable button object read by the importing frame
	 */
	public ActionConvertListener(AsciiPanel asciiPanel, JTextField thresholdTextbox, JCheckBox allColorsCheckbox) {
		this.setAsciiPanel(asciiPanel);
		this.setThresholdTextbox(thresholdTextbox);
		this.setAllColorsCheckbox(allColorsCheckbox);
	}

	/**
	 * Describes the behavior when is clicked the <i>Convert</i> button from 
	 * <i>ImageImporter</i> frame.
	 * 
	 * If the input values are populated and correct, it converts the chosen image to an ascii image and draws it 
	 * (in color or not depending on the value of the <i>tb</i> checkbox) in the main canvas/panel.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		BufferedImage bufferedImg = ImageEditorController.getInstance().getView().getImportBI();

		System.out.println("Convert Pressed..");
		if (bufferedImg != null) {
			getAsciiPanel().clear();
			int thresholdValue = Integer.parseInt(getThresholdTextbox().getText());
			BufferedImage[] glyphsList = getAsciiPanel().getGlyphsList();

			Map<Integer, Integer> index2numpixels = new HashMap<Integer, Integer>();
			for (int i = 0; i < glyphsList.length; i++) {
				BufferedImage bi = glyphsList[i];
				if (bi == null)
					continue;
				int tot = 0;
				for (int x = 0; x < bi.getWidth(); x++)
					for (int y = 0; y < bi.getHeight(); y++) {
						int c = bi.getRGB(x, y);
						Color color = new Color(c, false);
						if (color.getRed() > 0 || color.getGreen() > 0 || color.getBlue() > 0)
							tot++;
					}
				int key = (int) ((float) (tot) / (float) (bi.getWidth() * bi.getHeight()) * 255.f);
				if (!index2numpixels.containsKey(key))
					index2numpixels.put(key, i);

			}

			int[][] bufferMatrix = new int[bufferedImg.getWidth()][bufferedImg.getHeight()];
			// BufferedImage bi=ImageIO.read(new
			// File("resources/bitmaps/80x50Stefano.png"));
			for (int x = 0; x < bufferedImg.getWidth(); x++)
				for (int y = 0; y < bufferedImg.getHeight(); y++) {
					int c = bufferedImg.getRGB(x, y);
					Color color = new Color(c);
					int ri = Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue());
					// if (cc.getRed()>0||cc.getGreen()>0||cc.getBlue()>0)
					bufferMatrix[x][y] = Math.min(ri, thresholdValue);
				}

			if (!getAllColorsCheckbox().isSelected()) {
				BufferedImage convertedImg = convert4BitDefaultColors(bufferedImg);
				bufferedImg = convertedImg;
			}
			for (int x = 0; x < Math.min(bufferedImg.getWidth(), 80); x++)
				for (int y = 0; y < Math.min(bufferedImg.getHeight(), 80); y++) {
					int k = 255 - bufferMatrix[x][y];
					// System.out.println("**"+k);
					while (!index2numpixels.containsKey(k) && k > 0) {
						k--;
					}
					getAsciiPanel().setCursorDistanceFromLeft(x);
					getAsciiPanel().setCursorDistanceFromTop(y);
					int c = bufferedImg.getRGB(x, y);
					Color color = new Color(c);
					getAsciiPanel().write((char) k, color);

				}
			getAsciiPanel().repaint();
			ImporterView.getInstance().setVisible(false);
			ImporterView.getInstance().close();
		}
	}

	/**
	 * Converts the source image to 4-bit colour using the default 16-colour
	 * palette:
	 * <ul>
	 * <li>black</li>
	 * <li>dark red</li>
	 * <li>dark green</li>
	 * <li>dark yellow</li>
	 * <li>dark blue</li>
	 * <li>dark magenta</li>
	 * <li>dark cyan</li>
	 * <li>dark grey</li>
	 * <li>light grey</li>
	 * <li>red</li>
	 * <li>green</li>
	 * <li>yellow</li>
	 * <li>blue</li>
	 * <li>magenta</li>
	 * <li>cyan</li>
	 * <li>white</li>
	 * </ul>
	 * No transparency.
	 * 
	 * @param src the source image to convert
	 * @return a copy of the source image with a 4-bit colour depth, with the
	 *         default colour pallette
	 */
	public static BufferedImage convert4BitDefaultColors(BufferedImage src) {
		int[] cmap = new int[] { 0x000000, 0x800000, 0x008000, 0x808000, 0x000080, 0x800080, 0x008080, 0x808080,
				0xC0C0C0, 0xFF0000, 0x00FF00, 0xFFFF00, 0x0000FF, 0xFF00FF, 0x00FFFF, 0xFFFFFF };
		return convert4BitGivenColors(src, cmap);
	}

	/**
	 * Converts the source image to 4-bit colour using the given colour map. No
	 * transparency.
	 * 
	 * @param src  the source image to convert
	 * @param cmap the colour map, which should contain no more than 16 entries The
	 *             entries are in the form RRGGBB (hex).
	 * @return a copy of the source image with a 4-bit colour depth, with the custom
	 *         colour pallette
	 */
	public static BufferedImage convert4BitGivenColors(BufferedImage src, int[] cmap) {
		IndexColorModel icm = new IndexColorModel(4, cmap.length, cmap, 0, false, Transparency.OPAQUE,
				DataBuffer.TYPE_BYTE);
		BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_BINARY, icm);
		ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(),
				dest.getColorModel().getColorSpace(), null);
		cco.filter(src, dest);

		return dest;
	}

	/**
	 * The ascii panel getter
	 * @return the asciiPanel
	 */
	public AsciiPanel getAsciiPanel() {
		return asciiPanel;
	}

	/**
	 * The asciiPanel setter
	 * @param asciiPanel the ascii panel to set
	 */
	public void setAsciiPanel(AsciiPanel asciiPanel) {
		this.asciiPanel = asciiPanel;
	}

	/**
	 * The threshold textbox getter
	 * @return the thresholdTextbox
	 */
	public JTextField getThresholdTextbox() {
		return thresholdTextbox;
	}

	/**
	 * The threshold textbox setter
	 * @param thresholdTextbox the thresholdTextbox to set
	 */
	public void setThresholdTextbox(JTextField thresholdTextbox) {
		this.thresholdTextbox = thresholdTextbox;
	}

	/**
	 * The checkbox for 'all colors' getter
	 * @return the allColorsCheckbox
	 */
	public JToggleButton getAllColorsCheckbox() {
		return allColorsCheckbox;
	}

	/**
	 * The checkbox for 'all colors' setter
	 * @param allColorsCheckbox the allColorsCheckbox to set
	 */
	public void setAllColorsCheckbox(JToggleButton allColorsCheckbox) {
		this.allColorsCheckbox = allColorsCheckbox;
	}
}
