package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import chats.ChatLoader;
import contacts.ContactLoader;
import debugger.Debugger;
import java.awt.Toolkit;

public class MainFrame 
{
	JFrame frame;
	JPanel panel;
	JPanel ribbon;
	JScrollPane chatContainer;
	JScrollPane contactContainer;
	
	
	public JPanel getPanel()
	{
		return panel;
	}
	private static MainFrame singleInstance;
	
	public static MainFrame getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new MainFrame();
		}
		return singleInstance;
	}
	
	private ChatLoader chatLoader;
	public void setChatReference(ChatLoader chatLoader)
	{
		this.chatLoader=chatLoader;
	}
	private ContactLoader contactLoader;
	public void setContactReference(ContactLoader contactLoader)
	{
		this.contactLoader=contactLoader;
	}
	
	private MainFrame()
	{
		
		if(Debugger.testingMode()) {System.out.println("MainFrame: Creating Frame");}
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/img/chat (5).png")));
		//frame.setUndecorated(true);
		frame.setDefaultLookAndFeelDecorated(false);
		frame.setTitle("WhatsappOnJava");
		frame.getContentPane();
		frame.setBounds(100, 70, 350, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 340, 580);
		frame.getContentPane().add(panel);
		panel.setBackground(Color.ORANGE);
		panel.setLayout(null);
		frame.setVisible(true);
	}
	
	public void setFrameBorder()
	{
		panel.add(FrameBorder.getInstance().getFrameBorder());
	}
	
	public void setVerificationPanel()
	{
		panel.add(VerificationPanel.getInstance().getVerificationPanel());
	}
	
	
	public void setRegistrationPanel()
	{
		panel.add(RegistrationPanel.getInstance().getRegistrationPanel());
	}
	
	public void setRibbon()
	{		
		if(Debugger.testingMode())	{System.out.println("MainFrame: Adding Ribbon...");}
		
		frame.setVisible(false);
		panel.add(Ribbon.getInstance().getRibbon());
		frame.setVisible(true);
	}
	
	public void setRibbon(JPanel ribbon)
	{
		frame.setVisible(false);
		
		panel.add(ribbon);
		frame.setVisible(true);
	}
	
	public void setChatContainer()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Adding Chat container...");}
		panel.add(ChatContainer.getInstance().getChatContainer());
	}
	
	public void setChatContainer(JScrollPane chatContainer)
	{
		this.chatContainer = chatContainer;
		
		panel.add(chatContainer);
	}
	
	public void setContactContainer()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Adding contact container...");}
		panel.add(ContactContainer.getInstance().getContactContainer());
	}
	
	public void setContactContainer(JScrollPane contactContainer)
	{
		this.contactContainer = contactContainer;
		System.out.println("Adding contactContainer");
		panel.add(contactContainer);
	}
	
	public void setCallsContainer()
	{		
		if(Debugger.testingMode())	{System.out.println("MainFrame: Adding calls container...");}
		panel.add(CallsContainer.getInstance().getCallsContainer());
	}
	public void setConversationPanel()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Adding Conversation panel...");}
		panel.add(ConversationPanel.getInstance().getConversationPanel());
	}
	public void setAboutPanel()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Adding about panel...");}
		panel.add(ContactDetailPanel.getInstance().getAboutPanel());
	}
	public void setSettingsPanel()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Adding Settings panel...");}
		panel.add(SettingsPanel.getInstance().getSettingsPanel());
	}
	public void activateVerificationPanel()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Activating verification panel...");}
		FrameBorder.getInstance().setStatus("Verify your identity");
		VerificationPanel.getInstance().getVerificationPanel().setVisible(true);
		RegistrationPanel.getInstance().getRegistrationPanel().setVisible(false);
		Ribbon.getInstance().getRibbon().setVisible(false);
		ChatContainer.getInstance().getChatContainer().setVisible(false);
		ContactContainer.getInstance().getContactContainer().setVisible(false);
		CallsContainer.getInstance().getCallsContainer().setVisible(false);
		ConversationPanel.getInstance().getConversationPanel().setVisible(false);
		ContactDetailPanel.getInstance().getAboutPanel().setVisible(false);
	}
	
	public void activateRegistrationPanel()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Activating registration panel...");}
		FrameBorder.getInstance().setStatus("Registration");
		VerificationPanel.getInstance().getVerificationPanel().setVisible(false);
		RegistrationPanel.getInstance().getRegistrationPanel().setVisible(true);
		Ribbon.getInstance().getRibbon().setVisible(false);
		ChatContainer.getInstance().getChatContainer().setVisible(false);
		ContactContainer.getInstance().getContactContainer().setVisible(false);
		CallsContainer.getInstance().getCallsContainer().setVisible(false);
		ConversationPanel.getInstance().getConversationPanel().setVisible(false);
		ContactDetailPanel.getInstance().getAboutPanel().setVisible(false);
		
	}
	
	public void activateChatContainer()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Activating chat container...");}
		chatLoader.requestAllChatsOnGUI();
		
		FrameBorder.getInstance().setStatus("Active");
		VerificationPanel.getInstance().getVerificationPanel().setVisible(false);
		RegistrationPanel.getInstance().getRegistrationPanel().setVisible(false);
		Ribbon.getInstance().getRibbon().setVisible(true);
		ChatContainer.getInstance().getChatContainer().setVisible(true);
		ContactContainer.getInstance().getContactContainer().setVisible(false);
		CallsContainer.getInstance().getCallsContainer().setVisible(false);
		ConversationPanel.getInstance().getConversationPanel().setVisible(false);
		ContactDetailPanel.getInstance().getAboutPanel().setVisible(false);
	}
	
	public void activateContactContainer()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Activating contact container...");}
		contactLoader.requestAllContactsOnGUI();
		
		FrameBorder.getInstance().setStatus("Active");
		VerificationPanel.getInstance().getVerificationPanel().setVisible(false);
		RegistrationPanel.getInstance().getRegistrationPanel().setVisible(false);
		ChatContainer.getInstance().getChatContainer().setVisible(false);
		ContactContainer.getInstance().getContactContainer().setVisible(true);
		CallsContainer.getInstance().getCallsContainer().setVisible(false);
		ConversationPanel.getInstance().getConversationPanel().setVisible(false);
		ContactDetailPanel.getInstance().getAboutPanel().setVisible(false);
	}
	
	public void activateCallsContainer()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Activating calls conatiner...");}
		
		FrameBorder.getInstance().setStatus("Active");
		VerificationPanel.getInstance().getVerificationPanel().setVisible(false);
		RegistrationPanel.getInstance().getRegistrationPanel().setVisible(false);
		Ribbon.getInstance().getRibbon().setVisible(true);
		ChatContainer.getInstance().getChatContainer().setVisible(false);
		CallsContainer.getInstance().getCallsContainer().setVisible(true);
		ContactContainer.getInstance().getContactContainer().setVisible(false);
		ConversationPanel.getInstance().getConversationPanel().setVisible(false);
		ContactDetailPanel.getInstance().getAboutPanel().setVisible(false);
	}
	
	public void activateConversationPanel()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Activating conversation panel...");}
		FrameBorder.getInstance().setStatus("Conversation Screen");
		VerificationPanel.getInstance().getVerificationPanel().setVisible(false);
		RegistrationPanel.getInstance().getRegistrationPanel().setVisible(false);
		Ribbon.getInstance().getRibbon().setVisible(false);
		ChatContainer.getInstance().getChatContainer().setVisible(false);
		ContactContainer.getInstance().getContactContainer().setVisible(false);
		CallsContainer.getInstance().getCallsContainer().setVisible(false);
		ConversationPanel.getInstance().getConversationPanel().setVisible(true);
		ContactDetailPanel.getInstance().getAboutPanel().setVisible(false);
	}
	public void activateAboutPanel()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Activating about panel...");}
		FrameBorder.getInstance().setStatus("About Contact");
		VerificationPanel.getInstance().getVerificationPanel().setVisible(false);
		RegistrationPanel.getInstance().getRegistrationPanel().setVisible(false);
		Ribbon.getInstance().getRibbon().setVisible(false);
		ChatContainer.getInstance().getChatContainer().setVisible(false);
		ContactContainer.getInstance().getContactContainer().setVisible(false);
		CallsContainer.getInstance().getCallsContainer().setVisible(false);
		ConversationPanel.getInstance().getConversationPanel().setVisible(false);
		ContactDetailPanel.getInstance().getAboutPanel().setVisible(true);
	}
	public void activateSettingsPanel()
	{
		if(Debugger.testingMode())	{System.out.println("MainFrame: Activating settings panel...");}
		FrameBorder.getInstance().setStatus("Settings");
		VerificationPanel.getInstance().getVerificationPanel().setVisible(false);
		RegistrationPanel.getInstance().getRegistrationPanel().setVisible(false);
		Ribbon.getInstance().getRibbon().setVisible(false);
		ChatContainer.getInstance().getChatContainer().setVisible(false);
		ContactContainer.getInstance().getContactContainer().setVisible(false);
		ConversationPanel.getInstance().getConversationPanel().setVisible(false);
		CallsContainer.getInstance().getCallsContainer().setVisible(false);
		ContactDetailPanel.getInstance().getAboutPanel().setVisible(false);
		SettingsPanel.getInstance().getSettingsPanel().setVisible(true);
	}
	
	public void disposeFrame()
	{
		frame.dispose();
	}
}
