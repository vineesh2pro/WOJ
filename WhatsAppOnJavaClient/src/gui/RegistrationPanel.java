package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.MatteBorder;

import clientSocket.ClientToServer;
import clientUser.ClientUserFunctions;
import debugger.Debugger;
import theme.Theme;
import utility.FileManager;
import utility.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegistrationPanel
{
	private JPanel regPanel;
	private JTextField name;
	private JTextField mobile;
	private JTextField email;
	private JTextArea about;
	private RegistrationPanel reg;
	private File image;
	BufferedImage profilePicture;
	JLabel profilePcLabel;
	
	//Database Reference
	private ClientUserFunctions userFunctions;
	private ClientToServer cts;
		
	private static RegistrationPanel singleInstance;
	private JPanel mainPanel;
	private JPanel header;
	public static RegistrationPanel getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new RegistrationPanel();
		}
		return singleInstance;
	}
	
	public JPanel getRegistrationPanel()
	{
		return regPanel;	
	}

	public void setUserFunctionReference(ClientUserFunctions userFunctions)
	{
		this.userFunctions=userFunctions;
	}
	public void setClientToServerReference(ClientToServer cts)
	{
		this.cts=cts;
	}
	
	private RegistrationPanel() {
		
		regPanel = new JPanel();
		regPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		regPanel.setBounds(0, 20, 335, 573);
		regPanel.setVisible(true);
		regPanel.setLayout(null);
		
		header = new JPanel();
		header.setBackground(new Color(0, 108, 97));
		header.setBounds(0, 0, 354, 60);
		regPanel.add(header);
		header.setLayout(null);
		
		JLabel WhatsApp = new JLabel("WhatsApp");
		WhatsApp.setBounds(7, 5, 89, 24);
		header.add(WhatsApp);
		WhatsApp.setForeground(Color.WHITE);
		WhatsApp.setFont(new Font("Roboto", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("New User Registration");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(8, 31, 130, 14);
		header.add(lblNewLabel);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 60, 336, 503);
		regPanel.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(56, 183, 76, 14);
		mainPanel.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setOpaque(false);
		name.setBackground(new Color(0, 102, 51));
		name.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 102, 51)));
		name.setBounds(131, 180, 154, 20);
		mainPanel.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mobile");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(56, 225, 76, 14);
		mainPanel.add(lblNewLabel_1_1);
		
		mobile = new JTextField();
		mobile.setOpaque(false);
		mobile.setBackground(new Color(0, 102, 51));
		mobile.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 102, 51)));
		mobile.setColumns(10);
		mobile.setBounds(131, 222, 154, 20);
		mainPanel.add(mobile);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("E-mail ");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(56, 267, 76, 14);
		mainPanel.add(lblNewLabel_1_1_1);
		
		email = new JTextField();
		email.setOpaque(false);
		email.setBackground(new Color(0, 102, 51));
		email.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 102, 51)));
		email.setColumns(10);
		email.setBounds(131, 267, 154, 20);
		mainPanel.add(email);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("About");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2.setBounds(56, 338, 76, 14);
		mainPanel.add(lblNewLabel_1_1_2);
		
		about = new JTextArea("Hey There! I am using WhatsAppOnJava");
		about.setWrapStyleWord(true);
		about.setLineWrap(true);
		about.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 102, 51)));
		about.setBounds(131, 311, 154, 58);
		mainPanel.add(about);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(name.getText().equals("") || mobile.getText().equals("") || email.getText().equals("") || about.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please do not leave any field empty !!");
				}
				else
				{
					userFunctions.addUser(mobile.getText(),name.getText(),email.getText(),about.getText(),FileManager.convertToByte(profilePicture));
					cts.registerOnServer(mobile.getText(),name.getText(),email.getText(),about.getText());
					cts.updateProfilePicture(mobile.getText(),FileManager.convertToByte(profilePicture));		
					JOptionPane.showMessageDialog(null,name.getText()+" you are registered now.");
					MainFrame.getInstance().activateVerificationPanel();
					regPanel.repaint();
				}
				
			}
		});
		btnNewButton_1.setBackground(new Color(0, 102, 51));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(56, 393, 229, 23);
		mainPanel.add(btnNewButton_1);
		
		profilePcLabel = new JLabel("");
		profilePcLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profilePcLabel.setIcon(new ImageIcon(RegistrationPanel.class.getResource("/img/default_dp.png")));
		profilePcLabel.setBounds(97, 22, 150, 144);
		mainPanel.add(profilePcLabel);
		
		
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				MainFrame.getInstance().activateVerificationPanel();				
			}
		});
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(new Color(0, 102, 51));
		btnNewButton_1_1.setBounds(265, 469, 61, 23);
		mainPanel.add(btnNewButton_1_1);
		// Test ----------------------//
		setDefaultDP();
		
		JLabel addImage = new JLabel("Add");
		addImage.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent arg0)
			{				
				profilePicture=ImageManager.chooseImage();
				profilePcLabel.setIcon(new ImageIcon(ImageManager.circleCroper(profilePicture, 145, 145)));							
			}
		});
		addImage.setFont(new Font("Tahoma", Font.BOLD, 12));
		addImage.setBounds(198, 155, 49, 14);
		mainPanel.add(addImage);
	}
	public void setDefaultDP()
	{
		try 
		{
			profilePicture=ImageIO.read(RegistrationPanel.class.getResource("/img/default_dp.png"));
			if(Debugger.testingMode())	{System.out.println("RegistrationPanel: Setting default profile picture...");}		
			profilePcLabel.setIcon(new ImageIcon(ImageManager.circleCroper(profilePicture, 140,140)));
			
		}
		catch (IOException e) 
		{
			if(Debugger.testingMode())	{System.out.println("RegistrationPanel: Exception while setting default profile picture");}
			FrameBorder.getInstance().setStatus("Problem while setting default DP.",4000);
		}
		
	}
	
	
}
