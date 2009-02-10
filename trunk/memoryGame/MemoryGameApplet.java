/*
 * Project: Memory Game
 * File: MemoryGameApplet.c
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

import javax.swing.JApplet;

public class MemoryGameApplet extends JApplet
{
    private static final long serialVersionUID = -4179844911231101217L;

    MemoryGame game;

    public void init()
    {
        MemoryGamePanel panel = 
            new MemoryGamePanel(getCodeBase().toString(), true);
        game = panel.game;

        getContentPane().add(panel);
        setVisible(true);
    }

    public void start()
    {
        game.playGame();
    }
}
