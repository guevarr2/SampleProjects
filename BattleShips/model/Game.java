package model;

import static java.lang.Math.abs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class Game extends Observable implements ActionListener {
	private int[][] attackGrid = null;
	private int[][] shipGrid = null;
	private Timer timer = null;
	private int columnCheck;
	private int rowCheck;
	private int moves = 0;
	private int hits = 0;

	// Counts for the amount of spaces each boat can occupy
	// Will be used to limit the amount of tiles the user can click on
	private static int airCount = 5;
	private static int battleCount = 4;
	private static int destroyerCount = 3;
	private static int subCount = 3;
	private static int pbCount = 2;

	private static int[] placedRows = new int[5];
	private static int[] placedCols = new int[5];

	private static int placed = 0;
	private static int row;
	private static int col;
	private static boolean matchedRow;

	private iCommunicator communicator;
	private boolean isMyTurn;

	public Game(iCommunicator communicator, boolean isMyTurn) {
		super();
		this.communicator = communicator;
		this.isMyTurn = isMyTurn;
		timer = new Timer(1000, this);
		attackGrid = getGrid();
		shipGrid = getGrid();
	}
	
	// Grid getters
	public int[][] getShipGrid() {
		return shipGrid;
	}

	public int[][] getAttackGrid() {
		return attackGrid;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public int getHits() {
		return hits;
	}

	/**
	 * Initializes all the values in the grid to be open for selection
	 */
	private int[][] getGrid() {
		int[][] grid = new int[Globals.numSides][Globals.numSides];
		for (int row = 0; row < Globals.numSides; row++) {
			for (int col = 0; col < Globals.numSides; col++) {
				grid[row][col] = Const.OPEN;
			}
		}
		return grid;
	}

	public boolean allHit(int[][] grid) {
		int count = 0;
		for (int i = 0; i < Globals.numSides; i++) {
			for (int j = 0; j < Globals.numSides; j++) {
				if (grid[i][j] == Const.HIT)
					count++;
			}
		}
		return count == 17;
	}

	// Check that all ships have been placed, if so change the outer flag to exit
	// both loops
	public boolean allPlaced() {
		return airCount == 0 && battleCount == 0 && destroyerCount == 0 && subCount == 0 && pbCount == 0;
	}

	public void placeTile(int clickedRow, int clickedCol) {

		// Each ship will need a checking method or a counter to allow
		// only a certain amount of tiles to be changed
		// as well as an overall method for checking vertical or horizontal placement

		if (airCount != 0) {
			airCount = shipPlace(shipGrid, airCount, Const.airCarrier, clickedRow, clickedCol);
			if (airCount == 0) {
				setChanged();
				notifyObservers(Const.placeBattle);
				placed = 0;
			}
		} else if (battleCount != 0) {
			battleCount = shipPlace(shipGrid, battleCount, Const.battleship, clickedRow, clickedCol);
			if (battleCount == 0) {
				setChanged();
				notifyObservers(Const.placeDest);
				placed = 0;
			}
		} else if (destroyerCount != 0) {
			destroyerCount = shipPlace(shipGrid, destroyerCount, Const.destroyer, clickedRow, clickedCol);
			if (destroyerCount == 0) {
				setChanged();
				notifyObservers(Const.placeSub);
				placed = 0;
			}
		} else if (subCount != 0) {
			subCount = shipPlace(shipGrid, subCount, Const.submarine, clickedRow, clickedCol);
			if (subCount == 0) {
				setChanged();
				notifyObservers(Const.placePat);
				placed = 0;
			}
		} else if (pbCount != 0) {
			pbCount = shipPlace(shipGrid, pbCount, Const.patrolBoat, clickedRow, clickedCol);
			if (pbCount == 0) {
				placed = 0;
			}
		} else {
		}
		setChanged();
		notifyObservers(Const.placed);
		if (allPlaced()) {
			setChanged();
			notifyObservers(Const.allPlaced);
			setChanged();
			notifyObservers(isMyTurn ? Const.myTurn : Const.opponentTurn);
			if (!isMyTurn)
				timer.start();
		}
	}

	/**
	 * Determines where the user clicked and changes the tiles depending on the
	 * result of the selection.
	 * 
	 * White Tile Displayed when the tile is open for attack (0) Black Tile
	 * Displayed when the attack misses a ship (1) Red Tile Displayed when the
	 * attack hits a ship (2)
	 * 
	 */
	private int shipPlace(int[][] grid, int shipLength, int shipType, int clickedRow, int clickedCol) {
		if (modify(grid, clickedRow, clickedCol, shipType)) {
			shipLength--;
			placedRows[placed] = clickedRow;
			placedCols[placed] = clickedCol;
			placed++;

			switch (placed) {
			case 1:
				break;
			case 2:
				if ((clickedRow == row) && (abs(clickedCol - col) == 1)) {
					matchedRow = true;
				} else if (clickedCol == col && (abs(clickedRow - row) == 1)) {
					matchedRow = false;
				} else {
					for (int i = 0; i < placed; i++) {
						grid[placedRows[i]][placedCols[i]] = Const.OPEN;
					}
					shipLength = shipLength + placed;
					placed = 0;
				}
				break;
			default:
				if (!((clickedRow == row && matchedRow == true && (abs(clickedCol - col) == 1))
						|| (clickedCol == col && matchedRow == false && (abs(clickedRow - row) == 1)))) {
					for (int i = 0; i < placed; i++) {
						grid[placedRows[i]][placedCols[i]] = Const.OPEN;
					}
					shipLength = shipLength + placed;
					placed = 0;
				}
				break;
			}
			row = clickedRow;
			col = clickedCol;
		}
		return shipLength;
	}

	// This method is used to prevent collision on ship placement
	private boolean modify(int[][] grid, int clickRow, int clickCol, int shipType) {
		if (grid[clickRow][clickCol] != 0)
			return false;
		else {
			grid[clickRow][clickCol] = shipType;
			return true;
		}
	}

	public void doMove(int clickedRow, int clickedCol) {
		if (isMyTurn) {
			moves++;
			isMyTurn = !isMyTurn;
			String response = communicator.sendMove(clickedRow + " " + clickedCol);
			setChanged();
			notifyObservers(Const.connected);
			switch (response) {
			case Const.hit:
				attackGrid[clickedRow][clickedCol] = Const.HIT;
				hits++;
				break;
			case Const.miss:
				attackGrid[clickedRow][clickedCol] = Const.MISS;
				break;
			}
			setChanged();
			notifyObservers(response);
			if (allHit(attackGrid)) {
				setChanged();
				notifyObservers(Const.gameWon);
			} else {
				setChanged();
				notifyObservers(isMyTurn ? Const.myTurn : Const.opponentTurn);
				timer.start();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.stop();
		String move = communicator.getMove();
		setChanged();
		notifyObservers(Const.connected);
		String[] coords = move.split(" ");
		if (coords.length == 2) {
			String response = null;
			int row = Integer.parseInt(coords[0]);
			int col = Integer.parseInt(coords[1]);
			if (shipGrid[row][col] == Const.OPEN) {
				response = Const.miss;
				shipGrid[row][col] = Const.MISS;
			} else {
				response = Const.hit;
				shipGrid[row][col] = Const.HIT;

			}
			communicator.sendResponse(response);
			setChanged();
			notifyObservers(response);
			if (response == Const.hit && allHit(shipGrid)) {
				setChanged();
				notifyObservers(Const.gameLost);
			} else {
				isMyTurn = !isMyTurn;
				setChanged();
				notifyObservers(isMyTurn ? Const.myTurn : Const.opponentTurn);
			}
		}
	}

	public void updateStatus() {
		setChanged();
		notifyObservers(communicator.amIServer() ? Const.server : Const.client);
	}

}
