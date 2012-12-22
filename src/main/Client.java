package main;


import exceptions.NameException;
import gui.ChatWindow;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The client class is responsible for defining the behavior of the client connection. For 
 * income and outcome connections.
 * 
 * @author Jim Stanev
 *
 */
public class Client implements Runnable, KeyListener {

	protected Socket client;
	protected DataInputStream in = null;
	protected PrintStream out = null;
	protected String userName, clientName;
	protected ChatWindow chatWindow;
	
	/**
	 * Implements the enter key pressed behavior of the sendField text field.
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.VK_ENTER){
			String replay = userName+": "+chatWindow.sendField.getText()+"\n";
			chatWindow.textArea.append(replay);
			chatWindow.textArea.setForeground(Color.BLUE);
			out.println(chatWindow.sendField.getText());
			chatWindow.sendField.setText("");
		}
	}

	/**
	 * Unimplemented key released.
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {

	}

	/**
	 * Unimplemented key typed.
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	
	/**
	 * The thread run method. Defines the runnable. Gets the input stream and
	 * prints on the text area.
	 */
    @SuppressWarnings({ "deprecation" })
	public void run(){
		if(in!=null&&out!=null){
			while(true){
				try {
					String income = in.readLine();
					chatWindow.textArea.append(clientName+": "+income+"\n");
					chatWindow.textArea.setForeground(Color.RED);
				} catch (IOException e) {
					chatWindow.textArea.append(clientName+" disconnected");
					chatWindow.textArea.setForeground(Color.MAGENTA);
					break;
				} 
			}
		}
	}
    
    /**
     * This class is responsible for getting the user name and the client name during the
     * connection.
     * 
     * @author Jim Stanev
     *
     */
	protected class NameOptionPane{

		private JTextField userNameTextField;
		private JTextField clientNameTextField;

		/**
		 * The constructor.
		 * 
		 * @param IP the ip of the client
		 * @throws NameException
		 */
		public  NameOptionPane(String IP) throws NameException{
			userNameTextField = new JTextField(10);
			clientNameTextField = new JTextField(10);

		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("Username: "));
		    myPanel.add(userNameTextField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("Clientname: "));
		    myPanel.add(clientNameTextField);

		    int result = JOptionPane.showConfirmDialog(null, myPanel,"Please give the names. Calling : "+IP, JOptionPane.OK_CANCEL_OPTION);
		    if(result==JOptionPane.CLOSED_OPTION||result==JOptionPane.CANCEL_OPTION){
		    	result = JOptionPane.CLOSED_OPTION;
		    	throw new NameException("Names not given");
		    }
		    
		}
		
		/**
		 * The user name getter.
		 * 
		 * @return userNameTextField the text of the text field
		 */
		public String getUserName(){
			return userNameTextField.getText();
		}
		
		/**
		 * the client name getter
		 * @return clientNameTextField the text of the text field
		 */
		public String getClientName(){
			return clientNameTextField.getText();
		}
		
	}

}
