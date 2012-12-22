package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import exceptions.SocketException;
import exceptions.StreamException;
import exceptions.WindowIPOptionPaneException;

import main.OutcomeClient;

/**
 * This class is the main window.
 * 
 * @author Jim Stanev
 */
public class MainWindow extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private static JLabel statusLabel;
	
	private Icon connectIcon = new ImageIcon(getClass().getResource("resources/connect.gif"));
	private Icon exitIcon = new ImageIcon(getClass().getResource("resources/exit.gif"));
	
	/**
	 * The constructor.
	 */
	public MainWindow(){
		
		//Initialization
		super("JChat Room");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/logo.gif")));
		this.setLayout(new BorderLayout());
		
		//Tool bar menu
		JToolBar menuButtonToolbar = new JToolBar();
		JButton connectButton = new JButton("Connect", connectIcon);
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectionOptionPane connectionPane;
				try {
					connectionPane = new ConnectionOptionPane();
				} catch (WindowIPOptionPaneException e) {
					return;
				}
				try {
					@SuppressWarnings("unused")
					OutcomeClient outComeClient = new OutcomeClient(connectionPane.getIP());
				} catch (SocketException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Socket Error", JOptionPane.ERROR_MESSAGE);
				} catch (StreamException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Stream Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
			
			/**
			 * Inner class for the ip option pane.
			 * 
			 * @author Jim Stanev
			 *
			 */
			class ConnectionOptionPane{

				private JTextField ipTextField;
				
				/**
				 * The constructor.
				 * 
				 * @throws WindowIPOptionPaneException
				 */
				public  ConnectionOptionPane() throws WindowIPOptionPaneException{
					ipTextField = new JTextField(10);

				    JPanel myPanel = new JPanel();
				    myPanel.add(new JLabel("IP:"));
				    myPanel.add(ipTextField);

				    int result = JOptionPane.showConfirmDialog(null, myPanel,"Please give the ip address of the client", JOptionPane.OK_CANCEL_OPTION);
				    if(result==JOptionPane.CLOSED_OPTION||result==JOptionPane.CANCEL_OPTION){
				    	result = JOptionPane.CLOSED_OPTION;
				    	throw new WindowIPOptionPaneException("IP Dialog Pane: "+result);
				    }
				    
				}
				
				/**
				 * Get ip method.
				 * 
				 * @return ipTextField to string
				 */
				public String getIP(){
					return ipTextField.getText();
				}
				
			
				
			}

		});
		JButton exitButton = new JButton("Exit", exitIcon);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuButtonToolbar.add(connectButton);
		menuButtonToolbar.add(exitButton);
		this.add(menuButtonToolbar,BorderLayout.NORTH);
		
		//Message panel
		JPanel vendor = new JPanel();
		vendor.setLayout(new GridLayout(6, 1, 1, 1));
		vendor.setBackground(Color.BLACK);
		JLabel[] message = new JLabel[6];
		message[0] = new JLabel("      JChat Room v1.0     ");
		message[0].setFont(new Font("Courier New", Font.BOLD, 14));
		message[1] = new JLabel("");
		message[2] = new JLabel("         Java SE v1.7          ");
		message[2].setFont(new Font("Courier New", Font.ROMAN_BASELINE, 12));
		message[3] = new JLabel("          Jim Stanev          ");
		message[3].setFont(new Font("Courier New", 1, 12));
		message[4] = new JLabel("");
		message[5] = new JLabel("  e-mail: jimstanev@gmail.com ");
		message[5].setFont(new Font("Courier New", Font.ITALIC, 12));
		for(int i = 0;i<6;i++){
			message[i].setForeground(Color.RED);
			vendor.add(message[i]);
		}
		this.add(vendor,BorderLayout.CENTER);
		
		//Status label 
		statusLabel = new JLabel("");
		statusLabel.setFont(new Font("Courier New", Font.BOLD, 10));
		statusLabel.setForeground(Color.BLUE);
		this.add(statusLabel,BorderLayout.SOUTH);
	}
	
	/**
	 * The status label setter.
	 * 
	 * @param text sets the text of the label
	 */
	public static void setLabel(String text){
		statusLabel.setText(text);
	}
}
