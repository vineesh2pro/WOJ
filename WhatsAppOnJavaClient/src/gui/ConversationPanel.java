package gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.MetalScrollBarUI;

import chatExporter.ChatExporter;
import chats.ChatFunctions;
import clientSocket.ClientToServer;
import debugger.Debugger;
import onlineStatus.OnlineStatus;
import speechBubble.SpeechBubbleLoader;
import theme.Theme;
import userMessageQueue.MessageQueueFunctions;
import utility.FileBrowser;
import utility.ImageManager;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ConversationPanel 
{
	static JPanel myConversationPanel;
	public static JTextField outgoing;	
	public JPanel incoming;
	private static JLabel nameLabel;
	private JPanel conversationPanelHeader;	
	private ClientToServer cts;
	private SpeechBubbleLoader spl;
	public String number;
	private  Image background;
	private String about;
	private MessageQueueFunctions mqf;
	private ChatFunctions chatFunctions;
	
	private BufferedImage image;
	
	
	//---Active User---//
		public String activeUser;
		public void setActiveUser(String activeUser)
		{
			this.activeUser=activeUser;
		}
	
	
	public void setClientToServerReference(ClientToServer cts)
	{
		
		this.cts=cts;
	}
	
	public void setMessageQueueFunctionsReference(MessageQueueFunctions mqf)
	{
		this.mqf= mqf;
	}
	
	public void setChatBubbleReference(SpeechBubbleLoader spl)
	{
		this.spl= spl;
	}
	public void setChatFunctionsReference(ChatFunctions chatFunctions)
	{
		this.chatFunctions=chatFunctions;
	}
	//---Singleton Pattern---//
			private static ConversationPanel singleInstance;
			private JLabel profileIcon;
			private JLabel onlineStatus;
			private JScrollPane scrollPane;
			private JPanel outgoingPanel;
			public static ConversationPanel getInstance()
			{
				if(singleInstance==null)
				{
					singleInstance= new ConversationPanel();
				}
				return singleInstance;
			}
			
	public ConversationPanel() 
	{
		background= Toolkit.getDefaultToolkit().createImage(ConversationPanel.class.getResource("/img/chatBackground.png"));	
		myConversationPanel = new JPanel();
		myConversationPanel.setBackground(Color.LIGHT_GRAY);
		myConversationPanel.setBounds(0, 20, 335, 573);
		
		myConversationPanel.setLayout(null);
		
		conversationPanelHeader = new JPanel();
		conversationPanelHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ContactDetailPanel.getInstance().setAboutDetails(number,nameLabel.getText(),about);
				MainFrame.getInstance().activateAboutPanel();
			}
		});
		conversationPanelHeader.setBackground(new Color(0, 108, 97));
		conversationPanelHeader.setBounds(0, 0, 334, 50);
		myConversationPanel.add(conversationPanelHeader);
		conversationPanelHeader.setLayout(null);
		
		outgoingPanel = new JPanel();
		outgoingPanel.setBackground(Color.WHITE);
		outgoingPanel.setBounds(0, 530, 334, 35);
		myConversationPanel.add(outgoingPanel);
		outgoingPanel.setLayout(null);
		
		outgoing = new JTextField();
		outgoing.setOpaque(false);
		outgoing.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 102, 51)));
		outgoing.setBounds(42, 7, 261, 25);
		outgoingPanel.add(outgoing);
		outgoing.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(ConversationPanel.class.getResource("/img/back-arrow.png")));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.getInstance().activateChatContainer();
			}
		});
		lblNewLabel_1.setBounds(0, 0, 41, 50);
		conversationPanelHeader.add(lblNewLabel_1);
		
		nameLabel = new JLabel("Kangana G");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(93, 0, 165, 28);
		conversationPanelHeader.add(nameLabel);
		
		onlineStatus = new JLabel("");
		onlineStatus.setForeground(Color.WHITE);
		onlineStatus.setBounds(93, 28, 165, 22);
		conversationPanelHeader.add(onlineStatus);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(Debugger.testingMode()) {System.out.println("Right button clicked");}
				JPopupMenu menu= new JPopupMenu();
				JMenuItem clearChats= new JMenuItem("Clear chats");
				JMenuItem exportChats= new JMenuItem("Export chats");
				JMenuItem viewContact= new JMenuItem("View contact");
				menu.add(clearChats);
				menu.add(exportChats);
				menu.add(viewContact);				
				menu.show(e.getComponent(),e.getX(),e.getY());
				
				clearChats.addActionListener(new ActionListener(){  
				    public void actionPerformed(ActionEvent ae)
				    {				    	
				    	int temp =JOptionPane.showConfirmDialog(null,"Are you sure, you want to \ndelete messages of this chat?");
				    	if(temp==0)
				    	{
				    		if(Debugger.testingMode())	{System.out.println("ConversationPanel: Clearing all messages from queue");}
				    		
				    		mqf.clearQueue(activeUser+number);
				    		setChatsOnPanel();				    		
				    	}
				    	
				    }  
				    });  
				exportChats.addActionListener(new ActionListener(){  
				    public void actionPerformed(ActionEvent ae)
				    {	
				    	
				    	if(Debugger.testingMode())	{System.out.println("ConversationPanel: Exporting chats to file");}
				    	
				    	ChatExporter.getInstance().exportChatsToFile(activeUser, number);
				    }  
				    });  
				viewContact.addActionListener(new ActionListener(){  
				    public void actionPerformed(ActionEvent ae)
				    {				    	
				    	ContactDetailPanel.getInstance().setAboutDetails(number,nameLabel.getText(),about);
						MainFrame.getInstance().activateAboutPanel();
				    }  
				    });  
				
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon(ConversationPanel.class.getResource("/img/three_dots.png")));
		lblNewLabel_4.setBounds(304, 0, 20, 50);
		conversationPanelHeader.add(lblNewLabel_4);
		
		profileIcon = new JLabel("New label");
		profileIcon.setIcon(new ImageIcon(ConversationPanel.class.getResource("/img/user.png")));
		profileIcon.setBounds(38, 0, 41, 50);
		conversationPanelHeader.add(profileIcon);
		
		
		
		JLabel sendButton = new JLabel("Send");
		sendButton.setIcon(new ImageIcon(ConversationPanel.class.getResource("/img/send_button.png")));
		sendButton.setHorizontalAlignment(SwingConstants.CENTER);
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{	
				
				try
				{				
					cts.messageClientToServer(number,outgoing.getText(),null,null);	
					chatFunctions.updateChat(number, outgoing.getText());
					outgoing.setText("");
					outgoing.requestFocus();
					

				}
				catch(Exception ex)
				{
					if(Debugger.testingMode())	{System.out.println("ConversationPanel: Exception caught while sending message...");}
		    		FrameBorder.getInstance().setStatus("Message sending failed...",4000);
				}
				
			}
				
		});
		sendButton.setBounds(305, 0, 41, 32);
		outgoingPanel.add(sendButton);
		
		JLabel attachmentButton = new JLabel("");
		attachmentButton.setIcon(new ImageIcon(ConversationPanel.class.getResource("/img/add24px.png")));
		attachmentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				ArrayList a = FileBrowser.browseFile();
				cts.messageClientToServer(number,a.get(0),a.get(1).toString(),a.get(2).toString());
			}
		});
		attachmentButton.setHorizontalAlignment(SwingConstants.CENTER);
		attachmentButton.setBounds(3, 0, 41, 32);
		outgoingPanel.add(attachmentButton);
		
		
		JLabel profilePic = new JLabel("");
		profilePic.setIcon(new ImageIcon(ConversationPanel.class.getResource("/img/user.png")));
		profilePic.setBounds(36, 0, 58, 50);
		myConversationPanel.add(profilePic);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 50, 348, 479);
		myConversationPanel.add(scrollPane);
		
		incoming = new JPanel();		
		//incoming.setBackground(new Color(169, 169, 169));
		//incoming.setBackground(new Color(245, 245, 220));
		scrollPane.setViewportView(incoming);	
		scrollPane.getVerticalScrollBar().setUI(new WithoutArrowButtonScrollBarUI());
		//-----Risky ---//
		/*scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });*/
		//------------------------------------//
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		incoming.setLayout(new BorderLayout());
		incoming.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
		
		JLabel backgroundImage = new JLabel();
		//backgroundImage.setBackground(new Color(245, 222, 179));
		backgroundImage.setHorizontalAlignment(SwingConstants.CENTER);
		//incoming.add(backgroundImage, BorderLayout.CENTER);
		//backgroundImage.setIcon(new ImageIcon(background));

	}
	public JPanel getConversationPanel()
	{
		return myConversationPanel;
	}
	public void setChatDetail(String name,String number,String about,BufferedImage image)
	{
		this.image = image;
		nameLabel.setText(name);	
		profileIcon.setIcon(new ImageIcon(ImageManager.circleCroper(this.image, 45, 45)));
		this.number=number;
		this.about=about;
		
		
	}
	//---Test Zone---//
	public void setChatsOnPanel()	
	{	
		checkOnlineStatus();
		incoming.removeAll();
		incoming.repaint();
		incoming.revalidate();
		spl.loadChats(activeUser,number);		
	}
	private void checkOnlineStatus()
	{
		 new Thread(new Runnable()
				{

					@Override
					public void run() 
					{
						while(true)
						{
							cts.checkOnlineStatus(number);		
							try 
							{
								Thread.sleep(5000);
								String temp = OnlineStatus.getInstance().checkOnlineStatus(number);
								if(temp.equals("Online")) onlineStatus.setText(temp);
								if(temp.equals("Offline")) onlineStatus.setText(null);
								
								
							} 
							catch (InterruptedException e) 
							{
								
								e.printStackTrace();
							}
						}
						
					}
			
				}
				).start();
	}
	
	public void refreshScrollBar()
	{
		scrollPane.getViewport().setViewPosition(new Point(0,scrollPane.getVerticalScrollBar().getMaximum()));
		
	}
	//_______REISKY TEST--------------
	class ZeroSizeButton extends JButton {
		  @Override public Dimension getPreferredSize() {
		    return new Dimension();
		  }
		}

		class WithoutArrowButtonScrollBarUI extends MetalScrollBarUI {
		  @Override protected JButton createDecreaseButton(int orientation) {
		    return new ZeroSizeButton();
		  }

		  @Override protected JButton createIncreaseButton(int orientation) {
		    return new ZeroSizeButton();
		  }
		}
		
		public void setTheme()
		{
			conversationPanelHeader.setBackground(Theme.getHeader());
			incoming.setBackground(Theme.getIncomingBackground());
			outgoingPanel.setBackground(Theme.getOutgoingBackground());
			outgoing.setBackground(Theme.getOutgoingBackground());
			outgoing.setForeground(Theme.getOutgoingText());
			myConversationPanel.setBackground(Theme.getIncomingBackground());
		}
		
}

