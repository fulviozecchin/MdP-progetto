package com.unitelmasapienza.asciiart.imageeditor;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

import com.unitelmasapienza.asciiart.asciipanel.AsciiFont;
import com.unitelmasapienza.asciiart.asciipanel.AsciiPanel;

/**
 * Is the <b>main Frame</b> class for the application. It represents the container of the application window
 * 
 * @author Fulvio Zecchin
 * CONTROLLER
 */
public class ImageEditor extends JFrame {

	/**
	 * The indefier to serialize/deserialize the object
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The only instance of the class
	 * 
	 */
	public static ImageEditor instance;

	/**
	 * Represents the drawing space of the application
	 * MODEL
	 */
	private AsciiPanel generalPanel;
	
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
	 * <b><i>Singleton</i></b> implementation. Checks if an instance of the class already exists and returns it. 
	 * If it does not exist it creates and returns it.
	 * 
	 * @return ImageEditor instance
	 */
	public static ImageEditor getInstance() {

		if (instance == null)
			instance = new ImageEditor();
		return instance;
	}

	/**
	 * The constructor is private because by implementing the <b><i>Singleton</i></b> Pattern it is not visible outside the class
	 * 
	 */
	private ImageEditor() {
		super("ASCII ImageEditor");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setGeneralPanel(new AsciiPanel(80, 60, AsciiFont.CP437_16x16));
		setSelectedCharPreview(new AsciiPanel(1, 1, AsciiFont.CP437_16x16));
		this.setBounds(0, 0, 80 * 16 + 80, 60 * 16);
		this.setLayout(null);
		this.add(getGeneralPanel());
		getGeneralPanel().setCursorDistanceFromLeft(0);
		getGeneralPanel().setCursorDistanceFromTop(0);
		getGeneralPanel().write("Empty");
		JPanel controlTool = new JPanel();
		controlTool.setLayout(null);
		setCharColorPreview(new JPanel());
		setCharBackgroundColorPreview(new JPanel());
		setMinusButton(new JButton("-"));
		setPlusButton(new JButton("+"));
		setCharIndexButton(new JButton("1"));
		setPickToolButton(new JButton("Pick"));
		setPaintToolButton(new JButton("Paint"));
		setFillToolButton(new JButton("Fill"));

		getCharColorPreview().setBackground(getDrawnCharColor());
		getCharBackgroundColorPreview().setBackground(getDrawnCharBackgroundColor());

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

		getCharIndexButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CharacterSelector.getInstance().setVisible(true);
			}
		});
		getPickToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelectedToolIndex(1);
			}
		});

		getMinusButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getSelectedChar() > 0)
					setSelectedChar(getSelectedChar() - 1);
				getCharIndexButton().setLabel(getSelectedChar() + "");
				updatePreview();

			}
		});
		getPlusButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getSelectedChar() < 255)
					setSelectedChar(getSelectedChar() + 1);
				getCharIndexButton().setLabel(getSelectedChar() + "");
				updatePreview();
			}
		});

		getCharColorPreview().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(getCharColorPreview(), "Choose Foreground Color", getCharColorPreview().getBackground());

				if (newColor != null) {
					setDrawnCharColor(newColor);
					getCharColorPreview().setBackground(newColor);
				}
				updatePreview();

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

		getCharBackgroundColorPreview().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(getCharBackgroundColorPreview(), "Choose Foreground Color", getCharBackgroundColorPreview().getBackground());

				if (newColor != null) {
					setDrawnCharBackgroundColor(newColor);
					getCharBackgroundColorPreview().setBackground(newColor);
				}
				updatePreview();

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

		getFillToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelectedToolIndex(2);
			}
		});

		getPaintToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelectedToolIndex(0);
			}
		});

		getGeneralPanel().setBounds(80, 0, 80 * 16, 80 * 16);
		controlTool.setBounds(0, 0, 80, 80 * 16);
		this.add(controlTool);
		getGeneralPanel().addMouseListener(new EditorMouseLintener(this));
		getGeneralPanel().addMouseMotionListener(new EditorMouseMotionLintener(this));

		JMenuBar menuBar = new JMenuBar();
		JMenu menuBarFile = new JMenu("File");
		JMenuItem menuBarFileNew = new JMenuItem("New...");
		JMenuItem menuBarFileLoad = new JMenuItem("Load...");
		JMenuItem menuBarFileSave = new JMenuItem("Save...");
		JMenuItem menuBarFileImport = new JMenuItem("Import...");
		menuBar.add(menuBarFile);
		menuBarFile.add(menuBarFileNew);
		menuBarFile.add(menuBarFileLoad);
		menuBarFile.add(menuBarFileSave);
		menuBarFile.add(menuBarFileImport);
		this.setJMenuBar(menuBar);
		menuBar.setVisible(true);

		menuBarFileSave.addActionListener(new ActionSave());
		menuBarFileLoad.addActionListener(new ActionLoad());

		menuBarFileImport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageImporter.getInstance().setVisible(true);
				;
			}
		});
		menuBarFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageNew.getInstance().setVisible(true);
				;
			}
		});

		updatePreview();
	}

	/**
	 * Handles the event click on the <b>"Pick"</b> button of the GUI
	 * Replaces the font in use with one selected from those already present on the canvas 
	 * 
	 * @param button is the index of the button/function that is set
	 */
	public void onPick(int button) {
		int valueX = getGeneralPanel().getMouseCursorX();
		int valueY = getGeneralPanel().getMouseCursorY();
		setSelectedChar(getGeneralPanel().pickPanelCharIndex(valueX, valueY));
		setDrawnCharColor(getGeneralPanel().pickPanelCharsForegroundColors(valueX, valueY));
		setDrawnCharBackgroundColor(getGeneralPanel().pickPanelCharsBackgroundColors(valueX, valueY));
		getCharBackgroundColorPreview().setBackground(getDrawnCharBackgroundColor());
		getCharColorPreview().setBackground(getDrawnCharColor());
		getCharBackgroundColorPreview().repaint();
		getCharColorPreview().repaint();
		getCharIndexButton().setLabel(getSelectedChar() + "");
		setSelectedToolIndex(0);
		updatePreview();
	}

	/**
	 * Handles the click event on the application drawing canvas
	 * Draws the selected character on the canvas
	 * 
	 * @param button is the index of the button/function that is set
	 */
	public void onClick(int button) {
		int valueX = getGeneralPanel().getMouseCursorX();
		int valueY = getGeneralPanel().getMouseCursorY();

		getGeneralPanel().setCursorDistanceFromLeft(valueX);
		getGeneralPanel().setCursorDistanceFromTop(valueY);

		if (button == 1)
			getGeneralPanel().write((char) (getSelectedChar() + 0), getDrawnCharColor(), getDrawnCharBackgroundColor());
		else
			getGeneralPanel().write((char) 0);

		getGeneralPanel().repaint();
	}

	/**
	 * Handles the event click on the <b>"Fill"</b> button of the GUI
	 * Fills the entire drawing canvas with the selected font
	 * 
	 * @param button is the index of the button/function that is set
	 */
	public void onFill(int button) {
		int valueX = getGeneralPanel().getMouseCursorX();
		int valueY = getGeneralPanel().getMouseCursorY();

		if (button == 1) 
			getGeneralPanel().fill((char) (getSelectedChar() + 0), valueX, valueY, getDrawnCharColor(), getDrawnCharBackgroundColor());
		else 
			getGeneralPanel().fill((char) (0), valueX, valueY, Color.black, Color.black);

		getGeneralPanel().repaint();
	}

	/**
	 * Called when the mouse is moved within the canvas. 
	 * Saves the <i>x</i> and <i>y</i> coordinates of the cursor and the selected button/function.
	 * @param button is the index of the button/function that is set
	 * @param x is the value of <i>x</i>-coordinate
	 * @param y is the value of <i>y</i>-coordinate
	 */
	public void onCursorMove(int button, int x, int y) {
		getGeneralPanel().setMouseCursorX(x / 16);
		getGeneralPanel().setMouseCursorY(y / 16);
		getGeneralPanel().repaint();
	}

	/**
	 * Drawn character color getter
	 * 
	 * @return the color of the character
	 */
	public Color getDrawnCharColor() {
		return drawnCharColor;
	}

	/**
	 * Drawn character color setter
	 * 
	 * @param charColor is Color to set
	 */
	public void setDrawnCharColor(Color charColor) {
		this.drawnCharColor = charColor;
	}

	/**
	 * Drawn character background color getter
	 * 
	 * @return the background color of the character
	 */
	public Color getDrawnCharBackgroundColor() {
		return drawnCharBackgroundColor;
	}

	/**
	 * Drawn character background color setter
	 * 
	 * @param charBackgroundColor is background Color to set
	 */
	public void setDrawnCharBackgroundColor(Color charBackgroundColor) {
		this.drawnCharBackgroundColor = charBackgroundColor;
	}

	/**
	 * The character to draw index getter
	 * 
	 * @return the index of character to draw
	 */
	private Integer getSelectedchar() {
		return getSelectedChar();
	}

	/**
	 * The character to draw index setter
	 * 
	 * @param selectedchar the index of character to draw
	 */
	private void setSelectedchar(Integer selectedchar) {
		this.setSelectedChar(selectedchar);
	}

	/**
	 * The tool/function button index getter
	 * 
	 * @return the index of the tool/function
	 */
	public Integer getSelectedToolIndex() {
		return selectedToolIndex;
	}

	/**
	 * The tool/function button index setter
	 * 
	 * @param toolIndex is the index of the tool/function
	 */
	public void setSelectedToolIndex(Integer toolIndex) {
		this.selectedToolIndex = toolIndex;
	}

	/**
	 * Resets the drawing canvas by creating a new one. 
	 * As input it takes the dimensions of the new canvas (width and height)
	 * 
	 * @param width is the width of the new canvas
	 * @param height is the height of the new canvas
	 */
	public void reset(int width, int height) {
		this.remove(getGeneralPanel());
		setGeneralPanel(new AsciiPanel(width, height, AsciiFont.CP437_16x16));
		this.add(getGeneralPanel());
		getGeneralPanel().clear();
		getGeneralPanel().setCursorDistanceFromLeft(0);
		getGeneralPanel().setCursorDistanceFromTop(0);
		getGeneralPanel().write("Empty");
		getGeneralPanel().setBounds(80, 0, width * 16, height * 16);
		getGeneralPanel().addMouseListener(new EditorMouseLintener(this));
		getGeneralPanel().addMouseMotionListener(new EditorMouseMotionLintener(this));
	}

	/**
	 * Updates the preview of the selected character
	 * 
	 */
	private void updatePreview() {
		getSelectedCharPreview().clear();
		getSelectedCharPreview().write((char) (getSelectedChar() + 0), 0, 0, getDrawnCharColor(), getDrawnCharBackgroundColor());
		getSelectedCharPreview().repaint();
	}

	/**
	 * Updates the selected character when it is chosen from the character palette
	 * 
	 * @param i is the chosen character index
	 */
	public void onSelectChar(int i) {
		setSelectedChar(i);
		getCharIndexButton().setLabel(i + "");
		updatePreview();
	}

	/**
	 * The general ascii panel getter
	 * 
	 * @return the generalPanel
	 */
	public AsciiPanel getGeneralPanel() {
		return generalPanel;
	}

	/**
	 * The general ascii panel setter
	 * 
	 * @param generalPanel is the panel to set
	 */
	public void setGeneralPanel(AsciiPanel generalPanel) {
		this.generalPanel = generalPanel;
	}

	/**
	 * @return the selectedChar
	 */
	private Integer getSelectedChar() {
		return selectedChar;
	}

	/**
	 * @param selectedChar the selectedChar to set
	 */
	private void setSelectedChar(Integer selectedChar) {
		this.selectedChar = selectedChar;
	}

	/**
	 * The char color preview getter
	 * 
	 * @return the charColorPreview
	 */
	JPanel getCharColorPreview() {
		return charColorPreview;
	}

	/**
	 * The char color preview setter
	 * 
	 * @param charColorPreview is the char color preview to set
	 */
	void setCharColorPreview(JPanel charColorPreview) {
		this.charColorPreview = charColorPreview;
	}

	/**
	 * The char background color preview getter
	 * 
	 * @return the charBackgroundColorPreview
	 */
	JPanel getCharBackgroundColorPreview() {
		return charBackgroundColorPreview;
	}

	/**
	 * The char background color preview setter
	 * 
	 * @param charBackgroundColorPreview is the char background color preview to set
	 */
	void setCharBackgroundColorPreview(JPanel charBackgroundColorPreview) {
		this.charBackgroundColorPreview = charBackgroundColorPreview;
	}

	/**
	 * The minus button getter
	 * 
	 * @return the minusButton
	 */
	JButton getMinusButton() {
		return minusButton;
	}

	/**
	 * The minus button setter 
	 * @param minusButton is the button to set
	 */
	void setMinusButton(JButton minusButton) {
		this.minusButton = minusButton;
	}

	/**
	 * The plus button getter
	 * 
	 * @return the plusButton
	 */
	JButton getPlusButton() {
		return plusButton;
	}

	/**
	 * The plus button setter
	 * @param plusButton is the button to set
	 */
	void setPlusButton(JButton plusButton) {
		this.plusButton = plusButton;
	}

	/**
	 * The char index button getter
	 * 
	 * @return the charIndexButton
	 */
	JButton getCharIndexButton() {
		return charIndexButton;
	}

	/**
	 * The char index button setter
	 * 
	 * @param charIndexButton is the button to set
	 */
	void setCharIndexButton(JButton charIndexButton) {
		this.charIndexButton = charIndexButton;
	}

	/**
	 * The pick tool button getter
	 * 
	 * @return the pickToolButton
	 */
	JButton getPickToolButton() {
		return pickToolButton;
	}

	/**
	 * The pick tool button setter
	 * 
	 * @param pickToolButton is the button to set
	 */
	void setPickToolButton(JButton pickToolButton) {
		this.pickToolButton = pickToolButton;
	}

	/**
	 * The paint tool button getter
	 * 
	 * @return the paintToolButton
	 */
	JButton getPaintToolButton() {
		return paintToolButton;
	}

	/**
	 * The paint tool button setter
	 * 
	 * @param paintToolButton is the button to set
	 */
	void setPaintToolButton(JButton paintToolButton) {
		this.paintToolButton = paintToolButton;
	}

	/**
	 * The fill tool button getter
	 * 
	 * @return the fillToolButton
	 */
	JButton getFillToolButton() {
		return fillToolButton;
	}

	/**
	 * The fill tool button setter
	 * 
	 * @param fillToolButton the button to set
	 */
	void setFillToolButton(JButton fillToolButton) {
		this.fillToolButton = fillToolButton;
	}

	/**
	 * The selected char preview getter
	 * 
	 * @return the selectedCharPreview
	 */
	AsciiPanel getSelectedCharPreview() {
		return selectedCharPreview;
	}

	/**
	 * The selected char preview setter
	 * 
	 * @param selectedCharPreview is the selected char preview panel to set
	 */
	void setSelectedCharPreview(AsciiPanel selectedCharPreview) {
		this.selectedCharPreview = selectedCharPreview;
	}

	/**
	 * The imported buffered image getter
	 * 
	 * @return the importBI
	 */
	public BufferedImage getImportBI() {
		return importBI;
	}

	/**
	 * The imported buffered image setter
	 * 
	 * @param importBI the imported buffered image to set
	 */
	public void setImportBI(BufferedImage importBI) {
		this.importBI = importBI;
	}

}
