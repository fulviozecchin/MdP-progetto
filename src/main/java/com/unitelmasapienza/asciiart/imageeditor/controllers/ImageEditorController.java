package com.unitelmasapienza.asciiart.imageeditor.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.JColorChooser;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;
import com.unitelmasapienza.asciiart.asciipanel.PanelChar;
import com.unitelmasapienza.asciiart.asciipanel.factories.AsciiPanelFactory;
import com.unitelmasapienza.asciiart.asciipanel.factories.AsciiPanelFactoryConcrete;
import com.unitelmasapienza.asciiart.imageeditor.factories.ImageEditorViewFactory;
import com.unitelmasapienza.asciiart.imageeditor.factories.ImageEditorViewFactoryConcrete;
import com.unitelmasapienza.asciiart.imageeditor.listeners.ActionLoadListener;
import com.unitelmasapienza.asciiart.imageeditor.listeners.ActionSaveListener;
import com.unitelmasapienza.asciiart.imageeditor.listeners.ControllerMouseLintener;
import com.unitelmasapienza.asciiart.imageeditor.listeners.ControllerMouseMotionLintener;
import com.unitelmasapienza.asciiart.imageeditor.views.ImageEditorView;
import com.unitelmasapienza.asciiart.imageeditor.views.ImporterView;
import com.unitelmasapienza.asciiart.imageeditor.views.NewView;
import com.unitelmasapienza.asciiart.imageeditor.views.SelectCharView;

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
	 * This field is to implement the stack of <b>Undo</b> function in application.
	 * Every time that a character is drawn, here will be inserted a new PanelChar object which contains
	 * its data.
	 * When is clicked the Undo button, from this stack will be took the first element (last inserted) and relative character 
	 * will be canceled from drawing panel.
	 * This PanelChar, will be moved on the other stack <i>stackRedo</i> which represents the stack of <b>Redo</b> function in application.
	 * 
	 * So if is clicked the Redo button, after the re-painting of specific character, the relative PanelChar in stackRedo (last inserted) will be
	 * moved on stackUndo, ready to be (another time) canceled by Undo click.
	 * And so on...
	 * 
	 */
	private static Stack<PanelChar> stackUndo;
	
	/**
	 * This field is to implement the stack of <b>Redo</b> function in application.
	 * Every time that a character is canceled by Undo function, here will be inserted a new PanelChar object which contains
	 * its data.
	 * When is clicked the Redo button, from this stack will be took the first element (last inserted) and relative character 
	 * will be drawn in panel, at the same previous position (see PanelChar object fields).
	 * This PanelChar, will be moved on the other stack <i>stackUndo</i> which represents the stack of <b>Undo</b> function in application.
	 * 
	 * So if is clicked the Redo button, after the re-painting of specific character, the relative PanelChar in stackRedo (last inserted) will be
	 * moved on stackUndo, ready to be (another time) canceled by Undo click.
	 * And so on...
	 * 
	 */
	private static Stack<PanelChar> stackRedo;
	
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
	 * <b>Singleton</b> implementation. Checks if an instance of the <b>Undo Stack</b> already exists and returns it. 
	 * If it does not exist it creates and returns it.
	 * 
	 * @return stackUndo, the only stack for undo function on application.
	 * 
	 */
	public static Stack<PanelChar> getStackUndoInstance() {
		if(stackUndo == null)
			stackUndo = new Stack<PanelChar>();
		return stackUndo;
	}
	
	/**
	 * <b>Singleton</b> implementation. Checks if an instance of the <b>Redo Stack</b> already exists and returns it. 
	 * If it does not exist it creates and returns it.
	 * 
	 * @return stackRedo, the only stack for redo function on application.
	 * 
	 */
	public static Stack<PanelChar> getStackRedoInstance() {
		if(stackRedo == null)
			stackRedo = new Stack<PanelChar>();
		return stackRedo;
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
		getStackUndoInstance();
		getStackRedoInstance();
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
				SelectCharView.getInstance().setVisible(true);
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
		view.getPanel().addMouseListener(new ControllerMouseLintener(this));
		view.getPanel().addMouseMotionListener(new ControllerMouseMotionLintener(this));
		
		//Listener for file menu bar
		view.getMenuBarFileSave().addActionListener(new ActionSaveListener());
		view.getMenuBarFileLoad().addActionListener(new ActionLoadListener());
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
		
		//Listener for tools menu bar
		view.getMenuBarToolsPaint().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(0);
			}
		});
		
		view.getMenuBarToolsFill().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(1);
			}
		});
		
		view.getMenuBarToolsPick().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSelectedToolIndex(2);
			}
		});
		
		view.getUndoButton().addActionListener(new ActionLoadListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});
		
		view.getRedoButton().addActionListener(new ActionLoadListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				redo();
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

		if (button == 1) {
			PanelChar character = new PanelChar(valueX, valueY, (char) (view.getSelectedChar() + 0), view.getDrawnCharColor(), view.getDrawnCharBackgroundColor());
			pushInUndoStack(character);
			model.write((char) (view.getSelectedChar() + 0), view.getDrawnCharColor(), view.getDrawnCharBackgroundColor());
		}
		else
			model.write((char) 0);

		model.repaint();
	}
	
	/**
	 * This method manage the insert in undo stack.
	 * If the stack size is major of 20 (the max limit) it will remove the
	 * last element in stack (first inserted) and push the new element on top.
	 * 
	 * @param character is the character to push into stack
	 */
	private void pushInUndoStack(PanelChar character) {
		if(getStackUndoInstance().size() == 20) {
			getStackUndoInstance().remove(0);
			getStackUndoInstance().push(character);
		} else getStackUndoInstance().push(character);
	}
	
	/**
	 * This method manage the insert in redo stack.
	 * If the stack size is major of 20 (the max limit) it will remove the
	 * last element in stack (first inserted) and push the new element on top.
	 * 
	 * @param character is the character to push into stack
	 */
	private void pushInRedoStack(PanelChar character) {
		if(getStackRedoInstance().size() == 20) {
			getStackRedoInstance().remove(0);
			getStackRedoInstance().push(character);
		} else getStackRedoInstance().push(character);
	}
	
	/**
	 * This method manage the remove in undo stack.
	 * If there is something to remove from the stack, it will be remove the first (last inserted),
	 * and return it, else it return null.
	 * 
	 * @return first PanelChar from the stack, null otherwise
	 */
	private PanelChar popInUndoStack() {
		if(getStackUndoInstance().size() > 0) {
			PanelChar lastChar = getStackUndoInstance().pop();
			return lastChar;
		} else return null;
	}
	
	/**
	 * This method manage the remove in redo stack.
	 * If there is something to remove from the stack, it will be remove the first (last inserted),
	 * and return it, else it return null.
	 * 
	 * @return first PanelChar from the stack, null otherwise
	 */
	private PanelChar popInRedoStack() {
		if(getStackRedoInstance().size() > 0) {
			PanelChar lastChar = getStackRedoInstance().pop();
			return lastChar;
		} else return null;
	}
	
	/**
	 * This method checks if is possible to an undo.
	 * Simply check if there is something inside the undo stack (some operation).
	 * 
	 * @return true if is possible to do an Undo, false otherwise.
	 */
	private boolean canUndo() {
		return getStackUndoInstance().size() > 0 ? true : false;
	}
	
	/**
	 * This method checks if is possible to a redo.
	 * Simply check if there is something inside the redo stack (some operation).
	 * 
	 * @return true if is possible to do an Redo, false otherwise.
	 */
	private boolean canRedo() {
		return getStackRedoInstance().size() > 0 ? true : false;
	}
	
	/**
	 * This method provides to do an Undo.
	 * If is possible to proceed with undo, it take the first element in undo stack,
	 * push it in redo stack (for eventual redo function of this) and cancel the relative
	 * character from drawing panel.
	 * 
	 */
	private void undo() {
		if(canUndo()) {
			PanelChar character = popInUndoStack();
			pushInRedoStack(character);
			int x = character.getX();
			int y = character.getY();
			model.setCursorDistanceFromLeft(x);
			model.setCursorDistanceFromTop(y);
			model.write((char) 0);
			model.repaint();
		}
	}
	
	/**
	 * This method provides to do a Redo.
	 * If is possible to proceed with redo, it take the first element in redo stack,
	 * push it in undo stack (for eventual undo function of this) and re-writing the relative
	 * character in drawing panel.
	 */
	private void redo() {
		if(canRedo()) {
			PanelChar character = popInRedoStack();
			pushInUndoStack(character);
			int x = character.getX();
			int y = character.getY();
			char c = character.getSelectedChar();
			Color foreground = character.getForegroundColor();
			Color background = character.getBackgroundColor();
			model.setCursorDistanceFromLeft(x);
			model.setCursorDistanceFromTop(y);
			model.write((char) (c + 0), foreground, background);
			model.repaint();
		}
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
		view.getPanel().addMouseListener(new ControllerMouseLintener(this));
		view.getPanel().addMouseMotionListener(new ControllerMouseMotionLintener(this));
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
	
	/**
	 * This method reset the GUI tool to default <i>Paint Tool</i>.
	 * So when we need to reset the view (for example to create a new one) this method directly set the
	 * paint tool as default for drawing canvas.
	 * 
	 */
	public void resetView() {
		getView().setSelectedToolIndex(0);
		getView().repaint(); 
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
