package com.unitelmasapienza.asciiart.imageeditor.view;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;
import com.unitelmasapienza.asciiart.imageeditor.factory.ImageEditorViewFactoryConcrete;

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
//	public ImageEditorView() {
//		
//		//preparazione elementi della GUI
//		super("ASCII ImageEditor");
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setSelectedCharPreview(new AsciiPanel(1, 1, AsciiFont.CP437_16x16));
//		this.setBounds(0, 0, 80 * 16 + 80, 60 * 16);
//		this.setLayout(null);
//
//		JPanel controlTool = new JPanel();
//		controlTool.setLayout(null);
//		setCharColorPreview(new JPanel());
//		setCharBackgroundColorPreview(new JPanel());
//		setMinusButton(new JButton("-"));
//		setPlusButton(new JButton("+"));
//		setCharIndexButton(new JButton("1"));
//		
//		setPickToolButton(new JButton());
//		ImageIcon pickIcon = new ImageIcon("src/main/resources/icons/pick icon.png");
//		getPickToolButton().setIcon(pickIcon);
//		getPickToolButton().setToolTipText("Pick");
//		
//		setPaintToolButton(new JButton());
//		ImageIcon paintIcon = new ImageIcon("src/main/resources/icons/paint icon.png");
//		getPaintToolButton().setIcon(paintIcon);
//		getPaintToolButton().setToolTipText("Paint");
//		
//		setFillToolButton(new JButton());
//		ImageIcon fillIcon = new ImageIcon("src/main/resources/icons/fill icon.png");
//		getFillToolButton().setIcon(fillIcon);
//		getFillToolButton().setToolTipText("Fill");
//		
//		getCharColorPreview().setBackground(getDrawnCharColor());
//		getCharBackgroundColorPreview().setBackground(getDrawnCharBackgroundColor());
//		
//		//aggiunta elementi alla GUI
//		controlTool.add(getMinusButton());
//		controlTool.add(getCharIndexButton());
//		controlTool.add(getPlusButton());
//		controlTool.add(getCharColorPreview());
//		controlTool.add(getCharBackgroundColorPreview());
//		controlTool.add(getPickToolButton());
//		controlTool.add(getPaintToolButton());
//		controlTool.add(getFillToolButton());
//		controlTool.add(getSelectedCharPreview());
//		
//		getPaintToolButton().setBounds(0, 0, 80, 40);
//		getFillToolButton().setBounds(0, 40, 80, 40);
//		getPickToolButton().setBounds(0, 80, 80, 40);
//		getMinusButton().setBounds(0, 120, 15, 40);
//		getCharIndexButton().setBounds(15, 120, 34, 40);
//		getPlusButton().setBounds(65, 120, 15, 40);
//		getCharColorPreview().setBounds(0, 160, 40, 40);
//		getCharBackgroundColorPreview().setBounds(40, 160, 40, 40);
//		getSelectedCharPreview().setBounds(49, 120, 16, 16);
//		
//		controlTool.setBounds(0, 0, 80, 80 * 16);
//		this.add(controlTool);
//		
//		//aggiunta barra menu in alto della GUI
//		setMenuBar(new JMenuBar());
//		setMenuBarFile(new JMenu("File"));
//		
//		ImageIcon newIcon = new ImageIcon("src/main/resources/icons/new icon.png");
//		setMenuBarFileNew(new JMenuItem("New..."));
//		getMenuBarFileNew().setIcon(newIcon);
//		
//		ImageIcon loadIcon = new ImageIcon("src/main/resources/icons/load icon.png");
//		setMenuBarFileLoad(new JMenuItem("Load..."));
//		getMenuBarFileLoad().setIcon(loadIcon);
//		
//		ImageIcon saveImage = new ImageIcon("src/main/resources/icons/save icon.png");
//		setMenuBarFileSave(new JMenuItem("Save..."));
//		getMenuBarFileSave().setIcon(saveImage);
//		
//		ImageIcon importImage = new ImageIcon("src/main/resources/icons/import icon.png");
//		setMenuBarFileImport(new JMenuItem("Import..."));
//		getMenuBarFileImport().setIcon(importImage);
//		
//		menuBar.add(menuBarFile);
//		menuBarFile.add(menuBarFileNew);
//		menuBarFile.add(menuBarFileLoad);
//		menuBarFile.add(menuBarFileSave);
//		menuBarFile.add(menuBarFileImport);
//		this.setJMenuBar(menuBar);
//		menuBar.setVisible(true);
//	}
	
	/**
	 * This is public constructor used by Builder to create physically the ImageEditorView object for application.
	 * As correct implementation of the <b>Builder Pattern</b>, this constructor take in input all
	 * fields of this class which will be set from Builder, which will pass directly here to set
	 * the values.
	 * Based on correct fields, this constructor graphically organizes all the elements that make up the GUI 
	 * in order to initialize the graphical interface of the application.
	 * 
	 * @see ImageEditorViewBuilder to check all value control (nullable, default value etc.) and see the modular construction
     * of object by cascading methods calls.
     * @see ImageEditorViewFactoryConcrete check the cascading methods calls to the Builder Class.
     * 
	 * @param selectedCharPreview is the selected char preview for GUI
	 * @param charColorPreview is the char color preview for GUI
	 * @param charBackgroundColorPreview is the char background color preview for GUI
	 * @param minusButton is the minus button for GUI
	 * @param plusButton is the plus button for GUI
	 * @param charIndexButton is the char index button for GUI
 	 * @param pickToolButton is the pick button for GUI
	 * @param paintToolButton is the paint button for GUI
	 * @param fillToolButton is the fill button for GUI
	 * @param controlTool is the controlTool which contains all tool objects
	 * @param menuBar is the top menu bar for GUI
	 * @param fileMenu is the <i>File</i> menu for top menu bar
	 * @param newFileItem is the <i>New</i> menu item for File menu
	 * @param loadFileItem is the <i>Load</i> menu item for File menu
	 * @param saveFileItem is the <i>Save</i> menu item for File menu
	 * @param importFileItem is the <i>Import</i> menu item for File menu
	 * 
	 */
	public ImageEditorView(AsciiPanel selectedCharPreview, 
			JPanel charColorPreview, 
			JPanel charBackgroundColorPreview, 
			JButton minusButton,
			JButton plusButton, 
			JButton charIndexButton, 
			JButton pickToolButton, 
			JButton paintToolButton,
			JButton fillToolButton, 
			JPanel controlTool, 
			JMenuBar menuBar, 
			JMenu fileMenu, 
			JMenuItem newFileItem, 
			JMenuItem loadFileItem,
			JMenuItem saveFileItem, 
			JMenuItem importFileItem) {
		
		super("ASCII ImageEditor");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 80 * 16 + 80, 60 * 16);
		this.setLayout(null);
		
		controlTool.setLayout(null);
		
		//GUI Buttons
		setSelectedCharPreview(selectedCharPreview);
		setCharColorPreview(charColorPreview);
		setCharBackgroundColorPreview(charBackgroundColorPreview);
		setMinusButton(minusButton);
		setPlusButton(plusButton);
		setCharIndexButton(charIndexButton);
		setPickToolButton(pickToolButton);
		setPaintToolButton(paintToolButton);
		setFillToolButton(fillToolButton);
		
		getCharColorPreview().setBackground(getDrawnCharColor());
		getCharBackgroundColorPreview().setBackground(getDrawnCharBackgroundColor());
		
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
		
		//Menu Bar
		setMenuBar(menuBar);
		setMenuBarFile(fileMenu);
		
		setMenuBarFileNew(newFileItem);
		setMenuBarFileLoad(loadFileItem);
		setMenuBarFileSave(saveFileItem);
		setMenuBarFileImport(importFileItem);
		
		this.menuBar.add(getMenuBarFile());
		getMenuBarFile().add(getMenuBarFileNew());
		getMenuBarFile().add(getMenuBarFileLoad());
		getMenuBarFile().add(getMenuBarFileSave());
		getMenuBarFile().add(getMenuBarFileImport());
		
		this.setJMenuBar(menuBar);
		this.menuBar.setVisible(true);
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
