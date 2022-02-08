package serverUser;

import java.io.Serializable;
import java.util.HashMap;

import messageQueue.MessageQueueFunctions;
import serializeDeSerialize.SerializeDeSerialize;

public class ServerUserFunctions implements Serializable
{
	private SerializeDeSerialize sds = new SerializeDeSerialize();		
	private HashMap<String,ServerUserDetails> serverUserList;
	private MessageQueueFunctions mqf;
	public void setMessageQueueFunctionReference(MessageQueueFunctions mqf)
	{
		this.mqf=mqf;
	}
	public ServerUserFunctions()
	{		
		initiateDeSerialization();	
		if(serverUserList==null || serverUserList.isEmpty())
		{
			initializeUserList();
			System.out.println("Creating new serverUserList");
		}	
	}
	void initializeUserList()
	{
		serverUserList = new HashMap<String,ServerUserDetails>();
		updateUserList();
	}
	public void initiateDeSerialization()
	{
		serverUserList = (HashMap<String,ServerUserDetails>) sds.deSerializeObject(0);
	}
	public void initiateSerialization()
	{
		sds.serializeObject(serverUserList, 0);
	}
	
	public void addUser(String number,String name,String email,String about)
	{
		ServerUserDetails userObject=new ServerUserDetails(number,name,email,about);
		serverUserList.put(number,userObject);	
		updateUserList();
		System.out.println(number+" registered successfully to server");
		mqf.addNewQueue(number);		
	}
	public void removeUser(String number)
	{
		if(serverUserList.containsKey(number))
		{
			serverUserList.remove(number);
		}		
	}
	public HashMap<String,ServerUserDetails> getAllUsers()
	{
		return serverUserList;
	}
	public void updateUserList()
	{
		initiateSerialization();
		System.out.println("ServerSocket: user list updated");
	}
	private boolean checkDuplicacy(String number)
	{	
		if(serverUserList.containsKey(number))
		{
			System.out.println("Number already registered !!");
			return true;
		}		
		return false;		
	}
	public String getName(String number)
	{
		return serverUserList.get(number).getName();
	}
	
	public String getAbout(String user)
	{
		if(serverUserList.get(user)!=null)
		{
			return serverUserList.get(user).getAbout();
		}
		return "Hey there ! I m using WhatsappOnJava";
		
	}
	public void updateAbout(String number,String about)
	{
		serverUserList.get(number).setAbout(about);
		updateUserList();
	}
	
		
	
	
	
}
