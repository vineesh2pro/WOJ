package notificationHandler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

import start.StartClientApp;

public class NotificationPopUp 
{
	JFrame notificationPopUp;
	private JPanel contentPane;
	private JLabel userName;
	private JLabel notificationMessage;
	private JLabel lblNewLabel;
	
	public NotificationPopUp(String user,String numberOfMessage) 
	{
		notificationPopUp = new JFrame();
		notificationPopUp.setSize(250, 100);
		notificationPopUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		notificationPopUp.setAlwaysOnTop(true);
		notificationPopUp.setUndecorated(true);
		//---Size of the screen---//
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(notificationPopUp.getGraphicsConfiguration());// height of the task bar
		notificationPopUp.setLocation(scrSize.width - notificationPopUp.getWidth(), scrSize.height - toolHeight.bottom - notificationPopUp.getHeight());
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				StartClientApp sca=new StartClientApp();
			}
		});
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		notificationPopUp.setContentPane(contentPane);
		contentPane.setLayout(null);		
		
			
		
		notificationMessage = new JLabel("You have "+numberOfMessage+" new message");
		notificationMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		notificationMessage.setBounds(62, 43, 178, 46);
		contentPane.add(notificationMessage);	
		
		userName = new JLabel("Hey "+user);
		userName.setForeground(Color.BLACK);
		userName.setFont(new Font("Tahoma", Font.BOLD, 14));
		userName.setBounds(62, 28, 164, 27);
		contentPane.add(userName);
		
		JLabel messageIcon = new JLabel("Image");
		messageIcon.setIcon(new ImageIcon(NotificationPopUp.class.getResource("/img/messageIcon.png")));
		messageIcon.setBounds(3, 28, 49, 46);
		contentPane.add(messageIcon);
		
		JLabel lblNewLabel_1_1 = new JLabel("X");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				System.out.println("Button clicked");
				notificationPopUp.dispose();
			}
		});
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(219, 0, 31, 22);
		contentPane.add(lblNewLabel_1_1);
		
		lblNewLabel = new JLabel("WhatsappOnJava");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 51, 0));
		lblNewLabel.setBounds(3, 0, 131, 22);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new MatteBorder(4, 1, 1, 1, (Color) new Color(0, 51, 0)));
		separator.setForeground(new Color(0, 51, 0));
		separator.setBackground(new Color(0, 51, 0));
		separator.setBounds(13, 87, 227, 5);
		contentPane.add(separator);
		notificationPopUp.setVisible(true);
		try
		{
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			System.out.println("Trouble in sleeping...");
		}
		notificationPopUp.dispose();
		
	}
}
