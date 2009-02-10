/*
 * Project: Memory Game
 * File: MemoryGame.c
 *
 * Copyright (C) 2009 Daniel Meekins
 * Contact: dmeekins - gmail
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package memoryGame;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * This class runs the steps of the game, controlling the GUI through the
 * action listener.
 */
public class MemoryGame
{
    private ButtonListener buttonListener; // Listener for the button actions
    private MemoryGamePanel gui;           // The GUI this game controls
    
    // Total number of cards to play the game (number of different images x 2).
    // Number must be a perfect square (i.e. 4, 9, 16, etc). If an odd number,
    // then there will be an empty hole in the puzzle.
    public static final int NUM_CARDS = 16;
    
    /*
     * Creates a new memory game to control the given GUI
     */
    public MemoryGame(MemoryGamePanel theGUI)
    {
        gui = theGUI;
        buttonListener = new ButtonListener();
        
        // Adds each button to the actions listener
        for(CardButton c : gui.cardButtonList)
            c.addActionListener(buttonListener);
        for(int i = 0; i < gui.ctrlButtons.length; i++)
            gui.ctrlButtons[i].addActionListener(buttonListener);
    }
    
    /*
     * Plays the game by receiving the buttons the user clicked on from the
     * listener until the user finds all the matches.
     */
    public void playGame()
    {
        int numPairsFound;
        int numOfTries;
        int stillPlaying;
        JButton buttonPressed; // Button reference returned from the listener
        CardButton button1, button2; // The two card buttons to compare once
        							 // clicked on.
        
        stillPlaying = JOptionPane.YES_OPTION;
        
        while(stillPlaying == JOptionPane.YES_OPTION)
        {
            numPairsFound = 0;
            numOfTries = 0;
            buttonPressed = null;
            
            // Loop until all the cards are matched.
            while(numPairsFound != NUM_CARDS / 2)
            {
                // Wait for first card to be pressed
                buttonPressed = buttonListener.waitForButton();
                
                if(buttonPressed instanceof CardButton)
                {
                    button1 = (CardButton)buttonPressed;
                    button1.setFaceUp();
                }
                else
                {
                    if(buttonPressed.getActionCommand().equals("Exit"))
                        stillPlaying = JOptionPane.NO_OPTION;
                    break;
                }
                
                // Wait for second card to be pressed
                buttonPressed = buttonListener.waitForButton();
                
                if(buttonPressed instanceof CardButton)
                {
                    button2 = (CardButton)buttonPressed;
                    button2.setFaceUp();
                }
                else
                {
                    if(buttonPressed.getActionCommand().equals("Exit"))
                        stillPlaying = JOptionPane.NO_OPTION;
                    break;
                }
                
                // Update to show the changes made and then wait .8 seconds
                gui.updateDisplay(800);
                
                // Unflip the cards if they are not equals or if they are the
                // same card (if they refer to the same object)
                if(!button1.equals(button2) || button1 == button2)
                {
                    ((CardButton)button1).setFaceDown();
                    ((CardButton)button2).setFaceDown();
                }
                else
                {
                    button1.setVisible(false); // Hide the pair once found.
                    button2.setVisible(false);
                    numPairsFound++;
                }
                
                numOfTries++;
                
                // If found all matching pairs.
                if(numPairsFound == NUM_CARDS / 2)
                {
                    JOptionPane.showMessageDialog(gui, "YAY YOU WON!\n" +
                    		"It took you " + numOfTries + " number of tries.");
                    
                    gui.ctrlButtons[0].setText("Play Again");
                    
                    buttonPressed = buttonListener.waitForButton();
                }
            }
            
            if(buttonPressed.getActionCommand().equals("Play Again") || 
               buttonPressed.getActionCommand().equals("Reset"))
                gui.reset();
            else if(buttonPressed.getActionCommand().equals("Exit"))
                stillPlaying = JOptionPane.NO_OPTION;
        }
        
        return;
    }
}
