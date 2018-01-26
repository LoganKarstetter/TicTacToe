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
 * This class creates a diffPanel or difficulty panel which prompts the player for their
 * preferred computer difficulty.
 *
 */
@SuppressWarnings("serial")
public class DiffPanel extends JPanel
{	
	/** The width for this DiffPanel */
	private int dWidth = 650;
	/** The height for this DiffPanel */
	private int dHeight = 125;
	
	/** The JLabel to prompt the user to choose a computer difficulty level */
	private JLabel diffLabel;
	/** The text for the diffLabel */
	private String diffText = "Select the computer's difficulty level";
	
	/** A panel for organizing the difficulty buttons */
	private JPanel buttonPanel;
	
	/** The JButton for selecting the easy difficulty */
	private JButton easyButton;
	/** The text for the easyButton */
	private String easyText = "Easy";
	
	/** The JButton for selecting the normal difficulty */
	private JButton normalButton;
	/** The text for the normalButton */
	private String normalText = "Normal";
	
	/** The JButton for selecting the hard difficulty */
	private JButton hardButton;
	/** The text for the hardButton */
	private String hardText = "Hard";
	
	/** The difficulty chosen, 1 = easy, 2 = normal, 3 = hard */
	private int difficulty = 0;
	
	/**
	 * Constructor
	 * Prompts the user to select a difficulty.
	 * The difficulty sets the mode of the computer player.
	 */
	public DiffPanel()
	{
		//Set the layout and size of the main panel
		this.setLayout(new FlowLayout());
		this.setBackground(Display.Shadow);
		this.setPreferredSize(new Dimension(dWidth, dHeight));
		this.setMinimumSize(new Dimension(dWidth, dHeight));
		this.setMaximumSize(new Dimension(dWidth, dHeight));
		
		//Create the diffLabel
		diffLabel = new JLabel(diffText);
		diffLabel.setFont(new Font("", Font.PLAIN, 20));
		diffLabel.setBackground(Display.Shadow);
		diffLabel.setForeground(Display.GhostWhite);
		diffLabel.setPreferredSize(new Dimension(dWidth - 100, 30));
		diffLabel.setMinimumSize(new Dimension(dWidth - 100, 30));
		diffLabel.setMaximumSize(new Dimension(dWidth - 100, 30));
		diffLabel.setOpaque(true);
		
		//Create the buttonPanel
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Display.Shadow);
		buttonPanel.setLayout(new GridLayout(1, 3, 10, 0)); //Rows, columns, width spacing, height spacing
        buttonPanel.setPreferredSize(new Dimension(dWidth - 100, 50));
        buttonPanel.setMinimumSize(new Dimension(dWidth - 100, 50));
        buttonPanel.setMaximumSize(new Dimension(dWidth - 100, 50));

		//Create the easyButton
		easyButton = new JButton(easyText);
		easyButton.setFont(new Font("", Font.PLAIN, 20));
		easyButton.setFocusPainted(false);
		easyButton.setBackground(Color.BLACK);
		easyButton.setForeground(Display.GhostWhite);
		easyButton.setBorder(null);
		easyButton.setOpaque(true);
		easyButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				difficulty = 1;
				diffLabel.setText(diffText + " - " + easyText);
			}
		
		});
		
		//Create the normalButton
		normalButton = new JButton(normalText);
		normalButton.setFont(new Font("", Font.PLAIN, 20));
		normalButton.setFocusPainted(false);
		normalButton.setBackground(Color.BLACK);
		normalButton.setForeground(Display.GhostWhite);
		normalButton.setBorder(null);
		normalButton.setOpaque(true);
		normalButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				difficulty = 2;
				diffLabel.setText(diffText + " - " + normalText);
			}
		
		});
		
		//Create the hardButton
		hardButton = new JButton(hardText);
		hardButton.setFont(new Font("", Font.PLAIN, 20));
		hardButton.setFocusPainted(false);
		hardButton.setBackground(Color.BLACK);
		hardButton.setForeground(Display.GhostWhite);
		hardButton.setBorder(null);
		hardButton.setOpaque(true);
		hardButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				difficulty = 3;
				diffLabel.setText(diffText + " - " + hardText);
			}
		
		});
		
		//Add the buttons to the buttonPanel
		buttonPanel.add(easyButton);
		buttonPanel.add(normalButton);
		buttonPanel.add(hardButton);
	
		//Add the label and buttonPanel to the diffPanel
		this.add(diffLabel);
		this.add(buttonPanel);
	}
	
	/**
	 * Get the difficulty selected.
	 * @return difficulty
	 */
	public int getDifficulty()
	{
		return difficulty;
	}
}
