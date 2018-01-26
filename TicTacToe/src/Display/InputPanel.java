package Display;
import javafx.geometry.HorizontalDirection;

import java.awt.*;

import javax.swing.*;

/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates an inputPanel which is contains a JTextField for receiving the name
 * of the player during initial setup. The user name is then displayed by the infoPanel
 * during a game to inform player that it is their turn.
 *
 */
@SuppressWarnings("serial")
public class InputPanel extends JPanel
{
	/** The width for this inputPanel */
	private int width = 650;
	/** The height for this inputPanel */
	private int height = 100;
	
	/** The JLabel to prompt the user to enter their name */
	private JLabel inputLabel;
	/** The text for the inputLabel */
	private String inputText = "Enter your name ";
	
	/** The JTextField for entering the user's name */
	private JTextField inputField;
	/** The width for the inputField */
	private int fieldWidth = 550;
	/** The height for the inputField */
	private int fieldHeight = 30;
	
	/**
	 * Constructor
	 * Creates an inputPanel that prompts and receives input for a user's name.
	 * The user name is then displayed by the infoPanel during the game.
	 */
	public InputPanel()
	{
		//Set the layout and size of the main panel
		this.setBackground(Display.Shadow);
        this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		
		//Create the inputLabel
		inputLabel = new JLabel(inputText);
		inputLabel.setFont(new Font("", Font.PLAIN, 20));
		inputLabel.setBackground(Display.Shadow);
		inputLabel.setForeground(Display.GhostWhite);
		inputLabel.setPreferredSize(new Dimension(fieldWidth, 30));
		inputLabel.setMinimumSize(new Dimension(fieldWidth, 30));
		inputLabel.setMaximumSize(new Dimension(fieldWidth, 30));
		inputLabel.setOpaque(true);
		
		//Create the inputField
		inputField = new JTextField();
		inputField.setFont(new Font("", Font.PLAIN, 20));
		inputField.setBackground(Color.BLACK);
		inputField.setForeground(Display.GhostWhite);
		inputField.setCaretColor(Display.GhostWhite);
		inputField.setBorder(null);
		inputField.setEditable(true);
		inputField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		inputField.setMinimumSize(new Dimension(fieldWidth, fieldHeight));
		inputField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
		
		//Add the components to the inputPanel
        this.add(inputLabel);
        this.add(inputField);

        //Request focus to the inputField
        inputField.requestFocus();
	}
	
	/**
	 * Get the user name entered into the input field.
	 * @return inputField contents
	 */
	public String getUsername()
	{
		return inputField.getText();
	}
}
