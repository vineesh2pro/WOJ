package gui;

import chats.ChatFunctions;
import chats.ChatLoader;
import clientSocket.ClientToServer;
import clientUser.ClientUserFunctions;
import clientUser.ClientUserLoader;
import contacts.ContactFunctions;
import contacts.ContactLoader;
import speechBubble.SpeechBubbleLoader;
import theme.ThemeMode;
import userMessageQueue.MessageQueueFunctions;

public class SetGUI 
{
	MainFrame mf;
	Ribbon rib;
	ChatContainer chatCon;
	ContactContainer contactCon;
	//---Constructor---//
	public SetGUI()
	{
		mf = MainFrame.getInstance();
		mf.setFrameBorder();
		mf.setVerificationPanel();
		mf.activateVerificationPanel();
		mf.setRegistrationPanel();
		mf.setRibbon();
		mf.setChatContainer();
		mf.setContactContainer();
		mf.setCallsContainer();
		mf.setConversationPanel();
		mf.setAboutPanel();
		mf.setSettingsPanel();
		new ThemeMode().setLightTheme();
		new ThemeSetter().refreshTheme();
		
	}
	public void setContactReferences(ContactFunctions contactFunctions,ContactLoader contactLoader)
	{
		Ribbon.getInstance().setContactReference(contactLoader);
		MainFrame.getInstance().setContactReference(contactLoader);
		Ribbon.getInstance().setContactFunctionsReference(contactFunctions);
		ContactDetailPanel.getInstance().setContactFunctionsReference(contactFunctions);
	}
	public void setChatReferences(ChatFunctions chatFunctions,ChatLoader chatLoader)
	{
		Ribbon.getInstance().setChatReference(chatLoader);
		MainFrame.getInstance().setChatReference(chatLoader);
		ChatContainer.getInstance().setChatFunctionsReference(chatFunctions);
		ContactContainer.getInstance().setChatFunctionsReference(chatFunctions);
		ConversationPanel.getInstance().setChatFunctionsReference(chatFunctions);
	}
	public void setClientToServerReferences(ClientToServer cts)
	{
		VerificationPanel.getInstance().setClientToServerReference(cts);
		RegistrationPanel.getInstance().setClientToServerReference(cts);
		ConversationPanel.getInstance().setClientToServerReference(cts);
		SettingsPanel.getInstance().setClientToServerReference(cts);
	}
	public void setUserReferences(ClientUserFunctions clientUserFunctions,ClientUserLoader clientUserLoader)
	{
		RegistrationPanel.getInstance().setUserFunctionReference(clientUserFunctions);
		Ribbon.getInstance().setClientUserLoaderReference(clientUserLoader);
		SettingsPanel.getInstance().setUserFunctionReference(clientUserFunctions);
	}
	public void setChatBubbleReference(SpeechBubbleLoader spl)
	{
		ConversationPanel.getInstance().setChatBubbleReference(spl);
	}
	public void setMessageQueueReference(MessageQueueFunctions mqf)
	{
		ChatContainer.getInstance().setMessageQueueFunctionsReference(mqf);
		ConversationPanel.getInstance().setMessageQueueFunctionsReference(mqf);
	}
	
	
	
	
}
