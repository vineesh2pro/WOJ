package clientUser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.HashSet;

import chats.ChatDetails;
import debugger.Debugger;
import gui.FrameBorder;
import serializeDeSerialize.SerializeDeSerialize;


public class ClientUserFunctions implements Serializable
{
private SerializeDeSerialize sds = new SerializeDeSerialize();
	
	private HashSet<ClientUserDetails> userList;
	
	
	public ClientUserFunctions()
	{		
		initiateDeSerialization();	
		if(userList==null || userList.isEmpty())
		{
			initializeUserList();
			if(Debugger.testingMode())	{System.out.println("ClientUserFunctions: Creating new user list...");}
			
		}	
	}
	void initializeUserList()
	{
		userList = new HashSet<ClientUserDetails>();
	}
	public void initiateDeSerialization()
	{
		userList = (HashSet<ClientUserDetails>) sds.deSerializeObject(3);
	}
	public void initiateSerialization()
	{
		sds.serializeObject(userList, 3);
	}

	public void addUser(String number,String name,String email,String about,byte[] image)
	{
		if(checkDuplicacy(number))
		{
			if(Debugger.testingMode())	{System.out.println("ClientUserFunctions: This user already exist");}
			
		}
		else
		{
			ClientUserDetails userObject=new ClientUserDetails(number,name,email,about,image);
			userList.add(userObject);		
			updateUserList();
			
		}
		
		
		
	}
	public void removeUser(String number)
	{
		for(ClientUserDetails ud:userList)
		{
			if(ud.getNumber().equals(number))
			{
				userList.remove(ud);
				updateUserList();
			}
		}
	}
	public HashSet<ClientUserDetails> getAllUsers()
	{
		return userList;
	}
	public void updateUserList()
	{
		initiateSerialization();
		if(Debugger.testingMode())	{System.out.println("ClientUserFunctions: User List updated.");}
		
	}
	public Boolean checkDuplicacy(String number)
	{	
		for(ClientUserDetails cd: userList)
		{
			if(cd.getNumber().equals(number))
			{
				return true;
			}
		}
		return false;		
	}
	
	public void updateAbout(String number,String about)
	{
		for(ClientUserDetails ud:userList)
		{
			if(ud.getNumber().equals(number))
			{
				ud.setAbout(about);
				updateUserList();
			}
		}
	}
	
	public void updateProfilePicture(String number,byte[] image)
	{
		if(Debugger.testingMode())	{System.out.println("ClientUserFunctions: Updating profile picture...");}
		
		for(ClientUserDetails cud:userList)
		{
			if(cud.getNumber().equals(number))
			{
				cud.setImage(image);
				updateUserList();
			}
		}
	}
	
	
}
