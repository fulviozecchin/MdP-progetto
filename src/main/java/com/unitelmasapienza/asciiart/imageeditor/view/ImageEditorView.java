package com.unitelmasapienza.asciiart.imageeditor.view;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;

/**
 * VIEW
 * @author Fulvio Zecchin
 *
 */
public class ImageEditorView extends JFrame {
	
	/**
	 * MODEL
	 */
	private AsciiPanel panel;

	/**
	 * Represents the color of the drawn character
	 * 
	 */
	private Color drawnCharColor = Color.WHITE;
	
	/**
	 * Represents the background color of the drawn character
	 * 
	 */
	private Color drawnCharBackgroundColor = Color.BLACK;
	
	/**
	 * Represents the selected character that will be drawn
	 * 
	 */
	private Integer selectedChar = 1;
	
	/**
	 * Represents the index of the selected tool/function button
	 * 
	 */
	private Integer selectedToolIndex = 0;
	
	/**
	 * In the GUI palette indicates the color chosen for the character to be drawn 
	 * 
	 */
	private JPanel charColorPreview;
	
	/**
	 * In the GUI palette indicates the background color chosen for the character to be drawn
	 * 
	 */
	private JPanel charBackgroundColorPreview;
	
	/**
	 * In the GUI palette is the <b>"minus"</b> button to move between characters
	 * 
	 */
	private JButton minusButton;
	
	/**
	 * In the GUI palette is the <b>"plus"</b> button to move between characters
	 * 
	 */
	private JButton plusButton;
	
	/**
	 * In the GUI palette it represents the index of the chosen character 
	 * 
	 */
	private JButton charIndexButton;
	
	/**
	 * In the GUI palette it represents the button <b>"Pick"</b>
	 * 
	 */
	private JButton pickToolButton;
	
	/**
	 * In the GUI palette it represents the button <b>"Paint"</b>
	 * 
	 */
	private JButton paintToolButton;
	
	/**
	 * In the GUI palette it represents the button <b>"Fill"</b>
	 * 
	 */
	private JButton fillToolButton;
	
	/**
	 * In the GUI palette it represents the preview of selected character
	 * 
	 */
	private AsciiPanel selectedCharPreview;
	
	/**
	 * Is the buffered image that is imported
	 * 
	 */
	private BufferedImage importBI;
	
	/**
	 * In the GUI palette it represents the top choice menu
	 */
	private JMenuBar menuBar;
	
	/**
	 * In the choice menu in GUI palette it represents the menu called 'File' which
	 * contains all the menu item
	 */
	private JMenu menuBarFile;
	
	/**
	 * In the choice menu in GUI palette, inside 'File' menu it represents the field 'New'
	 */
	private JMenuItem menuBarFileNew;
	
	/**
	 * In the choice menu in GUI palette, inside 'File' menu it represents the field 'Load'
	 */
	private JMenuItem menuBarFileLoad;
	
	/**
	 * In the choice menu in GUI palette, inside 'File' menu it represents the field 'Save'
	 */
	private JMenuItem menuBarFileSave;
	
	/**
	 * In the choice menu in GUI palette, inside 'File' menu it represents the field 'Import'
	 */
	private JMenuItem menuBarFileImport;
	
	/**
	 * This is the view constructor. It initializes the GUI that the user will use.
	 */
	public ImageEditorView() {
		
		//preparazione elementi della GUI
		super("ASCII ImageEditor");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSelectedCharPreview(new AsciiPanel(1, 1, AsciiFont.CP437_16x16));
		this.setBounds(0, 0, 80 * 16 + 80, 60 * 16);
		this.setLayout(null);

		JPanel controlTool = new JPanel();
		controlTool.setLayout(null);
		setCharColorPreview(new JPanel());
		setCharBackgroundColorPreview(new JPanel());
		setMinusButton(new JButton("-"));
		setPlusButton(new JButton("+"));
		setCharIndexButton(new JButton("1"));
		
		setPickToolButton(new JButton());
		ImageIcon pickIcon = new ImageIcon("src/main/resources/icons/pick icon.png");
		getPickToolButton().setIcon(pickIcon);
		getPickToolButton().setToolTipText("Pick");
		
		setPaintToolButton(new JButton());
		ImageIcon paintIcon = new ImageIcon("src/main/resources/icons/paint icon.png");
		getPaintToolButton().setIcon(paintIcon);
		getPaintToolButton().setToolTipText("Paint");
		
		setFillToolButton(new JButton());
		ImageIcon fillIcon = new ImageIcon("src/main/resources/icons/fill icon.png");
		getFillToolButton().setIcon(fillIcon);
		getFillToolButton().setToolTipText("Fill");
		
		getCharColorPreview().setBackground(getDrawnCharColor());
		getCharBackgroundColorPreview().setBackground(getDrawnCharBackgroundColor());
		
		//aggiunta elementi alla GUI
		controlTool.add(getMinusButton());
		controlTool.add(getCharIndexButton());
		controlTool.add(getPlusButton());
		controlTool.add(getCharColorPreview());
		controlTool.add(getCharBackgroundColorPreview());
		controlTool.add(getPickToolButton());
		controlTool.add(getPaintToolButton());
		controlTool.add(getFillToolButton());
		controlTool.add(getSelectedCharPreview());
		
		getPaintToolButton().setBounds(0, 0, 80, 40);
		getFillToolButton().setBounds(0, 40, 80, 40);
		getPickToolButton().setBounds(0, 80, 80, 40);
		getMinusButton().setBounds(0, 120, 15, 40);
		getCharIndexButton().setBounds(15, 120, 34, 40);
		getPlusButton().setBounds(65, 120, 15, 40);
		getCharColorPreview().setBounds(0, 160, 40, 40);
		getCharBackgroundColorPreview().setBounds(40, 160, 40, 40);
		getSelectedCharPreview().setBounds(49, 120, 16, 16);
		
		controlTool.setBounds(0, 0, 80, 80 * 16);
		this.add(controlTool);
		
		//aggiunta barra menu in alto della GUI
		setMenuBar(new JMenuBar());
		setMenuBarFile(new JMenu("File"));
		
		ImageIcon newIcon = new ImageIcon("src/main/resources/icons/new icon.png");
		setMenuBarFileNew(new JMenuItem("New..."));
		getMenuBarFileNew().setIcon(newIcon);
		
		ImageIcon loadIcon = new ImageIcon("src/main/resources/icons/load icon.png");
		setMenuBarFileLoad(new JMenuItem("Load..."));
		getMenuBarFileLoad().setIcon(loadIcon);
		
		ImageIcon saveImage = new ImageIcon("src/main/resources/icons/save icon.png");
		setMenuBarFileSave(new JMenuItem("Save with name..."));
		getMenuBarFileSave().setIcon(saveImage);
		
		ImageIcon importImage = new ImageIcon("src/main/resources/icons/import icon.png");
		setMenuBarFileImport(new JMenuItem("Import..."));
		getMenuBarFileImport().setIcon(importImage);
		
		menuBar.add(menuBarFile);
		menuBarFile.add(menuBarFileNew);
		menuBarFile.add(menuBarFileLoad);
		menuBarFile.add(menuBarFileSave);
		menuBarFile.add(menuBarFileImport);
		this.setJMenuBar(menuBar);
		menuBar.setVisible(true);
	}
	
	
	//EVENTS
	
	/**
	 * Updates the preview of the selected character
	 * 
	 */
	public void updatePreview() {
		getSelectedCharPreview().clear();
		getSelectedCharPreview().write((char) (getSelectedChar() + 0), 0, 0, getDrawnCharColor(), getDrawnCharBackgroundColor());
		getSelectedCharPreview().repaint();
	}
	

	//GETTER & SETTER
	
	public AsciiPanel getPanel() {
		return panel;
	}

	public void setPanel(AsciiPanel panel) {
		this.panel = panel;
	}

	public Color getDrawnCharColor() {
		return drawnCharColor;
	}

	public void setDrawnCharColor(Color drawnCharColor) {
		this.drawnCharColor = drawnCharColor;
	}

	public Color getDrawnCharBackgroundColor() {
		return drawnCharBackgroundColor;
	}

	public void setDrawnCharBackgroundColor(Color drawnCharBackgroundColor) {
		this.drawnCharBackgroundColor = drawnCharBackgroundColor;
	}

	public Integer getSelectedChar() {
		return selectedChar;
	}

	public void setSelectedChar(Integer selectedChar) {
		this.selectedChar = selectedChar;
	}

	public Integer getSelectedToolIndex() {
		return selectedToolIndex;
	}

	public void setSelectedToolIndex(Integer selectedToolIndex) {
		this.selectedToolIndex = selectedToolIndex;
	}

	public JPanel getCharColorPreview() {
		return charColorPreview;
	}

	public void setCharColorPreview(JPanel charColorPreview) {
		this.charColorPreview = charColorPreview;
	}

	public JPanel getCharBackgroundColorPreview() {
		return charBackgroundColorPreview;
	}

	public void setCharBackgroundColorPreview(JPanel charBackgroundColorPreview) {
		this.charBackgroundColorPreview = charBackgroundColorPreview;
	}

	public JButton getMinusButton() {
		return minusButton;
	}

	public void setMinusButton(JButton minusButton) {
		this.minusButton = minusButton;
	}

	public JButton getPlusButton() {
		return plusButton;
	}

	public void setPlusButton(JButton plusButton) {
		this.plusButton = plusButton;
	}

	public JButton getCharIndexButton() {
		return charIndexButton;
	}

	public void setCharIndexButton(JButton charIndexButton) {
		this.charIndexButton = charIndexButton;
	}

	public JButton getPickToolButton() {
		return pickToolButton;
	}

	public void setPickToolButton(JButton pickToolButton) {
		this.pickToolButton = pickToolButton;
	}

	public JButton getPaintToolButton() {
		return paintToolButton;
	}

	public void setPaintToolButton(JButton paintToolButton) {
		this.paintToolButton = paintToolButton;
	}

	public JButton getFillToolButton() {
		return fillToolButton;
	}

	public void setFillToolButton(JButton fillToolButton) {
		this.fillToolButton = fillToolButton;
	}

	public AsciiPanel getSelectedCharPreview() {
		return selectedCharPreview;
	}

	public void setSelectedCharPreview(AsciiPanel selectedCharPreview) {
		this.selectedCharPreview = selectedCharPreview;
	}

	public BufferedImage getImportBI() {
		return importBI;
	}

	public void setImportBI(BufferedImage importBI) {
		this.importBI = importBI;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getMenuBarFile() {
		return menuBarFile;
	}

	public void setMenuBarFile(JMenu menuBarFile) {
		this.menuBarFile = menuBarFile;
	}

	public JMenuItem getMenuBarFileNew() {
		return menuBarFileNew;
	}

	public void setMenuBarFileNew(JMenuItem menuBarFileNew) {
		this.menuBarFileNew = menuBarFileNew;
	}

	public JMenuItem getMenuBarFileLoad() {
		return menuBarFileLoad;
	}

	public void setMenuBarFileLoad(JMenuItem menuBarFileLoad) {
		this.menuBarFileLoad = menuBarFileLoad;
	}

	public JMenuItem getMenuBarFileSave() {
		return menuBarFileSave;
	}

	public void setMenuBarFileSave(JMenuItem menuBarFileSave) {
		this.menuBarFileSave = menuBarFileSave;
	}

	public JMenuItem getMenuBarFileImport() {
		return menuBarFileImport;
	}

	public void setMenuBarFileImport(JMenuItem menuBarFileImport) {
		this.menuBarFileImport = menuBarFileImport;
	}
	
}
