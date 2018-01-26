package Display;
import Game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates an errorPanel which communicates with the player during the 
 * initial setup of a Tic-Tac-Toe game. It informs the user if they attempt to start
 * a game without properly entering their user name, choosing a difficulty, or selecting
 * a board symbol.
 *
 */
@SuppressWarnings("serial")
public class ErrorPanel extends JPanel
{
	/** The Game this panel starts */
	private Game currentGame;
	
	/** The width for this errorPanel */
	private int eWidth = 650;
	/** The height for this errorPanel */
	private int eHeight = 125;
	
	/** The JLabel for presenting the user with errors if they skipped step */
	private JLabel errorLabel;
	/** The text for the errorLabel */
	private String errorText = "Press start when you are ready to play";
	/** The JButton for starting the game after completing all the steps */
	private JButton startButton;
	/** The text for the startButton */
	private String startText = "Start game";
	
	/**
	 * Create an ErrorPanel to inform the player of error's made upon setting
	 * up a new Tic-Tac-Toe game. An errorPanel's only function is to update its
	 * errorLabel to present specific errors to the player.
	 * @param game
	 * @param inputPanel
	 * @param diffPanel
	 * @param symbolPanel
	 */
	public ErrorPanel(Game game, InputPanel inputPanel, DiffPanel diffPanel, SymbolPanel symbolPanel)
	{
		//Set the panel fields
		this.currentGame = game;
		
		//Set up the errorPanel
		this.setLayout(new FlowLayout());
		this.setBackground(Display.Shadow);
		this.setPreferredSize(new Dimension(eWidth, eHeight));
		this.setMinimumSize(new Dimension(eWidth, eHeight));
		this.setMaximumSize(new Dimension(eWidth, eHeight));
		
		//Create the errorLabel
		errorLabel = new JLabel(errorText);
		errorLabel.setFont(new Font("", Font.PLAIN, 20));
		errorLabel.setBackground(Display.Shadow);
		errorLabel.setForeground(Display.GhostWhite);
		errorLabel.setPreferredSize(new Dimension(eWidth - 100, 30));
		errorLabel.setMinimumSize(new Dimension(eWidth - 100, 30));
		errorLabel.setMaximumSize(new Dimension(eWidth - 100, 30));
		errorLabel.setOpaque(true);
		
		//Create the startButton
		startButton = new JButton(startText);
		startButton.setFont(new Font("", Font.PLAIN, 20));
		startButton.setFocusPainted(false);
		startButton.setBackground(Color.BLACK);
		startButton.setForeground(Display.GhostWhite);
        startButton.setPreferredSize(new Dimension(eWidth - 100, 50));
        startButton.setMinimumSize(new Dimension(eWidth - 100, 50));
        startButton.setMaximumSize(new Dimension(eWidth - 100, 50));
		startButton.setBorder(null);
		startButton.setOpaque(true);
		startButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				/* Only continue if the user has entered their name, selected a difficulty,
				 * and chosen a X's or O's.
				 */
				if (inputPanel.getUsername().equalsIgnoreCase(""))
				{
					errorText = "Please enter your name in the field above";
					errorLabel.setText(errorText);
				}
				else if (diffPanel.getDifficulty() == 0)
				{
					errorText = "Please select a difficulty";
					errorLabel.setText(errorText);
				}
				else if (symbolPanel.getSymbol() == 3)
				{
					errorText = "Please select X's or O's";
					errorLabel.setText(errorText);
				}
				else //If there are no errors, set up and start the game
				{
					currentGame.setUpGame();
				}
			}
		});
		
		//Add all the components to the errorPanel
		this.add(errorLabel);
		this.add(startButton);
	}

}
