/**
 * StatusBar:
 * Handles the information for the current state of the game and displays
 * them on a status bar at the bottom of the window.
 */
package view;

import java.awt.GridLayout;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Const;
import model.Game;

public class StatusBar  extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	
	private Label[] status = new Label[3];
	
	public StatusBar() {
		super(new GridLayout());
		status[0] = new Label("Not connected");
		this.add(status[0]);
		status[1] = new Label("Server");
		this.add(status[1]);
		status[2] = new Label("Please place Aircraft carrier (5 cells) on the right grid");
		this.add(status[2]);
	}
	
	private void setConnectionStatus(String text) {
		status[0].setText(text);
	}
	
	private void setServerStatus(String text) {
		status[1].setText(text);
	}
	
	private void setTurnStatus(String text) {
		status[2].setText(text);
	}
	
    public void update(Observable o, Object arg) {
    	switch ((String) arg) {
    	case Const.connected:
    		setConnectionStatus("Connected");
    		break;
    	case Const.server:
    		setServerStatus("Server");
    		break;
    	case Const.client:
    		setServerStatus("Client");
    		break;
    	case Const.myTurn:
    		setTurnStatus("My turn");
    		break;
    	case Const.opponentTurn:
    		setTurnStatus("Opponent turn");
    		break;
    	case Const.gameWon:
    		setTurnStatus("Congratulations, you won!!!!!");
    		break;
    	case Const.gameLost:
    		setTurnStatus("You Lost!");
    		break;
    	case Const.placeBattle:
    		setTurnStatus("Please place Battleship (4 cells) on the right grid");
    		break;
    	case Const.placeDest:
    		setTurnStatus("Please place Destroyer (3 cells) on the right grid");
    		break;
    	case Const.placeSub:
    		setTurnStatus("Please place Submarine (3 cells) on the right grid");
    		break;
    	case Const.placePat:
    		setTurnStatus("Please place Patrol boat (2 cells) on the right grid");
    		break;

    	}
    }
}
