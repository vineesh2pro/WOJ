package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import chats.ChatFunctions;
import contacts.ContactFunctions;
import debugger.Debugger;
import theme.Theme;
import userMessageQueue.MessageQueueFunctions;


public class ChatContainer 
{
	
	//---Singleton Pattern---//	
	private static ChatContainer singleInstance;
	private int i=0;
	public static ChatContainer getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new ChatContainer();
		}
		return singleInstance;
	}
	
	//---Instance Variables---//
	private JScrollPane scrollPane;
	private static JPanel mainPanel;	
	public JScrollPane getChatContainer()
	{
		return scrollPane; 
	}
	
	String activeUser;
	public void setActiveUser(String activeUser)
	{
		this.activeUser=activeUser;
	}
	
	
	//---Chat Function Object---//
		ChatFunctions chatFunctions;
		public void setChatFunctionsReference(ChatFunctions chatFunctions)
		{
			this.chatFunctions=chatFunctions;
		}
		private MessageQueueFunctions mqf;
		public void setMessageQueueFunctionsReference(MessageQueueFunctions mqf)
		{
			this.mqf= mqf;
		}
	
	//---Constructor---//
	private ChatContainer()
	{
		mainPanel = new JPanel();		
		//mainPanel.setBackground(Color.LIGHT_GRAY);
		scrollPane = new JScrollPane();		
		scrollPane.setViewportView(mainPanel);
		scrollPane.setBounds(0, 130, 340, 475);
		mainPanel.setLayout(null);
		//mainPanel.setLayout(new BorderLayout());		
		
	}
	
	private JPanel addChatPanel(int num,String chatName,String time,String chat,String number,BufferedImage image,String about)
	{
		ChatPanel cp = new ChatPanel(num,chatName,time,chat,number,image,about,Theme.getChatTileBackground(),Theme.getTextColor());
		cp.setChatFunctionsReference(chatFunctions);
		cp.setActiveUser(activeUser);
		cp.setMessageQueueFunctionsReference(mqf);
		
		JPanel temp = cp.getChatPanel();
		return temp;
	}
	public void setChatOnGUI(String chatName,String time, String chat,String number,BufferedImage image,String about)
	{
		
		if(Debugger.testingMode())	{System.out.println("ChatContainer: Contact panel asked me to add \"+chatName+\" at \"+i");}
		mainPanel.add(addChatPanel(i,chatName,time,chat,number,image,about));		
		i++;
	}
	public void resetChatContainer()
	{
		if(Debugger.testingMode())	{System.out.println("ChatContainer: Refreshing contact conatiner");}
		i=1;
		mainPanel.removeAll();
	}
	public void setTheme()
	{
		mainPanel.setBackground(Theme.getBackground());
	}
}

