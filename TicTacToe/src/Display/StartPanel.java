package Display;
import Game.Game;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates a startPanel which contains all of the other panels used in initial
 * game setup. The startPane contains a inputPanel for receiving the user name of the player,
 * a diffPanel for receiving the selected computer difficulty, a symbolPanel for receiving the
 * chosen symbol of the player (either X or O), and finally, an errorPanel which communicates
 * with the user and informs them of mistakes made during setup.
 *
 */
@SuppressWarnings("serial")
public class StartPanel extends JPanel
{
	/** The Game this startPanel begins */
	private Game currentGame;
	
	/** The InputPanel contained within this panel */
	private InputPanel inputPanel;
	
	/** The DiffPanel contained within this panel */
	private DiffPanel diffPanel;
	
	/** The SymbolPanel contained within this panel */
	private SymbolPanel symbolPanel;
	
	/** The ErrorPanel contained within this panel */
	private ErrorPanel errorPanel;
	
	/**
	 * Create a startPanel for setting up a game of Tic-Tac_Toe. A startPanel
	 * contains an inputPanel, diffPanel, symbolPanel, and errorPanel, which all
	 * interact with the player during initial set up.
	 * @param game
	 */
	public StartPanel(Game game)
	{
		//Set up the panel
		this.currentGame = game;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Display.Shadow);
		
		//Create the inputPanel
		inputPanel = new InputPanel();
		
		//Create the diffPanel
		diffPanel = new DiffPanel();
		
		//Create the symbolPanel
		symbolPanel = new SymbolPanel();
		
		//Create the errorPanel
		errorPanel = new ErrorPanel(currentGame, inputPanel, diffPanel, symbolPanel);
		
		//Add all the panels to the startPanel
		this.add(inputPanel);
		this.add(diffPanel);
		this.add(symbolPanel);
		this.add(errorPanel);
	}
	
	/**
	 * Get the inputPanel in this startPanel.
	 * @return inputPanel
	 */
	public InputPanel getInputPanel()
	{
		return inputPanel;
	}
	
	/**
	 * Get the diffPanel in this startPanel.
	 * @return diffPanel
	 */
	public DiffPanel getDiffPanel()
	{
		return diffPanel;
	}
	
	/**
	 * Get the symbolPanel in this startPanel.
	 * @return symbolPanel
	 */
	public SymbolPanel getSymbolPanel()
	{
		return symbolPanel;
	}
	
	/**
	 * Get the errorPanel in this startPanel.
	 * @return errorPanel
	 */
	public ErrorPanel getErrorPanel()
	{
		return errorPanel;
	}
}
