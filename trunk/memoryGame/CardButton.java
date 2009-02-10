/*
 * Project: Memory Game
 * File: CardButton.c
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * A button with an icon to represent a card for the memory game.
 */
public class CardButton extends JButton
{
    private static final long serialVersionUID = -7507399863924286730L;

    private int cardNum;      // Number of the image to know the file name
    private Icon imgIcon;     // "Front" picture for the card
    private Icon bgIcon;      // "Back" image of the card
    private boolean isFaceUp; // Whether if picture is shown or not

    public CardButton(int cardNumIn, String codeBase)
    {
        cardNum = cardNumIn;
        isFaceUp = false;

        try
        {
            /*
             * imgIcon = new ImageIcon(ImageLoader.getImage( new URL(codeBase +
             * "images/" + cardNum + ".jpg"))); bgIcon = new
             * ImageIcon(ImageLoader.getImage( new URL(codeBase +
             * "images/bg.jpg")));
             */

            imgIcon = new ImageIcon(ImageLoader.getImage(new URL(codeBase
                    + "images/" + cardNum + ".gif")));

            bgIcon = new ImageIcon(ImageLoader.getImage(new URL(codeBase
                    + "images/bg.gif")));
        }
        catch(MalformedURLException e)
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }

        setActionCommand(String.valueOf(cardNum));
        setIcon(bgIcon);
    }

    public int getNumber()
    {
        return cardNum;
    }

    /**
     * Determines whether a Card is equal to the give card, i.e. the number for
     * each is the same.
     * 
     * @param card
     *            The card to compare the current card to.
     * @return True if the card numbers are the same, false otherwise.
     */
    public boolean equals(CardButton card)
    {
        return cardNum == card.cardNum ? true : false;
    }

    public void setFaceUp()
    {
        setIcon(imgIcon);
        isFaceUp = true;
    }

    public void setFaceDown()
    {
        setIcon(bgIcon);
        isFaceUp = false;
    }

    public boolean isFaceUp()
    {
        return isFaceUp;
    }

    public int getWidth()
    {
        return imgIcon.getIconWidth();
    }

    public int getHeight()
    {
        return imgIcon.getIconHeight();
    }

    public String toString()
    {
        return "Num: " + cardNum + "; " + super.toString();
    }
}
