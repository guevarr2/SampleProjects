/**
 * BattleShipLauncher: Contains the main method to run the BattleShip Game
 */
package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import controller.AttackController;
import controller.ShipController;
import model.Client;
import model.Game;
import model.Globals;
import model.Server;
import model.iCommunicator;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GameLauncher extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static iCommunicator getCommunicator(String ip, int port) {
		if (ip == "127.0.0.1") {
			iCommunicator communicator = new Server(port);
			if (communicator.tryServer())
				return communicator;
			else {
				communicator = null;
				return new Client();
			}
		} else {
			InetAddress inetAddress;
			try {
				inetAddress = InetAddress.getLocalHost();
				String serverIPAddress = inetAddress.getHostName();
				if (ip == serverIPAddress)
					return new Server(port);
				else
					return new Client(ip, port);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public GameLauncher(iCommunicator communicator, boolean isMyTurn) {
		super("Battleships");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		Game game = new Game(communicator, isMyTurn);
		// Create BattleShipMenu, an extension of JFrame
		BattleShipMenu menu = new BattleShipMenu(game);
	    setJMenuBar(menu);
		// add AttackGrid to Frame, an extension of JPanel
		// Internally fetch and receive data from server
		AttackGrid attackGrid = new AttackGrid();
		AttackController attackController = new AttackController(game);
		attackGrid.setController(attackController);
		add(attackGrid, BorderLayout.CENTER);

		// add ShipGrid to Frame, an extension of JPanel
		ShipGrid shipGrid = new ShipGrid();
		ShipController shipController = new ShipController(game);
		shipGrid.setController(shipController);
		add(shipGrid, BorderLayout.EAST);

		// add StatusBar to Frame, an extension of JPanel
		StatusBar statusBar = new StatusBar();
		add(statusBar, BorderLayout.SOUTH);

		game.addObserver(attackGrid);
		game.addObserver(shipGrid);
		game.addObserver(statusBar);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		game.updateStatus();
	}

	public static void main(String args[]) {
		if (args.length > 1)
			Globals.communicator = getCommunicator(args[0], Integer.parseInt(args[1]));
		else if (args.length == 1)
			Globals.communicator = getCommunicator("127.0.0.1", Integer.parseInt(args[0]));
		else
			Globals.communicator = getCommunicator("127.0.0.1", 7966);
		Globals.myTurn = !Globals.communicator.amIServer();

		new GameLauncher(Globals.communicator, Globals.myTurn);
	}
}
