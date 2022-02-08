package clientUser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import chats.ChatDetails;
import contacts.ContactDetails;
import debugger.Debugger;
import gui.FrameBorder;
import utility.FileManager;


public class ClientUserLoader 
{
	private ClientUserFunctions uf;
	public ClientUserLoader(ClientUserFunctions uf)
	{
		this.uf=uf;
	}
	
	
	public void displayUsers()
	{
		for(ClientUserDetails ud: uf.getAllUsers())
		{
			if(Debugger.testingMode())	
			{
				System.out.println(ud.getNumber()+"\t"+ud.getName()+"\t\t"+ud.getEmail()+"\t\t"+ud.getAbout());
			}
		}
	}
	public BufferedImage getUserPic(String number)
	{
		if(Debugger.testingMode())	{System.out.println("ClientUserLoader: Fetching user profile picture...");}
		
		for(ClientUserDetails ud: uf.getAllUsers())
		{
			if(ud.getNumber().equals(number))
			{				
				return FileManager.byteToImage(ud.getImage());				
			}
		}
       
		return null;
	}
	public String getUserName(String number)
	{
		if(Debugger.testingMode())	{System.out.println("ClientUserLoader: Fetching user name...");}
		
		for(ClientUserDetails ud: uf.getAllUsers())
		{
			if(ud.getNumber().equals(number))
			{
				return ud.getName();
			}
		}
		return null;
	}
	
	public String getUserAbout(String number)
	{
		if(Debugger.testingMode())	{System.out.println("ClientUserLoader: Fetching user about...");}
		
		for(ClientUserDetails ud: uf.getAllUsers())
		{
			if(ud.getNumber().equals(number))
			{
				return ud.getAbout();
			}
		}
		return null;
	}
}