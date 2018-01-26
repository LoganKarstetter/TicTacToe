package Game;
import java.awt.*;

import javax.swing.UIManager;
/**
 * 
 * @author Logan Karstetter
 * Date: 9/?/2016
 * This class creates a new instance of a Tic-Tac-Toe game to be 
 * played between the user and the computer.
 *
 */
public class Launcher 
{
	/**
	 * The main method that creates a new instance of a Tic-Tac-Toe game.
	 * It also disables certain Swing UI aspects to provide a cleaner look
	 * to the game's graphical user interface.
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Remove the borders and selection flashing from Swing components
		UIManager.put("Button.select", Color.TRANSLUCENT);
		UIManager.put("Button.selectBorder", Color.TRANSLUCENT);
		
		//Create a new game of Tic-Tac-Toe
		Game newGame = new Game();
	}
}
