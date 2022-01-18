package com.unitelmasapienza.asciiart.imageeditor.views;

import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;

/**
 * This class is an implementation of <b>Builder Design Pattern</b>.
 * We direct call this Builder class for create and initialize an ImageEditorView object.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ImageEditorViewBuilder {
	
	/**
	 * Follow all relevant fields to inizialize of ImageEditorView.
	 * 
	 * @see ImageEditorView to understand every field.
	 * 
	 */
	
	private JPanel charColorPreview;
	private JPanel charBackgroundColorPreview;
	private JButton minusButton;
	private JButton plusButton;
	private JButton undoButton;
	private JButton redoButton;
	private JButton charIndexButton;
	private AsciiPanel selectedCharPreview;
	private BufferedImage importBI;
	private JMenuBar menuBar;
	private JMenu menuBarFile;
	private JMenu menuBarTools;
	private JMenuItem menuBarFileNew;
	private JMenuItem menuBarFileLoad;
	private JMenuItem menuBarFileSave;
	private JMenuItem menuBarFileImport;
	private JMenuItem menuBarToolsPaint;
	private JMenuItem menuBarToolsFill;
	private JMenuItem menuBarToolsPick;
	
	//This field will be passed to concrete view object already
	private JPanel controlTool;
	
	/**
	 * This method sets the field selectedCharPreview.
	 * Its input could be null. In this case, it will set the default preview with default font.
	 * 
	 * @param selectedChar is the eventual char preview
	 * 
	 * @return  Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder selectedCharPreview(AsciiPanel... selectedChar) {
		
		if(selectedChar.length > 0 && selectedChar[0] != null) setSelectedCharPreview(selectedChar[0]);
		else {
			AsciiPanel charPreview = new AsciiPanel(1, 1, AsciiFont.CP437_16x16);
			charPreview.setToolTipText("Selected Char Preview");
			setSelectedCharPreview(charPreview);
		}
		
		return this;
	}
	
	/**
	 * This method sets the field charColorPreview.
	 * Its input could be null. In this case, it will set the default preview with a new one.
	 * 
	 * @param charColorPreview is the eventual char color preview
	 * 
	 * @return  Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder charColorPreview(JPanel... charColorPreview) {
		
		if(charColorPreview.length > 0) setCharColorPreview(charColorPreview[0]);
		else {
			JPanel panel = new JPanel();
			panel.setToolTipText("Char Color");
			setCharColorPreview(panel);
		}
		
		return this;
	}
	
	/**
	 * This method sets the field charBackgroundColorPreview.
	 * Its input could be null. In this case, it will set the default color preview with a new one.
	 * 
	 * @param charBackgroundColorPreview is the eventual char background color preview
	 * 
	 * @return  Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder charBackgroundColorPreview(JPanel... charBackgroundColorPreview) {
		
		if(charBackgroundColorPreview.length > 0) setCharBackgroundColorPreview(charBackgroundColorPreview[0]);
		else {
			JPanel panel = new JPanel();
			panel.setToolTipText("Char Background Color");
			setCharBackgroundColorPreview(panel);
		}
		
		return this;
	}
	
	/**
	 * This method sets the minus button.
	 * Its input could be null. In this case, it will create a new minus button.
	 * 
	 * @param minusButton is the button to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder minusButton(JButton... minusButton) {
		
		if(minusButton.length > 0) setMinusButton(minusButton[0]);
		else {
			JButton button = new JButton();
			Icon minusIcon = new ImageIcon("src/main/resources/icons/minus icon.png");
			button.setIcon(minusIcon);
			button.setToolTipText("Previous Char");
			setMinusButton(button);
		}
		
		return this;
	}
	
	/**
	 * This method sets the plus button.
	 * Its input could be null. In this case, it will create a new plus button.
	 * 
	 * @param plusButton is the button to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder plusButton(JButton... plusButton) {
		
		if(plusButton.length > 0) setPlusButton(plusButton[0]);
		else {
			JButton button = new JButton();
			Icon plusIcon = new ImageIcon("src/main/resources/icons/plus icon.png");
			button.setIcon(plusIcon);
			button.setToolTipText("Next Char");
			setPlusButton(button);
		}
		
		return this;
	}
	
	/**
	 * This method sets the undo button.
	 * Its input could be null. In this case, it will create a new undo button.
	 * 
	 * @param undoButton is the button to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder undoButton(JButton... undoButton) {
		
		if(undoButton.length > 0) setUndoButton(undoButton[0]);
		else {
			JButton button = new JButton();
			Icon undoIcon = new ImageIcon("src/main/resources/icons/undo icon.png");
			button.setIcon(undoIcon);
			button.setToolTipText("Undo");
			setUndoButton(button);
		}
		
		return this;
	}
	
	/**
	 * This method sets the redo button.
	 * Its input could be null. In this case, it will create a new redo button.
	 * 
	 * @param redoButton is the button to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder redoButton(JButton... redoButton) {
		
		if(redoButton.length > 0) setRedoButton(redoButton[0]);
		else {
			JButton button = new JButton();
			Icon redoIcon = new ImageIcon("src/main/resources/icons/redo icon.png");
			button.setIcon(redoIcon);
			button.setToolTipText("Redo");
			setRedoButton(button);
		}
		
		return this;
	}
	
	/**
	 * This method sets the char index button.
	 * Its input could be null. In this case, it will create a new char index button.
	 * 
	 * @param charIndexButton is the button to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder charIndexButton(JButton... charIndexButton) {
		
		if(charIndexButton.length > 0 ) setCharIndexButton(charIndexButton[0]);
		else {
			JButton button = new JButton("1");
			button.setToolTipText("Characters List");
			setCharIndexButton(button);
		}
		
		return this;
	}
	
	
	/**
	 * This method is to set the all GUI components (buttons, preview etc.) to the GUI control tool.
	 * The control tool will contains all GUI elements to control and use the application by user.
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder createControlTool() {
		
		JPanel controlTool = new JPanel();
		controlTool.setLayout(null);
		
		if(getMinusButton() != null) controlTool.add(getMinusButton());
		if(getCharIndexButton() != null) controlTool.add(getCharIndexButton());
		if(getPlusButton() != null) controlTool.add(getPlusButton());
		if(getCharColorPreview() != null) controlTool.add(getCharColorPreview());
		if(getCharBackgroundColorPreview() != null) controlTool.add(getCharBackgroundColorPreview());
		if(getSelectedCharPreview() != null) controlTool.add(getSelectedCharPreview());
		if(getUndoButton() != null) controlTool.add(getUndoButton());
		if(getRedoButton() != null) controlTool.add(getRedoButton());
		
		setControlTool(controlTool);
		
		return this;
	}
	
	/**
	 * This method sets the top menu bar of application.
	 * Its input could be null. In this case, it will create a new menu bar.
	 * 
	 * @param menuBar is the menu bar to set.
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder menuBar(JMenuBar... menuBar) {
		
		if(menuBar.length > 0 && menuBar[0] != null) setMenuBar(menuBar[0]);
		else setMenuBar(new JMenuBar());
		
		return this;
	}
	
	/**
	 * This method sets the 'New' menu option on top File menu bar.
	 * Its input could be null. In this case, it will create a new menu item on File.
	 * 
	 * @param newFileItem is the item to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder newFileMenuItem(JMenuItem... newFileItem) {
		
		if(newFileItem.length > 0 && newFileItem[0] != null) setMenuBarFileNew(newFileItem[0]);
		else {
			ImageIcon newIcon = new ImageIcon("src/main/resources/icons/new icon.png");
			setMenuBarFileNew(new JMenuItem("New..."));
			getMenuBarFileNew().setIcon(newIcon);
		}
		return this;
	}
	
	/**
	 * This method sets the 'Load' menu option on top File menu bar.
	 * Its input could be null. In this case, it will create a new menu item on File.
	 * 
	 * @param loadFileItem is the item to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder loadFileMenuItem(JMenuItem... loadFileItem) {
		
		if(loadFileItem.length > 0 && loadFileItem[0] != null) setMenuBarFileLoad(loadFileItem[0]);
		else {
			ImageIcon loadIcon = new ImageIcon("src/main/resources/icons/load icon.png");
			setMenuBarFileLoad(new JMenuItem("Load..."));
			getMenuBarFileLoad().setIcon(loadIcon);
		}
		return this;
	}
	
	/**
	 * This method sets the 'Save' menu option on top File menu bar.
	 * Its input could be null. In this case, it will create a new menu item on File.
	 * 
	 * @param saveFileItem is the item to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder saveFileMenuItem(JMenuItem... saveFileItem) {
		
		if(saveFileItem.length > 0 && saveFileItem[0] != null) setMenuBarFileSave(saveFileItem[0]);
		else {
			ImageIcon saveIcon = new ImageIcon("src/main/resources/icons/save icon.png");
			setMenuBarFileSave(new JMenuItem("Save..."));
			getMenuBarFileSave().setIcon(saveIcon);
		}
		return this;
	}
	
	/**
	 * This method sets the 'Import' menu option on top File menu bar.
	 * Its input could be null. In this case, it will create a new menu item on File.
	 * 
	 * @param importFileItem is the item to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder importFileMenuItem(JMenuItem... importFileItem) {
		
		if(importFileItem.length > 0 && importFileItem[0] != null) setMenuBarFileImport(importFileItem[0]);
		else {
			ImageIcon imortIcon = new ImageIcon("src/main/resources/icons/import icon.png");
			setMenuBarFileImport(new JMenuItem("Import..."));
			getMenuBarFileImport().setIcon(imortIcon);
		}
		return this;
	}
	
	/**
	 * This method sets the 'Paint' menu option on top Tools menu bar.
	 * Its input could be null. In this case, it will create a new menu item on Tools.
	 * 
	 * @param paintToolsItem is the item to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder paintToolsMenuItem(JMenuItem... paintToolsItem) {
		
		if(paintToolsItem.length > 0 && paintToolsItem[0] != null) setMenuBarToolsPaint(paintToolsItem[0]);
		else {
			ImageIcon paintIcon = new ImageIcon("src/main/resources/icons/paint icon.png");
			setMenuBarToolsPaint(new JMenuItem("Paint"));
			getMenuBarToolsPaint().setIcon(paintIcon);
		}
		return this;
	}
	
	/**
	 * This method sets the 'Fill' menu option on top Tools menu bar.
	 * Its input could be null. In this case, it will create a new menu item on Tools.
	 * 
	 * @param fillToolsItem is the item to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder fillToolsMenuItem(JMenuItem... fillToolsItem) {
		
		if(fillToolsItem.length > 0 && fillToolsItem[0] != null) setMenuBarToolsFill(fillToolsItem[0]);
		else {
			ImageIcon fillIcon = new ImageIcon("src/main/resources/icons/fill icon.png");
			setMenuBarToolsFill(new JMenuItem("Fill"));
			getMenuBarToolsFill().setIcon(fillIcon);
		}
		return this;
	}
	
	/**
	 * This method sets the 'Pick' menu option on top Tools menu bar.
	 * Its input could be null. In this case, it will create a new menu item on Tools.
	 * 
	 * @param fillToolsItem is the item to set
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder pickToolsMenuItem(JMenuItem... pickToolsItem) {
		
		if(pickToolsItem.length > 0 && pickToolsItem[0] != null) setMenuBarToolsPick(pickToolsItem[0]);
		else {
			ImageIcon pickIcon = new ImageIcon("src/main/resources/icons/pick icon.png");
			setMenuBarToolsPick(new JMenuItem("Pick"));
			getMenuBarToolsPick().setIcon(pickIcon);
		}
		return this;
	}
	
	/**
	 * This method add all menu item to 'File' option of top menu bar.
	 * 
	 * @param fileMenu is the file menu to set.
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder addFileToMenuBar(JMenu... fileMenu) {
		
		if(fileMenu.length > 0 && fileMenu[0] != null) setMenuBarFile(fileMenu[0]);
		else {
			//Setting 'File' menu
			setMenuBarFile(new JMenu("File"));
			getMenuBar().add(getMenuBarFile());
			if(getMenuBarFileNew() != null) getMenuBarFile().add(getMenuBarFileNew());
			if(getMenuBarFileLoad() != null) getMenuBarFile().add(getMenuBarFileLoad());
			if(getMenuBarFileSave() != null) getMenuBarFile().add(getMenuBarFileSave());
			if(getMenuBarFileImport() != null) getMenuBarFile().add(getMenuBarFileImport());
		}
		
		return this;
	}
	
	/**
	 * This method add all menu item to 'Tools' option of top menu bar.
	 * 
	 * @param toolsMenu is the file menu to set.
	 * 
	 * @return Builder itself, as <i>Builder Pattern</i>.
	 */
	public ImageEditorViewBuilder addToolsToMenuBar(JMenu... toolsMenu) {
		
		if(toolsMenu.length > 0 && toolsMenu[0] != null) setMenuBarTools(toolsMenu[0]);
		else {
			//Setting 'Tools' menu
			setMenuBarTools(new JMenu("Tools"));
			getMenuBar().add(getMenuBarTools());
			if(getMenuBarToolsPaint() != null) getMenuBarTools().add(getMenuBarToolsPaint());
			if(getMenuBarToolsFill() != null) getMenuBarTools().add(getMenuBarToolsFill());
			if(getMenuBarToolsPick() != null) getMenuBarTools().add(getMenuBarToolsPick());
		}
		
		return this;
	}
	
	/**
	 * This method is the final method in the cascade calls to create a concrete ImageEditorView object.
	 * It direct calls the constructor of ImageEditorView passing all its values (already sets by previous calls).
	 * 
	 * @return the final ImageEditorView object
	 */
	public ImageEditorView build() {
		return new ImageEditorView(getSelectedCharPreview(),
				getCharColorPreview(),
				getCharBackgroundColorPreview(),
				getMinusButton(),
				getPlusButton(),
				getUndoButton(),
				getRedoButton(),
				getCharIndexButton(),
				getControlTool(),
				getMenuBar(),
				getMenuBarFile(),
				getMenuBarTools(),
				getMenuBarFileNew(),
				getMenuBarFileLoad(),
				getMenuBarFileSave(),
				getMenuBarFileImport(),
				getMenuBarToolsPaint(),
				getMenuBarToolsFill(),
				getMenuBarToolsPick()
				);
	}
	
	
	/**
	 * <b>Builder</b> Version of the char color preview getter.
	 * 
	 * @return the char color preview.
	 */
	private JPanel getCharColorPreview() {
		return charColorPreview;
	}

	/**
	 * <b>Builder</b> Version of the char color preview setter.
	 * 
	 * @param charColorPreview is the color preview to set.
	 */
	private void setCharColorPreview(JPanel charColorPreview) {
		this.charColorPreview = charColorPreview;
	}

	/**
	 * <b>Builder</b> Version of the char background color preview getter.
	 * 
	 * @return the background color preview. 
	 */
	private JPanel getCharBackgroundColorPreview() {
		return charBackgroundColorPreview;
	}

	/**
	 * <b>Builder</b> Version of the char background color preview setter.
	 *  
	 * @param charBackgroundColorPreview is the background color preview to set.
	 */
	private void setCharBackgroundColorPreview(JPanel charBackgroundColorPreview) {
		this.charBackgroundColorPreview = charBackgroundColorPreview;
	}

	/**
	 * <b>Builder</b> Version of the minus button getter.
	 * 
	 * @return the minus button.
	 */
	private JButton getMinusButton() {
		return minusButton;
	}

	/**
	 * <b>Builder</b> Version of the minus button setter.
	 * 
	 * @param minusButton is the minus button to set.
	 */
	private void setMinusButton(JButton minusButton) {
		this.minusButton = minusButton;
	}

	/**
	 * <b>Builder</b> Version of the plus button getter.
	 * 
	 * @return the plus button.
	 */
	private JButton getPlusButton() {
		return plusButton;
	}

	/**
	 * <b>Builder</b> Version of the plus button setter.
	 * 
	 * @param plusButton is the plus button to set.
	 */
	private void setPlusButton(JButton plusButton) {
		this.plusButton = plusButton;
	}
	
	/**
	 * <b>Builder</b> Version of the undo button getter.
	 * 
	 * @return the undo button.
	 */
	private JButton getUndoButton() {
		return undoButton;
	}

	/**
	 * <b>Builder</b> Version of the undo button setter.
	 * 
	 * @param undoButton is the undo button to set.
	 */
	private void setUndoButton(JButton undoButton) {
		this.undoButton = undoButton;
	}

	/**
	 * <b>Builder</b> Version of the redo button getter.
	 * 
	 * @return the redo button.
	 */
	private JButton getRedoButton() {
		return redoButton;
	}

	/**
	 * <b>Builder</b> Version of the redo button setter.
	 * 
	 * @param redoButton is the redo button to set.
	 */
	private void setRedoButton(JButton redoButton) {
		this.redoButton = redoButton;
	}
	
	/**
	 * <b>Builder</b> Version of the char index button getter.
	 * 
	 * @return char index button
	 */
	private JButton getCharIndexButton() {
		return charIndexButton;
	}

	/**
	 * <b>Builder</b> Version of the char index button setter.
	 * 
	 * @param charIndexButton is the button to set.
	 */
	private void setCharIndexButton(JButton charIndexButton) {
		this.charIndexButton = charIndexButton;
	}

	/**
	 * <b>Builder</b> Version of the selected char preview getter.
	 * 
	 * @return the selected char preview.
	 */
	private AsciiPanel getSelectedCharPreview() {
		return selectedCharPreview;
	}

	/**
	 * <b>Builder</b> Version of the selected char preview setter.
	 * 
	 * @param selectedCharPreview is the selected char preview to set.
	 */
	private void setSelectedCharPreview(AsciiPanel selectedCharPreview) {
		this.selectedCharPreview = selectedCharPreview;
	}

	/**
	 * <b>Builder</b> Version of the import buffered image getter.
	 * 
	 * @return the import buffered image.
	 */
	private BufferedImage getImportBI() {
		return importBI;
	}

	/**
	 * <b>Builder</b> Version of the import buffered image setter.
	 * 
	 * @param importBI is the buffered image to set.
	 */
	private void setImportBI(BufferedImage importBI) {
		this.importBI = importBI;
	}

	/**
	 * <b>Builder</b> Version of the menu bar getter.
	 * 
	 * @return the menu bar.
	 */
	private JMenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * <b>Builder</b> Version of the menu bar setter.
	 * 
	 * @param menuBar is the menu bar to set.
	 */
	private void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File getter.
	 * 
	 * @return the menu bar File
	 */
	private JMenu getMenuBarFile() {
		return menuBarFile;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File setter.
	 * 
	 * @param menuBarFile is the menu bar file to set.
	 */
	private void setMenuBarFile(JMenu menuBarFile) {
		this.menuBarFile = menuBarFile;
	}

	/**
	 * <b>Builder</b> Version of the menu bar Tools getter.
	 * 
	 * @return the menu bar Tools
	 */
	private JMenu getMenuBarTools() {
		return menuBarTools;
	}

	/**
	 * <b>Builder</b> Version of the menu bar Tools setter.
	 * 
	 * @param menuBarTools is the menu bar tools to set.
	 */
	private void setMenuBarTools(JMenu menuBarTools) {
		this.menuBarTools = menuBarTools;
	}
	
	/**
	 * <b>Builder</b> Version of the menu bar File <i>New</i> item getter.
	 * 
	 * @return the menu item for 'New' option.
	 */
	private JMenuItem getMenuBarFileNew() {
		return menuBarFileNew;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File <i>New</i> item setter.
	 * 
	 * @param menuBarFileNew is the menu item for 'New' option to set.
	 */
	private void setMenuBarFileNew(JMenuItem menuBarFileNew) {
		this.menuBarFileNew = menuBarFileNew;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File <i>Load</i> item getter.
	 * 
	 * @return the menu item for 'Load' option.
	 */
	private JMenuItem getMenuBarFileLoad() {
		return menuBarFileLoad;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File <i>Load</i> item setter.
	 * 
	 * @param menuBarFileLoad is the menu item for 'Load' option to set.
	 */
	private void setMenuBarFileLoad(JMenuItem menuBarFileLoad) {
		this.menuBarFileLoad = menuBarFileLoad;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File <i>Save</i> item getter.
	 * 
	 * @return the menu item for 'Save' option.
	 */
	private JMenuItem getMenuBarFileSave() {
		return menuBarFileSave;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File <i>Save</i> item setter.
	 * 
	 * @param menuBarFileSave is the menu item for 'Save' option to set.
	 */
	private void setMenuBarFileSave(JMenuItem menuBarFileSave) {
		this.menuBarFileSave = menuBarFileSave;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File <i>Import</i> item getter.
	 * 
	 * @return the menu item for 'Import' option.
	 */
	private JMenuItem getMenuBarFileImport() {
		return menuBarFileImport;
	}

	/**
	 * <b>Builder</b> Version of the menu bar File <i>Import</i> item setter.
	 * 
	 * @param menuBarFileImport is the menu item for 'Import' option to set.
	 */
	private void setMenuBarFileImport(JMenuItem menuBarFileImport) {
		this.menuBarFileImport = menuBarFileImport;
	}
	
	/**
	 * <b>Builder</b> Version of the GUI control tool panel getter.
	 * 
	 * @return the GUI control tool.
	 */
	private JPanel getControlTool() {
		return controlTool;
	}

	/**
	 * <b>Builder</b> Version of the GUI control tool panel setter.
	 * 
	 * @param controlTool is the GUI control tool panel to set.
	 */
	private void setControlTool(JPanel controlTool) {
		this.controlTool = controlTool;
	}

	/**
	 * <b>Builder</b> Version of the menu bar Tools <i>Paint</i> item getter.
	 * 
	 * @return the menu item for 'Paint' option.
	 */
	private JMenuItem getMenuBarToolsPaint() {
		return menuBarToolsPaint;
	}

	/**
	 * <b>Builder</b> Version of the menu bar Tools <i>Paint</i> item setter.
	 * 
	 * @param menuBarToolsPaint is the menu item for 'Paint' option to set.
	 */
	private void setMenuBarToolsPaint(JMenuItem menuBarToolsPaint) {
		this.menuBarToolsPaint = menuBarToolsPaint;
	}

	/**
	 * <b>Builder</b> Version of the menu bar Tools <i>Fill</i> item getter.
	 * 
	 * @return the menu item for 'Fill' option.
	 */
	private JMenuItem getMenuBarToolsFill() {
		return menuBarToolsFill;
	}

	/**
	 * <b>Builder</b> Version of the menu bar Tools <i>Fill</i> item setter.
	 * 
	 * @param menuBarToolsFill is the menu item for 'Paint' option to set.
	 */
	private void setMenuBarToolsFill(JMenuItem menuBarToolsFill) {
		this.menuBarToolsFill = menuBarToolsFill;
	}
	
	/**
	 * <b>Builder</b> Version of the menu bar Tools <i>Pick</i> item getter.
	 * 
	 * @return the menu item for 'Pick' option.
	 */
	private JMenuItem getMenuBarToolsPick() {
		return menuBarToolsPick;
	}

	/**
	 * <b>Builder</b> Version of the menu bar Tools <i>Pick</i> item setter.
	 * 
	 * @param menuBarToolsPick is the menu item for 'Pick' option to set.
	 */
	private void setMenuBarToolsPick(JMenuItem menuBarToolsPick) {
		this.menuBarToolsPick = menuBarToolsPick;
	}
	
	

}
