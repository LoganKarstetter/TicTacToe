package Display;
import Board.BoardButton;
import Game.Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;


/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates a boardPanel which holds all the boardButtons. Upon creation, a board
 * panel will generate all of its board buttons, the amount of which depending on the size
 * of the board. It handles reseting buttons in the event of a new game. 
 *
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel
{
	/** The Game this boardPanel represents */
	private Game currentGame;
	/** A 3x3 matrix of BoardButtons to represent board spaces */
	private BoardButton[][] boardButtons;
	
	/**
	 * Constructor that creates a panel consisting of a 3x3 set of 
	 * BoardButtons. The BoardButtons will display the X's and O's.
	 * @param game
	 */
	public BoardPanel(Game game)
	{
		//Set up the layout of the panel
		this.currentGame = game;
		this.setLayout(new GridLayout(3, 3, 5, 5));
		this.setBackground(Display.Shadow);
		
		//Create the boardButtons and add them to the panel
		boardButtons = new BoardButton[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				boardButtons[i][j] = new BoardButton(currentGame, currentGame.getBoard(), i, j);
				this.add(boardButtons[i][j]);
			}
		}
	}
	
	/**
	 * Reset all the boardButtons to 
	 * their default values.
	 */
	public void resetButtons()
	{
		//Reset all the buttons
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				boardButtons[i][j].resetButton();
			}
		}
	}
	
	/**
	 * Update the gameOver status for the boardButtons.
	 * If the game is over then this blocks input with
	 * the buttons.
	 * @param gameOver
	 */
	public void setGameOver(boolean gameOver)
	{
		//Loop through and update the buttons
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				boardButtons[i][j].setGameOver(gameOver);
			}
		}
	}
	
	/**
	 * Highlight the winning spaces in the board
	 * using a set of points.
	 * @param points
	 */
	public void highlightSpaces(Point[] points)
	{
		//Loop through and repaint the three boardButtons
		for (int i = 0; i < points.length; i++)
		{
			boardButtons[(int)points[i].getX()][(int)points[i].getY()].setBackground(Display.GhostWhite);
			boardButtons[(int)points[i].getX()][(int)points[i].getY()].setForeground(Color.BLACK);
			
		}
	}
	
	/**
	 * Get the 2D of BoardButtons that make up
	 * the game board.
	 * @return boardButtons
	 */
	public BoardButton[][] getBoardButtons()
	{
		return boardButtons;
	}
}
