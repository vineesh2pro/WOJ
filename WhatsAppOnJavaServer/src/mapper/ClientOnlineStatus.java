package mapper;

import java.util.HashMap;

import serializeDeSerialize.SerializeDeSerialize;
import serverUser.ServerUserDetails;

public class ClientOnlineStatus
{
	HashMap<String,String> onlineStatus;
	SerializeDeSerialize sds= new SerializeDeSerialize();
	
	//Single Instance---//
	private static ClientOnlineStatus singleInstance;
	public static ClientOnlineStatus getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new ClientOnlineStatus();
			return singleInstance;			
		}
		return singleInstance;
	}
	
	private ClientOnlineStatus()
	{
		initiateDeSerialization();	
		if(onlineStatus==null || onlineStatus.isEmpty())
		{
			initializeOnlineStatus();
			System.out.println("Creating new serverUserList");
		}	
		startThread();
	}
	private void initializeOnlineStatus()
	{
		onlineStatus = new HashMap<String,String>();
	}
	
	public void initiateDeSerialization()
	{
		onlineStatus = (HashMap<String,String>) sds.deSerializeObject(3);
	}
	public void initiateSerialization()
	{
		sds.serializeObject(onlineStatus, 3);
	}
	
	public void addOnlineStatus(String number,String status)
	{
		onlineStatus.put(number, status);
		initiateSerialization();
	}
	public String getOnlineStatus(String number)
	{
		if(onlineStatus.containsKey(number))
		{
			return onlineStatus.get(number);
		}
		return "Offline";
	}
	
	private void startThread()
	{
		Thread t = new Thread(new Runnable()
		{

					@Override
					public void run() 
					{
						while(true)
						{
							
							try
							{
								Thread.sleep(5000);
								  for (HashMap.Entry<String,String> entry : onlineStatus.entrySet())  
								  {
									  onlineStatus.put(entry.getKey(),"Offline");									 								  
								  }
							            
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
		);
		t.start();
	}
	
	public void display()
	{
		System.out.println("Display Function");
		for (HashMap.Entry<String,String> entry : onlineStatus.entrySet())  
		  {			  
			   System.out.println(entry.getKey()+" "+getOnlineStatus(entry.getKey()));
			  
		  }
	}
	
	
	

}
