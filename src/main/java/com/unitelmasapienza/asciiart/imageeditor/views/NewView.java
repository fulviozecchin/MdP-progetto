package com.unitelmasapienza.asciiart.imageeditor.views;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.unitelmasapienza.asciiart.imageeditor.listeners.ActionNewListener;

/**
 * The class manages the frame dialog for the creation of a <b>new main drawing canvas</b>.
 * 
 * @see ImageEditorView which represents the main frame class for the application 
 *      and calls the new frame action at the click of the <b>New...</b> button under <i>File</i> menu.
 * @see ActionNewListener which is the related Action Listener.
 * 
 * @author Fulvio Zecchin
 *
 */
public class NewView extends JDialog {

	/**
	 * The indefier to serialize/deserialize the object
	 * 
	 */
	private static final long serialVersionUID = -1844498949903148619L;

	/**
	 * Represents the <b>Create New</b> button
	 * 
	 */
	private JButton createNewButton;

	/**
	 * Represents the label for <b>Width</b>
	 * 
	 */
	private JLabel widthLabel;

	/**
	 * Represents the textbox for the input of the width value
	 * 
	 */
	private JTextField widthTextbox;

	/**
	 * Represents the label for <b>Height</b>
	 * 
	 */
	private JLabel heightLabel;

	/**
	 * Represents the textbox for the input of the height value
	 * 
	 */
	private JTextField heightTextbox;

	/**
	 * The only instance of the class
	 * 
	 */
	private static NewView instance;

	/**
	 * <b><i>Singleton</i></b> implementation. Checks if an instance of the class already exists and returns it. 
	 * If it does not exist it creates and returns it.
	 * 
	 * @return CharacterSelector instance
	 */
	public static NewView getInstance() {
		if (instance == null)
			instance = new NewView();
		return instance;
	}

	/**
	 * The constructor is private because by implementing the <b><i>Singleton</i></b> Pattern it is not visible outside the class
	 * 
	 */
	private NewView() {
		super.setTitle("Create New Canvas");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setBounds(0, 0, 320, 230);
		this.setResizable(false);
		this.setLayout(null);
		setWidthLabel(new JLabel("Width:"));
		setWidthTextbox(new JTextField("80"));
		setHeightLabel(new JLabel("Height:"));
		setHeightTextbox(new JTextField("60"));
		setCreateNewButton(new JButton("Create New"));
		getCreateNewButton().addActionListener(new ActionNewListener());
		this.add(getWidthLabel());
		this.add(getWidthTextbox());
		this.add(getHeightLabel());
		this.add(getHeightTextbox());
		this.add(getCreateNewButton());
		getWidthLabel().setBounds(0, 0, 320, 40);
		getWidthTextbox().setBounds(0, 40, 320, 40);
		getHeightLabel().setBounds(0, 80, 320, 40);
		getHeightTextbox().setBounds(0, 120, 320, 40);
		getCreateNewButton().setBounds(0, 160, 320, 40);
	}

	/**
	 * The create new button getter
	 * 
	 * @return the createNewButton
	 */
	public JButton getCreateNewButton() {
		return createNewButton;
	}

	/**
	 * The create new button setter
	 * 
	 * @param createNewButton is the button to set
	 */
	public void setCreateNewButton(JButton createNewButton) {
		this.createNewButton = createNewButton;
	}

	/**
	 * The width label getter
	 * 
	 * @return the widthLabel
	 */
	public JLabel getWidthLabel() {
		return widthLabel;
	}

	/**
	 * The width label setter
	 * 
	 * @param widthLabel is the label to set
	 */
	public void setWidthLabel(JLabel widthLabel) {
		this.widthLabel = widthLabel;
	}

	/**
	 * The width textbox getter
	 * 
	 * @return the widthTextbox
	 */
	public JTextField getWidthTextbox() {
		return widthTextbox;
	}

	/**
	 * The width textbox setter
	 * 
	 * @param widthTextbox is the textfield to set
	 */
	public void setWidthTextbox(JTextField widthTextbox) {
		this.widthTextbox = widthTextbox;
	}

	/**
	 * The height label getter
	 * 
	 * @return the heightLabel
	 */
	public JLabel getHeightLabel() {
		return heightLabel;
	}

	/**
	 * The height label setter
	 * 
	 * @param heightLabel is the label to set
	 */
	public void setHeightLabel(JLabel heightLabel) {
		this.heightLabel = heightLabel;
	}

	/**
	 * The height textbox getter
	 * 
	 * @return the heightTextbox
	 */
	public JTextField getHeightTextbox() {
		return heightTextbox;
	}

	/**
	 * The height textbox setter
	 * 
	 * @param heightTextbox is the textebox to set
	 */
	public void setHeightTextbox(JTextField heightTextbox) {
		this.heightTextbox = heightTextbox;
	}
}
