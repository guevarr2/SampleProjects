/**
 * Initial menu layout
 * Menu class contains gui part with JMenus and JMenuItems required for the
 * project. These include, information about the design group, exiting the program,
 * mnemonics for each item and menu, statistics of the game, connection troubleshooting,
 * and the ability to establish a connection between host and client.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.Game;

@SuppressWarnings("serial")
public class BattleShipMenu extends JMenuBar {
	// private variables, can add
	Game game;
	private static String ruleHeader = new String("BattleShip Rules");
	private static String rules = new String(
			"Rules description\n 1. Players willl have a chance to place their ships on the grid.\n 2. Players will then take turns giving coordinates to send torpedoes.\n 3. The game will update by hit or miss.\n 4. To win, a player must sink all the ships of their opponent.\n");
	private static String contributers = new String(
			"Aaron Guevarra, guevarr2 \nSimon Val Rankov, sranko2 \nFilip Serwinski, serwins2\n");
	private static String aboutHeader = new String("Group Members(Name, netID)");
	private static String connection = new String(
			"When run on the same machine the first instance of the application will run as a server.\nThe second instance will assume the cleint role.\nConnection between the two will happen automatically when you make your first move.\nIf you need to play on two different machines, the program with need two command line parameters:\nPlease run the server on the server machine with 127.0.01 PortNumber, e.g., 7966\nand the second instance on the client machine with parameters Server IPAddress and Port number, e.g, 192.168.0.37 7966.");
	private static String connectionHeader = new String("Connection Troubleshooting");
	private static String statsHeader = new String("Game Statistics");

	// Creation of fonts applied to menus and menu items along with dialog boxes
	Font fMenu = new Font("Times New Roman", Font.PLAIN, 28);
	Font fMenuItem = new Font("Times New Roman", Font.PLAIN, 24);
	Font fDialog = new Font("Times New Roman", Font.PLAIN, 24);
	

	public BattleShipMenu(Game game) {
		this.game = game;
		UIManager.put("Menu.font", fMenu);
		UIManager.put("MenuItem.font", fMenuItem);
		UIManager.put("OptionPane.messageFont", fDialog);

		// Main File tab with its components
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		// About item in file menu
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic('A');
		fileMenu.add(aboutItem);
		aboutItem.addActionListener(

				new ActionListener() { // inner class

					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(BattleShipMenu.this, contributers, aboutHeader,
								JOptionPane.PLAIN_MESSAGE);
					}
				} // end inner class
		); // end actionListener

		// Exit item in file menu
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		fileMenu.add(exitItem);
		exitItem.addActionListener(

				new ActionListener() { // inner class

					// Application termination
					public void actionPerformed(ActionEvent event) {
						System.exit(0);
					}
				} // end inner class
		); // end actionListener

		// Help menu tab
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');

		// Game Description Item in help Menu
		JMenuItem ruleItem = new JMenuItem("Rules");
		ruleItem.setMnemonic('R');
		helpMenu.add(ruleItem);
		ruleItem.addActionListener(

				new ActionListener() { // inner class

					// Message box event
					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(BattleShipMenu.this, rules, ruleHeader,
								JOptionPane.PLAIN_MESSAGE);
					}
				} // end inner class
		); // end actionListener

		// Connection Help Item
		JMenuItem connectionItem = new JMenuItem("Connection");
		connectionItem.setMnemonic('C');
		helpMenu.add(connectionItem);
		connectionItem.addActionListener(

				new ActionListener() { // inner class

					// Message box event
					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(BattleShipMenu.this, connection, connectionHeader,
								JOptionPane.PLAIN_MESSAGE);
					}
				} // end inner class
		); // end actionListener

		// Statistics
		JMenu statsMenu = new JMenu("Statistics");
		statsMenu.setMnemonic('S');

		JMenuItem statsItem = new JMenuItem("Display");
		statsItem.setMnemonic('D');
		statsMenu.add(statsItem);
		statsItem.addActionListener(

				new ActionListener() { // inner class

					// Message Box
					public void actionPerformed(ActionEvent event) {
						String statistic = "Total number of moves: " + game.getMoves() + "\nTotal number of hits: " + game.getHits();
						JOptionPane.showMessageDialog(BattleShipMenu.this, statistic, statsHeader,
								JOptionPane.PLAIN_MESSAGE);
					}
				} // end inner class
		); // end actionListener

		// Creating bar menu and setting along with adding all menus
		add(fileMenu);
		add(helpMenu);
		add(statsMenu);
	}

}
