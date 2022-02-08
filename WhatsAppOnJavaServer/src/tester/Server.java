package tester;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Server extends JFrame implements Serializable{

	private JPanel contentPane;
	private JLabel imageIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Server() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 592, 445);
		contentPane.add(panel);
		panel.setLayout(null);
		
		imageIcon = new JLabel("Image");
		imageIcon.setBounds(10, 11, 582, 423);
		panel.add(imageIcon);
		start();
		
	}
	
	public void start()
	{
		new Thread(new Runnable()
	
	
	{			
		public void run()
		{				
			System.out.println(" thread running...");
			try
			{		
				
				while(true)
				{	
					
					ServerSocket ss = new ServerSocket(7001);
					Socket s= ss.accept();
					//BufferedInputStream bis= new BufferedInputStream(is);
					
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					
					
					ArrayList arrayList= (ArrayList) ois.readObject();
					System.out.println("File: "+(File) arrayList.get(1));
					BufferedImage bi= ImageIO.read((File) arrayList.get(1));
					String st= (String) arrayList.get(0);
					ois.close();
					ss.close();
					imageIcon.setIcon(new ImageIcon(bi));	
					JOptionPane.showMessageDialog(null,new ImageIcon(bi));
					
					System.out.println("String: "+st);
					System.out.println("BufferedImage: "+bi);
					
					}				
			}
			catch(Exception x)
			{
				x.printStackTrace();
			}				
		}
		
	}).start();	
	}
	

}
