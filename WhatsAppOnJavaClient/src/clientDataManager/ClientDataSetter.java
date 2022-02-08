package clientDataManager;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import chats.ChatFunctions;
import contacts.ContactFunctions;
import debugger.Debugger;
import gui.ConversationPanel;
import gui.FrameBorder;
import gui.MainFrame;
import onlineStatus.OnlineStatus;
import speechBubble.SpeechBubbleLoader;
import userMessageQueue.MessageQueueFunctions;
import utility.FileManager;

public class ClientDataSetter
{
	private Boolean newMessage=false;
	private ChatFunctions chatFunctions;
	private ContactFunctions contactFunctions;
	private MessageQueueFunctions mqf;
	private SpeechBubbleLoader spl;
	private String activeUser;
	
	public void setChatFunctionsReference(ChatFunctions chatFunctions)
	{
		this.chatFunctions=chatFunctions;
	}
	public void setContactFunctionsReference(ContactFunctions contactFunctions)
	{
		this.contactFunctions= contactFunctions;
	}
	public void setMessageQueueReferences(MessageQueueFunctions mqf)
	{
		this.mqf=mqf;
	}
	public void setChatBubbleReference(SpeechBubbleLoader spl)
	{
		this.spl= spl;
	}
	public void setActiveUser(String activeUser)
	{
		this.activeUser=activeUser;
	}
	
	
	//---Single Instance---//
	private static ClientDataSetter singleInstance;
	public static ClientDataSetter getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new ClientDataSetter();
			return singleInstance;
		}
		return singleInstance;
	}
	
	public void clientMessageSetter(ArrayList messageFrame) 
	{		
		if(Debugger.testingMode())	{System.out.println("ClientDataSetter: Setting messages to client queue");}		
		mqf.addMessage(activeUser,messageFrame.get(0).toString(),messageFrame);
		chatFunctions.updateChat(messageFrame.get(0).toString(), messageFrame.get(2).toString()); // updating chat tile with recent message.
		FrameBorder.getInstance().setStatus("You have new message",3000);				
	}
	
	public void setAbout(ArrayList<String> aboutFrame)
	{
		if(Debugger.testingMode())	{System.out.println("ClientDataSetter: Updating abouts...");}		
		contactFunctions.updateAbout(aboutFrame.get(0),aboutFrame.get(1));
		chatFunctions.updateAbout(aboutFrame.get(0),aboutFrame.get(1));
	}
	
	public void setProfilePicture(ArrayList imageFrame)
	{
		if(Debugger.testingMode())	{System.out.println("ClientDataSetter: Updating profile picture");}		
		chatFunctions.updateProfilePicture(imageFrame.get(0).toString(),(byte[]) imageFrame.get(1));
		contactFunctions.updateProfilePicture(imageFrame.get(0).toString(),(byte[]) imageFrame.get(1));		
	}
	
	public void setOnlineStatus(ArrayList statusFrame)
	{
		OnlineStatus.getInstance().putOnlineStatus(statusFrame.get(0).toString(), statusFrame.get(1).toString());
	}
}
