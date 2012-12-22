package main;

import javax.swing.JOptionPane;

import exceptions.SocketException;

import gui.MainWindow;
/**
 * The main class. From this class starts the application.
 * 
 * @author Jim Stanev
 *
 */
public class Main {

	/**
	 * The main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		//Main window
		MainWindow application = new MainWindow();
		application.setVisible(true);
		application.pack();
		application.setResizable(false);
		
		//Start the server
		try {
			@SuppressWarnings("unused")
			Server serverStrat = new Server();
		} catch (SocketException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Socket Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

	}

}
