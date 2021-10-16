package com.unitelmasapienza.asciiart.imageeditor.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;
import com.unitelmasapienza.asciiart.asciipanel.factory.AsciiPanelFactory;
import com.unitelmasapienza.asciiart.imageeditor.ActionLoad;
import com.unitelmasapienza.asciiart.imageeditor.ActionSave;
import com.unitelmasapienza.asciiart.imageeditor.CharacterSelector;
import com.unitelmasapienza.asciiart.imageeditor.ImageImporter;
import com.unitelmasapienza.asciiart.imageeditor.ImageNew;
import com.unitelmasapienza.asciiart.imageeditor.listener.EditorViewMouseLintener;
import com.unitelmasapienza.asciiart.imageeditor.listener.EditorViewMouseMotionLintener;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;

public class ImageEditorController {
	
	private AsciiPanelFactory panelFactory;
	
	private AsciiPanel model;
	private ImageEditorView view;
	
	public ImageEditorController(AsciiPanelFactory panelFactory) {
		this.panelFactory = panelFactory;
		this.model = panelFactory.createAsciiPanel(80, 60, AsciiFont.CP437_16x16);
		preparingModel(model);
		this.view = new ImageEditorView();
		this.view.setPanel(model);
		this.view.add(model);
		initController();
		view.setVisible(true);
	}
	
	/**
	 * This method sets graphical information for model
	 * @param model is the model
	 */
	private void preparingModel(AsciiPanel model) {
		model.setBounds(80, 0, 80 * 16, 80 * 16);
		model.setCursorDistanceFromLeft(0);
		model.setCursorDistanceFromTop(0);
		model.write("Empty");
	}
	
	/**
	 * Method to inizialize the controller. Add the actionListeners to GUI buttons
	 * of view.
	 * 
	 */
	private void initController() {
		
		//selected char button
		view.getCharIndexButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CharacterSelector.getInstance().setVisible(true);
			}
		});
		
		//pick button
		view.getPickToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(1);
			}
		});
		
		//minus button
		view.getMinusButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getSelectedChar() > 0)
					view.setSelectedChar(view.getSelectedChar() - 1);
				view.getCharIndexButton().setLabel(view.getSelectedChar() + "");
				view.updatePreview();

			}
		});
		
		//plus button
		view.getPlusButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getSelectedChar() < 255)
					view.setSelectedChar(view.getSelectedChar() + 1);
				view.getCharIndexButton().setLabel(view.getSelectedChar() + "");
				view.updatePreview();
			}
		});
		
		//char color preview
		view.getCharColorPreview().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(view.getCharColorPreview(), "Choose Foreground Color", view.getCharColorPreview().getBackground());

				if (newColor != null) {
					view.setDrawnCharColor(newColor);
					view.getCharColorPreview().setBackground(newColor);
				}
				view.updatePreview();

			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

		});
		
		//background char color preview
		view.getCharBackgroundColorPreview().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(view.getCharBackgroundColorPreview(), "Choose Foreground Color", view.getCharBackgroundColorPreview().getBackground());

				if (newColor != null) {
					view.setDrawnCharBackgroundColor(newColor);
					view.getCharBackgroundColorPreview().setBackground(newColor);
				}
				view.updatePreview();

			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

		});
		
		//fill button
		view.getFillToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(2);
			}
		});
		
		//paint button
		view.getPaintToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(0);
			}
		});
		
		//panel-view
		view.getPanel().addMouseListener(new EditorViewMouseLintener(view));
		view.getPanel().addMouseMotionListener(new EditorViewMouseMotionLintener(view));
		
		//menu bar
		view.getMenuBarFileSave().addActionListener(new ActionSave());
		view.getMenuBarFileLoad().addActionListener(new ActionLoad());
		view.getMenuBarFileImport().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageImporter.getInstance().setVisible(true);
				;
			}
		});
		view.getMenuBarFileNew().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageNew.getInstance().setVisible(true);
				;
			}
		});
		
		view.updatePreview();
	}
	
	
	
	
	//EVENTS
	
	/**
	 * Handles the event click on the <b>"Pick"</b> button of the GUI
	 * Replaces the font in use with one selected from those already present on the canvas 
	 * 
	 * @param button is the index of the button/function that is set
	 */
	public void onPick(int button) {
		//TODO: Valutare se prenderli dalla nuova AsciiPanelView
		int valueX = model.getMouseCursorX();
		int valueY = model.getMouseCursorY();
		view.setSelectedChar(model.pickPanelCharIndex(valueX, valueY));
		view.setDrawnCharColor(model.pickPanelCharsForegroundColors(valueX, valueY));
		view.setDrawnCharBackgroundColor(model.pickPanelCharsBackgroundColors(valueX, valueY));
		view.getCharBackgroundColorPreview().setBackground(view.getDrawnCharBackgroundColor());
		view.getCharColorPreview().setBackground(view.getDrawnCharColor());
		view.getCharBackgroundColorPreview().repaint();
		view.getCharColorPreview().repaint();
		view.getCharIndexButton().setLabel(view.getSelectedChar() + "");
		view.setSelectedToolIndex(0);
		view.updatePreview();
	}
	
	/**
	 * Handles the click event on the application drawing canvas
	 * Draws the selected character on the canvas
	 * 
	 * @param button is the index of the button/function that is set
	 */
	public void onClick(int button) {
		int valueX = model.getMouseCursorX();
		int valueY = model.getMouseCursorY();

		model.setCursorDistanceFromLeft(valueX);
		model.setCursorDistanceFromTop(valueY);

		if (button == 1)
			model.write((char) (view.getSelectedChar() + 0), view.getDrawnCharColor(), view.getDrawnCharBackgroundColor());
		else
			model.write((char) 0);

		model.repaint();
	}
	
	/**
	 * Handles the event click on the <b>"Fill"</b> button of the GUI
	 * Fills the entire drawing canvas with the selected font
	 * 
	 * @param button is the index of the button/function that is set
	 */
	public void onFill(int button) {
		int valueX = model.getMouseCursorX();
		int valueY = model.getMouseCursorY();

		if (button == 1) 
			model.fill((char) (view.getSelectedChar() + 0), valueX, valueY, view.getDrawnCharColor(), view.getDrawnCharBackgroundColor());
		else 
			model.fill((char) (0), valueX, valueY, Color.black, Color.black);

		model.repaint();
	}
	
	/**
	 * Updates the selected character when it is chosen from the character palette
	 * 
	 * @param i is the chosen character index
	 */
	public void onSelectChar(int i) {
		view.setSelectedChar(i);
		view.getCharIndexButton().setLabel(i + "");
		view.updatePreview();
	}
	
	/**
	 * Resets the drawing canvas by creating a new one. 
	 * As input it takes the dimensions of the new canvas (width and height)
	 * 
	 * @param width is the width of the new canvas
	 * @param height is the height of the new canvas
	 */
	public void reset(int width, int height) {
		view.remove(model);
		this.model = panelFactory.createAsciiPanel(width, height, AsciiFont.CP437_16x16);
		view.add(model);
		model.clear();
		model.setCursorDistanceFromLeft(0);
		model.setCursorDistanceFromTop(0);
		model.write("Empty");
		model.setBounds(80, 0, width * 16, height * 16);
		model.addMouseListener(new EditorViewMouseLintener(view));
		model.addMouseMotionListener(new EditorViewMouseMotionLintener(view));
	}

}
