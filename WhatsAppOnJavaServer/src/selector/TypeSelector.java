package selector;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import mapper.ClientFilter;
import mapper.ClientOnlineStatus;
import mapper.ClientSocketMapper;
import messageQueue.MessageQueueFunctions;
import serverUser.ServerUserFunctions;
import serverUser.UserProfilePicture;

public class TypeSelector 
{
	private ServerUserFunctions suf;
	private MessageQueueFunctions mqf;
	private ClientSocketMapper csm;
	private UserProfilePicture upp;
	
	public void setServerUserFunctionsReference(ServerUserFunctions suf)
	{
		this.suf = suf;
	}
	public void setMessageQueueFunctionsReference(MessageQueueFunctions mqf)
	{
		this.mqf = mqf;
	}
	public void setClientSocketMapperReference(ClientSocketMapper csm)
	{
		this.csm=csm;
	}
	public void setUserProfilePictureReference(UserProfilePicture upp)
	{
		this.upp= upp;
	}
	
	/** Request Code ---
	 * 0: user registration for first time
	 * 1: user socket registration at login time
	 * 2: Sending message to server
	 * 3: Requesting message from server
	 * 4: Updating profilePicture on server
	 * 5: requesting profilePicture from server
	 * 6: updating user about
	 * 7: requesting about from server
	 */
	
	public Object checkMessageType(ArrayList requestArray)
	{
		if(requestArray.get(0).equals("0"))
		{
			ArrayList<String> request =requestArray;
			suf.addUser(request.get(1),request.get(2),request.get(3),request.get(4));
			System.out.println("ServerSocket: User registered.");
			return null;
		}
		if(requestArray.get(0).equals("2"))
		{			
			mqf.addMessage(requestArray);
			System.out.println("ServerSocket: Message pushed.");
			return null;
		}
		if(requestArray.get(0).equals("3"))
		{
			ArrayList<String> request =requestArray;
			//System.out.println("ServerSocket: processing message request...");	
			ClientOnlineStatus.getInstance().addOnlineStatus(request.get(1), "Online");
			Queue q=mqf.pullMessage(request.get(1));	
			if(q==null || q.isEmpty())
			{
				return new LinkedList();
			}
			return q;
			
		}
			if(requestArray.get(0).equals("4"))
		{
			//System.out.println("File at checkRequestType: "+requestArray.get(2));
			upp.updateProfilePicture(requestArray.get(1),(byte[])requestArray.get(2));
			System.out.println("ServerSocket: Profile picture saved.");			
			return null;			
		}
		if(requestArray.get(0).equals("5"))
		{
			ArrayList<String> request =requestArray;					 
			System.out.println("ServerSocket: Fetching profile picture...");
			return upp.fetchProfilePicture(request.get(2));
			
		}
		if(requestArray.get(0).equals("6"))
		{
			ArrayList<String> request =requestArray;
			suf.updateAbout(request.get(1),request.get(2));
			System.out.println("ServerSocket: About updated.");
			return null;			
		}
		if(requestArray.get(0).equals("7"))
		{
			ArrayList<String> request =requestArray;			
			System.out.println("ServerSocket: Fetching about...");
			return suf.getAbout(request.get(2));		
		}
		
		if(requestArray.get(0).equals("8"))
		{
			ArrayList<String> request =requestArray;			
			//System.out.println("ServerSocket: Checking online status...");
			return ClientOnlineStatus.getInstance().getOnlineStatus(request.get(2));
			
		}
		
		return null;
		
		
		
	}
	public void checkMessageType(ArrayList message,ObjectOutputStream writer)
	{
		String userName= (String) message.get(1);
		if(!(message==null || message.equals("")))
		{
			
			if(message.get(0).equals("1"))
			{
				System.out.println("Client want to register socket ");
				ClientFilter.getInstance().addSocket(userName, writer);
			}
		}
		else
		{
			System.out.println("String is null");
		}
	}
	
}
