package com.unitelmasapienza.asciiart.imageeditor.listener;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import com.unitelmasapienza.asciiart.imageeditor.ImageEditor;
import com.unitelmasapienza.asciiart.imageeditor.ImageImporter;
import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;

/**
 * The class manages the Action Listener for the <b>import images function</b>
 * @see ImageImporter that represents the importing images frame
 * @author Fulvio Zecchin
 *
 */
public class ActionImportView implements ActionListener {

	/**
	 * Default constructor
	 * 
	 */
	public ActionImportView() {}

	/**
	 * Describes the behavior when a is clicked the <i>import</i> button from 
	 * the Importer frame.
	 * @see ImageImporter that represents the importing images frame 
	 * 
	 * It starts with a file system path to navigate to in order to choose the image to import. 
	 * Once the image is chosen, it resizes it and adapts it to the size of the canvas in the drawing 
	 * frame, ready to be converted and drawn.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser("resources/");
		int returnVal = fileChooser.showOpenDialog(ImageImporter.getInstance());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				ImageEditorController.getInstance().getView().setImportBI(ImageIO
						.read(new File(fileChooser.getSelectedFile().getAbsolutePath())));
				System.out.println("Resizing...");
				int panelWidth = ImageEditor.getInstance().getGeneralPanel().getPanelWidthInCharacters();
				int panelHeight = ImageEditor.getInstance().getGeneralPanel().getPanelHeightInCharacters();

				BufferedImage resized = new BufferedImage(panelWidth, panelHeight, ImageEditor.getInstance().getImportBI().getType());
				Graphics2D graphics = resized.createGraphics();
				graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				graphics.drawImage(ImageEditor.getInstance().getImportBI(), 0, 0, panelWidth, panelHeight, 0, 0,
						ImageEditor.getInstance().getImportBI().getWidth(), ImageEditor.getInstance().getImportBI().getHeight(),
						null);
				graphics.dispose();
				ImageEditor.getInstance().setImportBI(resized);

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

}
