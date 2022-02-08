package mapper;

import java.net.Socket;
import java.util.HashMap;

public class ClientSocketMapper 
{
	private HashMap<String,Socket> clientSocket = new HashMap<String,Socket>();
	
	public void addSocketToMapper(String number,Socket clientSocket)
	{
		System.out.println("New Client Added "+number+" and "+clientSocket+"into mapper");
		this.clientSocket.put(number,clientSocket);
		displaySockets();
	}
	
	public Socket getSpecificSocket(String number)
	{
		return clientSocket.get(number);
	}
	public void displaySockets()
	{
		System.out.println("******List of active clients*******\n");
		for(HashMap.Entry<String,Socket> entry : clientSocket.entrySet())  
        System.out.println("Key = " + entry.getKey()+", Value = " + entry.getValue());
	}
}
