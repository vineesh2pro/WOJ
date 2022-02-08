package chats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import clientUser.ClientUserDetails;
import contacts.ContactDetails;
import debugger.Debugger;
import gui.FrameBorder;
import serializeDeSerialize.SerializeDeSerialize;


public class ChatFunctions implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SerializeDeSerialize sds = new SerializeDeSerialize();
	
	//private HashSet<ChatDetails> chatList;
	private ArrayList<ChatDetails> chatList;
	
	public ChatFunctions()
	{		
		initiateDeSerialization();	
		if(chatList==null || chatList.isEmpty())
		{
			initializeChatList();
			if(Debugger.testingMode())	{System.out.println("ChatFunctions: Creating new Chat List...");}
		}			
	}
	void initializeChatList()
	{
		chatList = new ArrayList<ChatDetails>();
	}
	public void initiateDeSerialization()
	{
		chatList = (ArrayList<ChatDetails>) sds.deSerializeObject(2);
	}
	public void initiateSerialization()
	{
		sds.serializeObject(chatList, 2);
	}
	
	public void addChat(String number,String recentChat,String about,byte[] imageFile)
	{
		if(checkDuplicacy(number))
		{
			if(Debugger.testingMode())	{System.out.println("ChatFunctions: This chat alreaady exist...");}
		}
		else
		{
			chatList.add(new ChatDetails(number,recentChat,about,imageFile));		
			updateChatList();
		}
		
	}
	public void removeChat(String number)
	{
		ChatDetails temp=null;
		for(ChatDetails cd:chatList)
		{
			if(cd.getNumber().equals(number))
			{
				temp=cd;
			}
		}
		chatList.remove(temp);
		if(Debugger.testingMode())	{System.out.println("ChatFunctions: Chat removed");}		
		updateChatList();
	}
	public void removeAllChats()
	{
		chatList.clear();
	}
	public ArrayList<ChatDetails> getAllChats()
	{
		return chatList;
	}
	public void updateChatList()
	{
		initiateSerialization();
	}
	public void addSortedChat(ChatDetails cd)
	{
		chatList.add(cd);
	}
	public void sortChatList()
	{
		if(Debugger.testingMode()) {System.out.println("Sorting Chats !");}		
		Collections.sort(chatList, (o1,o2)->o2.getTime().compareTo(o1.getTime()));		
	}
	public void updateChat(String number,String message)
	{
		for(ChatDetails cd: chatList)
		{
			if(cd.getNumber().equals(number))
			{
				if(Debugger.testingMode()) {System.out.println(cd.getNumber());}
				cd.setChat(message);
				if(Debugger.testingMode()) {System.out.println(cd.getChat());}
				updateChatList();
			}
		}
	}
	public void updateProfilePicture(String number,byte[] image)
	{
		for(ChatDetails cd:chatList)
		{
			if(cd.getNumber().equals(number))
			{
				cd.setImage(image);
				updateChatList();
			}
		}
	}
	public BufferedImage getProfilePicture(String number)
	{
		for(ChatDetails cd:chatList)
		{
			if(cd.getNumber().equals(number))
			{
				return cd.getImage();			
			}
		}
		return null;
	}
	
	public void updateAbout(String number,String about)
	{
		for(ChatDetails cd:chatList)
		{
			if(cd.getNumber().equals(number))
			{
				cd.setAbout(about);
				updateChatList();
			}
		}
	}
	
	public Boolean checkDuplicacy(String number)
	{	
		for(ChatDetails cd: chatList)
		{
			if(cd.getNumber().equals(number))
			{
				return true;
			}
		}
		return false;		
	}
	
	
}
