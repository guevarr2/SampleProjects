package model;

public class Globals {
    public static iCommunicator communicator = null;
    public static boolean myTurn = false;

	// in pixels
	public static int panelSize = 600;
	public static int margins = 20;
	public static int numSides = 11;
	public static int tileSize = (panelSize - (2 * margins)) / numSides;
	public static int gridSize = tileSize * numSides;

}
