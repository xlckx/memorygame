/*
 * Project: Memory Game
 * File: MemoryGameDriver.c
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

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class MemoryGameDriver
{
    private MemoryGame game;
    private MemoryGamePanel panel;
    private String frameTitle;
    private URL iconURL;

    public MemoryGameDriver(String frameTitle)
    {
        String codeBase = "file:";

        this.frameTitle = frameTitle;

        // Get the url for the image to set as the frame's icon
        try
        {
            iconURL = new URL(codeBase + "images/icon.jpg");
        }
        catch(MalformedURLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        panel = new MemoryGamePanel(codeBase, false);
        game = panel.game;

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI()
    {
        JFrame frame = new JFrame(frameTitle);
        frame.getContentPane().add(panel);
        frame.setIconImage(ImageLoader.getImage(iconURL));
        frame.setResizable(false);
        frame.setSize(panel.getPreferredSize());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        MemoryGameDriver driver = new MemoryGameDriver("Animals Memory Game");
        driver.game.playGame();
        System.exit(0);
    }
}
