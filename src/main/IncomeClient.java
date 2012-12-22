package main;

import exceptions.NameException;
import exceptions.StreamException;
import gui.ChatWindow;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * This class is responsible when an income connection is listed.
 * 
 * @author Jim Stanev
 *
 */
public class IncomeClient extends Client{
	
	
	/**
	 * The consturctor.
	 * 
	 * @param client the socket of the client
	 * @throws StreamException
	 */
	public IncomeClient(Socket client) throws StreamException{
		this.client = client;
		
		//Starts the streams
		try {
			in = new DataInputStream(client.getInputStream());
			out = new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			throw new StreamException("Unable to open the streams");
		}
		
		//Get user name and client name
		NameOptionPane namePane;
		try {
			namePane = new NameOptionPane(client.getInetAddress().getHostAddress());
			clientName = namePane.getClientName();
			userName = namePane.getUserName();
		} catch (NameException e) {
			clientName = "Client";
			userName = "User";
		}
		
		
		//Set the chat window for the connection
		this.chatWindow = new ChatWindow(clientName);
		chatWindow.setVisible(true);
		chatWindow.pack();
		chatWindow.setResizable(false);
		
		chatWindow.sendField.addKeyListener(this);
		
		//Make a thread and start
		Thread incomeClient = new Thread(this);
		incomeClient.start();
	}
	
	
}
