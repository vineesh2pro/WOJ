package gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import chats.ChatFunctions;
import theme.Theme;

public class ContactContainer 
{
	//---Singleton Pattern---//
	private static ContactContainer singleInstance;
	private static int i=1;
	public static ContactContainer getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new ContactContainer();
		}
		return singleInstance;
	}
		
	//---Instance Variables---//
	private JScrollPane scrollPane;
	private static JPanel mainPanel;
	
		
	public JScrollPane getContactContainer()
	{
		return scrollPane;
	}

	//---Chat Function Object---//
	ChatFunctions chatFunctions;
	public void setChatFunctionsReference(ChatFunctions chatFunctions)
	{
		this.chatFunctions=chatFunctions;
	}
			
	//---Constructor---//
	private ContactContainer()
	{
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 130, 334, 1000);
		//mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setLayout(null);
		
		
		scrollPane = new JScrollPane(mainPanel);
		scrollPane.setBounds(0, 130, 340, 1000);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
	
	private JPanel addContactsPanel(int num,String contactNumber,String contactName,String contactAbout,BufferedImage image)
	{
		
		ContactPanel cp = new ContactPanel(num,contactNumber,contactName,contactAbout,image,Theme.getContactTileBackground(),Theme.getTextColor());
		cp.setChatFunctionsReference(chatFunctions);
		JPanel temp = cp.getContactsPanel();
		return temp;
	}
	public void setContactsOnGUI(String contactNumber,String contactName, String contactAbout,BufferedImage image)
	{
		
		mainPanel.add(addContactsPanel(i,contactNumber,contactName,contactAbout,image));
		
		i++;
		
	}
	public void resetContactContainer()
	{
		i=1;
		mainPanel.removeAll();
	}
	public void setTheme()
	{
		mainPanel.setBackground(Theme.getBackground());
	}
}
