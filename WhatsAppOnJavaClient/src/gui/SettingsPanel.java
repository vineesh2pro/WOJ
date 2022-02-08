package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import clientSocket.ClientToServer;
import clientUser.ClientUserFunctions;
import debugger.Debugger;
import theme.Theme;
import theme.ThemeMode;
import utility.FileManager;
import utility.ImageManager;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class SettingsPanel 
{
	private JPanel settingsPanel;
	private JLabel userName;
	private JTextArea about;
	private JLabel phone;
	private JLabel profilePicLabel;
	private BufferedImage profilePic;
	
	//---Singleton pattern---//
	private static SettingsPanel singleInstance;
	public static SettingsPanel getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new SettingsPanel();
			return singleInstance;
		}
		return singleInstance;
	}
	
	public JPanel getSettingsPanel()
	{
		return settingsPanel;
	}

	private ClientToServer cts;
	public void setClientToServerReference(ClientToServer cts)
	{
		this.cts= cts;
	}
	private ClientUserFunctions userFunctions;
	public void setUserFunctionReference(ClientUserFunctions userFunctions)
	{
		this.userFunctions=userFunctions;
	}
	
	private String activeUser;
	private JPanel header;
	private JPanel panel;
	private JLabel nameHeading;
	private JLabel aboutHeading;
	private JLabel phoneHeading;
	private JLabel theme;
	public void setActiveUser(String activeUser)
	{
		this.activeUser=activeUser;
	}
	
	public SettingsPanel() 
	{
		settingsPanel= new JPanel();
		settingsPanel.setBounds(0, 20, 335, 573);
		settingsPanel.setLayout(null);
		
		header = new JPanel();
		header.setBounds(0, 0, 335, 50);
		header.setBackground(new Color(0, 108, 97));
		settingsPanel.add(header);
		header.setLayout(null);
		
		JLabel back = new JLabel("");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				panel.setVisible(false);
				MainFrame.getInstance().activateChatContainer();
			}
		});
		back.setHorizontalAlignment(SwingConstants.CENTER);
		back.setIcon(new ImageIcon(SettingsPanel.class.getResource("/img/back-arrow.png")));
		back.setBounds(0, 0, 49, 50);
		header.add(back);
		
		JLabel heading = new JLabel("Settings");
		heading.setForeground(Color.WHITE);
		heading.setFont(new Font("Tahoma", Font.BOLD, 14));
		heading.setBounds(59, 0, 266, 50);
		header.add(heading);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 50, 335, 523);
		settingsPanel.add(panel);
		panel.setLayout(null);
		
		profilePicLabel = new JLabel("");
		profilePicLabel.setIcon(new ImageIcon(SettingsPanel.class.getResource("/img/default_dp.png")));
		profilePicLabel.setBounds(103, 31, 120, 131);
		panel.add(profilePicLabel);
		
		nameHeading = new JLabel("Name");
		nameHeading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameHeading.setBounds(10, 195, 325, 22);
		panel.add(nameHeading);
		
		userName = new JLabel("UserName");
		userName.setFont(new Font("Tahoma", Font.BOLD, 14));
		userName.setBounds(10, 216, 325, 38);
		panel.add(userName);
		
		aboutHeading = new JLabel("About");
		aboutHeading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aboutHeading.setBounds(10, 257, 325, 22);
		panel.add(aboutHeading);
		
		about = new JTextArea("ABOUT");
		about.setFont(new Font("Tahoma", Font.BOLD, 14));
		about.setLineWrap(true);
		about.setWrapStyleWord(true);
		about.setEditable(false);
		about.setOpaque(false);
		about.setBorder(null);
		about.setBounds(10, 278, 290, 56);
		panel.add(about);
		
		phoneHeading = new JLabel("Phone");
		phoneHeading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneHeading.setBounds(10, 345, 315, 22);
		panel.add(phoneHeading);
		
		phone = new JLabel("phone number");
		phone.setFont(new Font("Tahoma", Font.BOLD, 14));
		phone.setBounds(10, 366, 315, 38);
		panel.add(phone);
		
		JLabel appName = new JLabel("WhatsappOnJava");
		appName.setIcon(new ImageIcon(SettingsPanel.class.getResource("/img/chat (4).png")));
		appName.setFont(new Font("Tahoma", Font.BOLD, 14));
		appName.setHorizontalAlignment(SwingConstants.CENTER);
		appName.setBounds(0, 461, 335, 38);
		appName.setForeground(new Color(0, 108, 97));
		panel.add(appName);
		
		theme = new JLabel("Theme");
		theme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		theme.setBounds(10, 415, 64, 22);
		panel.add(theme);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 108, 97));
		separator.setBackground(new Color(0, 108, 97));
		separator.setBounds(10, 252, 315, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 108, 97));
		separator_1.setBackground(new Color(0, 108, 97));
		separator_1.setBounds(10, 333, 315, 2);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 108, 97));
		separator_2.setBackground(new Color(0, 108, 97));
		separator_2.setBounds(10, 402, 315, 2);
		panel.add(separator_2);
		
		JLabel editPicture = new JLabel("Edit");
		editPicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(Debugger.testingMode())	{System.out.println("SettingsPanel: user wanna change profile picture");}
				
				profilePic=ImageManager.chooseImage();
				profilePicLabel.setIcon(new ImageIcon(ImageManager.circleCroper(profilePic, 120, 120)));
				cts.updateProfilePicture(activeUser, FileManager.convertToByte(profilePic));
				userFunctions.updateProfilePicture(activeUser, FileManager.convertToByte(profilePic));
			}
		});
		editPicture.setHorizontalAlignment(SwingConstants.TRAILING);
		editPicture.setBounds(174, 148, 49, 14);
		panel.add(editPicture);
		
		JLabel editAbout = new JLabel("Edit");
		editAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(Debugger.testingMode())	{System.out.println("SettingPanel: Edit about clicked");}
				about.setEditable(true);
				editAbout.setText("Update");
				{
					editAbout.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) 
						{
							if(Debugger.testingMode())	{System.out.println("SettingsPanel: updating about");}				
							cts.updateAbout(activeUser,about.getText());
							userFunctions.updateAbout(activeUser,about.getText());	
							about.setEditable(false);
							editAbout.setText("Edit");
						}
					});
				}
							
			}
		});
		editAbout.setHorizontalAlignment(SwingConstants.TRAILING);
		editAbout.setBounds(276, 308, 49, 14);
		panel.add(editAbout);
		
		JRadioButton lightTheme = new JRadioButton("Light");
		lightTheme.setSelected(true);
		JRadioButton darkTheme = new JRadioButton("Dark");
		lightTheme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				new ThemeMode().setLightTheme();
				new ThemeSetter().refreshTheme();
				setTheme();
				darkTheme.setSelected(false);
				
			}
		});
		lightTheme.setFont(new Font("Tahoma", Font.BOLD, 14));
		lightTheme.setBounds(80, 415, 64, 23);
		lightTheme.setOpaque(false);
		
		panel.add(lightTheme);
		
		
		darkTheme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				new ThemeMode().setDarkTheme();
				new ThemeSetter().refreshTheme();
				setTheme();
				lightTheme.setSelected(false);
			}
		});
		darkTheme.setFont(new Font("Tahoma", Font.BOLD, 14));
		darkTheme.setBounds(153, 415, 64, 23);
		darkTheme.setOpaque(false);
		panel.add(darkTheme);
		panel.setVisible(false);
		

	}
	public void setValues(String userName,String phone,String about,BufferedImage image)
	{
		profilePicLabel.setIcon(new ImageIcon(ImageManager.circleCroper(image, 120,120)));
		this.userName.setText(userName);
		this.phone.setText(phone);
		this.about.setText(about);
		panel.setVisible(true);
	}
	public void setTheme()
	{
		panel.setBackground(Theme.getBackground());
		header.setBackground(Theme.getHeader());
		nameHeading.setForeground(Theme.getTextColor());
		userName.setForeground(Theme.getTextColor());
		aboutHeading.setForeground(Theme.getTextColor());
		about.setForeground(Theme.getTextColor());
		phone.setForeground(Theme.getTextColor());
		phoneHeading.setForeground(Theme.getTextColor());
		theme.setForeground(Theme.getTextColor());
		
	}
}
