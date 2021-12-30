package com.unitelmasapienza.asciiart.imageeditor.views;

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
import com.unitelmasapienza.asciiart.imageeditor.controllers.ImageEditorController;
import com.unitelmasapienza.asciiart.imageeditor.factories.ImageEditorViewFactoryConcrete;

/**
 * This class is the main graphics class for the application. It's represents the GUI used by user to interact with
 * application.
 * Technically, in programming side, this is also <i>View</i> in <b>Model-View-Controller</b> Design Pattern.
 * So as a <i>view</i> it is responsible for relations with user, to catch and react on his input during the 
 * navigation, but also to show him the result of computation and the application status.
 * For this reasons, this class contains (as the correct implementation of MVC wants) an instance of AsciiPanel
 * which represents the <i>Model</i> in this application.
 * This view, uses the model to get the status of application, in drawing space, and immediately show them to user.
 * 
 * @see ImageEditorController to understand how the <i>Controller</i> of the application works.
 * @see AsciiPanel to understand the <i>Model</i> details for the application.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ImageEditorView extends JFrame {
	
	/**
	 * The indefier to serialize/deserialize the object
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * MODEL
	 * 
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
	 * In the choice menu in GUI palette it represents the menu called 'Edit' which
	 * contains all the menu item
	 */
	private JMenu menuBarEdit;
	
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
	 * In the choice menu in GUI palette, inside 'Edit' menu it represents the field 'Select'
	 */
	private JMenuItem menuItemSelect;
	
	/**
	 * In the choice menu in GUI palette, inside 'Edit' menu it represents the field 'Copy'
	 */
	private JMenuItem menuItemCopy;
	
	/**
	 * In the choice menu in GUI palette, inside 'Edit' menu it represents the field 'Cut'
	 */
	private JMenuItem menuItemCut;
	
	/**
	 * In the choice menu in GUI palette, inside 'Edit' menu it represents the field 'Paste'
	 */
	private JMenuItem menuItemPaste;

	
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
	 * @param editMenu is the <i>Edit</i> menu for top menu bar
	 * @param newFileItem is the <i>New</i> menu item for File menu
	 * @param loadFileItem is the <i>Load</i> menu item for File menu
	 * @param saveFileItem is the <i>Save</i> menu item for File menu
	 * @param importFileItem is the <i>Import</i> menu item for File menu
	 * @param selectItem is the <i>Select</i> menu item for Edit menu
	 * @param copyItem is the <i>Copy</i> menu item for Edit menu
	 * @param cutItem is the <i>Cut</i> menu item for Edit menu
	 * @param pasteItem is the <i>Paste</i> menu item for Edit menu
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
			JMenu editMenu,
			JMenuItem newFileItem, 
			JMenuItem loadFileItem,
			JMenuItem saveFileItem, 
			JMenuItem importFileItem,
			JMenuItem selectItem,
			JMenuItem copyItem,
			JMenuItem cutItem,
			JMenuItem pasteItem
			) {
		
		super("ASCII ART - Image Editor");
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
		
		//positioning all GUI elements
		positioningViewElements(controlTool);
		
		//positioning top-menu bar
		composingMenuBar(menuBar, fileMenu, editMenu, newFileItem, loadFileItem, saveFileItem, importFileItem, 
				selectItem, copyItem, cutItem, pasteItem);
		
	}
	
	/**
	 * This method positions the GUI elements in the view.
	 * It takes the controlTool in input to set the buttons and add it to the Container class which is the main 
	 * super-class that contains the view.
	 * 
	 * @param controlTool is the panel object which contains all GUI elements.
	 */
	private void positioningViewElements(JPanel controlTool) {
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
	}
	
	/**
	 * This method positions the top-menu bar to the GUI.
	 * It takes all fields related to top-menu bar (the <i>main</i> bar, and the relative voices (<i>File</i>, <i>Edit</i>) with
	 * all relative sub-items, and sets to the top main bar.
	 * Finally it adds the menu-bar to the parent (<b>Application Frame</> menu bar and sets it to visible.
	 * 
	 * @param menuBar is the general top-menu bar
	 * @param fileMenu is the 'File' menu bar voice
	 * @param editMenu is the 'Edit' menu bar voice
	 * @param newFileItem is the 'New' item on File Menu
	 * @param loadFileItem is the 'Load' item on File Menu
	 * @param saveFileItem is the 'Save' item on File Menu
	 * @param importFileItem is the 'Import' item on File Menu
	 * @param selectItem is the 'Select' item on Edit Menu
	 * @param copyItem is the 'Copy' item on Edit Menu
	 * @param cutItem is the 'Cut' item on Edit Menu
	 * @param pasteItem is the 'Paste' item on Edit Menu
	 */
	private void composingMenuBar(
			JMenuBar menuBar, 
			JMenu fileMenu,
			JMenu editMenu,
			JMenuItem newFileItem, 
			JMenuItem loadFileItem,
			JMenuItem saveFileItem, 
			JMenuItem importFileItem,
			JMenuItem selectItem,
			JMenuItem copyItem,
			JMenuItem cutItem,
			JMenuItem pasteItem) {
		
		setMenuBar(menuBar);
		setMenuBarFile(fileMenu);
		setMenuBarEdit(editMenu);
		
		//setting file menu items
		setMenuBarFileNew(newFileItem);
		setMenuBarFileLoad(loadFileItem);
		setMenuBarFileSave(saveFileItem);
		setMenuBarFileImport(importFileItem);
		
		//setting edit menu items
		setMenuItemSelect(selectItem);
		setMenuItemCopy(copyItem);
		setMenuItemCut(cutItem);
		setMenuItemPaste(pasteItem);
		
		//add file menu to top-bar
		this.menuBar.add(getMenuBarFile());
		if(getMenuBarFileNew() != null) getMenuBarFile().add(getMenuBarFileNew());
		if(getMenuBarFileLoad() != null) getMenuBarFile().add(getMenuBarFileLoad());
		if(getMenuBarFileSave() != null) getMenuBarFile().add(getMenuBarFileSave());
		if(getMenuBarFileImport() != null) getMenuBarFile().add(getMenuBarFileImport());
		
		//add edit menu to top-bar
		this.menuBar.add(getMenuBarEdit());
		if(getMenuItemSelect() != null) getMenuBarEdit().add(getMenuItemSelect());
		if(getMenuItemCopy() != null) getMenuBarEdit().add(getMenuItemCopy());
		if(getMenuItemCut() != null) getMenuBarEdit().add(getMenuItemCut());
		if(getMenuItemPaste() != null) getMenuBarEdit().add(getMenuItemPaste());
		
		//make top-bar visible
		this.setJMenuBar(menuBar);
		this.menuBar.setVisible(true);
	}
	
	
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
	
	/**
	 * The panel (model) getter.
	 * 
	 * @return the AsciiPanel of the view.
	 */
	public AsciiPanel getPanel() {
		return panel;
	}

	/**
	 * The panel (model) setter.
	 * 
	 * @param panel the AsciiPanel to set on view.
	 */
	public void setPanel(AsciiPanel panel) {
		this.panel = panel;
	}

	/**
	 * The drawn char color getter.
	 * 
	 * @return the drawn char color.
	 */
	public Color getDrawnCharColor() {
		return drawnCharColor;
	}

	/**
	 * The drawn char color setter.
	 * 
	 * @param drawnCharColor is the color to set.
	 */
	public void setDrawnCharColor(Color drawnCharColor) {
		this.drawnCharColor = drawnCharColor;
	}

	/**
	 * The drawn char background color getter.
	 * 
	 * @return the drawn char color background.
	 */
	public Color getDrawnCharBackgroundColor() {
		return drawnCharBackgroundColor;
	}

	/**
	 * The drawn char background color setter.
	 * 
	 * @param drawnCharBackgroundColor the background color to set.
	 */
	public void setDrawnCharBackgroundColor(Color drawnCharBackgroundColor) {
		this.drawnCharBackgroundColor = drawnCharBackgroundColor;
	}

	/**
	 * The selected char index getter.
	 *  
	 * @return the selected char index.
	 */
	public Integer getSelectedChar() {
		return selectedChar;
	}

	/**
	 * The selected char index setter.
	 * 
	 * @param selectedChar is the char index to set.
	 */
	public void setSelectedChar(Integer selectedChar) {
		this.selectedChar = selectedChar;
	}

	/**
	 * The tool index getter.
	 *  
	 * @return the tool index.
	 */
	public Integer getSelectedToolIndex() {
		return selectedToolIndex;
	}

	/**
	 * The tool index setter.
	 * 
	 * @param selectedToolIndex is the tool index to set.
	 */
	public void setSelectedToolIndex(Integer selectedToolIndex) {
		this.selectedToolIndex = selectedToolIndex;
	}

	/**
	 * The char color preview getter.
	 *  
	 * @return the char color preview object.
	 */
	public JPanel getCharColorPreview() {
		return charColorPreview;
	}

	/**
	 * The char color preview setter.
	 *  
	 * @param charColorPreview is the char color preview object to set.
	 */
	public void setCharColorPreview(JPanel charColorPreview) {
		this.charColorPreview = charColorPreview;
	}

	/**
	 * The char background color preview getter.
	 *  
	 * @return the char background color preview object.
	 */
	public JPanel getCharBackgroundColorPreview() {
		return charBackgroundColorPreview;
	}

	/**
	 * The char background color preview setter.
	 * 
	 * @param charBackgroundColorPreview is the char color background preview object to set.
	 */
	public void setCharBackgroundColorPreview(JPanel charBackgroundColorPreview) {
		this.charBackgroundColorPreview = charBackgroundColorPreview;
	}

	/**
	 * The minus button getter.
	 * 
	 * @return the minus button.
	 */
	public JButton getMinusButton() {
		return minusButton;
	}

	/**
	 * The minus button setter.
	 * 
	 * @param minusButton the button to set.
	 */
	public void setMinusButton(JButton minusButton) {
		this.minusButton = minusButton;
	}

	/**
	 * The plus button getter.
	 * 
	 * @return the plus button.
	 */
	public JButton getPlusButton() {
		return plusButton;
	}

	/**
	 * The plus button setter.
	 * 
	 * @param plusButton the button to set.
	 */
	public void setPlusButton(JButton plusButton) {
		this.plusButton = plusButton;
	}

	/**
	 * The char index button getter.
	 * 
	 * @return the char index button.
	 */
	public JButton getCharIndexButton() {
		return charIndexButton;
	}

	/**
	 * The char index button setter.
	 * 
	 * @param charIndexButton the button to set.
	 */
	public void setCharIndexButton(JButton charIndexButton) {
		this.charIndexButton = charIndexButton;
	}

	/**
	 * The pick tool button getter.
	 * 
	 * @return the pick tool button.
	 */
	public JButton getPickToolButton() {
		return pickToolButton;
	}

	/**
	 * The pick tool button getter.
	 * 
	 * @param pickToolButton the button to set.
	 */
	public void setPickToolButton(JButton pickToolButton) {
		this.pickToolButton = pickToolButton;
	}

	/**
	 * The paint tool button getter.
	 * 
	 * @return the paint tool button.
	 */
	public JButton getPaintToolButton() {
		return paintToolButton;
	}

	/**
	 * The paint tool button setter.
	 * 
	 * @param paintToolButton the button to set.
	 */
	public void setPaintToolButton(JButton paintToolButton) {
		this.paintToolButton = paintToolButton;
	}

	/**
	 * The fill tool button getter.
	 * 
	 * @return the fill tool button.
	 */
	public JButton getFillToolButton() {
		return fillToolButton;
	}

	/**
	 * The fill tool button setter.
	 * 
	 * @param fillToolButton the button to set.
	 */
	public void setFillToolButton(JButton fillToolButton) {
		this.fillToolButton = fillToolButton;
	}

	/**
	 * The selected char preview getter.
	 * 
	 * @return the selected char preview.
	 */
	public AsciiPanel getSelectedCharPreview() {
		return selectedCharPreview;
	}

	/**
	 * The selected char preview setter.
	 * 
	 * @param selectedCharPreview preview to set.
	 */
	public void setSelectedCharPreview(AsciiPanel selectedCharPreview) {
		this.selectedCharPreview = selectedCharPreview;
	}

	/**
	 * The imported Buffered Image getter.
	 * 
	 * @return the Buffered Image
	 */
	public BufferedImage getImportBI() {
		return importBI;
	}

	/**
	 * The imported Buffered Image setter.
	 * 
	 * @param importBI is the Buffered Image to set.
	 */
	public void setImportBI(BufferedImage importBI) {
		this.importBI = importBI;
	}

	/**
	 * The top menu bar setter.
	 * 
	 * @param menuBar top menu bar to set.
	 */
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	/**
	 * The top menu bar File object getter.
	 * 
	 * @return top menu bar File.
	 */
	public JMenu getMenuBarFile() {
		return menuBarFile;
	}

	/**
	 * The top menu bar File object setter. 
	 * 
	 * @param menuBarFile the menu object to set.
	 */
	public void setMenuBarFile(JMenu menuBarFile) {
		this.menuBarFile = menuBarFile;
	}

	/**
	 * The top menu bar Edit object getter.
	 * 
	 * @return top menu bar Edit.
	 */
	private JMenu getMenuBarEdit() {
		return menuBarEdit;
	}

	/**
	 * The top menu bar Edit object setter. 
	 * 
	 * @param menuBarEdit the menu object to set.
	 */	
	private void setMenuBarEdit(JMenu menuBarEdit) {
		this.menuBarEdit = menuBarEdit;
	}
	
	/**
	 * The <i>New</i> item of menu file getter.
	 * 
	 * @return the new item of menu file.
	 */
	public JMenuItem getMenuBarFileNew() {
		return menuBarFileNew;
	}

	/**
	 * The <i>New</i> item of menu file setter. 
	 * 
	 * @param menuBarFileNew item to set
	 */
	public void setMenuBarFileNew(JMenuItem menuBarFileNew) {
		this.menuBarFileNew = menuBarFileNew;
	}

	/**
	 * The <i>Load</i> item of menu file getter.
	 * 
	 * @return the load item of menu file.
	 */
	public JMenuItem getMenuBarFileLoad() {
		return menuBarFileLoad;
	}

	/**
	 * The <i>Load</i> item of menu file setter. 
	 * 
	 * @param menuBarFileLoad item to set.
	 */
	public void setMenuBarFileLoad(JMenuItem menuBarFileLoad) {
		this.menuBarFileLoad = menuBarFileLoad;
	}

	/**
	 * The <i>Save</i> item of menu file getter.
	 * 
	 * @return the save item of menu file.
	 */
	public JMenuItem getMenuBarFileSave() {
		return menuBarFileSave;
	}

	/**
	 * The <i>Save</i> item of menu file setter. 
	 * 
	 * @param menuBarFileSave item to set.
	 */
	public void setMenuBarFileSave(JMenuItem menuBarFileSave) {
		this.menuBarFileSave = menuBarFileSave;
	}

	/**
	 * The <i>Import</i> item of menu file getter.
	 * 
	 * @return the import item of menu file.
	 */
	public JMenuItem getMenuBarFileImport() {
		return menuBarFileImport;
	}

	/**
	 * The <i>Import</i> item of menu file setter.
	 *  
	 * @param menuBarFileImport item to set
	 */
	public void setMenuBarFileImport(JMenuItem menuBarFileImport) {
		this.menuBarFileImport = menuBarFileImport;
	}
	
	/**
	 * The <i>Select</i> item of menu edit getter.
	 * 
	 * @return the select item of menu edit.
	 */
	private JMenuItem getMenuItemSelect() {
		return menuItemSelect;
	}

	/**
	 * The <i>Select</i> item of menu edit setter.
	 *  
	 * @param menuItemSelect item to set
	 */
	private void setMenuItemSelect(JMenuItem menuItemSelect) {
		this.menuItemSelect = menuItemSelect;
	}
	
	/**
	 * The <i>Copy</i> item of menu edit getter.
	 * 
	 * @return the copy item of menu edit.
	 */
	private JMenuItem getMenuItemCopy() {
		return menuItemCopy;
	}

	/**
	 * The <i>Copy</i> item of menu edit setter.
	 *  
	 * @param menuItemCopy item to set
	 */
	private void setMenuItemCopy(JMenuItem menuItemCopy) {
		this.menuItemCopy = menuItemCopy;
	}

	/**
	 * The <i>Cut</i> item of menu edit getter.
	 * 
	 * @return the cut item of menu edit.
	 */
	private JMenuItem getMenuItemCut() {
		return menuItemCut;
	}

	/**
	 * The <i>Cut</i> item of menu edit setter.
	 *  
	 * @param menuItemCut item to set
	 */
	private void setMenuItemCut(JMenuItem menuItemCut) {
		this.menuItemCut = menuItemCut;
	}

	/**
	 * The <i>Paste</i> item of menu edit getter.
	 * 
	 * @return the paste item of menu edit.
	 */
	private JMenuItem getMenuItemPaste() {
		return menuItemPaste;
	}

	/**
	 * The <i>Paste</i> item of menu edit setter.
	 *  
	 * @param menuItemPaste item to set
	 */
	private void setMenuItemPaste(JMenuItem menuItemPaste) {
		this.menuItemPaste = menuItemPaste;
	}
	
}
