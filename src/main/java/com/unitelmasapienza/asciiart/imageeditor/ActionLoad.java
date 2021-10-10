package com.unitelmasapienza.asciiart.imageeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;
import com.unitelmasapienza.asciiart.asciipanel.AsciiRaster;

/**
 * The class manages the Action Listener for the <b>function of loading an existing ascii image</b> in the drawing canvas.
 * @see ImageEditor which represents the main frame class for the application 
 *      and calls the loading action at the click of the <b>Load...</b> button under <i>File</i> menu.
 * @author Fulvio Zecchin
 *
 */
public class ActionLoad implements ActionListener {

	/**
	 * Is the parent dialog passed to the JFileChooser to open the dialog for selecting the image to load
	 * 
	 */
	private AsciiPanel parentDialog;
	
	/**
	 * Default constructor
	 * 
	 */
	public ActionLoad()	{}
	
	/**
	 * Describes the behavior when a is clicked the <b>load</b> button from 
	 * <i>file</i> menu' in main frame ImageEditor.
	 * 
	 * It starts with a file system path to navigate to in order to choose the image to load.
	 * Once chosen it is loaded (painted) into the main canvas
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser("resources/");
		int returnVal = fileChooser.showOpenDialog(getParentDialog());	

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			AsciiRaster raster = AsciiRaster.createRasterFromFile(fileChooser.getSelectedFile().getAbsolutePath());
			ImageEditor.getInstance().reset(raster.getRasterLimitX(), raster.getRasterLimitY());
			ImageEditor.getInstance().getGeneralPanel().paintRaster(raster, 0, 0, false);
		}
	}

	/**
	 * The parent dialog getter
	 * @return the parentDialog
	 */
	AsciiPanel getParentDialog() {
		return parentDialog;
	}

	/**
	 * The parent dialog setter
	 * @param parentDialog the parentDialog to set
	 */
	void setParentDialog(AsciiPanel parentDialog) {
		this.parentDialog = parentDialog;
	}

}