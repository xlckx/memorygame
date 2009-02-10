/*
 * Project: Memory Game
 * File: ButtonListener.c
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ButtonListener implements ActionListener
{
    private JButton button; // Button that was pressed
    private Object lock; // Object used to synchronize the button
    private boolean waiting; // Whether waiting for action or not

    public ButtonListener()
    {
        lock = new Object();
    }

    /*
     * Gets the reference to the button that was clicked on.
     */
    public void actionPerformed(ActionEvent event)
    {
        button = (JButton)event.getSource();
        waiting = false;

        synchronized(lock)
        {
            lock.notifyAll();
        }
    }

    /*
     * "Pauses" the program, waiting until a button is pressed. Returns a
     * reference to the button that was pressed.
     */
    public JButton waitForButton()
    {
        waiting = true;

        try
        {
            synchronized(lock)
            {
                while(waiting == true)
                    lock.wait();
            }
        }
        catch(InterruptedException e)
        {
        }

        return button;
    }
}
