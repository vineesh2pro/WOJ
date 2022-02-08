package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import chats.ChatFunctions;
import contacts.ContactFunctions;
import theme.Theme;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;


public class CallsContainer 
{
	
	//---Singleton Pattern---//	
	private static CallsContainer singleInstance;
	private int i=0;
	public static CallsContainer getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new CallsContainer();
		}
		return singleInstance;
	}
	
	//---Instance Variables---//
	private JScrollPane scrollPane;
	private static JPanel mainPanel;	
	public JScrollPane getCallsContainer()
	{
		return scrollPane; 
	}
	//---Constructor---//
	private CallsContainer()
	{
		mainPanel = new JPanel();		
	//	mainPanel.setBackground(Color.LIGHT_GRAY);
		scrollPane = new JScrollPane();		
		scrollPane.setViewportView(mainPanel);
		scrollPane.setBounds(0, 130, 334, 470);
		mainPanel.setLayout(null);		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(CallsContainer.class.getResource("/img/call.png")));
		lblNewLabel.setBounds(10, 48, 312, 264);
		mainPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("This feature will be available soon");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 310, 332, 39);
		mainPanel.add(lblNewLabel_1);		
	}
	
	public void setTheme()
	{
		mainPanel.setBackground(Theme.getBackground());
	}
	
	
}

