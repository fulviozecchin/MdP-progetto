package com.unitelmasapienza.asciiart.imageeditor.view;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.listener.ActionConvertListener;
import com.unitelmasapienza.asciiart.imageeditor.listener.ActionImportListener;
/**
 * The class manages the <b>importing images frame</b> into the canvas of application.
 * 
 * @see ImageEditorView which represents the main View class for the application 
 *      and calls the importing action at the click of the <b>Import...</b> button under <i>File</i> menu.
 * @see ActionImportListener which is the related Action Listener.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ImporterView extends JDialog {

	/**
	 * The indefier to serialize/deserialize the object
	 * 
	 */
	private static final long serialVersionUID = -1577837505931479839L;

	/**
	 * Represents the <b>import</b> button
	 * 
	 */
	private JButton importButton;
	
	/**
	 * Represents the <b>convert</b> button
	 * 
	 */
	private JButton convertButton;
	
	/**
	 * Represents the label for <b>Threshold</b>
	 * 
	 */
	private JLabel thresholdLabel;
	
	/**
	 * Represents the textbox for the input of the threshold value
	 * 
	 */
	private JTextField thresholdTextbox;
	
	/**
	 * Represents the checkbox for able/disable <b>all colors</b> in the imported image
	 * 
	 */
	private JCheckBox allColorsCheckbox;

	/**
	 * The only instance of the class
	 * 
	 */
	private static ImporterView instance;

	/**
	 * <b><i>Singleton</i></b> implementation. Checks if an instance of the class already exists and returns it. 
	 * If it does not exist it creates and returns it.
	 * 
	 * @return ImageImporter instance
	 */
	public static ImporterView getInstance() {
		if (instance == null)
			instance = new ImporterView();
		return instance;
	}

	/**
	 * The constructor is private because by implementing the <b><i>Singleton</i></b> Pattern it is not visible outside the class
	 * 
	 */
	private ImporterView() {
		super.setTitle("Import Image");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setBounds(0, 0, 320, 230);
		this.setResizable(false);
		this.setLayout(null);
		setThresholdLabel(new JLabel("Threshold:"));
		setThresholdTextbox(new JTextField("240"));
		setAllColorsCheckbox(new JCheckBox("all colors", true));
		setConvertButton(new JButton("Convert"));
		setImportButton(new JButton("Import ..."));
		getImportButton().addActionListener(new ActionImportListener());
		getConvertButton().addActionListener(new ActionConvertListener(ImageEditorController.getInstance().getModel(), getThresholdTextbox(), getAllColorsCheckbox()));
		this.add(getImportButton());
		this.add(getThresholdLabel());
		this.add(getThresholdTextbox());
		this.add(getAllColorsCheckbox());
		this.add(getConvertButton());
		getImportButton().setBounds(0, 0, 320, 40);
		getThresholdLabel().setBounds(0, 40, 320, 40);
		getThresholdTextbox().setBounds(0, 80, 320, 40);
		getAllColorsCheckbox().setBounds(0, 120, 320, 40);
		getConvertButton().setBounds(0, 160, 320, 40);
	}

	/**
	 * Describes the behavior when the character palette frame is closed
	 * 
	 */
	public void close() {
		instance.setVisible(false);
		instance.dispose();
		instance = null;

	}

	/**
	 * The import button getter
	 * 
	 * @return the importButton
	 */
	public JButton getImportButton() {
		return importButton;
	}

	/**
	 * The import button setter
	 * 
	 * @param importButton is the button to set
	 */
	public void setImportButton(JButton importButton) {
		this.importButton = importButton;
	}

	/**
	 * The convert button getter
	 * 
	 * @return the convertButton
	 */
	public JButton getConvertButton() {
		return convertButton;
	}

	/**
	 * The convert button setter
	 * 
	 * @param convertButton is the button to set
	 */
	public void setConvertButton(JButton convertButton) {
		this.convertButton = convertButton;
	}

	/**
	 * The threshold label getter
	 * 
	 * @return the thresholdLabel
	 */
	public JLabel getThresholdLabel() {
		return thresholdLabel;
	}

	/**
	 * The threshold label setter
	 * 
	 * @param thresholdLabel is the label to set
	 */
	public void setThresholdLabel(JLabel thresholdLabel) {
		this.thresholdLabel = thresholdLabel;
	}

	/**
	 * The threshold textbox getter
	 * 
	 * @return the thresholdTextbox
	 */
	public JTextField getThresholdTextbox() {
		return thresholdTextbox;
	}

	/**
	 * The threshold textbox setter
	 * 
	 * @param thresholdTextbox is the textbox to set
	 */
	public void setThresholdTextbox(JTextField thresholdTextbox) {
		this.thresholdTextbox = thresholdTextbox;
	}

	/**
	 * The all colors checkbox getter
	 * 
	 * @return the allColorsCheckbox
	 */
	public JCheckBox getAllColorsCheckbox() {
		return allColorsCheckbox;
	}

	/**
	 * The all colors checkbox setter
	 * 
	 * @param allColorsCheckbox is the checkbox to set
	 */
	public void setAllColorsCheckbox(JCheckBox allColorsCheckbox) {
		this.allColorsCheckbox = allColorsCheckbox;
	}
}
