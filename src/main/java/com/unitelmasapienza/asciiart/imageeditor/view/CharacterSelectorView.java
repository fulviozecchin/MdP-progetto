package com.unitelmasapienza.asciiart.imageeditor.view;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.unitelmasapienza.asciiart.imageeditor.controller.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.listener.ActionViewCharListener;

/**
 * The class manages the <b>character palette frame</b> for choosing and selecting the character to draw.
 * 
 * @see ActionViewCharListener which is the related Action Listener.
 * 
 * @author Fulvio Zecchin
 *
 */
public class CharacterSelectorView extends JDialog {

	/**
	 * The identifier to serialize/deserialize the object
	 * 
	 */
	private static final long serialVersionUID = -3046096625552352801L;
	
	/**
	 * Should be a label/descriptor of a frame object. 
	 * However, it is never used in the application.
	 * 
	 */
	JLabel frameLabel;
	
	/**
	 * Represents the list of characters (treated as buttons) that you can select.
	 * 
	 */
	List<JButton> charsPalette;
	
	/**
	 * The only instance of the class.
	 * 
	 */
	static CharacterSelectorView instance;

	/**
	 * <b><i>Singleton</i></b> implementation. Checks if an instance of the class already exists and returns it. 
	 * If it does not exist it creates and returns it.
	 * 
	 * @return CharacterSelector instance
	 */
	public static CharacterSelectorView getInstance() {
		if (instance == null)
			instance = new CharacterSelectorView();
		return instance;
	}

	/**
	 * The constructor is private because by implementing the <b><i>Singleton</i></b> Pattern it is not visible outside the class.
	 * 
	 */
	private CharacterSelectorView() {
		super.setTitle("Character Selector");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setBounds(0, 0, 16 * 16, 16 * 16 + 32);
		this.setResizable(false);
		this.setLayout(null);
		ImageEditorController controller = ImageEditorController.getInstance();
		BufferedImage[] glyphsList = controller.getModel().getGlyphsList();
		for (int i = 0; i < glyphsList.length; i++) {
			int x = i % 16;
			int y = i / 16;
			JButton charAsButton = new JButton(new ImageIcon(glyphsList[i]));
			this.add(charAsButton);
			charAsButton.setBounds(x * 16, y * 16, 16, 16);
			charAsButton.addActionListener(new ActionViewCharListener(i));
		}
	}

	/**
	 * Describes the behavior when the character palette frame is closed.
	 * 
	 */
	public void close() {
		instance.setVisible(false);
		instance.dispose();
		instance = null;
	}
}
