package com.unitelmasapienza.asciiart.imageeditor.view;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;

public class ImageEditorViewBuilder {
	
	private AsciiPanel panel;
	private Color drawnCharColor = Color.WHITE;
	private Color drawnCharBackgroundColor = Color.BLACK;
	private Integer selectedChar = 1;
	private Integer selectedToolIndex = 0;
	private JPanel charColorPreview;
	private JPanel charBackgroundColorPreview;
	private JButton minusButton;
	private JButton plusButton;
	private JButton charIndexButton;
	private JButton pickToolButton;
	private JButton paintToolButton;
	private JButton fillToolButton;
	private AsciiPanel selectedCharPreview;
	private BufferedImage importBI;
	private JMenuBar menuBar;
	private JMenu menuBarFile;
	private JMenuItem menuBarFileNew;
	private JMenuItem menuBarFileLoad;
	private JMenuItem menuBarFileSave;
	private JMenuItem menuBarFileImport;
	
	private JPanel controlTool;
	
	
	//Methods to build with object properties
	
	public ImageEditorViewBuilder selectedCharPreview(AsciiPanel... selectedChar) {
		
		if(selectedChar.length > 0 && selectedChar[0] != null) setSelectedCharPreview(selectedChar[0]);
		else setSelectedCharPreview(new AsciiPanel(1, 1, AsciiFont.CP437_16x16));
		
		return this;
	}
	
	public ImageEditorViewBuilder charColorPreview(JPanel... charColorPreview) {
		
		if(charColorPreview.length > 0) setCharColorPreview(charColorPreview[0]);
		else setCharColorPreview(new JPanel());
		
		return this;
	}
	
	public ImageEditorViewBuilder charBackgroundColorPreview(JPanel... charBackgroundColorPreview) {
		
		if(charBackgroundColorPreview.length > 0) setCharBackgroundColorPreview(charBackgroundColorPreview[0]);
		else setCharBackgroundColorPreview(new JPanel());
		
		return this;
	}
	
	public ImageEditorViewBuilder minusButton(JButton... minusButton) {
		
		if(minusButton.length > 0) setMinusButton(minusButton[0]);
		else setMinusButton(new JButton("-"));
		
		return this;
	}
	
	public ImageEditorViewBuilder plusButton(JButton... plusButton) {
		
		if(plusButton.length > 0) setPlusButton(plusButton[0]);
		else setPlusButton(new JButton("+"));
		
		return this;
	}
	
	public ImageEditorViewBuilder charIndexButton(JButton... charIndexButton) {
		
		if(charIndexButton.length > 0 ) setCharIndexButton(charIndexButton[0]);
		else setCharIndexButton(new JButton("1"));
		
		return this;
	}
	
	public ImageEditorViewBuilder pickToolButton(JButton... pickToolButton ) {
		
		if(pickToolButton.length > 0) setPickToolButton(pickToolButton[0]);
		else {
			JButton pickButton = new JButton();
			ImageIcon pickIcon = new ImageIcon("src/main/resources/icons/pick icon.png");
			pickButton.setIcon(pickIcon);
			pickButton.setToolTipText("Pick");
			setPickToolButton(pickButton);
		}
		return this;
	}
	
	public ImageEditorViewBuilder paintToolButton(JButton... paintToolButton) {
		
		if(paintToolButton.length > 0) setPaintToolButton(paintToolButton[0]);
		else {
			JButton paintButton = new JButton();
			ImageIcon paintIcon = new ImageIcon("src/main/resources/icons/paint icon.png");
			paintButton.setIcon(paintIcon);
			paintButton.setToolTipText("Paint");
			setPaintToolButton(paintButton);
		}
		return this;
	}
	
	public ImageEditorViewBuilder fillToolButton(JButton... fillToolButton) {
		
		if(fillToolButton.length > 0) setFillToolButton(fillToolButton[0]);
		else {
			JButton fillButton = new JButton();
			ImageIcon fillIcon = new ImageIcon("src/main/resources/icons/fill icon.png");
			fillButton.setIcon(fillIcon);
			fillButton.setToolTipText("Fill");
			setFillToolButton(fillButton);
		}
		
		return this;
	}
	
	//Chiamare alla fine per il controlTool che conterra' tutti gli elementi di disegno della GUI
	public ImageEditorViewBuilder createControlTool() {
		
		JPanel controlTool = new JPanel();
		controlTool.setLayout(null);
		
		if(getMinusButton() != null) controlTool.add(getMinusButton());
		if(getCharIndexButton() != null) controlTool.add(getCharIndexButton());
		if(getPlusButton() != null) controlTool.add(getPlusButton());
		if(getCharColorPreview() != null) controlTool.add(getCharColorPreview());
		if(getCharBackgroundColorPreview() != null) controlTool.add(getCharBackgroundColorPreview());
		if(getPickToolButton() != null) controlTool.add(getPickToolButton());
		if(getPaintToolButton() != null) controlTool.add(getPaintToolButton());
		if(getFillToolButton() != null) controlTool.add(getFillToolButton());
		if(getSelectedCharPreview() != null) controlTool.add(getSelectedCharPreview());
		
		setControlTool(controlTool);
		
		return this;
	}
	
	public ImageEditorViewBuilder menuBar(JMenuBar... menuBar) {
		
		if(menuBar.length > 0 && menuBar[0] != null) setMenuBar(menuBar[0]);
		else setMenuBar(new JMenuBar());
		
		return this;
	}
	
	public ImageEditorViewBuilder newFileMenuItem(JMenuItem... newFileItem) {
		
		if(newFileItem.length > 0 && newFileItem[0] != null) setMenuBarFileNew(newFileItem[0]);
		else {
			ImageIcon newIcon = new ImageIcon("src/main/resources/icons/new icon.png");
			setMenuBarFileNew(new JMenuItem("New..."));
			getMenuBarFileNew().setIcon(newIcon);
		}
		return this;
	}
	
	public ImageEditorViewBuilder loadFileMenuItem(JMenuItem... loadFileItem) {
		
		if(loadFileItem.length > 0 && loadFileItem[0] != null) setMenuBarFileLoad(loadFileItem[0]);
		else {
			ImageIcon loadIcon = new ImageIcon("src/main/resources/icons/load icon.png");
			setMenuBarFileLoad(new JMenuItem("Load..."));
			getMenuBarFileLoad().setIcon(loadIcon);
		}
		return this;
	}
	
	public ImageEditorViewBuilder saveFileMenuItem(JMenuItem... saveFileItem) {
		
		if(saveFileItem.length > 0 && saveFileItem[0] != null) setMenuBarFileSave(saveFileItem[0]);
		else {
			ImageIcon saveIcon = new ImageIcon("src/main/resources/icons/save icon.png");
			setMenuBarFileSave(new JMenuItem("Save..."));
			getMenuBarFileSave().setIcon(saveIcon);
		}
		return this;
	}
	
	public ImageEditorViewBuilder importFileMenuItem(JMenuItem... importFileItem) {
		
		if(importFileItem.length > 0 && importFileItem[0] != null) setMenuBarFileImport(importFileItem[0]);
		else {
			ImageIcon imortIcon = new ImageIcon("src/main/resources/icons/import icon.png");
			setMenuBarFileImport(new JMenuItem("Import..."));
			getMenuBarFileImport().setIcon(imortIcon);
		}
		return this;
	}
	
	
	//Chiamare alla fine per il menu' che conterra' tutte le voci di opzione aggiunte
	public ImageEditorViewBuilder addFileToMenuBar(JMenu... fileMenu) {
		
		if(fileMenu.length > 0 && fileMenu[0] != null) setMenuBarFile(fileMenu[0]);
		else {
			setMenuBarFile(new JMenu("File"));
			getMenuBar().add(getMenuBarFile());
			if(getMenuBarFileNew() != null) getMenuBarFile().add(getMenuBarFileNew());
			if(getMenuBarFileLoad() != null) getMenuBarFile().add(getMenuBarFileLoad());
			if(getMenuBarFileSave() != null) getMenuBarFile().add(getMenuBarFileSave());
			if(getMenuBarFileImport() != null) getMenuBarFile().add(getMenuBarFileImport());
		}
		
		return this;
	}
	
	public ImageEditorView build() {
		return new ImageEditorView(getSelectedCharPreview(),
				getCharColorPreview(),
				getCharBackgroundColorPreview(),
				getMinusButton(),
				getPlusButton(),
				getCharIndexButton(),
				getPickToolButton(),
				getPaintToolButton(),
				getFillToolButton(),
				getControlTool(),
				getMenuBar(),
				getMenuBarFile(),
				getMenuBarFileNew(),
				getMenuBarFileLoad(),
				getMenuBarFileSave(),
				getMenuBarFileImport());
	}
	
	
	//GETTERS AND SETTERS
	

	private AsciiPanel getPanel() {
		return panel;
	}

	private void setPanel(AsciiPanel panel) {
		this.panel = panel;
	}

	private Color getDrawnCharColor() {
		return drawnCharColor;
	}

	private void setDrawnCharColor(Color drawnCharColor) {
		this.drawnCharColor = drawnCharColor;
	}

	private Color getDrawnCharBackgroundColor() {
		return drawnCharBackgroundColor;
	}

	private void setDrawnCharBackgroundColor(Color drawnCharBackgroundColor) {
		this.drawnCharBackgroundColor = drawnCharBackgroundColor;
	}

	private Integer getSelectedChar() {
		return selectedChar;
	}

	private void setSelectedChar(Integer selectedChar) {
		this.selectedChar = selectedChar;
	}

	private Integer getSelectedToolIndex() {
		return selectedToolIndex;
	}

	private void setSelectedToolIndex(Integer selectedToolIndex) {
		this.selectedToolIndex = selectedToolIndex;
	}

	private JPanel getCharColorPreview() {
		return charColorPreview;
	}

	private void setCharColorPreview(JPanel charColorPreview) {
		this.charColorPreview = charColorPreview;
	}

	private JPanel getCharBackgroundColorPreview() {
		return charBackgroundColorPreview;
	}

	private void setCharBackgroundColorPreview(JPanel charBackgroundColorPreview) {
		this.charBackgroundColorPreview = charBackgroundColorPreview;
	}

	private JButton getMinusButton() {
		return minusButton;
	}

	private void setMinusButton(JButton minusButton) {
		this.minusButton = minusButton;
	}

	private JButton getPlusButton() {
		return plusButton;
	}

	private void setPlusButton(JButton plusButton) {
		this.plusButton = plusButton;
	}

	private JButton getCharIndexButton() {
		return charIndexButton;
	}

	private void setCharIndexButton(JButton charIndexButton) {
		this.charIndexButton = charIndexButton;
	}

	private JButton getPickToolButton() {
		return pickToolButton;
	}

	private void setPickToolButton(JButton pickToolButton) {
		this.pickToolButton = pickToolButton;
	}

	private JButton getPaintToolButton() {
		return paintToolButton;
	}

	private void setPaintToolButton(JButton paintToolButton) {
		this.paintToolButton = paintToolButton;
	}

	private JButton getFillToolButton() {
		return fillToolButton;
	}

	private void setFillToolButton(JButton fillToolButton) {
		this.fillToolButton = fillToolButton;
	}

	private AsciiPanel getSelectedCharPreview() {
		return selectedCharPreview;
	}

	private void setSelectedCharPreview(AsciiPanel selectedCharPreview) {
		this.selectedCharPreview = selectedCharPreview;
	}

	private BufferedImage getImportBI() {
		return importBI;
	}

	private void setImportBI(BufferedImage importBI) {
		this.importBI = importBI;
	}

	private JMenuBar getMenuBar() {
		return menuBar;
	}

	private void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	private JMenu getMenuBarFile() {
		return menuBarFile;
	}

	private void setMenuBarFile(JMenu menuBarFile) {
		this.menuBarFile = menuBarFile;
	}

	private JMenuItem getMenuBarFileNew() {
		return menuBarFileNew;
	}

	private void setMenuBarFileNew(JMenuItem menuBarFileNew) {
		this.menuBarFileNew = menuBarFileNew;
	}

	private JMenuItem getMenuBarFileLoad() {
		return menuBarFileLoad;
	}

	private void setMenuBarFileLoad(JMenuItem menuBarFileLoad) {
		this.menuBarFileLoad = menuBarFileLoad;
	}

	private JMenuItem getMenuBarFileSave() {
		return menuBarFileSave;
	}

	private void setMenuBarFileSave(JMenuItem menuBarFileSave) {
		this.menuBarFileSave = menuBarFileSave;
	}

	private JMenuItem getMenuBarFileImport() {
		return menuBarFileImport;
	}

	private void setMenuBarFileImport(JMenuItem menuBarFileImport) {
		this.menuBarFileImport = menuBarFileImport;
	}
	
	private JPanel getControlTool() {
		return controlTool;
	}

	private void setControlTool(JPanel controlTool) {
		this.controlTool = controlTool;
	}

}
