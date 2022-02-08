package mapper;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ClientFilter
{
	private static ClientFilter singleInstance;
	public static ClientFilter getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new ClientFilter();
			return singleInstance;
		}
		else
		{
			return singleInstance;
		}
	}
	
	private HashMap<String,ObjectOutputStream> myClientFilter = new HashMap<String,ObjectOutputStream>();
	
	public void addSocket(String number,ObjectOutputStream writer)
	{
		System.out.println("New Client Added "+number+" and "+writer);
		this.myClientFilter.put(number,writer);
		displaySockets();
	}
	
	public ObjectOutputStream getSpecificSocket(String number)
	{
		return myClientFilter.get(number);
	}
	public void displaySockets()
	{
		System.out.println("******List of active clients*******\n");
		for(HashMap.Entry<String,ObjectOutputStream> entry : myClientFilter.entrySet())  
        System.out.println("Key = " + entry.getKey()+", Value = " + entry.getValue());
	}
	
}
