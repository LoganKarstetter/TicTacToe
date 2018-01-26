package Board;

import Game.Game;
import Display.Display;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates BoardButtons which act as an interactable medium with the player.
 * The boardPanel displays these buttons to the player, which similarly display the 
 * X's and O's. Upon clicking a board button, regardless of whether by the player or computer,
 * the button will check itself for availability and communicate the result to the actual board. 
 *
 */
@SuppressWarnings("serial")
public class BoardButton extends JButton
{
	/** The current Tic-Tac-Toe interacting with this button game */
	private Game currentGame;
	/** The current board this button belongs to */
	private Board gameBoard;
	/** The boolean determining whether this button contains a value already */
	private boolean available;
	/** The text displayed by this button */
	private String buttonText;
	/** The boolean determining whether the game is over */
	private boolean gameOver;
	
	/**
	 * Constructor that creates a BoardButton that displays either an X or O.
	 * If the button does not already display either symbol, 
	 * clicking the button will display the symbol associated with the current turn.
	 * @param game
	 * @param x
	 * @param y
	 */
	public BoardButton(Game game, Board board, int x, int y)
	{
		//Set up the boardButton
        this.currentGame = game;
		this.gameBoard = board;
		this.available = true;
		this.gameOver = false;
		this.setFont(new Font("", Font.BOLD, 100));
		this.setBackground(Color.BLACK);
		this.setForeground(Display.GhostWhite);
		this.setFocusPainted(false);
		this.setBorder(null);
		this.setOpaque(true);
        this.setBorderPainted(false); //Needed on Mac
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (!gameOver) //Don't do anything if the game is over
				{
					//Regardless verify if the slot is available
					if (available)
					{
						//If the button is selected see who is selecting it
						if (currentGame.getIsPlayers()) //If it is the players turn
						{
							//Set the value in the board to 1 for the player
							currentGame.getBoard().setValue(x, y, 1);
							//If the symbol is 0, then set the text to an X
							if (currentGame.getSymbol() == 0)
							{
								buttonText = "X";
								gameBoard.incXCount();
							}
							else //Draw an O
							{
								buttonText = "O";
								gameBoard.incOCount();
							}
							//Set the last player x and y
							currentGame.setLastPlayerX(x);
							currentGame.setLastPlayerY(y);
						}
						else if (!currentGame.getIsPlayers())//If it is the computer's turn
						{
							//Set the value in the board to 2 for the computer
							currentGame.getBoard().setValue(x, y, 2);
							//Set the symbol to the opposite of the player's
							if (currentGame.getSymbol() == 0)
							{
								buttonText = "O";
								gameBoard.incOCount();
							}
							else //Draw an X
							{
								buttonText = "X";
								gameBoard.incXCount();
							}
							//Set the last computer x and y
							currentGame.setLastCompX(x);
							currentGame.setLastCompY(y);
						}
						//Set the text for this button
						BoardButton.this.setText(buttonText);

						//Check and see if anyone has won
						int status = 0; //Set to 0 (continue game) by default
						if (currentGame.getIsPlayers()) 
						{
							//If it is the player's turn, check for 1's
							status = gameBoard.checkBoard(x, y, 1);
							//Increment the number of turns taken
							currentGame.incPlayerTurns();
						}
						else if (!currentGame.getIsPlayers()) 
						{
							//If it is the computer's turn, check for 2's
							status = gameBoard.checkBoard(x, y, 2);
							//Increment the number of turns taken
							currentGame.incCompTurns();
						}
						//Set the availability to false
						available = false;
						//Check and see if the current game is over, or continuing
						currentGame.checkGame(status);
					}
				}
			}
		});
	}
	
	/**
	 * "Click" this button as the computer.
	 * Allows the computer to select squares in the board.
	 */
	public void computerClick()
	{
		//Simulate the computer clicking the button
		this.doClick();
	}
	
	/**
	 * Reset the fields in this button to their
	 * default values.
	 */
	public void resetButton()
	{
		//Reset the available to false and the buttonText
		available = true;
	    gameOver = false;
		buttonText = "";
		this.setText(buttonText);
		this.setBackground(Color.BLACK);
		this.setForeground(Display.GhostWhite);
	}
	
	/**
	 * Get the boolean determining whether this button is 
	 * currently available.
	 * @return available
	 */
	public boolean isAvailable()
	{
		return available;
	}
	
	/**
	 * Set the gameOver status for the boardButton.
	 * @param gameOver
	 */
	public void setGameOver(boolean gameOver)
	{
		this.gameOver = gameOver;
	}
}
