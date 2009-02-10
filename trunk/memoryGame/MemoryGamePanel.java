/*
 * Project: Memory Game
 * File: MemoryGamePanel.c
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MemoryGamePanel extends JPanel
{
    private static final long serialVersionUID = 4402254398785256803L;

    private ButtonPanel buttonPanel; // Panel for the card buttons
    private CtrlPanel ctrlPanel;     // Panel for the reset and exit buttons
    private Dimension size;          // Size of the frame
    private String codeBase;         // File base of the program location
    private int bgImgNum;            // Random number for the background image
    private ArrayList<Image> bgImageList; // List of all the background images
    ArrayList<CardButton> cardButtonList; // List of the card buttons
    ArrayList<CardButton> currButtonList; // Current list of card buttons used
    JButton[] ctrlButtons; // Array of the game control buttons
    MemoryGame game;       // Game that controls this GUI

    public final int NUM_IMAGES = 10; // Total number of images to chose from.

    /*
     * Creates a new JFrame with the given title and creates its contents
     */
    public MemoryGamePanel(String codeBaseIn, boolean isApplet)
    {
        codeBase = codeBaseIn;
        bgImgNum = (int)(Math.random() * NUM_IMAGES);

        // Control buttons based on whether is is an application or applet
        // (Applets don't need an exit buttons)
        ctrlButtons = new JButton[isApplet ? 1 : 2];
        ctrlButtons[0] = new JButton("Reset");
        if(!isApplet)
            ctrlButtons[1] = new JButton("Exit");

        bgImageList = new ArrayList<Image>(NUM_IMAGES);

        // Get all the background images and store them at once so the game
        // will reset quicker
        try
        {
            for(int i = 0; i < NUM_IMAGES; i++)
                bgImageList.add(ImageLoader.getImage(new URL(codeBase
                        + "images/" + i + "_bg.gif")));
            // new URL(codeBase + "images/" + i + "_bg.jpg")));
        }
        catch(MalformedURLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        cardButtonList = new ArrayList<CardButton>(NUM_IMAGES * 2);
        currButtonList = new ArrayList<CardButton>(MemoryGame.NUM_CARDS);

        // Create a list and add all the images to chose from to it.
        // Creates and adds each images twice.
        for(int i = 0; i < NUM_IMAGES; i++)
        {
            CardButton cb;

            cb = new CardButton(i, codeBase);
            cb.setBorderPainted(false);
            cardButtonList.add(cb);

            cb = new CardButton(i, codeBase);
            cb.setBorderPainted(false);
            cardButtonList.add(cb);
        }

        // Creates the game controller
        game = new MemoryGame(this);

        // Create the two panels
        buttonPanel = new ButtonPanel(getCardButtonList(), 
                (Image)bgImageList.get(bgImgNum));
        ctrlPanel = new CtrlPanel(ctrlButtons, 
                (int)buttonPanel.getPreferredSize().getWidth());

        // The size is the width of either of the panels and the sum of the
        // height of the two panels.
        size = new Dimension((int)(buttonPanel.getPreferredSize().getWidth()),
                (int)(buttonPanel.getPreferredSize().getHeight() + 
                        ctrlPanel.getPreferredSize().getHeight()));

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(ctrlPanel, BorderLayout.SOUTH);
    }

    /*
     * Resets the buttons for the button panel.
     */
    public void reset()
    {
        // Unhide all the card buttons and set them face down
        for(CardButton cb : cardButtonList)
        {
            cb.setFaceDown();
            cb.setVisible(true);
        }

        ctrlButtons[0].setText("Reset");
        bgImgNum = (int)(Math.random() * NUM_IMAGES);
        buttonPanel.reset(getCardButtonList(), 
                (Image)bgImageList.get(bgImgNum));
        updateDisplay(0);
    }

    /*
     * Returns a shuffled list of randomly chosen pairs from the list.
     */
    public ArrayList<CardButton> getCardButtonList()
    {
        currButtonList.clear();

        // Starts from a random even index of the cardButtonList to get the
        // card buttons. Uses the mod operator to loop back around the index.
        int numImgs = NUM_IMAGES * 2;
        for(int i = (int)(Math.random() * numImgs) * 2; 
            currButtonList.size() != MemoryGame.NUM_CARDS; i += 2)
        {
            if((i % numImgs != bgImgNum * 2) &&
               ((i % numImgs) != bgImgNum * 2 + 1))
            {
                currButtonList.add(cardButtonList.get(i % numImgs));
                currButtonList.add(cardButtonList.get((i % numImgs) + 1));
            }
        }

        Collections.shuffle(currButtonList);

        return currButtonList;
    }

    /*
     * Returns the size of the frame
     */
    public Dimension getPreferredSize()
    {
        return size;
    }

    /*
     * redraws the panel, then waits a specified period
     */
    public void updateDisplay(int millis)
    {
        buttonPanel.updateDisplay(millis);
    }
}
