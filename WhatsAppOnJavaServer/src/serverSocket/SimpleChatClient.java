package serverSocket;
import java.io.*;
import java.net.*;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.*;



public class SimpleChatClient {

	private JFrame frame;
	private JTextField outgoingTextField;
	private JTextArea incomingTextArea;
	
	private String username = "Guest";
	private JLabel usernameLabel;
	
	private Socket sock;
	private BufferedReader br;
	private PrintWriter pr;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleChatClient window = new SimpleChatClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SimpleChatClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 347, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("SAM Chat");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 341, 28);
				
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(fileMenu);
		
		JMenuItem connectMenuItem = new JMenuItem("Connect");
		connectMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		connectMenuItem.addActionListener(new MenuButtonListener());
		fileMenu.add(connectMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		exitMenuItem.addActionListener(new MenuButtonListener());
		fileMenu.add(exitMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(helpMenu);
		
		JMenuItem authorMenuItem = new JMenuItem("Author");
		authorMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		authorMenuItem.addActionListener(new MenuButtonListener());
		helpMenu.add(authorMenuItem);
		
		JMenuItem contactMenuItem = new JMenuItem("Contact");
		contactMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contactMenuItem.addActionListener(new MenuButtonListener());
		helpMenu.add(contactMenuItem);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		aboutMenuItem.addActionListener(new MenuButtonListener());
		helpMenu.add(aboutMenuItem);
		
		frame.getContentPane().add(menuBar);
		
		JPanel incomingPanel = new JPanel();
		incomingPanel.setBackground(UIManager.getColor("Panel.background"));
		incomingPanel.setBounds(0, 26, 341, 242);
		incomingPanel.setLayout(null);
		
		incomingTextArea = new JTextArea();
		incomingTextArea.setBounds(10, 11, 321, 220);
		incomingTextArea.setLineWrap(true);
		incomingTextArea.setWrapStyleWord(true);
		incomingTextArea.setEditable(false);
		
		DefaultCaret caret = (DefaultCaret)incomingTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JScrollPane scroller = new JScrollPane(incomingTextArea);
		scroller.setBounds(10, 11, 321, 220);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		incomingPanel.add(scroller);
		
		frame.getContentPane().add(incomingPanel);
		
		JPanel outgoingPanel = new JPanel();
		outgoingPanel.setBounds(0, 267, 341, 87);
		outgoingPanel.setLayout(null);
			
		outgoingTextField = new JTextField();
		outgoingTextField.setBounds(10, 11, 247, 28);
		outgoingTextField.setColumns(10);
		outgoingTextField.addActionListener(new HitEnterListener());
		outgoingPanel.add(outgoingTextField);
		
		JButton sendButton = new JButton("SEND");
		sendButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		sendButton.setBounds(267, 11, 64, 28);
		sendButton.addActionListener(new SendButtonListener());
		outgoingPanel.add(sendButton);

		usernameLabel = new JLabel("Active Username: "+username);
		usernameLabel.setVerticalAlignment(SwingConstants.TOP);
		usernameLabel.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
		usernameLabel.setBounds(10, 50, 199, 26);
		outgoingPanel.add(usernameLabel);
		
		frame.getContentPane().add(outgoingPanel);
	}
	public class MenuButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent mb)
		{
			String action = mb.getActionCommand();
			
			if(action.equals("Connect"))
			{
				setUsername();
				setUpNetworking();
				setUpThread();
			}
			else if(action.equals("Exit"))
				System.exit(0);
			else if(action.equals("Author"))
				JOptionPane.showMessageDialog(null, "Syed Aeliya Mahdi Taqvi");
			else if(action.equals("Contact"))
				JOptionPane.showMessageDialog(null, "+91 9454023365 \n taqvisam@gmail.com");
			else if(action.equals("About"))
				JOptionPane.showMessageDialog(null, "A simple chat client to send messages to "
						+ "friends connected to provided server. \n"
						+ "I give full right to the user to modify the code "
						+ "to suit his/her need. \n"
						+ "You can change ip address in the 'Socket object' to deploy it to real world");
		}
	}
	
	public void setUsername()
	{
		try
		{
			username = JOptionPane.showInputDialog("Enter Your Name");
			if(username.isEmpty())
			{
				username = "Guest";
			}
			usernameLabel.setText("Active Username: "+username);
		}
		catch(Exception e)
		{
			username = "Guest";
		}
	}
	
	public void setUpNetworking()
	{
		try{
			sock = new Socket("127.0.0.1",5000);
			InputStreamReader ipr = new InputStreamReader(sock.getInputStream());
			br = new BufferedReader(ipr);
			
			pr = new PrintWriter(sock.getOutputStream());
			JOptionPane.showMessageDialog(null, "Connection Established");
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Connection Failed. Retry \n See log File for more info");
			username = "Guest";
			usernameLabel.setText("Active Username: "+username);
		}
	}
	
	public void setUpThread()
	{
		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();
	}
	public class HitEnterListener implements ActionListener
	{
		public void actionPerformed(ActionEvent he)
		{
			try
			{
				pr.println(username+": "+outgoingTextField.getText());
				pr.flush();

			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Writing failed");
			}
			outgoingTextField.setText("");
			outgoingTextField.requestFocus();
		}
	}
	
	public class SendButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent sb)
		{
			try
			{
				pr.println(username+": "+outgoingTextField.getText());
				pr.flush();

			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Writing failed");
			}
			outgoingTextField.setText("");
			outgoingTextField.requestFocus();
			
		}
	}
	public class IncomingReader implements Runnable 
	{
		public void run() 
		{
			String message;
			try 
			{
				while((message=br.readLine())!= null) 
				{
					//System.out.println("read " + message);
					incomingTextArea.append(message + "\n");
				}
			} 
			catch(Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "Unable to retrieve messages from Server");
				//ex.printStackTrace();
			}
		}
	}
}
