package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import model.Globals;

public class ShipController implements MouseListener {
	private Game game;
    public ShipController(Game game) {
        this.game = game;
    }

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
    		    game.placeTile(clickedRow, clickedCol);
    }

    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }

}
