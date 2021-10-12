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
		this.view.add(model);
		initController();
	}
	
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
		model.addMouseListener(new EditorViewMouseLintener(view));
		model.addMouseMotionListener(new EditorViewMouseMotionLintener(view));
		
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


}
