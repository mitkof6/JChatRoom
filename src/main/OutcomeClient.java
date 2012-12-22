package main;

import exceptions.NameException;
import exceptions.SocketException;
import exceptions.StreamException;
import gui.ChatWindow;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class is responsible when an outcome connection is listed.
 * 
 * @author Jim Stanev
 *
 */
public class OutcomeClient extends Client{

	/**
	 * The constructor.
	 * 
	 * @param ip the ip of the server calling
	 * @throws SocketException
	 * @throws StreamException
	 */
	public OutcomeClient(String ip) throws SocketException, StreamException{
		
		//Connect to the server
		try {
			this.client = new Socket(ip, Server.getPort());
		} catch (UnknownHostException e) {
			throw new SocketException("Unable to connect with client");
		} catch (IOException e) {
			throw new SocketException("Unable to connect with client");
		}
		
		//Open the streams
		try {
			in = new DataInputStream(client.getInputStream());
			out = new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			throw new StreamException("Unable to open the streams");
		}
		
		//Get user name and client name
		NameOptionPane namePane;
		try {
			namePane = new NameOptionPane(ip);
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
