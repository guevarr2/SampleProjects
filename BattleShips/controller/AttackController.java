package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import model.Globals;

public class AttackController implements MouseListener {
	private Game game;
    public AttackController(Game game) {
        this.game = game;
    }
    
	/**
	 * Determines where the user clicked and changes the tiles depending
	 * on the result of the selection.
	 * 
	 * White Tile  Displayed when the tile is open for attack (0)
	 * Black Tile  Displayed when the attack misses a ship (1)
	 * Red Tile    Displayed when the attack hits a ship (2)
	 * 
	 */
      
    public void mousePressed(MouseEvent event) {
    	  
    		    // get x, y position of where user clicked
    		    int x = event.getX() - Globals.margins;
    		    int y = event.getY() - Globals.margins;

    		    // find which tile user clicked
    		    int clickedCol = x / Globals.tileSize;
    		    int clickedRow = y / Globals.tileSize;
    		    
    		    // do nothing if clicked outside of grid
    		    if (clickedCol < 1 || clickedRow < 1 || x > Globals.gridSize || y > Globals.gridSize)
    		      return;
     		    game.doMove(clickedRow, clickedCol);
    }

    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }

}
