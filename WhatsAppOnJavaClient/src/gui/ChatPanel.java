package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;


import chats.ChatFunctions;
import clientSocket.ClientToServer;
import debugger.Debugger;
import speechBubble.BubbleSetter;
import userMessageQueue.MessageQueueFunctions;
import utility.ImageManager;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChatPanel 
{
	private JPanel mainPanel;
	private  JLabel recentMsg;
	private JLabel profileIcon;
	

	public JPanel getChatPanel() 
	{
		return mainPanel;
	}
	public void setChatPanel(JPanel mainPanel) 
	{
		this.mainPanel = mainPanel;
	}
	//---Active User---//
	String activeUser;
	public void setActiveUser(String activeUser)
	{
		this.activeUser=activeUser;
	}
	
	//---Chat Function Object---//
		private ChatFunctions chatFunctions;
		public void setChatFunctionsReference(ChatFunctions chatFunctions)
		{
			this.chatFunctions=chatFunctions;
		}
		
		private MessageQueueFunctions mqf;
		public void setMessageQueueFunctionsReference(MessageQueueFunctions mqf)
		{
			this.mqf= mqf;
		}
		
		
		
	
	ChatPanel(int num,String chatName,String time,String chat,String number,BufferedImage image,String about,Color background,Color text)
	{
		RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
		rl.setFill( true );
		
		mainPanel = new JPanel(rl);
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(SwingUtilities.isRightMouseButton(e) || e.isControlDown())
				{
					if(Debugger.testingMode())	{System.out.println("ChatPanel: Right button clicked.");}					
					JPopupMenu menu= new JPopupMenu();
					JMenuItem delete= new JMenuItem("Delete");
					JMenuItem clear= new JMenuItem("Clear");
					menu.add(delete);
					menu.add(clear);
					menu.show(e.getComponent(),e.getX(),e.getY());
					delete.addActionListener(new ActionListener(){  
					    public void actionPerformed(ActionEvent ae)
					    {
					    	int temp=JOptionPane.showConfirmDialog(null,"Are you sure, you want to\n delete "+chatName+" chats");
					    	if(temp==0)
					    	{
					    		chatFunctions.removeChat(number);
					    		mqf.clearQueue(activeUser+number);
					    	}					    						            
					    }  
					    });  
					clear.addActionListener(new ActionListener(){  
					    public void actionPerformed(ActionEvent ae)
					    {
					    	if(Debugger.testingMode())	{System.out.println("ChatPanel: Clear button pressed");}
					    	mqf.clearQueue(activeUser+number);
					    }  
					    });  
				}
				else
				{
					
					if(Debugger.testingMode())	{System.out.println("ChatPanel: Left button pressed");}
					ConversationPanel.getInstance().setChatDetail(chatName,number,about,image);
					ConversationPanel.getInstance().incoming.removeAll();
					ConversationPanel.getInstance().incoming.revalidate();
					ConversationPanel.getInstance().setChatsOnPanel();					
					MainFrame.getInstance().activateConversationPanel();
				}
				
				
			}
		});
		
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, (80*(num-1)),334, 80);
		
		//---Left Panel for Sender's DP---//
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(background);
		//leftPanel.setBounds(10, 11, 78, 78);
		leftPanel.setLayout(new BorderLayout(0, 0));
		profileIcon = new JLabel("");
		profileIcon.setHorizontalAlignment(SwingConstants.CENTER);
		profileIcon.setIcon(new ImageIcon(ImageManager.circleCroper(image, 60,60)));
		leftPanel.add(profileIcon);
		mainPanel.add(leftPanel, new Float(25));
		
		//---Middle Panel for Sender Name & Recent message---//
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(null);
		middlePanel.setBackground(background);
		middlePanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		//middlePanel.setBounds(87, 11, 159, 78);
		JLabel name = new JLabel(chatName);
		name.setForeground(text);
		name.setFont(new Font("Tahoma", Font.BOLD, 15));
		name.setBounds(10, 12, 139, 25);
		middlePanel.add(name);
		recentMsg = new JLabel(chat);
		recentMsg.setForeground(text);
		recentMsg.setFont(new Font("Roboto", Font.PLAIN, 12));
		recentMsg.setBounds(10, 40, 163, 14);
		middlePanel.add(recentMsg);
		mainPanel.add(middlePanel, new Float(55));
		
		//---Right Panel for Date and unread msg---//
		JPanel rigthPanel = new JPanel();
		rigthPanel.setLayout(null);
		rigthPanel.setBackground(background);
		rigthPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		//rigthPanel.setBounds(246, 11, 78, 78);
		JLabel label_1 = new JLabel(time);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(text);
		label_1.setFont(new Font("Roboto", Font.PLAIN, 11));
		label_1.setBounds(-44, 15, 104, 14);
		rigthPanel.add(label_1);
		mainPanel.add(rigthPanel, new Float(20));
		
		JLabel label_1_1 = new JLabel(""); // number of unread message.
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1_1.setForeground(new Color(0, 102, 51));
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1.setBounds(24, 40, 33, 29);
		rigthPanel.add(label_1_1);
		//mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
	}	
	
}
