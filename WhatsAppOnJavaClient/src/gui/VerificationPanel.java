package gui;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import chats.ChatLoader;
import clientDataManager.ClientDataSetter;
import clientSocket.ClientToServer;
import clientUser.UserAuthentication;
import debugger.Debugger;
import clientUser.ClientUserFunctions;
import clientUser.ClientUserLoader;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VerificationPanel {

	//private JFrame frame;
	private static VerificationPanel singleInstance;
	private JPanel mainPanel;
	private JTextField mobileNumber;
	static public String userName;
	
	
	private ClientToServer cts;
	
	public static VerificationPanel getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new VerificationPanel();
		}
		return singleInstance;
	}
	public JPanel getVerificationPanel()
	{
		return mainPanel;
	}
	
	public void setClientToServerReference(ClientToServer cts)
	{
		this.cts=cts;
	}
	
	
	
	private VerificationPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBounds(0, 20, 335, 573);
		mainPanel.setVisible(true);
		mainPanel.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 108, 97));
		panel.setBounds(0, 0, 354, 60);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		JLabel WhatsApp = new JLabel("WhatsAppOnJava");
		WhatsApp.setBounds(7, 5, 203, 24);
		panel.add(WhatsApp);
		WhatsApp.setForeground(Color.WHITE);
		WhatsApp.setFont(new Font("Roboto", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("User Verification");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(8, 31, 130, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 47, 336, 528);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Enter registered mobile number");
		lblNewLabel_1.setBounds(71, 366, 169, 14);
		panel_1.add(lblNewLabel_1);
		
		mobileNumber = new JTextField();
		mobileNumber.setFont(new Font("Arial", Font.BOLD, 15));
		mobileNumber.setOpaque(false);
		mobileNumber.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 102, 51)));
		mobileNumber.setBounds(71, 392, 106, 20);
		mobileNumber.setColumns(10);
		panel_1.add(mobileNumber);
		
		JButton btnNewButton_1 = new JButton("New User?");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				MainFrame.getInstance().activateRegistrationPanel();				
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(245, 472, 81, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(VerificationPanel.class.getResource("/img/chat (1).png")));
		lblNewLabel_2.setBounds(10, 25, 316, 330);
		panel_1.add(lblNewLabel_2);
		
		JLabel loginButton = new JLabel(">>");
		loginButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
	                loginAction();
	            }
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginButton.setHorizontalAlignment(SwingConstants.CENTER);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(0, 51, 51));
		loginButton.setOpaque(true);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				loginAction();
				/*try
				{
					
					userName=UserAuthentication.getInstance().authenticateUser(mobileNumber.getText());
				   
					if(!(userName==null))
					{
						System.out.println("USER FOUND");
						FrameBorder.getInstance().setStatus("Active");	
						setActiveUser();
						JOptionPane.showMessageDialog(null,userName+" you are logged in.");	
						MainFrame.getInstance().activateChatContainer();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"User doesn't exist");
						int temp = JOptionPane.showConfirmDialog(null,"Do you want to register?");
						if(temp==0)
						{
							MainFrame.getInstance().activateRegistrationPanel();
						}
						
					}
				}
				catch(Exception ex)
				{
					System.out.println("Exception caught while verification");
				}*/
			}
		});
		loginButton.setBounds(172, 388, 41, 24);
		panel_1.add(loginButton);
		
	}
	
	private void setActiveUser()
	{
		cts.setActiveUser(mobileNumber.getText());
		ChatContainer.getInstance().setActiveUser(mobileNumber.getText());
		ConversationPanel.getInstance().setActiveUser(mobileNumber.getText());
		Ribbon.getInstance().setActiveUser(mobileNumber.getText());
		SettingsPanel.getInstance().setActiveUser(mobileNumber.getText());
		ClientDataSetter.getInstance().setActiveUser(mobileNumber.getText());
	}
	private void loginAction()
	{
		try
		{
			
			userName=UserAuthentication.getInstance().authenticateUser(mobileNumber.getText());
		   
			if(!(userName==null))
			{
				if(Debugger.testingMode())	{System.out.println("VerificationPanel: User found.");}
				
				FrameBorder.getInstance().setStatus("Active");	
				setActiveUser();
				JOptionPane.showMessageDialog(null,userName+" you are logged in.","Logged In",JOptionPane.PLAIN_MESSAGE);	
				MainFrame.getInstance().activateChatContainer();
			}
			else
			{				
				JOptionPane.showMessageDialog(null, "User doesn't exist","Error",JOptionPane.ERROR_MESSAGE);
				int temp = JOptionPane.showConfirmDialog(null,"Do you want to register?");
				if(temp==0)
				{
					MainFrame.getInstance().activateRegistrationPanel();
				}
				
			}
		}
		catch(Exception ab)
		{
			if(Debugger.testingMode())	{System.out.println("VerificationPanel: Exception while authenticating user");}
			FrameBorder.getInstance().setStatus("User Authentication Failed.",4000);
		}
	}
}
