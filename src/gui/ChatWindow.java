package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
/**
 * This class is called when a connection is done.
 * 
 * @author Jim Stanev
 */
public class ChatWindow extends JFrame{
	
	
	private static final long serialVersionUID = 1L;

	public JTextArea textArea;
	public JTextField sendField;

	/**
	 * The constructor.
	 * @param clientName the client's name
	 * 
	 */
	public ChatWindow(String clientName){
		
		//Initialization
		super("JChat Room "+clientName);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/logo.gif")));
		this.setLayout(new BorderLayout());
		this.setBounds(200, 200, 0, 0);
		
		//Text area
		textArea = new JTextArea(10, 20);
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier", Font.ITALIC, 13));
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setAutoscrolls(true);
		this.add(textAreaScrollPane, BorderLayout.CENTER);
				
		//Send field
		JPanel controls = new JPanel();
		controls.setBackground(Color.BLACK);
		sendField = new JTextField(20);
		controls.add(sendField);
		this.add(controls,BorderLayout.SOUTH);
		
	}
}
