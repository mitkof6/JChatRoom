package main;

import exceptions.SocketException;
import exceptions.StreamException;
import gui.MainWindow;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


/**
 * This class is the server of the application. It listens on a port and when a connection
 * is established it calls the {@link OutcomeClient}.
 * 
 * @author Jim Stanev
 *
 */
public class Server {
	private ServerSocket server;
	private static int port = 10000;
	private int backlog = 10;
	private Socket client;
	
	/**
	 * The constructor.
	 * 
	 * @throws SocketException
	 */
	@SuppressWarnings("static-access")
	public Server() throws SocketException{
		
		//Starts the server
		try {
			server = new ServerSocket(port, backlog);
			
			//Sets the MainWindow status label
			MainWindow.setLabel("IP -> "+server.getInetAddress().getLocalHost().getHostAddress()+" : "+getPort());
			
			//Waits for a connection
			while(true){
				client = server.accept();
				try {
					@SuppressWarnings("unused")
					IncomeClient clientInstance = new IncomeClient(client);
				} catch (StreamException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Stream Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (IOException e) {
			throw new SocketException("Unable to start the server try again");
		}
	}

	/**
	 * The port getter.
	 * 
	 * @return port the port of the server
	 */
	public static int getPort(){
		return port;
	}
	

}
