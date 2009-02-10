/*
 * Project: Memory Game
 * File: CtrlPanel.c
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CtrlPanel extends JPanel
{
    private static final long serialVersionUID = 2457951258330065444L;
    
    private Dimension size;
    public static final int HEIGHT = 40;
    
    /*
     * Creates a panel with the given array of buttons and the width to fit to
     * the parent frame.
     */
    public CtrlPanel(JButton[] buttons, int width)
    {
        size = new Dimension(width, HEIGHT);
        setBackground(Color.DARK_GRAY);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        if(buttons != null)
            for(int i = 0; i < buttons.length; i++)
                add(buttons[i]);
    }
    
    /*
     * Create a panel with no buttons.
     */
    public CtrlPanel(int width)
    {
        this(null, width);
    }
    
    /*
     * Returns the Dimension size of the panel
     */
    public Dimension getPreferredSize()
    {
        return size;
    }
}
