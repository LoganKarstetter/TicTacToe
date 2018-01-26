package Board;

import Display.BoardPanel;

import java.awt.Point;

/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates a game board which stores the locations of player and computer moves.
 * It is not directly accessed by the player, but updates to display changes and has
 * methods to update and check the status of the board, including checking for winning
 * moves.
 *
 */
public class Board 
{
	/** The value associated with the player in the gameBoard array */
	public static final int PLAYERVALUE = 1;
	/** The value associated with the computer in the gameBoard array */
	public static final int COMPUTERVALUE = 2;
	
	/** The boardPanel that displays this board */
	private BoardPanel boardPanel;
	/** The Tic-Tac-Toe game board, represented as a matrix */
	private int[][] gameBoard;
	/** The number of squares in the gameBoard */
	private int boardSize = 9;
	/** The number of x's on the board */
	private int xCount;
	/** The number of o's on the board */
	private int oCount;
	
	/**
	 * Constructor that creates a new 3x3 game board.
	 * Initially all counts and board values are set to zero.
	 */
	public Board()
	{
		//Create a 3x3 board
		gameBoard = new int[3][3];
		//Initialize the array to zeros
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				gameBoard[i][j] = 0;
			}
		}
		//Default xCount and oCount to zero
		xCount = 0;
		oCount = 0;
	}
	
	/**
	 * Check the contents of an inputed slot to make sure it is empty.
	 * If empty, ten set the value of the slot to an inputed value.
	 * Inputed values are always either 1(player) or 2(computer).
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setValue(int x, int y, int value)
	{
		//Verify that the selected slot is empty
		if (gameBoard[x][y] == 0)
		{
			//Verify that the inputed value is 1, or 2
			if (value == PLAYERVALUE || value == COMPUTERVALUE)
			{
				//Set the value
				gameBoard[x][y] = value;
			}
		}
	}
	
	/**
	 * Check the board for a victory or draw after the most recent move.
	 * If there is a victory 1(player) or 2(computer) will return.
	 * In the case of a draw, 3 will return or 0 if the game should continue.
	 * @param x
	 * @param y
	 * @param value
	 * @return 0(continue game), 1(player wins), 2(computer wins), or 3(draw) 
	 */
	public int checkBoard(int x, int y, int value)
	{
		//Create an array of three points for highlighting winning spaces
		Point[] winningPoints = new Point[3];
		//Verify that at least three x's or o's have been placed
		if (xCount >= 3 || oCount >= 3)
		{
			//It winCount == 3, victory
			int winCount = 0;
			
			//Check columns 
			for (int i = 0; i < 3; i++)
			{
				if (gameBoard[x][i] == value)
				{
					winningPoints[i] = new Point(x, i);
					winCount++;
					if (winCount == 3)
					{
						//Pass the winning points to the boardPanel
						boardPanel.highlightSpaces(winningPoints);
						return value;
					}
				}
			}
			winCount = 0;
			//Check rows 
			for (int i = 0; i < 3; i++)
			{
				if (gameBoard[i][y] == value)
				{
					winningPoints[i] = new Point(i, y);
					winCount++;
					if (winCount == 3)
					{
						//Pass the winning points to the boardPanel
						boardPanel.highlightSpaces(winningPoints);
						return value;
					}
				}
			}
			winCount = 0;
			//Check diagonal \
			for (int i = 0; i < 3; i++)
			{
				if (gameBoard[i][i] == value)
				{
					winningPoints[i] = new Point(i, i);
					winCount++;
					if (winCount == 3)
					{
						//Pass the winning points to the boardPanel
						boardPanel.highlightSpaces(winningPoints);
						return value;
					}
				}
			}
			winCount = 0;
			//Check diagonal /
			for (int i = 0; i < 3; i++)
			{
				if (gameBoard[i][2 - i] == value)
				{
					winningPoints[i] = new Point(i, 2 - i);
					winCount++;
					if (winCount == 3)
					{
						//Pass the winning points to the boardPanel
						boardPanel.highlightSpaces(winningPoints);
						return value;
					}
				}
			}
		}
		//If no victory, it must be a draw
		if (xCount + oCount == 9)
		{
			//If it is a draw 
			return 3;
		}
		else //If not a draw, then the game must continue
		{
			return 0;
		}
	}
	
	/**
	 * Clear and reset the game board to zeros.
	 * Reset the x and y count variables to zero.
	 */
	public void resetBoard()
	{
		//Reset the 3x3 board
		gameBoard = new int[3][3];
		//Initialize the array to zeros
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				gameBoard[i][j] = 0;
			}
		}
		//Reset xCount and oCount to zero
		xCount = 0;
		oCount = 0;
	}
	
	/**
	 * Increment the xCount by 1.
	 */
	public void incXCount()
	{
		xCount++;
	}
	
	/**
	 * Increment the oCount by 1.
	 */
	public void incOCount()
	{
		oCount++;
	}
	
	/**
	 * Get the 3x3 gameBoard array.
	 * @return gameBoard
	 */
	public int[][] getGameBoard()
	{
		return gameBoard;
	}
	
	/**
	 * Get the number of total squares in the board.
	 * @return boardSize
	 */
	public int getBoardSize()
	{
		return boardSize;
	}
	
	/**
	 * Get the number of X's on the board.
	 * @return xCount
	 */
	public int getXCount()
	{
		return xCount;
	}
	
	/**
	 * Get the number of O's on the board.
	 * @return oCount
	 */
	public int getOCount()
	{
		return oCount;
	}
	
	/**
	 * Set the boardPanel that displays this board.
	 * @param boardPanel
	 */
	public void setBoardPanel(BoardPanel boardPanel)
	{
		this.boardPanel = boardPanel;
	}
}
