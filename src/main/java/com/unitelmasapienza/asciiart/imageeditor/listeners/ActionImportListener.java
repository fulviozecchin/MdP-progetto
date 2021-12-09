package com.unitelmasapienza.asciiart.imageeditor.listeners;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import com.unitelmasapienza.asciiart.imageeditor.controllers.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.views.ImporterView;

/**
 * The class manages the Action Listener for the <b>import images function</b>.
 * 
 * @see ImporterView that represents the importing images frame.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ActionImportListener implements ActionListener {

	/**
	 * Default constructor
	 * 
	 */
	public ActionImportListener() {}

	/**
	 * Describes the behavior when a is clicked the <i>import</i> button from 
	 * the Importer frame.
	 * @see ImporterView that represents the importing images frame 
	 * 
	 * It starts with a file system path to navigate to in order to choose the image to import. 
	 * Once the image is chosen, it resizes it and adapts it to the size of the canvas in the drawing 
	 * frame, ready to be converted and drawn.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser("resources/");
		int returnVal = fileChooser.showOpenDialog(ImporterView.getInstance());
		
		ImageEditorController controller = ImageEditorController.getInstance();
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				controller.getView().setImportBI(ImageIO
						.read(new File(fileChooser.getSelectedFile().getAbsolutePath())));
				System.out.println("Resizing...");
				int panelWidth = controller.getModel().getPanelWidthInCharacters();
				int panelHeight = controller.getModel().getPanelHeightInCharacters();

				BufferedImage resized = new BufferedImage(panelWidth, panelHeight, controller.getView().getImportBI().getType());
				Graphics2D graphics = resized.createGraphics();
				graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				graphics.drawImage(controller.getView().getImportBI(), 0, 0, panelWidth, panelHeight, 0, 0,
						controller.getView().getImportBI().getWidth(), controller.getView().getImportBI().getHeight(),
						null);
				graphics.dispose();
				controller.getView().setImportBI(resized);

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

}
