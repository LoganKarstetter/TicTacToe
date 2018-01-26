package Display;
import Game.Game;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates a display for the Tic-Tac-Toe game. It creates and encompasses all of the other panels
 * and controls the display of the contentPanel, which displays either the game or the set up.
 * Similarly, it also creates the game's JFrame which holds all the panels.
 *
 */
public class Display 
{
	/** The string identifying the StartPanel within the contentPanel */
	private final String STARTPANEL = "StartPanel";
	/** The string identifying the BoardPanel within the contentPanel */
	private final String BOARDPANEL = "BoardPanel";
	
	/** The game represented by this display */
	private Game currentGame;
	
	/** The JFrame for this display */
	private JFrame frame;
	/** The text displayed at the top of the frame */
	private String frameTitle = "TIC-TAC-TOE Game";
	/** The width of the frame */
	private int fWidth = 650;
	/** The height of the frame */
	private int fHeight = 600;
	
	/** The content panel for this display. It determines whether to display the settings or board */
	private JPanel contentPanel;
	/** The CardLayout set inside the contentPanel */
	private CardLayout layout;
	
	/** The StartPanel contained within the frame */
	private StartPanel startPanel;
	/** The BoardPanel contained within the frame */
	private BoardPanel boardPanel;
	/** The InfoPanel contained within the frame */
	private InfoPanel infoPanel;

	/** The custom color used for the panels */
	public static Color Shadow = new Color(42, 49, 50);
	/** The custom color used for the fonts */
	public static Color GhostWhite = new Color(248, 248, 255);
	
	/**
	 * Create a display for the Tic-Tac-Toe game. A display consists of a JFrame,
	 * which holds a startPanel for setting up a game, a boardPanel for playing the 
	 * game, and an infoPanel for displaying game information to the player. It features
	 * a contentPanel which is used to switch between the start and boardPanels.
	 * @param game
	 */
	public Display(Game game)
	{
		//Set the Game field
		currentGame = game;
		
		//Create the frame
		frame = new JFrame();
		frame.setTitle(frameTitle);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(fWidth, fHeight));
		frame.setMinimumSize(new Dimension(fWidth, fHeight));
		frame.setMaximumSize(new Dimension(fWidth, fHeight));
		frame.setLayout(new BorderLayout());
		
		//Create the StartPanel
		startPanel = new StartPanel(currentGame);
		//Create the BoardPanel
		boardPanel = new BoardPanel(currentGame);
		//Create the InfoPanel
		infoPanel = new InfoPanel(currentGame);
		
		//Create the contentPanel and layout
		contentPanel = new JPanel();
		layout = new CardLayout();
		contentPanel.setLayout(layout);
	
		//Add the components to the contentPanel in cardLayout
		contentPanel.add(startPanel, STARTPANEL);
		contentPanel.add(boardPanel, BOARDPANEL);
		
		//Add the components to the frame
		frame.add(infoPanel, BorderLayout.NORTH);
		frame.add(contentPanel, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
        frame.pack();

	}
	
	/**
	 * Switch the display of the content panel to the game board.
	 */
	public void switchDisplay()
	{
		//Switch the layout
		layout.show(contentPanel, BOARDPANEL);
	}
	
	/**
	 * Get the startPanel displayed.
	 * @return startPanel
	 */
	public StartPanel getStartPanel()
	{
		return startPanel;
	}
	
	/**
	 * Get the boardPanel displayed.
	 * @return boardPanel
	 */
	public BoardPanel getBoardPanel()
	{
		return boardPanel;
	}
	
	/**
	 * Get the infoPanel displayed.
	 * @return infoPanel
	 */
	public InfoPanel getInfoPanel()
	{
		return infoPanel;
	}
}
