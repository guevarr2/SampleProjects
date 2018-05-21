/**
 * AttackGrid Class: This class is a panel that allows the user to select where 
 * to attack on the grid. The idea is to track a pair object containing a row and 
 * column value whenever the user clicks on the ShipGrid. This will be sent to 
 * the server to determine a HIT or a MISS and update the panel based on the result.
 */
package view;

import javax.swing.JPanel;

import controller.AttackController;
import model.Const;
import model.Game;
import model.Globals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial") // suppress serialization warning

public class AttackGrid extends JPanel implements Observer {
	int[][] grid = null;
	AttackController attackController = null;

	/**
	 * Constructor:
	 */
	public AttackGrid() {
		setPanelSettings(Globals.panelSize);
	}

	/**
	 * Constructor with custom size
	 * 
	 * @param size
	 *            size of the grid in pixels
	 */
	public AttackGrid(int size) {
		Globals.panelSize = size;
		Globals.numSides = 11;
		Globals.margins = 20;
		Globals.tileSize = (Globals.panelSize - (2 * Globals.margins)) / Globals.numSides;
		setPanelSettings(Globals.panelSize);
	}
	

	/**
	 * Configures the setting for the JPanel
	 */
	private void setPanelSettings(int pixelSize) {
		setPreferredSize(new Dimension(pixelSize, pixelSize + Globals.margins));
		setBackground(Color.DARK_GRAY);
		setForeground(Const.seaBlue);
	}

	/**
	 * Method to paint components to the JPanel It's automatically called using the
	 * super method
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		drawGrid(g2);
		drawLabels(g2);
	}

	// *********************************************************************//
	// *********************** GRAPHICS METHODS ****************************//
	// *********************************************************************//

	/**
	 * Creates a grid of tiles using Graphics2D
	 * 
	 * @param g
	 *            the graphic to be manipulated
	 */
	private void drawGrid(Graphics2D g) {
		for (int i = 0; i < Globals.numSides; i++) {
			for (int j = 0; j < Globals.numSides; j++) {
				// position to paint the tile
				int row = i;
				int col = j;

				int x = Globals.margins + col * Globals.tileSize;
				int y = Globals.margins + row * Globals.tileSize;
				// set tile color
				if (grid == null) {
					if (row == 0 && col == 0) // light gray for (0,0)
						g.setColor(Color.lightGray);
					else if (row == 0 || col == 0) // gray for the labels
						g.setColor(Color.GRAY);
					else
						g.setColor(getForeground()); // default
				} else {
					if (row == 0 && col == 0) // light gray for (0,0)
						g.setColor(Color.lightGray);
					else if (row == 0 || col == 0) // gray for the labels
						g.setColor(Color.GRAY);
					else if (grid[row][col] == Const.MISS) // black tile for missed
						g.setColor(Color.BLACK);
					else if (grid[row][col] == Const.HIT) // red for hits
						g.setColor(Color.RED);
					else
						g.setColor(getForeground()); // default

				}
				// fill margins
				g.fillRect(x, y, Globals.tileSize, Globals.tileSize);
				g.setColor(Color.black.darker());

				// background color
				g.drawRect(x, y, Globals.tileSize, Globals.tileSize);
				g.setColor(Color.WHITE);
			}
		}
	}

	/**
	 * Draws the labels for the grid
	 * 
	 * @param g
	 *            the graphic component to draw on
	 */
	private void drawLabels(Graphics2D g) {
		for (int row = 0; row < Globals.numSides; row++) {
			for (int col = 0; col < Globals.numSides; col++) {
				// tile positions with margins
				int x = Globals.margins + col * Globals.tileSize;
				int y = Globals.margins + row * Globals.tileSize;

				// Draw row, column labels
				if (col == 0 && row > 0) {
					drawCenteredString(g, String.valueOf(row), x, y);
				} else if (row == 0 && col > 0) {
					char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
					drawCenteredString(g, String.valueOf(alphabet[col - 1]), x, y);
				}

			}
		}
	}

	/**
	 * Draws the string for the labels
	 * 
	 * @param g
	 *            the graphic component to draw on
	 * @param s
	 *            the string to be drawn
	 * @param x
	 *            the x position to draw
	 * @param y
	 *            the y position to draw
	 */
	private void drawCenteredString(Graphics g, String s, int x, int y) {
		// get the distance above and below the baseline for the given font (SansSerif)
		FontMetrics fm = g.getFontMetrics();
		int asc = fm.getAscent();
		int des = fm.getDescent();

		// fit centered relative to the tile size
		x = x + (Globals.tileSize - fm.stringWidth(s)) / 2;
		y = y + (asc + (Globals.tileSize - (asc + des)) / 2);

		// draw the text to the tile
		g.drawString(s, x, y);
	}

	@Override
	public void update(Observable o, Object arg) {
		switch ((String) arg) {
		case Const.allPlaced:
		case Const.myTurn:
			this.addMouseListener(attackController);
			break;
		case Const.opponentTurn:
			this.removeMouseListener(attackController);
			break;
		case Const.hit:
		case Const.miss:
			Game game = (Game) o;
			this.grid = game.getAttackGrid();
			repaint();
			break;
		}
	}

	public void setController(AttackController attackController) {
		this.attackController = attackController;

	}
}
