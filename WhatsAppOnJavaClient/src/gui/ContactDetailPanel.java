package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import chats.ChatFunctions;
import contacts.ContactFunctions;
import theme.Theme;
import utility.ImageManager;

import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ContactDetailPanel 
{
	private JPanel mainPanel;
	
	private static ContactDetailPanel singleInstance;	
	private JLabel name;
	private JLabel about;
	private JLabel number;

	private JLabel profilePicture;
	public static ContactDetailPanel getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new ContactDetailPanel();
		}
		return singleInstance;
	}
	
	public JPanel getAboutPanel()
	{
		return mainPanel;
	}	
	
	private ChatFunctions chatFunctions;
	public void setChatFunctionsReference(ChatFunctions chatFunctions)
	{
		this.chatFunctions=chatFunctions;
	}
	private ContactFunctions contactFunctions;
	private JLabel muteLabel;
	private JTextArea encryption;
	private JLabel encryptionHeading;
	public void setContactFunctionsReference(ContactFunctions contactFunctions)
	{
		this.contactFunctions = contactFunctions;
	}
	
	private ContactDetailPanel()
	{
		mainPanel   = new JPanel();
		//mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0,20,335,575);
		mainPanel.setLayout(null);
		
		name = new JLabel("MS Dhoni");
		name.setForeground(new Color(204, 204, 255));
		name.setFont(new Font("Tahoma", Font.BOLD, 19));
		name.setBounds(25, 263, 283, 32);
		mainPanel.add(name);
		
		muteLabel = new JLabel("Mute Notification");
		
		muteLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.GRAY));
		muteLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		muteLabel.setBounds(25, 319, 283, 39);
		mainPanel.add(muteLabel);
		
		about = new JLabel("I am Indian Cricketer");
		about.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		about.setBounds(25, 464, 310, 32);
		mainPanel.add(about);
		
		JLabel lblNewLabel_4 = new JLabel("About and phone number");
		lblNewLabel_4.setForeground(new Color(0, 102, 0));
		lblNewLabel_4.setBackground(new Color(0, 102, 0));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(25, 440, 310, 25);
		mainPanel.add(lblNewLabel_4);
		
		number = new JLabel("8400002659");
		number.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		number.setFont(new Font("Tahoma", Font.BOLD, 12));
		number.setBounds(25, 489, 283, 32);
		mainPanel.add(number);
		
		encryptionHeading = new JLabel("Encryption   \r\n");
		encryptionHeading.setVerticalAlignment(SwingConstants.TOP);
		encryptionHeading.setFont(new Font("Tahoma", Font.BOLD, 14));
		encryptionHeading.setBounds(25, 368, 310, 32);
		mainPanel.add(encryptionHeading);
		
		encryption = new JTextArea();
		encryption.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		encryption.setLineWrap(true);
		encryption.setText("Message and calls are \r\nend-to-end encrypted.");
		encryption.setBounds(25, 390, 283, 39);
		encryption.setOpaque(false);
		mainPanel.add(encryption);
		
		JLabel lblNewLabel_7 = new JLabel("Report & Block");
		lblNewLabel_7.setForeground(new Color(255, 0, 0));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(25, 527, 283, 26);
		mainPanel.add(lblNewLabel_7);
		
		JLabel muteUnMute = new JLabel("Mute");
		muteUnMute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(muteUnMute.getText().equals("Mute"))
				{
					muteUnMute.setText("Unmute");
					muteUnMute.setForeground(Color.GREEN);
				}
				else
				{
					muteUnMute.setText("Mute");
					muteUnMute.setForeground(Color.RED);
				}
			}
		});
		muteUnMute.setForeground(Color.RED);
		muteUnMute.setFont(new Font("Tahoma", Font.BOLD, 14));
		muteUnMute.setBounds(228, 333, 65, 14);
		mainPanel.add(muteUnMute);
		
		JLabel back = new JLabel("");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				MainFrame.getInstance().activateConversationPanel();
			}
		});
		back.setIcon(new ImageIcon(ContactDetailPanel.class.getResource("/img/back-arrow.png")));
		back.setBounds(0, 0, 65, 32);
		mainPanel.add(back);
		
		profilePicture = new JLabel("");
		profilePicture.setVerticalAlignment(SwingConstants.TOP);
		profilePicture.setHorizontalAlignment(SwingConstants.CENTER);
		profilePicture.setIcon(new ImageIcon("C:\\Users\\taqvi\\Desktop\\MS.jpg"));
		profilePicture.setBounds(0, 0, 335, 295);
		mainPanel.add(profilePicture);
	}
	public void setAboutDetails(String number,String name,String about)
	{
		this.name.setText(name);
		this.number.setText(number);
		this.about.setText(about);		
		Image image= ImageManager.resize(contactFunctions.getProfilePicture(number), 335,295);
		this.profilePicture.setIcon(new ImageIcon(image));
	}
	public void setTheme()
	{
		mainPanel.setBackground(Theme.getBackground());
		muteLabel.setForeground(Theme.getTextColor());
		encryptionHeading.setForeground(Theme.getTextColor());
		encryption.setForeground(Theme.getTextColor());
		about.setForeground(Theme.getTextColor());
		number.setForeground(Theme.getTextColor());
		
	}
}