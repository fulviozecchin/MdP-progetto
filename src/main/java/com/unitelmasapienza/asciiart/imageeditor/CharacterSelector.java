package com.unitelmasapienza.asciiart.imageeditor;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * The class manages the <b>character palette frame</b> for choosing and selecting the character to draw
 * @see ActionCharListener which is the related Action Listener
 * @author Fulvio Zecchin
 *
 */
public class CharacterSelector extends JFrame {

	/**
	 * The indefier to serialize/deserialize the object
	 * 
	 */
	private static final long serialVersionUID = -3046096625552352801L;
	
	/**
	 * Should be a label/descriptor of a frame object. 
	 * However, it is never used in the application
	 * 
	 */
	JLabel frameLabel;
	
	/**
	 * Represents the list of characters (treated as buttons) that you can select
	 * 
	 */
	List<JButton> charsPalette;
	
	/**
	 * The only instance of the class
	 * 
	 */
	static CharacterSelector instance;

	/**
	 * <b><i>Singleton</i></b> implementation. Checks if an instance of the class already exists and returns it. 
	 * If it does not exist it creates and returns it.
	 * 
	 * @return CharacterSelector instance
	 */
	public static CharacterSelector getInstance() {
		if (instance == null)
			instance = new CharacterSelector();
		return instance;
	}

	/**
	 * The constructor is private because by implementing the <b><i>Singleton</i></b> Pattern it is not visible outside the class
	 * 
	 */
	private CharacterSelector() {
		super("ASCII ImageEditor - CharacterSelector");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setBounds(0, 0, 16 * 16, 16 * 16 + 32);
		this.setResizable(false);
		this.setLayout(null);
		ImageEditor imageEditor = ImageEditor.getInstance();
		BufferedImage[] glyphsList = imageEditor.getGeneralPanel().getGlyphsList();
		for (int i = 0; i < glyphsList.length; i++) {
			int x = i % 16;
			int y = i / 16;
			JButton charAsButton = new JButton(new ImageIcon(glyphsList[i]));
			this.add(charAsButton);
			charAsButton.setBounds(x * 16, y * 16, 16, 16);
			charAsButton.addActionListener(new ActionCharListener(i));
		}

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
}
