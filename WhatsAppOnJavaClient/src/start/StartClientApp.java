package start;

import chatExporter.ChatExporter;
import chats.ChatFunctions;
import chats.ChatLoader;
import chats.ChatNumberCheck;
import clientDataManager.ClientDataSetter;
import clientSocket.ClientToServer;
import clientUser.UserAuthentication;
import clientUser.ClientUserFunctions;
import clientUser.ClientUserLoader;
import contacts.ContactFunctions;
import contacts.ContactLoader;
import debugger.Debugger;
import gui.SetGUI;
import speechBubble.SpeechBubbleLoader;
import userMessageQueue.MessageQueueFunctions;

public class StartClientApp 
{
	
	
	
	private ContactFunctions contactFunctions;
	private ClientToServer cts;
	private ContactLoader contactLoader;
	private ChatLoader chatLoader;	
	private ClientUserLoader clientUserLoader;
	private ChatFunctions chatFunctions;
	private ChatNumberCheck chatNumberCheck;
	private ClientUserFunctions clientUserFunctions;
	private SetGUI sgui;
	private ClientDataSetter cdm;
	private MessageQueueFunctions mqf;
	private SpeechBubbleLoader sbl;
	private ChatExporter chatExporter;
	
	public static void main(String[] args)
	{
		StartClientApp sa = new StartClientApp();	
	}
	public StartClientApp()
	{
		Debugger.enableTestingMode();
		loadUsers();
		loadMessageQueue();
		loadChatBubble();
		startClientSocket();		
		loadContacts();
		loadChats();
		startClientDataSetter();
		loadChatExporter();
		loadGUI();	
		
	}
	
	private void loadUsers() 
	{
		clientUserFunctions = new ClientUserFunctions();
		clientUserLoader = new ClientUserLoader(clientUserFunctions);
		UserAuthentication.getInstance().setUserFunctionReference(clientUserFunctions);		
		clientUserLoader.displayUsers();
	}
	private void loadContacts()
	{
		contactFunctions = new ContactFunctions();
		contactLoader = new ContactLoader(contactFunctions);
		contactLoader.setClientToServerReference(cts);				
	}
	private void loadChats() 
	{
		chatFunctions = new ChatFunctions();
		chatNumberCheck = new ChatNumberCheck(this.contactFunctions);
		chatLoader = new ChatLoader(chatFunctions,chatNumberCheck);			
	}
	public void loadGUI()
	{		
		sgui = new SetGUI();
		sgui.setChatReferences(chatFunctions, chatLoader);
		sgui.setClientToServerReferences(cts);
		sgui.setContactReferences(contactFunctions, contactLoader);
		sgui.setUserReferences(clientUserFunctions,clientUserLoader);
		sgui.setChatBubbleReference(sbl);
		sgui.setMessageQueueReference(mqf);
	}
	public void startClientSocket()
	{
		cts= new ClientToServer();
		cts.setMessageQueueReferences(mqf);		
	}
	public void startClientDataSetter()
	{
		cdm= ClientDataSetter.getInstance();
		cdm.setChatFunctionsReference(chatFunctions);
		cdm.setContactFunctionsReference(contactFunctions);
		cdm.setMessageQueueReferences(mqf);
		cdm.setChatBubbleReference(sbl);
	}
	
	public void loadMessageQueue()
	{
		mqf= new MessageQueueFunctions();
		
	}
	public void loadChatBubble()
	{
		sbl= SpeechBubbleLoader.getInstance();
		sbl.setMessageQueueReference(mqf);
	}
	public void loadChatExporter()
	{
		chatExporter = ChatExporter.getInstance();
		chatExporter.setMessageQueueFunctionsReference(mqf);
	}
}
