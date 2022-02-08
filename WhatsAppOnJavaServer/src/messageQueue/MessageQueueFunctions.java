package messageQueue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

import mapper.ClientFilter;
import mapper.ClientSocketMapper;

import serializeDeSerialize.SerializeDeSerialize;
import serverUser.ServerUserFunctions;

public class MessageQueueFunctions implements Serializable
{
	ServerUserFunctions suf;
	ClientSocketMapper csm;
	private HashMap<String,MessageQueue> msgList;
	private SerializeDeSerialize sds = new SerializeDeSerialize();		
		
	public MessageQueueFunctions()
	{		
		initiateDeSerialization();	
		if(msgList==null || msgList.isEmpty())
		{
			initializeMsgList();
			System.out.println("Creating new msgList");
			initiateSerialization();
		}	
	}
	public void setServerUserFunctionsReference(ServerUserFunctions suf)
	{
		this.suf=suf;
	}
	public void setClientSocketMapperReference(ClientSocketMapper csm)
	{
		this.csm=csm;
	}
	
	
	void initializeMsgList()
	{
		msgList = new HashMap<String,MessageQueue>();
	}
	public void initiateDeSerialization()
	{
		msgList = (HashMap<String,MessageQueue>) sds.deSerializeObject(1);
	}
	public void initiateSerialization()
	{
		sds.serializeObject(msgList, 1);
	}

	public void addMessage(ArrayList msgFrame)
	{		
		if(msgList.containsKey(msgFrame.get(2)))
		{
			System.out.println("User key present "+msgList.containsKey(msgFrame.get(2)));
			msgList.get(msgFrame.get(2)).addMessageToQueue(msgFrame);
			System.out.println("Succesfully wrote msg to "+msgFrame.get(2)+" Queue" );
			updateMsgList();
		}
		else
		{
			System.out.println("User key present "+msgList.containsKey(msgFrame.get(2)));
			System.out.println("Creating new MsgQueue for " +msgFrame.get(2));
			msgList.put(msgFrame.get(2).toString(), new MessageQueue());
			msgList.get(msgFrame.get(2)).addMessageToQueue(msgFrame);
			System.out.println("Succesfully wrote msg to "+msgFrame.get(2)+" Queue" );
			updateMsgList();
		}
		//ClientFilter.getInstance().getSpecificSocket("0").println(suf.getName(destination)+"~"+checkQueueSize(destination));
		//ClientFilter.getInstance().getSpecificSocket("0").flush();
	}
	
	public Queue<ArrayList> pullMessage(String number)
	{
		if(msgList.containsKey(number))
		{
			//System.out.println("User key present "+msgList.containsKey(number));
			return msgList.get(number).getCompleteQueue();
		}
		else
		{
			return null;
		}
	}
	
	public void updateMsgList()
	{
		//printAllMessages();
		initiateSerialization();
		//System.out.println("Message queue updated.");
	}
	public Integer checkQueueSize(String number)
	{
		return msgList.get(number).getCompleteQueue().size();
	}
	
	public void printAllMessages()
	{
		msgList.forEach((key,value)-> 
		{
			System.out.print(key + "=");
			value.printAllMessage();
		});;
	}
	public void addNewQueue(String number)
	{
		msgList.put(number, new MessageQueue());
		updateMsgList();
	}
}
