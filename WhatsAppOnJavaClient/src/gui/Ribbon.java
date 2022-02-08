package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import chats.ChatLoader;
import clientUser.ClientUserFunctions;
import clientUser.ClientUserLoader;
import contacts.ContactFunctions;
import contacts.ContactLoader;
import debugger.Debugger;
import theme.Theme;
import utility.FileManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ribbon 
{
	//---Singleton Pattern---//
	private static Ribbon singleInstance;
	
	JLabel calls;
	JLabel chats;
	JLabel contacts;
	
	public static Ribbon getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance = new Ribbon();
		}
		return singleInstance;
	}
	
	//---Instance Variable---//
	private JPanel mainPanel;	
	private ChatLoader chatLoader;
	private ContactLoader contactLoader;
	private ContactFunctions contactFunctions;
	private ClientUserLoader cul;
	
	public void setChatReference(ChatLoader chatLoader)
	{
		this.chatLoader=chatLoader;
	}
	public void setContactReference(ContactLoader contactLoader)
	{
		this.contactLoader=contactLoader;
	}
	public void setContactFunctionsReference(ContactFunctions contactFunctions)
	{
		this.contactFunctions=contactFunctions;
	}	
	public void setClientUserLoaderReference(ClientUserLoader clientUserLoader)
	{
		this.cul = clientUserLoader;
	}
	
	//---Active User---//
	private String activeUser;
	private JPanel upperPanel;
	private JPanel lowerPanel;
	public void setActiveUser(String activeUser)
	{
		this.activeUser=activeUser;
	}
		
	public JPanel getRibbon()
	{
		return mainPanel;
	}
	
	//---Constructor---//
	private Ribbon()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBounds(0, 20, 340, 110);
		mainPanel.setLayout(null);
		
		upperPanel = new JPanel();
		upperPanel.setBackground(new Color(0, 108, 97));
		upperPanel.setBounds(0, 0, 334, 55);
		upperPanel.setLayout(null);
		JLabel WhatsApp = new JLabel("WhatsAppOnJava");
		WhatsApp.setForeground(Color.WHITE);
		WhatsApp.setFont(new Font("Roboto", Font.BOLD, 18));
		WhatsApp.setBounds(15, 15, 155, 22);
		upperPanel.add(WhatsApp);
		JLabel refresh = new JLabel();
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				switchTab(3);
			}
		});
		refresh.setIcon(new ImageIcon(Ribbon.class.getResource("/img/refresh.png")));
		refresh.setHorizontalAlignment(SwingConstants.LEFT);
		refresh.setBounds(220, 15, 30, 30);
		upperPanel.add(refresh);
		JLabel addNewContact = new JLabel();
		addNewContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(Debugger.testingMode())	{System.out.println("Ribbon: Add contact button pressed.");}
				
				JTextField name = new JTextField(1);
				JTextField number = new JTextField(1);
				JPanel panel = new JPanel(new GridLayout(0,1));
				panel.add(new JLabel("Name:"));
				panel.add(name);			  
			    panel.add(new JLabel("Number:"));
			    panel.add(number);
			    
			   
			    int result = JOptionPane.showConfirmDialog(null, panel, 
			               "Please enter the name and contact number", JOptionPane.OK_CANCEL_OPTION);
			      if (result == JOptionPane.OK_OPTION) 
			      {			    	  
					try 
					{
						BufferedImage image = ImageIO.read(Ribbon.class.getResource("/img/default_dp.png"));
						contactFunctions.addContact(number.getText(),name.getText(),"Hey there I m using whatsappOnJava",FileManager.convertToByte(image));
					} 
					catch (IOException e) 
					{						
						if(Debugger.testingMode())	{System.out.println("Ribbon: Exception while adding contact");}
						FrameBorder.getInstance().setStatus("Problem while adding new contact.",4000);
					}			    	 
			      }
			      
				
			}
		});
		addNewContact.setForeground(Color.WHITE);
		addNewContact.setFont(new Font("Tahoma", Font.BOLD, 25));
		addNewContact.setText("+");
		addNewContact.setHorizontalAlignment(SwingConstants.CENTER);
		addNewContact.setBounds(260, 0, 30, 55);
		upperPanel.add(addNewContact);
		JLabel ThreeDots = new JLabel();
		ThreeDots.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
				JPopupMenu menu= new JPopupMenu();
				JMenuItem settings= new JMenuItem("Settings");				
				menu.add(settings);
				
				menu.show(e.getComponent(),e.getX(),e.getY());
				settings.addActionListener(new ActionListener(){  
				    public void actionPerformed(ActionEvent ae)
				    {				    	
				    	MainFrame.getInstance().activateSettingsPanel();	
				    	SettingsPanel.getInstance().setValues(cul.getUserName(activeUser), activeUser, cul.getUserAbout(activeUser),cul.getUserPic(activeUser));
				    }  
				    });  
				
			}
		});
		ThreeDots.setHorizontalAlignment(SwingConstants.CENTER);
		ThreeDots.setIcon(new ImageIcon(Ribbon.class.getResource("/img/three_dots.png")));
		ThreeDots.setBounds(300, 15, 30, 30);
		upperPanel.add(ThreeDots);
		mainPanel.add(upperPanel);
		
		lowerPanel = new JPanel();
		lowerPanel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(192, 192, 192)));
		lowerPanel.setBackground(new Color(0, 108, 97));
		lowerPanel.setBounds(0, 55, 334, 55);
		lowerPanel.setLayout(null);
		
		calls = new JLabel("CALLS");
		calls.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				switchTab(1);
			}
		});
		calls.setForeground(Color.WHITE);
		calls.setHorizontalAlignment(SwingConstants.CENTER);
		calls.setFont(new Font("Roboto", Font.BOLD, 14));
		calls.setBounds(0, 0, 111, 55);
		lowerPanel.add(calls);
		chats = new JLabel("CHATS");
		chats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				switchTab(2);
			}
		});
		
		chats.setHorizontalAlignment(SwingConstants.CENTER);
		chats.setForeground(Color.WHITE);
		chats.setFont(new Font("Roboto", Font.BOLD, 14));
		chats.setBounds(112, 0, 111, 55);
		lowerPanel.add(chats);
		
		contacts = new JLabel("CONTACTS");
		contacts.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{			
				switchTab(3);
			}
		});
		
		contacts.setHorizontalAlignment(SwingConstants.CENTER);
		contacts.setForeground(Color.WHITE);
		contacts.setFont(new Font("Roboto", Font.BOLD, 14));
		contacts.setBounds(223, 0, 111, 55);
		lowerPanel.add(contacts);
		mainPanel.add(lowerPanel);
	}
	
	public void switchTab(int num)
	{
		if(num==1)
		{
			calls.setBorder(new MatteBorder(0, 0, 6, 0, (Color) new Color(255, 255, 255)));
			chats.setBorder(null);
			contacts.setBorder(null);
			
			MainFrame.getInstance().activateCallsContainer();	
		}
		if(num==2)
		{
			calls.setBorder(null);
			chats.setBorder(new MatteBorder(0, 0, 6, 0, (Color) new Color(255, 255, 255)));			
			contacts.setBorder(null);
			
			MainFrame.getInstance().activateChatContainer();
			//chatLoader.requestAllChatsOnGUI();
		}
		if(num==3)
		{
			calls.setBorder(null);
			chats.setBorder(null);
			contacts.setBorder(new MatteBorder(0, 0, 6, 0, (Color) new Color(255, 255, 255)));
			
			MainFrame.getInstance().activateContactContainer();
			//contactLoader.requestAllContactsOnGUI();
			
		}
		
	}
	public void setTheme()
	{
		mainPanel.setBackground(Theme.getFrameBorder());
		upperPanel.setBackground(Theme.getHeader());
		lowerPanel.setBackground(Theme.getHeader());		
	}
	
}
