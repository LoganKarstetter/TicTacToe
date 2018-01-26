package Display;

import Game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates an infoPanel for displaying information about the status of the game
 * and prompting the player to play again after completion of a game. It features a cardLayout
 * display which switches according to the status of the game. During a game it displays whose
 * turn it is, and afterwards it prompts the player to either quit or player another game.
 *
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel
{
	/** The string identifying the StartPanel within the contentPanel */
	private final String GAMEINFOPANEL = "GameInfoPanel";
	/** The string identifying the BoardPanel within the contentPanel */
	private final String GAMEOVERPANEL = "GameOverPanel";
	
	/** The current game being played */
	private Game currentGame;
	
	/** The inner panel that displays either the game info */
	private JPanel gameInfoPanel;
	/** The inner panel that displays either the game over info */
	private JPanel gameOverPanel;
	/** The CardLayout that switches the contents of the contentPanel */
	private CardLayout layout;
	
	/** The Tic-Tac-Toe JLabel */
	private JLabel gameLabel;
	/** The text for the gameLabel */
	private String gameText = "TIC-TAC-TOE";
	
	/** The JLabel for displaying the current turn and win status */
	private JLabel outputLabel;
	/** The text for the outputLabel */
	private String outputText = "Created by Logan Karstetter";
	
	/** The JLabel for prompting the user to play again after a game ends */
	private JLabel restartLabel;
	/** The text for the computerLabel */
	private String restartText;
	
	/** The JButton that accepts yes input for playing again */
	private JButton yesButton;
	/** The text for the yesButton */
	private String yesText = "Yes";
	
	/** The JButton that accepts quit input */
	private JButton quitButton;
	/** The text for the quitButton */
	private String quitText = "Quit";
	
	/**
	 * Creates a panel to display information about the game.
	 * This includes the name of the game, and when decided, the
	 * winner. It also prompts the user to play again after a game is
	 * completed.
	 * @param game
	 */
	public InfoPanel(Game game)
	{
		//Set up the infoPanel and layout
		this.currentGame = game;
		this.setBackground(Display.Shadow);
		layout = new CardLayout();
		this.setLayout(layout);
		
		//Create the gameInfoPanel and its components
		gameInfoPanel = new JPanel();
		gameInfoPanel.setBackground(Display.Shadow);
		gameInfoPanel.setForeground(Display.GhostWhite);
		gameInfoPanel.setOpaque(true);
		
		//Create the gameLabel
		gameLabel = new JLabel(gameText, SwingConstants.CENTER);
		gameLabel.setFont(new Font("", Font.PLAIN, 35));
		gameLabel.setBackground(Display.Shadow);
		gameLabel.setForeground(Display.GhostWhite);
		gameLabel.setOpaque(true);
		
		//Create the outputLabel
		outputLabel = new JLabel(outputText, SwingConstants.CENTER);
		outputLabel.setFont(new Font("", Font.PLAIN, 20));
		outputLabel.setBackground(Display.Shadow);
		outputLabel.setForeground(Display.GhostWhite);
		outputLabel.setOpaque(true);
		
		//Add the gameLabel and outputLabel to the gameInfoPanel
		gameInfoPanel.setLayout(new GridLayout(2, 1, 5, 5));
		gameInfoPanel.add(gameLabel);
		gameInfoPanel.add(outputLabel);
		
		//Create the gameOverPanel and its components
		gameOverPanel = new JPanel();
		gameOverPanel.setBackground(Display.Shadow);
		gameOverPanel.setForeground(Display.GhostWhite);
		gameOverPanel.setOpaque(true);
		
		//Create the restartLabel
		restartLabel = new JLabel(restartText, SwingConstants.CENTER);
		restartLabel.setFont(new Font("", Font.PLAIN, 20));
		restartLabel.setBackground(Display.Shadow);
		restartLabel.setForeground(Display.GhostWhite);
        restartLabel.setPreferredSize(new Dimension(425, 75));
        restartLabel.setMinimumSize(new Dimension(425, 75));
        restartLabel.setMaximumSize(new Dimension(425, 75));
		restartLabel.setOpaque(true);

		
		//Create the yesButton
		yesButton = new JButton(yesText);
		yesButton.setFont(new Font("", Font.PLAIN, 20));
		yesButton.setFocusPainted(false);
		yesButton.setBackground(Color.BLACK);
		yesButton.setForeground(Display.GhostWhite);
        yesButton.setPreferredSize(new Dimension(100, 75));
        yesButton.setMinimumSize(new Dimension(100, 75));
        yesButton.setMaximumSize(new Dimension(100, 75));
		yesButton.setBorder(null);
		yesButton.setOpaque(true);
		yesButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//If the player chooses to play again, reset the display and game
				switchDisplay();
				currentGame.resetGame();
			}
		});
		
		
		//Create the quitButton
		quitButton = new JButton(quitText);
		quitButton.setFont(new Font("", Font.PLAIN, 20));
		quitButton.setFocusPainted(false);
		quitButton.setBackground(Color.BLACK);
		quitButton.setForeground(Display.GhostWhite);
        quitButton.setPreferredSize(new Dimension(100, 75));
        quitButton.setMinimumSize(new Dimension(100, 75));
        quitButton.setMaximumSize(new Dimension(100, 75));
		quitButton.setBorder(null);
		quitButton.setOpaque(true);
		quitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//If the player chooses to quit, then exit normally
				System.exit(0);
			}
		});
		
		//Add the components to the OptionsPanel
		//gameOverPanel.setLayout(new GridLayout(1, 4, 5, 5));
        gameOverPanel.setLayout(new FlowLayout());
		gameOverPanel.add(restartLabel);
		gameOverPanel.add(yesButton);
		gameOverPanel.add(quitButton);
		
		//Add the components to the infoPanel in cardLayout
		this.add(gameInfoPanel, GAMEINFOPANEL);
		this.add(gameOverPanel, GAMEOVERPANEL);
	}
	
	/**
	 * Update the resartText according to who won the game or if it was a draw.
	 * Note: 1 = player won, 2 = computer won, 3 = draw
	 * @param status
	 */
	public void displayResults(int status)
	{
		//Update the restartText according to the status variable
		if (status == 1) //Player won
		{
			restartText = "You won! Play again?";
		}
		else if (status == 2) //Computer won
		{
			restartText = "You lost! Play again?";
		}
		else //It was a draw
		{
			restartText = "Draw! Play again?";
		}
		restartLabel.setText(restartText);
		
		//Switch the display
		switchDisplay();
	}
	
	/**
	 * Switch the display of the infoPanel.
	 */
	public void switchDisplay()
	{
		//Flips to the next card of the container. If the currently visible card is the last one, this method flips to the first card in the layout.
		layout.next(this);
	}
	
	/**
	 * Set the output text for this panel
	 * @param text
	 */
	public void setOutputText(String text)
	{
		outputText = text;
		outputLabel.setText(outputText);
	}
}
