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
import com.unitelmasapienza.asciiart.asciipanel.factory.AsciiPanelFactoryConcrete;
import com.unitelmasapienza.asciiart.imageeditor.factory.ImageEditorViewFactory;
import com.unitelmasapienza.asciiart.imageeditor.factory.ImageEditorViewFactoryConcrete;
import com.unitelmasapienza.asciiart.imageeditor.listener.ActionLoadView;
import com.unitelmasapienza.asciiart.imageeditor.listener.ActionSaveView;
import com.unitelmasapienza.asciiart.imageeditor.listener.EditorControllerMouseLintener;
import com.unitelmasapienza.asciiart.imageeditor.listener.EditorControllerMouseMotionLintener;
import com.unitelmasapienza.asciiart.imageeditor.view.CharacterSelectorView;
import com.unitelmasapienza.asciiart.imageeditor.view.ImageEditorView;
import com.unitelmasapienza.asciiart.imageeditor.view.ImporterView;
import com.unitelmasapienza.asciiart.imageeditor.view.NewView;

/**
 * This class, in <b>MVC (Model View Controller) Design Pattern</b> represents the controller.
 * We can see it as the controller of the application in general.
 * So as a <i>controller</i> it is responsible for initializing the components, 
 * preparing the <i>View</i> and the <i>Model</i> (which it has as private fields).
 * It is also responsible for reacting and processing the interactions that the user has with the <i>View</i>, 
 * calling the related business logic.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ImageEditorController {
	
	/**
	 * The only instance of the class
	 * 
	 */
	public static ImageEditorController instance;
	
	/**
	 * The factory that concretely creates/initialize AsciiPanel (Model)
	 * for application.
	 * 
	 */
	private AsciiPanelFactory panelFactory;
	
	/**
	 * The factory that concretely creates/initialize AsciiPanel (Model)
	 * for application.
	 * 
	 */
	private ImageEditorViewFactory viewFactory;
	
	/**
	 * This represents the <i>Model</i> field for the Controller.
	 * 
	 */
	private AsciiPanel model;
	
	/**
	 * This represents the <i>View</i> field for the Controller.
	 * 
	 */
	private ImageEditorView view;
	
	/**
	 * Public constructor to create the controller and initialize model and view.
	 * It needs an AsciiPanelFactory to set the factory for instantiate AsciiPanel (Model).
	 * 
	 * @param panelFactory is the factory that will be responsible for creating AsciiPanel.
	 */
	public ImageEditorController(AsciiPanelFactory panelFactory, ImageEditorViewFactory viewFactory) {
		setPanelFactory(panelFactory);
		setViewFactory(viewFactory);
		setModel(getPanelFactory().createAsciiPanel(80, 60, AsciiFont.CP437_16x16));
		preparingModel(model);
		setView(getViewFactory().createView());
		getView().setPanel(model);
		getView().add(model);
		initController();
		getView().setVisible(true);
	}
	
	/**
	 * <b>Singleton</b> implementation. Checks if an instance of the class already exists and returns it. 
	 * If it does not exist it creates and returns it.
	 * 
	 * In fact, this can be seen as the <i>'access point'</i> of the application;  
	 * the one that initializes the controller, and consequently all the components useful for the application.
	 * 
	 * @return ImageEditorController the controller instance
	 */
	public static ImageEditorController getInstance() {
		if (instance == null)
			instance = new ImageEditorController(new AsciiPanelFactoryConcrete(), new ImageEditorViewFactoryConcrete()); //Here we're passing a valid concrete factory for AsciiPanel
		return instance;
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
	 * This is the method to initialize the controller. 
	 * It provides to make all the necessary operations to prepare the elements of the application, 
	 * then it initializes the <i>View</i> (adding all the graphical elements to the GUI) 
	 * and the relative listeners to the View and model.
	 * 
	 */
	private void initController() {
		setListeners();
		getView().updatePreview();
	}
	
	/**
	 * This method is to add all event listeners to the GUI element of application.
	 * 
	 */
	private void setListeners() {
		
		//Listener for selected char button
		view.getCharIndexButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CharacterSelectorView.getInstance().setVisible(true);
			}
		});
		
		//Listener for paint button
		view.getPaintToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(0);
			}
		});
		
		//Listener for fill button
		view.getFillToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(2);
			}
		});
		
		//Listener for pick button
		view.getPickToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(1);
			}
		});
		
		//Listener for minus button
		view.getMinusButton().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getSelectedChar() > 0)
					view.setSelectedChar(view.getSelectedChar() - 1);
				view.getCharIndexButton().setLabel(view.getSelectedChar() + "");
				view.updatePreview();

			}
		});
		
		//Listener for plus button
		view.getPlusButton().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getSelectedChar() < 255)
					view.setSelectedChar(view.getSelectedChar() + 1);
				view.getCharIndexButton().setLabel(view.getSelectedChar() + "");
				view.updatePreview();
			}
		});
		
		//Listener for char color preview
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
		
		//Listener for background char color preview
		view.getCharBackgroundColorPreview().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(view.getCharBackgroundColorPreview(), "Choose Background Color", view.getCharBackgroundColorPreview().getBackground());

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
		
		//Listener for panel-view
		view.getPanel().addMouseListener(new EditorControllerMouseLintener(this));
		view.getPanel().addMouseMotionListener(new EditorControllerMouseMotionLintener(this));
		
		//Listener for menu bar
		view.getMenuBarFileSave().addActionListener(new ActionSaveView());
		view.getMenuBarFileLoad().addActionListener(new ActionLoadView());
		view.getMenuBarFileImport().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImporterView.getInstance().setVisible(true);
				;
			}
		});
		view.getMenuBarFileNew().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewView.getInstance().setVisible(true);
				;
			}
		});
	}
	
	
	//EVENTS
	

	/**
	 * Handles the event click on the <b>"Pick"</b> button of the GUI
	 * Replaces the font in use with one selected from those already present on the canvas 
	 * 
	 * @param button is the index of the button/function that is set
	 */
	public void onPick(int button) {
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
		
		model.clear();
		model.setCursorDistanceFromLeft(0);
		model.setCursorDistanceFromTop(0);
		model.write("Empty");
		model.setBounds(80, 0, width * 16, height * 16);
		
		view.setPanel(model);
		view.add(model);
		view.getPanel().addMouseListener(new EditorControllerMouseLintener(this));
		view.getPanel().addMouseMotionListener(new EditorControllerMouseMotionLintener(this));
	}
	
	/**
	 * Called when the mouse is moved within the canvas. 
	 * Saves the <i>x</i> and <i>y</i> coordinates of the cursor and the selected button/function.
	 * @param button is the index of the button/function that is set
	 * @param x is the value of <i>x</i>-coordinate
	 * @param y is the value of <i>y</i>-coordinate
	 */
	public void onCursorMove(int button, int x, int y) {
		view.getPanel().setMouseCursorX(x / 16);
		view.getPanel().setMouseCursorY(y / 16);
		view.getPanel().repaint();
	}
	
	
	
	//GETTERS AND SETTERS
	
	
	/**
	 * The panel factory getter.
	 * It's used to use the panelFatory inside this class.
	 * 
	 * @return this panelFactory.
	 */
	private AsciiPanelFactory getPanelFactory() {
		return panelFactory;
	}
	
	/**
	 * The panel factory setter.
	 * It's a private setter because the purpose is to call it only inside the controller constructor,
	 * passing the concrete factory.
	 * 
	 * @param panelFactory is the concrete factory of AsciiPanel for controller.
	 */
	private void setPanelFactory(AsciiPanelFactory panelFactory) {
		this.panelFactory = panelFactory;
	}

	/**
	 * The view factory getter.
	 * It's used to use the viewFactory inside this class.
	 * 
	 * @return this viewFactory.
	 */
	private ImageEditorViewFactory getViewFactory() {
		return viewFactory;
	}

	/**
	 * The view factory setter.
	 * It's a private setter because the purpose is to call it only inside the controller constructor,
	 * passing the concrete factory.
	 * 
	 * @param viewFactory is the concrete factory of ImageEditorView for controller.
	 */
	private void setViewFactory(ImageEditorViewFactory viewFactory) {
		this.viewFactory = viewFactory;
	}

	/**
	 * Controller <i>Model</i> getter.
	 * 
	 * @return the controller's model
	 */
	public AsciiPanel getModel() {
		return model;
	}

	/**
	 * Controller <i>model</i> setter.
	 * 
	 * @param model is the model to set in controller's model field.
	 */
	public void setModel(AsciiPanel model) {
		this.model = model;
	}

	/**
	 * Controller <i>view</i> getter.
	 * 
	 * @return the controller's view
	 */
	public ImageEditorView getView() {
		return view;
	}

	/**
	 * Controller <i>view</i> setter.
	 * 
	 * @param view is the view to set in controller's view field.
	 */
	public void setView(ImageEditorView view) {
		this.view = view;
	}

}
