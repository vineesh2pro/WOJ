package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import serverSocket.ChatServerSocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame 
{
	private JFrame frame;
	public JPanel incomingPanel;
	public JLabel serverStatusLabel;
	public String serviceStatus = "Not Running";
	public JButton startServiceButton;
	public JButton stopServiceButton;
	
	//ChatServerSocket references
	ChatServerSocket css;
	
	private static MainFrame singleInstance;
	
	public static MainFrame getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new MainFrame();
		}
		return singleInstance;
	}

	public void setChatServerSocketReference(ChatServerSocket css)
	{
		this.css=css;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	private MainFrame()
	{
	try 
	{
				
				for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				    if ("Nimbus".equals(info.getName())) {
				        UIManager.setLookAndFeel(info.getClassName());
				        
				        break;
				    }
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("WhatsappOnJavaServer");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		frame.setBounds(100, 100, 347, 265);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 341, 28);
				
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(fileMenu);
		
		JMenuItem startServiceMenuItem = new JMenuItem("Start Service");
		startServiceMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		startServiceMenuItem.addActionListener(new MenuButtonListener());
		fileMenu.add(startServiceMenuItem);
		
		JMenuItem stopServiceMenuItem = new JMenuItem("Stop Service");
		stopServiceMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		stopServiceMenuItem.addActionListener(new MenuButtonListener());
		fileMenu.add(stopServiceMenuItem);
		
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
		
		incomingPanel = new JPanel();
		incomingPanel.setBackground(Color.RED);
		//incomingPanel.setBackground(UIManager.getColor("Panel.background"));
		incomingPanel.setBounds(0, 26, 341, 121);
		incomingPanel.setLayout(null);
		frame.getContentPane().add(incomingPanel);

		JPanel outgoingPanel = new JPanel();
		outgoingPanel.setBounds(0, 147, 341, 89);
		outgoingPanel.setLayout(null);
			
		startServiceButton = new JButton("START SERVICE");
		startServiceButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				css.requestServiceStart(true);
			}
		});
		startServiceButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		startServiceButton.setBounds(10, 11, 150, 28);
		//startServiceButton.addActionListener(new StartServiceButtonListener());
		outgoingPanel.add(startServiceButton);

		stopServiceButton = new JButton("STOP SERVICE");
		stopServiceButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				css.requestServiceStop(true);
			}
		});
		stopServiceButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		stopServiceButton.setBounds(181, 11, 150, 28);
		stopServiceButton.setEnabled(false);
		//stopServiceButton.addActionListener(new StopServiceButtonListener());
		outgoingPanel.add(stopServiceButton);
		
		serverStatusLabel = new JLabel("Server Status : "+serviceStatus);
		serverStatusLabel.setVerticalAlignment(SwingConstants.TOP);
		serverStatusLabel.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
		serverStatusLabel.setBounds(10, 50, 199, 26);
		outgoingPanel.add(serverStatusLabel);
		
		frame.getContentPane().add(outgoingPanel);
		frame.setVisible(true);
		
	}
	public class MenuButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent mb)
		{
			String action = mb.getActionCommand();
			
			if(action.equals("Connect"))
			{
				
			}
			else if(action.equals("Exit"))
				System.exit(0);
			else if(action.equals("Author"))
				JOptionPane.showMessageDialog(null, "Syed Aeliya Mahdi Taqvi");
			else if(action.equals("Contact"))
				JOptionPane.showMessageDialog(null, "+91 9454023365 \n taqvisam@gmail.com");
			else if(action.equals("About"))
				JOptionPane.showMessageDialog(null, "\t**********WhatsAppOnJava********** \n"
						+ "This application is purely based on Object Oriented Programming approach. \n"
						+"This application is made by Syed Aeliya Mahdi Taqvi\n"
						+"Under the supervision of Er. Vineesh Cutting");					
						
		}
	}
}
