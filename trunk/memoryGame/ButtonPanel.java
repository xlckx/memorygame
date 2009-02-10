/*
 * Project: Memory Game
 * File: ButtonPanel.c
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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{
    private static final long serialVersionUID = 7880299718953636153L;
    
    private Dimension size;
    private Image background;
    
    /*
     * Creates the panel to put the given list of card buttons on and set
     * the background to the given image. Sets its size according to the
     * number of buttons and size of the buttons.
     */
    public ButtonPanel(ArrayList<CardButton> buttonList, Image bgImage)
    {
        int width = (buttonList.get(0)).getWidth();
        int height = (buttonList.get(0)).getHeight();
        int numPerLine = (int)(Math.sqrt(MemoryGame.NUM_CARDS));
        
        size = new Dimension(numPerLine * width, numPerLine * height);
        
        setLayout(new GridLayout(numPerLine, numPerLine));
        setOpaque(false);
        reset(buttonList, bgImage);
    }
    
    /*
     * Resets the panel by removing the current buttons, adding the list
     * again, and resetting the background image.
     */
    public void reset(ArrayList<CardButton> buttonList, Image bgImage)
    {
        removeAll();
        
        for(CardButton cb : buttonList)
            add(cb);
        
        if(bgImage != null)
            background = bgImage;
        
        // Some how doing this gets the panel to update its component
        // references to the new given card list and display them correctly.
        ((Component)getComponent(0)).setVisible(false);
        ((Component)getComponent(0)).setVisible(true);
        
        repaint();
    }
    
    /*
     * Paints the background and buttons on the panel
     */
    public void paint(Graphics g)
    {
        g.drawImage(background, 0, 0, this); // Draws background image
        super.paint(g); // Draws the buttons over the background
    }
    
    /*
     * Returns the Dimension size of the panel.
     */
    public Dimension getPreferredSize()
    {
        return size;
    }
    
    /*
	 * redraws the panel, then waits a specified period
	 */
	public void updateDisplay(int milliseconds) 
	{
		repaint();
	    try 
		{
			Thread.sleep(milliseconds);
		} 
		catch (InterruptedException e) 
		{
		}
	}
}
