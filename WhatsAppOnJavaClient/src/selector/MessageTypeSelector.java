package selector;

import java.util.ArrayList;


import clientDataManager.ClientDataSetter;
import debugger.Debugger;
import gui.FrameBorder;

public class MessageTypeSelector 
{
	ClientDataSetter cms=ClientDataSetter.getInstance();
	static private MessageTypeSelector singleInstance;
	public static MessageTypeSelector getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance=new MessageTypeSelector();
			return singleInstance;
		}
		else
		{
			return singleInstance;
		}
	}
	
	/** Response Code ---
	 * 3.1 user message queue
	 * 5.1 user profile picture	 
	 * 7.1: sending user about
	 * 8.1 online status
	 */
	
	public void checkMessageType(String dest,Object res)
	{		
		ArrayList response= (ArrayList) res;	
		if(response.get(0).equals("3.1"))
		{
			if(Debugger.testingMode())	{System.out.println("MessageTypeSelector: Reading message from server...");}			
			
			response.remove(0);	// Removing first index which has response code only.
			cms.clientMessageSetter(response);
		}
		if(response.get(0).equals("5.1"))
		{
			response.remove(0);
			if(Debugger.testingMode())	{System.out.println("MessageTypeSelector: fetching profile picture...");}		
			cms.setProfilePicture(response);			
		}
		if(response.get(0).equals("7.1"))
		{			
			if(Debugger.testingMode())	{System.out.println("MessageTypeSelector: Fetching about...");}
			response.remove(0);
			cms.setAbout(response);
		}
		if(response.get(0).equals("8.1"))
		{			
			if(Debugger.testingMode())	{System.out.println("MessageTypeSelector: Checking online status...");}
			response.remove(0);
			cms.setOnlineStatus(response);
		}
	}
}
