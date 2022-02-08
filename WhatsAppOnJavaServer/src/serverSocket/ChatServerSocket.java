package serverSocket;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import gui.MainFrame;
import mapper.ClientFilter;
import mapper.ClientSocketMapper;
import messageQueue.MessageQueueFunctions;
import selector.TypeSelector;
import serverUser.UserProfilePicture;

public class ChatServerSocket 
{
	
	ClientSocketMapper csm = new ClientSocketMapper();
	ArrayList inputArray = new ArrayList();
	ArrayList requestArray = new ArrayList();
	ArrayList outputArray = new ArrayList();
	
	private String serviceStatus = "Not Running";
	private boolean writeEnabler = false;
	private boolean networkEstablished = false;
	ObjectOutputStream tempWriterObject;
	
	
	//---References---//
	TypeSelector ts;
	MessageQueueFunctions mqf;	
	public void setTypeSelectorReference(TypeSelector ts)
	{
		this.ts = ts;
	}
	public void setMessageQueueReference(MessageQueueFunctions mqf)
	{
		this.mqf=mqf;
	}
	//----------------------------------------------------------------//
	
	public void requestServiceStart(Boolean fromGUI)
	{
		setUpServiceStatus(fromGUI);
		setUpNetworking();
	}
	public void requestServiceStop(Boolean fromGUI)
	{
		setDownServiceStatus(fromGUI);
	}
	public void setUpServiceStatus()
	{
		writeEnabler = false;
		serviceStatus = "Running";
	}
	public void setUpServiceStatus(Boolean fromGUI)
	{
		writeEnabler = false;
		MainFrame.getInstance().incomingPanel.setBackground(Color.GREEN);
		MainFrame.getInstance().startServiceButton.setEnabled(false);
		MainFrame.getInstance().stopServiceButton.setEnabled(true);
		serviceStatus = "Running";
		MainFrame.getInstance().serverStatusLabel.setText("Server Status : "+serviceStatus);
	}
	public void setDownServiceStatus()
	{
		writeEnabler = true;
		serviceStatus = "Not Running";
	}
	public void setDownServiceStatus(Boolean fromGUI)
	{
		writeEnabler = true;
		MainFrame.getInstance().incomingPanel.setBackground(Color.RED);
		MainFrame.getInstance().startServiceButton.setEnabled(true);
		MainFrame.getInstance().stopServiceButton.setEnabled(false);
		serviceStatus = "Not Running";
		MainFrame.getInstance().serverStatusLabel.setText("Server Status : "+serviceStatus);
	}
	
	public void setUpNetworking()
	{
		if(networkEstablished==false)
		{
			
			try
			{
				ServerSocket serverSock = new ServerSocket(5000);
				networkEstablished=true;
				
				Thread k = new Thread(new Runnable()
				{
					private ObjectOutputStream oos;

					public void run()
					{
						try
						{
							while(true)
							{
								Socket clientSocket = serverSock.accept();		
								oos = new ObjectOutputStream(clientSocket.getOutputStream());
								tempWriterObject=oos;								
								System.out.println("Thread is running");								
								Thread t = new Thread(new ClientHandler(clientSocket));
								t.start();
							}
						}
						catch(Exception x)
						{
							
						}
					}
				});
				k.start();
			}
			catch(Exception e)
			{
				serviceStatus = "Not Running";
			}
		}
		else
		{
			
		}
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
	
	/** Response Code ---
	 * 3.1 user message queue	 
	 * 7.1: sending user about
	 */
	
	public class ClientHandler implements Runnable 
	{
		
		Socket sock;
		private BufferedInputStream bis;
		private ObjectInputStream ois;
		private ArrayList tempArray;
		
	
		public ClientHandler(Socket clientSocket) 
		{
			
			try 
			{
				sock = clientSocket;
				ois = new ObjectInputStream(sock.getInputStream());
				bis = new BufferedInputStream(sock.getInputStream());
				//inputArray= (ArrayList)  ois.readObject();
				System.out.println("Client Handler is active now");				
				//ts.checkMessageType(inputArray,tempWriterObject);
				
			} 
			catch(Exception ex) 
			{
				System.out.println("At 164");
				ex.printStackTrace();
			}
		}
		
		public void run() 
		{
			System.out.println("Inside Client Handler RUN function");
			try 
			{			
				inputArray= (ArrayList)  ois.readObject();
				tempArray = inputArray;
				
				
				while(true)
				{					
							
												
					ts.checkMessageType(tempArray,tempWriterObject);
					Object temp= ts.checkMessageType(tempArray);
				
					if(temp instanceof byte[])
					{
						System.out.println("Image Request");
						sendProfilePictureToClient(tempArray.get(1).toString(),temp,tempArray.get(2).toString());
					}
					if(temp instanceof Queue)
					{					
						getMessageFromQueue(tempArray.get(1).toString(),temp);					
					}		
					if(temp instanceof String)
					{
						
						if(temp.equals("Online") || temp.equals("Offline"))
						{
							sendOnlineStatusToClient(tempArray.get(1).toString(),temp.toString(),tempArray.get(2).toString());
						}
						else
						{
							sendAboutToClient(tempArray.get(1).toString(),temp.toString(),tempArray.get(2).toString());
						}
						
						
					}
						tempArray= (ArrayList) ois.readObject();
				} 
			}
			catch(Exception ex) 
			{
				//ex.printStackTrace();
			}
		}
	}
	
	public void getMessageFromQueue(String number,Object queue)
	{
		Queue<ArrayList> msgQueue=(Queue<ArrayList>) queue;
		//System.out.println("Retriving message from queue...");		
		if(msgQueue!=null)
		{
			while(!msgQueue.isEmpty())
			{
				sendQueueMessageToClient(number,msgQueue.poll());			
			}
			mqf.updateMsgList();
		}
		
	}
	public void sendQueueMessageToClient(String number,ArrayList msgFrame)
	{
		try 
		{
			msgFrame.set(0,"3.1");
			ClientFilter.getInstance().getSpecificSocket(number).writeObject(msgFrame);
			ClientFilter.getInstance().getSpecificSocket(number).flush();
			System.out.println("ServerSocket after flush: "+msgFrame);			
		} 
		catch (IOException e) 
		{			
			//e.printStackTrace();
		}
		
	}
	public void sendAboutToClient(String number,String about,String user)
	{
		try 
		{
			if(about!=null)
			{
				ArrayList a= new ArrayList();
				a.add("7.1");
				a.add(user);
				a.add(about);
				ClientFilter.getInstance().getSpecificSocket(number).writeObject(a);
				ClientFilter.getInstance().getSpecificSocket(number).flush();
				System.out.println("ServerSocket after flush: "+a);
				a.clear();
			}
			
			
		} 
		catch (IOException e) 
		{			
			//e.printStackTrace();
		}
	}
	
	public void sendProfilePictureToClient(String number,Object imageFile,String user)
	{
		try 
		{
			if(imageFile!=null)
			{
				ArrayList a= new ArrayList();
				a.add("5.1");
				a.add(user);
				a.add((byte[]) imageFile);
				ClientFilter.getInstance().getSpecificSocket(number).writeObject(a);
				ClientFilter.getInstance().getSpecificSocket(number).flush();
				System.out.println("ServerSocket after flush: "+a);
				a.clear();
			}
			
		} 
		catch (IOException e) 
		{			
			//e.printStackTrace();
		}
	}
	
	public void sendOnlineStatusToClient(String number,String status,String user)
	{
		try 
		{
			if(status!=null)
			{
				ArrayList a= new ArrayList();
				a.add("8.1");
				a.add(user);
				a.add(status);
				ClientFilter.getInstance().getSpecificSocket(number).writeObject(a);
				ClientFilter.getInstance().getSpecificSocket(number).flush();
				//System.out.println("ServerSocket after flush: "+a+number);
				a.clear();
			}
			
			
		} 
		catch (IOException e) 
		{			
			//e.printStackTrace();
		}
	}
}
