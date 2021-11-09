package com.unitelmasapienza.asciiart.imageeditor.view;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
	
	
	//Methods to build with object properties
	
	public ImageEditorViewBuilder setCharColorPreview(JPanel... charColorPreview) {
		
		if(charColorPreview.length > 0) setCharBackgroundColorPreview(charBackgroundColorPreview);
		else setCharBackgroundColorPreview(new JPanel());
		
		return this;
	}
	
	public ImageEditorViewBuilder setCharBackgroundColorPreview(JPanel... charBackgroundColorPreview) {
		
		if(charBackgroundColorPreview.length > 0) setCharBackgroundColorPreview(charBackgroundColorPreview);
		else setCharBackgroundColorPreview(new JPanel());
		
		return this;
	}
	
	public ImageEditorViewBuilder setMinusButton(JButton... minusButton) {
		
		if(minusButton.length > 0) setMinusButton(minusButton);
		else setMinusButton(new JButton("-"));
		
		return this;
	}
	
	public ImageEditorViewBuilder setPlusButton(JButton... plusButton) {
		
		if(plusButton.length > 0) setPlusButton(plusButton);
		else setPlusButton(new JButton("+"));
		
		return this;
	}
	
	public ImageEditorViewBuilder setCharIndexButton(JButton... charIndexButton) {
		
		if(charIndexButton.length > 0 ) setCharIndexButton(charIndexButton);
		else setCharIndexButton(new JButton("1"));
		
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	//Chiamare alla fine per il controlTool che conterra' tutti gli elementi fino ad ora creati
	public ImageEditorViewBuilder createControlTool() {
		
		JPanel controlTool = new JPanel();
		controlTool.setLayout(null);
		
		return this;
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
	
}
