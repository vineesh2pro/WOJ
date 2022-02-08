package serverUser;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import messageQueue.MessageQueue;
import serializeDeSerialize.SerializeDeSerialize;
import utility.FileManager;

public class UserProfilePicture 
{
	private static UserProfilePicture singleInstance;
	public static UserProfilePicture getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new UserProfilePicture();
			return singleInstance;
		}
		return singleInstance;
	}
	private SerializeDeSerialize sds = new SerializeDeSerialize();	
	private HashMap<String,byte[]> upp;
	
	public UserProfilePicture()
	{
		initiateDeSerialization();		
		if(upp==null || upp.isEmpty())
		{
			initializeUPP();
			System.out.println("Creating new UPP...");
		}	
	}
	
	private void initializeUPP()
	{
		upp = new HashMap<String,byte[]>();
	}
	public void initiateDeSerialization()
	{
		upp = (HashMap<String,byte[]>) sds.deSerializeObject(2);
	}
	public void initiateSerialization()
	{
		sds.serializeObject(upp,2);
	}
	public void update()
	{
		initiateSerialization();
	}
	
	public void updateProfilePicture(Object userName,byte[] file)
	{
		try
		{
			System.out.println("File at UPP: "+file);
			//JOptionPane.showMessageDialog(null, new ImageIcon(file),userName+" pic at server",JOptionPane.INFORMATION_MESSAGE);
			upp.put(userName.toString(),file);
			System.out.println("ServerSocket: ProfilePic Updated");
			update();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public byte[] fetchProfilePicture(String userName) 
	{
		BufferedImage image=null;
		try 
		{
			image = ImageIO.read(UserProfilePicture.class.getResource("/img/default_dp.png"));
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
		}
		if(upp.get(userName)!=null)
		{
			return upp.get(userName);
		}
		return FileManager.convertToByte(image);
		
	}	
	

	
}
