package Game;
import Board.Board;
import Display.Display;

import java.util.Random;

/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates an instance of a Tic-Tac-Toe game. After the initial setup, it
 * creates a new game board and determines whether the player or computer will go first.
 * It is also responsible for switching the game display from the setup panel to the game panel.
 * Afterwards, it controls all aspects of the game itself, including controlling and determining
 * the moves made by the computer for all three difficulties. It also controls whose turn it is, and 
 * instructs the game board to check for victories.
 *
 */
public class Game 
{
	/** The Display that displays the game and its contents */
	private Display display;
	/** The board for this game of Tic-Tac-Toe */
	private Board board;
	/** A random number generator for coin tosses and computer moves */
	private Random rand;
	/** The boolean determining whether the game is completed */
	boolean gameOver;
	
	/** The value of the coin flipped to determine who goes first */
	private int coinToss;
	/** The value determining whether or not it is the player's turn */
	private boolean isPlayers;
	/** The number of turns taken by the player */
	private int playerTurns;
	/** The number of turns taken by the computer */
	private int compTurns;
	/** The x value of the last square taken by the player */
	private int lastPlayerX;
	/** The y value of the last square taken by the player */
	private int lastPlayerY;
	/** The x value of the last square taken by the computer */
	private int lastCompX;
	/** The y value of the last square taken by the computer */
	private int lastCompY;
	
	
	/** The user name entered by the player */
	private String username;
	/** The difficulty chosen by the player */
	private int difficulty;
	/** The symbol chosen by the player */
	private int symbol;
	
	/**
	 * Creates a game board, random number generator and display.
	 * The game board is a grid of buttons that displays the current state of the game/
	 * The random is generated to determine whether the computer or player will have the 
	 * first turn. The display prompts the user for their information and then shows the board.
	 */
	public Game()
	{
		//Create a new board and start the game
		board = new Board();
		
		//Initialize rand
		rand = new Random();
		
		//Create a display for the game
		display = new Display(this);
		
		//Set the boardPanel for the board
		board.setBoardPanel(display.getBoardPanel());
	}
	
	/**
	 * Switches the display to show the game board.
	 * Gets the user name, difficulty, and symbol from the startPanel.
	 * Then calls the startGame() method to begin the game.
	 */
	public void setUpGame()
	{
		//First switch the display to the board
		display.switchDisplay();
				
		//Get the user name, difficulty, and symbol choice from the display
		username = display.getStartPanel().getInputPanel().getUsername();
		difficulty = display.getStartPanel().getDiffPanel().getDifficulty();
		symbol = display.getStartPanel().getSymbolPanel().getSymbol();
		
		//Start a new game
		startGame();
	}
	
	/**
	 * Start a new game of Tic-Tac-Toe.
	 * This method tosses a coin to determine who goes first
	 * and then depending on the outcome, either the player
	 * or computer will take their turn.
	 */
	public void startGame()
	{
		//Set the number of turns and the gameOver status
		playerTurns = 0;
		compTurns = 0;
		gameOver = false;
		
		//Decide who goes first, flip a coin basically
		coinToss = rand.nextInt(2);
		
		/* Determine who goes first, from here on the game will play out based on who's turn it is.
		 * The computer will play almost instantly, and it's turns will be trigger by when the user clicks a
		 * square on the board. Once the board is full or there is a winner, the game ends.
		 */
		if (coinToss == 0)
		{
			//The player goes first
			isPlayers = true;
			//Determine if the player's name ends in an 's'
			if (username.charAt(username.length() - 1) != 's')
			{
				display.getInfoPanel().setOutputText(username + "'s turn!");
			}
			else
			{
				display.getInfoPanel().setOutputText(username + "' turn!");
			}
		}
		else //The computer goes first
		{
			isPlayers = false;
			display.getInfoPanel().setOutputText("Computer's turn!");
			computerTurn();
		}
	}
	
	/**
	 * Perform a turn by the computer, the complexity of which depends
	 * on the difficulty level chosen by the player. If the difficulty is
	 * 1, the computer will simply choose a random square on the board to fill.
	 */
	public void computerTurn()
	{
		//Make sure the game isn't over before continuing
		if (!gameOver)
		{
			//Determine the difficulty and make a move
			if (difficulty == 1) //Easy
			{
				//Choose a random available square
				placeRandom();
			}
			else if (difficulty == 2) //Normal
			{
				/*
				 * If the difficulty is normal, try and actually win the game.
				 * So if we take a square, try and place adjacent to it on the next
				 * turn. If this is the first turn, take a random square to begin with.
				 */
				//If this is the first turn, choose a random square
				if (compTurns == 0)
				{
					//Choose a random available square
					placeRandom();
				}
				else //This is not the first turn
				{
					//Find an adjacent square, if one is not available, pick a random square
					boolean set = placeAdjacent(lastCompX, lastCompY);
					
					//If the computer could not find any adjacent squares, choose a random one
					if (!set)
					{
						//Choose a random available square
						placeRandom();
					}
				}
			}
			else //Hard
			{
				/*
				 * If the difficulty is hard, try even harder to win the game.
				 * So if we take a square, still try and place adjacent to it on the next
				 * turn. However, if the player has two squares in a row and is about to win,
				 * that needs to be countered first. If no such situation exists, then continue
				 * placing adjacent squares. If this is the first turn, take a random square to begin with.
				 */
				//If this is the very first turn, choose a random square
				if (compTurns == 0 && playerTurns == 0)
				{
					//Choose a random available square
					placeRandom();
				}
				else //This is not the first turn
				{
					/*
					 * Determine whether the player or computer is in a position to
					 * possibly win on their next turn. If so, counter or execute the move, 
					 * if not then place an adjacent. If an adjacent is not possible, choose
					 * a random square.
					 */
					boolean set = false;
					//Find an adjacent square to the player, if one is not available
					if (playerTurns > 0)
					{
						//See if the computer can find a critical or game winning move
						set = placeCritical();
					}
					//If there are no current critical squares to claim, find a square adjacent to the player
					if (!set)
					{
						set = placeAdjacent(lastPlayerX, lastPlayerY);
					}
					//If the computer could not find an open adjacent square to the player, place on adjacent to itself
					if (!set)
					{
						set = placeAdjacent(lastCompX, lastCompY);
					}
					//If the computer could not find any adjacent squares to the player or itself, choose a random one
					if (!set)
					{
						//Choose a random available square
						placeRandom();
					}
				}
			}
		}
	}
	
	/**
	 * Checks the status of this game after a turn is taken.
	 * The turn may be taken by the computer or player.
	 * The status integer inputed determines whether or not the game continues.
	 * The key for status is: 0-Continue, 1-Player Victory, 2-Computer Victory, or 3-Draw
	 * @param status
	 */
	public void checkGame(int status)
	{
		/*
		 * The most common status will be 0, which means the game will continue.
		 * We should check this first since it is more likely to appear, thus
		 * reducing the number of comparisons in the method until the game actually 
		 * is about to end, i.e. status is not 0.
		 */
		if (status == 0 && !gameOver) //Continue with the game
		{
			//Advance to the next turn
			if (isPlayers)
			{
				//It is the computer's turn
				setIsPlayers(false);
				computerTurn();
			}
			else
			{
				//Set it to the players turn
				setIsPlayers(true);
			}
		}
		else
		{
			System.out.println(status);
			//Determine how the game has ended
			if (status == Board.PLAYERVALUE) // status == 1
			{
				//Display that the player has won
				display.getInfoPanel().setOutputText(username + " has won!");
				System.out.println(username + " has won!");
			}
			else if (status == Board.COMPUTERVALUE) // status == 2
			{
				//Display that the computer has won
				display.getInfoPanel().setOutputText("The computer has won!");
				System.out.println("The computer has won!");
			}
			else
			{
				//Display the draw
				display.getInfoPanel().setOutputText("Draw!");
				System.out.println("Draw!");
			}
			//Set the gameOver status
			gameOver = true;
			//Set the boardButtons gameOver status
			display.getBoardPanel().setGameOver(gameOver);
			//Switch the infoPanel and display the results of the game
			display.getInfoPanel().displayResults(status);
		}
	}
	
	/**
	 * Select a random square to place a value.
	 * This method generates two random integers to represent
	 * rows and columns. It then checks that square on the board
	 * to determine if it is available. If so, it sets a value
	 * to that square. If the square is not available, it generates two new integers
	 * and tries again.
	 */
	public void placeRandom()
	{
		//Create integers to hold the column and row
		int column;
		int row;
		
		//Loop until a square is found and placed
		boolean placed = false;
		while (!placed && !gameOver)
		{
			column = rand.nextInt(3);
			row = rand.nextInt(3);
			//Place and X or O if this square is available
			placed = placeIfAvailable(column, row);
		}
	}
	
	/**
	 * Select a square adjacent to the square located
	 * at the inputed x and y on the board. Returns a 
	 * boolean to specify whether a move was possible.
	 * @param lastX
	 * @param lastY
	 * @return true or false
	 */
	public boolean placeAdjacent(int lastX, int lastY)
	{   
		/* 
		 * Check all adjacent squares until a value is set. Check diagonals, then left to right,
		 * and up and down. If the squares are checked in this order the computer will
		 * have a higher chance of getting three in a row or disrupting the players
		 * strategy.
		 */
		boolean set = false;
		System.out.println("Last: [" + lastX + "][" + lastY + "]");
		//Diagonals
		if (lastX > 0 && lastY > 0 && !set) //Up and left
		{
			set = placeIfAvailable(lastX - 1, lastY - 1);
		}
		if (lastX < 2 && lastY > 0 && !set) //Up and right
		{
			set = placeIfAvailable(lastX + 1, lastY - 1);
		}
		if (lastX > 0 && lastY < 2 && !set) //Down and left
		{
			set = placeIfAvailable(lastX - 1, lastY + 1);
		}
		if (lastX < 2 && lastY < 2 && !set) //Down and right
		{
			set = placeIfAvailable(lastX + 1, lastY + 1);
		}
		
		//Basic directions
		if (lastX > 0 && !set) //Left adjacent
		{
			//Place and X or O if this square is available
			set = placeIfAvailable(lastX - 1, lastY);
		}
		if (lastX < 2 && !set) //Right adjacent
		{
			set = placeIfAvailable(lastX + 1, lastY);
		}
		if (lastY > 0 && !set) //Up adjacent
		{
			set = placeIfAvailable(lastX, lastY - 1);
		}
		if (lastY < 2 && !set) //Down adjacent
		{
			set = placeIfAvailable(lastX, lastY + 1);
		}

		return set;
	}
	
	/**
	 * Check the board for situations where either the computer
	 * or player could win on their next turn. Return a boolean
	 * determining whether a critical square was found and claimed.
	 * A critical square is a point within the board in which either 
	 * the computer or player could take in order to win on their 
	 * next turn. If no such point exists, return false.
	 * @return set, whether or not the value was set
	 */
	public boolean placeCritical()
	{
		boolean set = false;
		//The number of computer squares taken in a particular column, row, or diagonal
		int compCount = 0;
		//The number of empty squares
		int emptyCount = 0;
		//The number of player taken squares
		int playerCount = 0;
		//The last empty x index read
		int emptyX = 0;
		//The last empty y index read
		int emptyY = 0;
		
		/*
		 * Store at least one critical move. This is essential to
		 * allow the computer to be able to prioritize looking for winning
		 * moves without discarding all other moves. With a stored move,
		 * even if the computer can't win this turn, it isn't forced to 
		 * sacrifice any possible moves.
		 */
		//The X value of a critical move found, store a default value of -1
		int criticalX = -1;
		//The Y value of a critical move found
		int criticalY = -1;
		
		//Check all three columns
		for (int i = 0; i < 3 && !set; i++)
		{
			for (int j = 0; j < 3 && !set; j++)
			{
				if (board.getGameBoard()[i][j] == Board.COMPUTERVALUE)
				{
					//If this is an owned square, increment the count
					compCount++;
				}
				else if (board.getGameBoard()[i][j] != 0)
				{
					//If this is an enemy square, increment the count
					playerCount++;
				}
				else //Empty square
				{
					/*
					 * Store the x and y for the most recent empty square. If there is only
					 * one empty square remaining in a column, row, or diagonal. Then it is
					 * a critical square that needs to be addressed.
					 */
					emptyCount++;
					emptyX = i;
					emptyY = j;
				}
				if (compCount == 2 && emptyCount == 1 || playerCount == 2 && emptyCount == 1)
				{
					System.out.println("Point found! [" + emptyX + "]" + "[" + emptyY + "]");
					if (playerCount == 0)
					{
						System.out.println("Victory point found! [" + emptyX + "]" + "[" + emptyY + "]");
						//If there are not player squares here, this is a game winning move, so set it
						set = placeIfAvailable(emptyX, emptyY);
					}
					if (!set) //In the event the computer found a winning move that was unavailable, allow it to store the move
					{
						//Store the move for use if the computer can't find a game winning move
						criticalX = emptyX;
						criticalY = emptyY;
					}
				}
			}
			//Reset the counts after checking each column
			compCount = 0;
			playerCount = 0;
			emptyCount = 0;
		}
		
		//Check all three rows
		for (int i = 0; i < 3 && !set; i++)
		{
			for (int j = 0; j < 3 && !set; j++)
			{
				if (board.getGameBoard()[j][i] == Board.COMPUTERVALUE)
				{
					//If this is an owned square, increment the count
					compCount++;
				}
				else if (board.getGameBoard()[j][i] != 0)
				{
					//If this is an enemy square, increment the count
					playerCount++;
				}
				else //Empty square
				{
					/*
					 * Store the x and y for the most recent empty square. If there is only
					 * one empty square remaining in a column, row, or diagonal. Then it is
					 * a critical square that needs to be addressed.
					 */
					emptyCount++;
					emptyX = j;
					emptyY = i;
				}
				if (compCount == 2 && emptyCount == 1 || playerCount == 2 && emptyCount == 1)
				{
					System.out.println("Point found! [" + emptyX + "]" + "[" + emptyY + "]");
					if (playerCount == 0)
					{
						System.out.println("Victory point found! [" + emptyX + "]" + "[" + emptyY + "]");
						//If there are not player squares here, this is a game winning move, so set it
						set = placeIfAvailable(emptyX, emptyY);
					}
					if (!set) //In the event the computer found a winning move that was unavailable, allow it to store the move
					{
						//Store the move for use if the computer can't find a game winning move
						criticalX = emptyX;
						criticalY = emptyY;
					}
				}
			}
			//Reset the counts after checking each row
			compCount = 0;
			playerCount = 0;
			emptyCount = 0;
		}
		
		//Check diagonal \
		for (int i = 0; i < 3 && !set; i++)
		{
			if (board.getGameBoard()[i][i] == Board.COMPUTERVALUE)
			{
				//If this is an owned square, increment the count
				compCount++;
			}
			else if (board.getGameBoard()[i][i] != 0)
			{
				//If this is an enemy square, increment the count
				playerCount++;
			}
			else //Empty square
			{
				/*
				 * Store the x and y for the most recent empty square. If there is only
				 * one empty square remaining in a column, row, or diagonal. Then it is
				 * a critical square that needs to be addressed.
				 */
				emptyCount++;
				emptyX = i;
				emptyY = i;
			}
			if (compCount == 2 && emptyCount == 1 || playerCount == 2 && emptyCount == 1)
			{
				System.out.println("Point found! [" + emptyX + "]" + "[" + emptyY + "]");
				if (playerCount == 0)
				{
					System.out.println("Victory point found! [" + emptyX + "]" + "[" + emptyY + "]");
					//If there are not player squares here, this is a game winning move, so set it
					set = placeIfAvailable(emptyX, emptyY);
				}
				if (!set) //In the event the computer found a winning move that was unavailable, allow it to store the move
				{
					//Store the move for use if the computer can't find a game winning move
					criticalX = emptyX;
					criticalY = emptyY;
				}
			}
		}
		//Reset the counts
		compCount = 0;
		playerCount = 0;
		emptyCount = 0;
		
		//Check diagonal /
		for (int i = 0; i < 3 && !set; i++)
		{
			if (board.getGameBoard()[i][2- i] == Board.COMPUTERVALUE)
			{
				//If this is an owned square, increment the count
				compCount++;
			}
			else if (board.getGameBoard()[i][2 - i] != 0)
			{
				//If this is an enemy square, increment the count
				playerCount++;
			}
			else //Empty square
			{
				/*
				 * Store the x and y for the most recent empty square. If there is only
				 * one empty square remaining in a column, row, or diagonal. Then it is
				 * a critical square that needs to be addressed.
				 */
				emptyCount++;
				emptyX = i;
				emptyY = 2 - i;
			}
			if (compCount == 2 && emptyCount == 1 || playerCount == 2 && emptyCount == 1)
			{
				System.out.println("Point found! [" + emptyX + "]" + "[" + emptyY + "]");
				if (playerCount == 0)
				{
					System.out.println("Victory point found! [" + emptyX + "]" + "[" + emptyY + "]");
					//If there are not player squares here, this is a game winning move, so set it
					set = placeIfAvailable(emptyX, emptyY);
				}
				if (!set) //In the event the computer found a winning move that was unavailable, allow it to store the move
				{
					//Store the move for use if the computer can't find a game winning move
					criticalX = emptyX;
					criticalY = emptyY;
				}
			}
		}
		
		//If at the end, no winning move was played, use the stored critical values
		if (criticalX != -1 && criticalY != -1) //-1 is the initialization value so we can tell if these are legit
		{
			//Attempt to place the stored critical move
			set = placeIfAvailable(criticalX, criticalY);
		}
		return set;
	}
	
	/**
	 * Place and X or O if the square at the inputed
	 * column and row is currently available. Returns a boolean
	 * to specify whether the move was possible.
	 * @param column
	 * @param row
	 * @return true or false
	 */
	public boolean placeIfAvailable(int column, int row)
	{
		//Check if the square is available
		if (display.getBoardPanel().getBoardButtons()[column][row].isAvailable())
		{
			//Perform a computer click
			display.getBoardPanel().getBoardButtons()[column][row].computerClick();
			return true;
		}
		//The square is not available
		return false;
	}
	
	/**
	 * Reset the game board and buttons.
	 * Calls the startGame method to start a new game.
	 */
	public void resetGame()
	{
		//Reset the game board
		board.resetBoard();
		//Reset the board buttons
		display.getBoardPanel().resetButtons();
		//Start a new game
		startGame();
	} 
	
	/**
	 * Set the whether or not it is the players turn
	 * @param isTurn
	 */
	public void setIsPlayers(boolean isTurn)
	{
		//Set isPlayers
		isPlayers = isTurn;
		if (isPlayers)
		{
			//Display that it is the player's turn in the output label
			//Determine if the player's name ends in an 's'
			if (username.charAt(username.length() - 1) != 's')
			{
				display.getInfoPanel().setOutputText(username + "'s turn!");
			}
			else
			{
				display.getInfoPanel().setOutputText(username + "' turn!");
			}
		}
		else 
		{
			//Display that it is the computer's turn in the output label
			display.getInfoPanel().setOutputText("Computer's turn!");
		}
	}
	
	/**
	 * Increment the number of player turns taken
	 */
	public void incPlayerTurns()
	{
		playerTurns++;
	}
	
	/**
	 * Increment the number of computer turns taken
	 */
	public void incCompTurns()
	{
		compTurns++;
	}
	
	/**
	 * Set the x of last player move
	 * @param x
	 */
	public void setLastPlayerX(int x)
	{
		lastPlayerX = x;
	}
	
	/**
	 * Set the y of last player move
	 * @param y
	 */
	public void setLastPlayerY(int y)
	{
		lastPlayerY = y;
	}
	
	/**
	 * Set the x of last comp move
	 * @param x
	 */
	public void setLastCompX(int x)
	{
		lastCompX = x;
	}
	
	/**
	 * Set the y of last computer move
	 * @param y
	 */
	public void setLastCompY(int y)
	{
		lastCompY = y;
	}
	
	/**
	 * Get the current Board in this game.
	 * @return board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * Get who's turn it currently is.
	 * If true, it is the players true, if false then
	 * it is the computer's turn
	 * @return isPlayers
	 */
	public boolean getIsPlayers()
	{
		return isPlayers;
	}
	
	/**
	 * Get the player's symbol
	 * 0 = x, 1 = O
	 * @return symbol
	 */
	public int getSymbol()
	{
		return symbol;
	}
}
