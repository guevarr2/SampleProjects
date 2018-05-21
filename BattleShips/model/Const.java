package model;

import java.awt.Color;

public class Const {
	  // Values for ships in terms of placement for the grid
	  public static final int OPEN = 0;
	  public static final int airCarrier = 1;
	  public static final int battleship = 2;
	  public static final int destroyer = 3;
	  public static final int submarine = 4;
	  public static final int patrolBoat = 5;
	  
	  // The color for the sea and the ships
	  public static final Color seaBlue = new Color(0, 50, 75);
	  public static final Color shipColorAC = new Color(104, 97, 97);
	  public static final Color shipColorB = new Color(110, 104, 104);
	  public static final Color shipColorD = new Color(95, 95, 95);
	  public static final Color shipColorS = new Color(104, 101, 101);
	  public static final Color shipColorPB = new Color(100, 96, 96);
	  
	  // Responses
	  public static final String miss = "MISS";
	  public static final String hit = "HIT";
	  public static final int MISS = 7;
	  public static final int HIT = 8;
	  //Other actions
	  public static final String gameWon = "ALLHIT";
	  public static final String gameLost = "ALLSUNK";
	  public static final String allPlaced = "ALLPLACED";
	  public static final String placed = "PLACED";
	  public static final String connected = "CONNECTED";
	  public static final String server = "SERVER";
	  public static final String client = "CLIENT";
	  public static final String myTurn = "MYTURN";
	  public static final String opponentTurn = "OPPONENTTURN";
	  
	  public static final String placeAir = "PLACEAIR";
	  public static final String placeBattle = "PLACEBAT";
	  public static final String placeDest = "PLACEDEST";
	  public static final String placeSub = "PLACESUB";
	  public static final String placePat = "PLACEPAT";
}
