package onlineStatus;

import java.util.HashMap;



public class OnlineStatus 
{
	HashMap<String,String> onlineStatus = new HashMap<String,String>();
	
	//---Single Instance---//
	
		private static OnlineStatus singleInstance;
		public static OnlineStatus getInstance()
		{
			if(singleInstance==null)
			{
				singleInstance= new OnlineStatus();
				return singleInstance;			
			}
			return singleInstance;
		}
	
	
	public void putOnlineStatus(String number,String status)
	{
		onlineStatus.put(number, status);
	}
	public String checkOnlineStatus(String number)
	{
		if(onlineStatus.containsKey(number))
		{
			return onlineStatus.get(number);
		}
		return "";
	}

}
