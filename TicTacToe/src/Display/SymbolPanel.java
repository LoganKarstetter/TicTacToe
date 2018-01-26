package Display;
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
 * This class creates a SymbolPanel which features JButtons for receiving the player's
 * preference of either X or O during the initial setup of a Tic-Tac-Toe game.
 *
 */
@SuppressWarnings("serial")
public class SymbolPanel extends JPanel
{
	/** The width for this SymbolPanel */
	private int sWidth = 650;
	/** The height for this SymbolPanel */
	private int sHeight = 125;
	
	/** The JLabel to prompt the user to choose x's or o's */
	private JLabel symbolLabel;
	/** The text for the symbolLabel */
	private String symbolText = "Select X's or O's";
	
	/** A panel for organizing the symbol buttons */
	private JPanel buttonPanel;
	
	/** The JButton for selecting X's */
	private JButton xButton;
	/** The text for the xButton */
	private String xText = "X";
	/** The JButton for selecting O's */
	private JButton oButton;
	/** The text for the oButton */
	private String oText = "O";
	
	/** The symbol chosen, 0 = x, 1 = o, the default is 3 so that the error panel can verify it. */
	private int symbol = 3;
	
	/**
	 * Creates a panel to prompt the user to select X's or O's.
	 * The options appear on JButtons that, upon input, store and display the user's choice.
	 * The outcome of the selection has no effect on the game.
	 */
	public SymbolPanel()
	{
		//Set the layout and size of the main panel
		this.setLayout(new FlowLayout());
		this.setBackground(Display.Shadow);
		this.setPreferredSize(new Dimension(sWidth, sHeight));
		this.setMinimumSize(new Dimension(sWidth, sHeight));
		this.setMaximumSize(new Dimension(sWidth, sHeight));
		
		//Create the symbolLabel
		symbolLabel = new JLabel(symbolText);
		symbolLabel.setFont(new Font("", Font.PLAIN, 20));
		symbolLabel.setBackground(Display.Shadow);
		symbolLabel.setForeground(Display.GhostWhite);
		symbolLabel.setPreferredSize(new Dimension(sWidth - 100, 30));
		symbolLabel.setMinimumSize(new Dimension(sWidth - 100, 30));
		symbolLabel.setMaximumSize(new Dimension(sWidth - 100, 30));
		
		//Create the buttonPanel
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Display.Shadow);
		//buttonPanel.setLayout(new GridLayout(2, 1, 0, 10));
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.setPreferredSize(new Dimension(sWidth - 100, 50));
        buttonPanel.setMinimumSize(new Dimension(sWidth - 100, 50));
        buttonPanel.setMaximumSize(new Dimension(sWidth - 100, 50));
				
		//Create the xButton
		xButton = new JButton(xText);
		xButton.setFont(new Font("", Font.PLAIN, 20));
		xButton.setFocusPainted(false);
		xButton.setBackground(Color.BLACK);
		xButton.setForeground(Display.GhostWhite);
		xButton.setBorder(null);
		xButton.setOpaque(true);
		xButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//Set the symbol to X
				symbol = 0;
				symbolLabel.setText(symbolText + " - " + xText);
			}
		
		});
		//Create the oButton
		oButton = new JButton(oText);
		oButton.setFont(new Font("", Font.PLAIN, 20));
		oButton.setFocusPainted(false);
		oButton.setBackground(Color.BLACK);
		oButton.setForeground(Display.GhostWhite);
		oButton.setBorder(null);
		oButton.setOpaque(true);
		oButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//Set the symbol to O
				symbol = 1;
				symbolLabel.setText(symbolText + " - " + oText);
			}
		
		});
		
		//Add the components to the buttonPanel
		buttonPanel.add(xButton);
		buttonPanel.add(oButton);
		
		//Add the components to the diffPanel
		this.add(symbolLabel);
		this.add(buttonPanel);
	}
	
	/**
	 * Get the user's choice of X or O.
	 * Returns an int, 0 = x, 1 = 2.
	 * @return symbol
	 */
	public int getSymbol()
	{
		return symbol;
	}
}
