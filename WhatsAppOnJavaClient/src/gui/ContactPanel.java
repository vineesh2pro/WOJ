package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import chats.ChatFunctions;
import chats.ChatLoader;
import utility.FileManager;
import utility.ImageManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ContactPanel 
{
	//---Instance Variables---//
	private JPanel mainPanel;
	private JLabel name;
	
	//---Chat Function Object---//
	ChatFunctions chatFunctions;
	public void setChatFunctionsReference(ChatFunctions chatFunctions)
	{
		this.chatFunctions=chatFunctions;
	}
	public JPanel getContactsPanel()
	{
		return mainPanel;
	}

	public void setChatPanel(JPanel mainPanel) 
	{
		this.mainPanel = mainPanel;
	}
	ContactPanel(int num,String contactNumber,String contactName,String contactAbout,BufferedImage image,Color background,Color text)
	{
		RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
		rl.setFill( true );
		
		mainPanel = new JPanel(rl);
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ConversationPanel.getInstance().setChatDetail(contactName,contactNumber,contactAbout,image);
				MainFrame.getInstance().activateConversationPanel();
				chatFunctions.addChat(contactNumber,null,contactAbout,FileManager.convertToByte(image));
			}
		});
		//mainPanel.setLayout(null);
		
		//mainPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		mainPanel.setBackground(background);
		mainPanel.setBounds(0, (50*(num-1)),334, 50);
		
		//---Left Panel for Contacts DP---//
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(background);
		//leftPanel.setBounds(10, 11, 78, 78);
		leftPanel.setLayout(new BorderLayout(0, 0));
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		label.setIcon(new ImageIcon(ImageManager.circleCroper(image, 45,45)));
		leftPanel.add(label);
		mainPanel.add(leftPanel, new Float(25));
		
		//---Right Panel for Name & About message---//
		
		RelativeLayout rl2 = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl2.setFill( true );
		
		JPanel rightPanel = new JPanel(rl2);
		//rightPanel.setLayout(null);
		rightPanel.setBackground(background);
		//middlePanel.setBounds(87, 11, 159, 78);
		
		name = new JLabel(contactName);
		name.setVerticalAlignment(SwingConstants.BOTTOM);
		name.setForeground(text);
		name.setFont(new Font("Tahoma", Font.BOLD, 15));
		//name.setBounds(10, 12, 214, 25);
		rightPanel.add(name,new Float(50));
		JLabel about = new JLabel(contactAbout);
		about.setFont(new Font("Roboto", Font.PLAIN, 12));
		about.setForeground(text);
		//about.setBounds(10, 40, 214, 14);
		rightPanel.add(about, new Float(50));
		
		mainPanel.add(rightPanel, new Float(75));
	}
	
}
