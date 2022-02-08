package clientSocket;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import debugger.Debugger;
import gui.FrameBorder;
import selector.MessageTypeSelector;
import speechBubble.SpeechBubbleLoader;
import userMessageQueue.MessageQueueFunctions;
import utility.FileManager;
import utility.ImageManager;

public class ClientToServer
{
	private ArrayList inputArray = new ArrayList();	
	private ObjectOutputStream oos;
	private String number;	
	private BufferedOutputStream bos;
	private MessageQueueFunctions mqf;
	
	
	public void setMessageQueueReferences(MessageQueueFunctions mqf)
	{
		this.mqf=mqf;
	}
	
	
	//---Constructor---//
	public ClientToServer()
	{
		setUpNetworking();
	}
	//--- Set active user and start message request thread---//
	public void setActiveUser(String number)
	{
		try
		{
			this.number = number; 
			if(Debugger.testingMode()) {System.out.println(number+" is active");}
			socketRegistrationRequest();			
			Thread requestThread = new Thread(new Runnable()
			{
				public void run()
				{
					if(Debugger.testingMode())	{System.out.println("ClientToServer: Message request thread running...");}					
					try
					{
						
						while(true)
						{							
							requestMessageFromServer();	
							Thread.sleep(2000);	
							
						}
					}
					catch(Exception x)
					{
						if(Debugger.testingMode())	
						{
							System.out.println("ClientToServer: Exception caught in request thread");
							x.printStackTrace();
						}
						FrameBorder.getInstance().setStatus("problem in fetching message from server",4000);
						
					}
				}
			});
			requestThread.start();			
		}
		catch(Exception e)
		{
			this.number = number;
			if(Debugger.testingMode())	
			{
				System.out.println("ClientToServer: Exception caught in request thread");
				e.printStackTrace();
			}
			
		}
	}
	
	
	//--- creating client socket and outputStream---//
	public void setUpNetworking()
	{
			try
			{
				Thread k = new Thread(new Runnable()
				{
					private ObjectOutputStream objectOutputStream;

					public void run()
					{
						try
						{
							if(Debugger.testingMode())	{System.out.println("ClientToServer: OutputStream thread started.");}
							
							while(true)
							{
								Socket clientSocket = new Socket("localhost",5000);	
								objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
								bos = new BufferedOutputStream(clientSocket.getOutputStream());
								oos=objectOutputStream;		
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
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while setting up thread");
					e.printStackTrace();
				}
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
	
	//---ClientHandler sub class run thread and creating input stream---//
	public class ClientHandler implements Runnable 
	{
		
		Socket sock;
		private BufferedInputStream bis;
		private ObjectInputStream ois;
		
	
		public ClientHandler(Socket clientSocket) 
		{			
			try 
			{
				sock = clientSocket;
				ois = new ObjectInputStream(sock.getInputStream());				
				inputArray= (ArrayList)  ois.readObject();
				if(Debugger.testingMode())	{System.out.println("ClientToServer: InputStream is ready.");}
				
			} 
			catch(Exception ex) 
			{
				
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught in InputStream thread.");
					ex.printStackTrace();
				}
				FrameBorder.getInstance().setStatus("problem in fetching message from server",4000);
			}
		}
		
		//---Run method inside client on thread---//
		public void run() 
		{
			if(Debugger.testingMode())	{System.out.println("ClientToServer: Incoming reader started.");}			
			try 
			{			
				ArrayList tempArray=inputArray;				
				while(true)
				{	
					if(Debugger.testingMode())	{System.out.println("ClientToServer: Response from server "+tempArray);}
					
					MessageTypeSelector.getInstance().checkMessageType(number, tempArray);
					tempArray= (ArrayList) ois.readObject();
				} 
			}
			catch(Exception ex) 
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught in incoming thread");
					ex.printStackTrace();
				}
				FrameBorder.getInstance().setStatus("problem in incoming thread",2000);
			}
		}
	}
	
		//------Register On Server---//
		public void registerOnServer(String number,String name,String email,String about) 
		{
			ArrayList requestArray= new ArrayList();
			requestArray.add("0");
			requestArray.add(number);
			requestArray.add(name);
			requestArray.add(email);
			requestArray.add(about);		
			try
			{
				if(Debugger.testingMode())	{System.out.println("ClientToServer: Sending resgistration request...");}
							
				oos.writeObject(requestArray);
				oos.flush();
			}
			catch(Exception e)
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while registration");
					e.printStackTrace();
				}
				FrameBorder.getInstance().setStatus("User registration failed",4000);
			}
		}
		
		//---Socket Registration Request---//
		private void socketRegistrationRequest()
		{
			ArrayList requestArray = new ArrayList();
			requestArray.add("1");
			requestArray.add(number);		
			try
			{			
				
				if(Debugger.testingMode())	{System.out.println("ClientToServer: Sending socket registration request... ");}
				oos.writeObject(requestArray);
				oos.flush();
			}
			catch(Exception e)
			{				
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while socket registration ");
					e.printStackTrace();
				}
				FrameBorder.getInstance().setStatus("Socket registration failed",3000);
			}
			
		}
		
		//------Send Message to Server---//
		public void messageClientToServer(String destination,Object message,String fileName,String extension)
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
			LocalDateTime now = LocalDateTime.now();
			ArrayList requestArray= new ArrayList();
			requestArray.add("2");
			requestArray.add(number);
			requestArray.add(destination);
			requestArray.add(message);
			requestArray.add(dtf.format(now));
			requestArray.add(fileName);
			requestArray.add(extension);
			
			if(Debugger.testingMode())	{System.out.println("ClientToServer: Message Queue "+requestArray);}			
			try
			{		
				if(Debugger.testingMode())	{System.out.println("ClientToServer: Sending message to server...");}
				
				oos.writeObject(requestArray);
				oos.flush();	
				requestArray.remove(0);				
				mqf.addMessage(number,destination,requestArray);
				
			}
			catch(Exception e)
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while sending message ");
					e.printStackTrace();
				}
				FrameBorder.getInstance().setStatus("Message sending failed.",4000);
			}
		}
		
		//------Request Message from Server---//
		public void requestMessageFromServer() 
		{
			ArrayList requestArray= new ArrayList();
			requestArray.add("3");
			requestArray.add(number);		
			try
			{		
				//System.out.println("ClientSocket: Requesting message from server...");
				oos.writeObject(requestArray);
				oos.flush();
			}
			catch(Exception e)
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while requesting message ");
				}
				FrameBorder.getInstance().setStatus("Message request failed.",4000);
			}
		}
		
		//---Update profile picture on server---//
		public void updateProfilePicture(String number,byte[] file)
		{				
			if(Debugger.testingMode()) {System.out.println("File: "+file);}
			
			ArrayList requestArray= new ArrayList();
			requestArray.add("4");
			requestArray.add(number);	
			requestArray.add(file);
			try
			{					
				if(Debugger.testingMode())	{System.out.println("ClientToServer: Updating profile picture on server...");}
							
				oos.writeObject(requestArray);				
				oos.flush();
			}
			catch(Exception e)
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while updating profile picture ");
				}
				FrameBorder.getInstance().setStatus("Problem in updating DP.",4000);
			}
			
		}
		
		//--- Request profile picture from server---//
		public void requestProfilePicture(String user)
		{
			ArrayList requestArray= new ArrayList();
			requestArray.add("5");
			requestArray.add(number);		
			requestArray.add(user);
			try
			{		
				if(Debugger.testingMode())	{System.out.println("ClientToServer: Requesting profile picture from server... "+requestArray);}
				
				oos.writeObject(requestArray);
				oos.flush();
			}
			catch(Exception e)
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while requesting profile picture ");
				}
				FrameBorder.getInstance().setStatus("problem in fetching DP from server",4000);
			}
			
		}
		
		//---Update about on server---//
		public void updateAbout(String number,String about)
		{
			ArrayList requestArray= new ArrayList();
			requestArray.add("6");
			requestArray.add(number);	
			requestArray.add(about);
			try
			{		
				if(Debugger.testingMode())	{System.out.println("ClientToServer: Updating about on server... "+requestArray);}
				
				oos.writeObject(requestArray);
				oos.flush();
			}
			catch(Exception e)
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while updating about ");
					e.printStackTrace();
				}
				FrameBorder.getInstance().setStatus("problem while updating about.",4000);
			}
		}
		
		//Request about from server
		public void requestAbout(String contact)
		{
			ArrayList requestArray= new ArrayList();
			requestArray.add("7");
			requestArray.add(number);	
			requestArray.add(contact);
			try
			{		
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Reuesting about from server... "+requestArray);
				}
				
				oos.writeObject(requestArray);
				oos.flush();
			}
			catch(Exception e)
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while requesting about...");
					e.printStackTrace();
				}
				FrameBorder.getInstance().setStatus("problem while fetching about from server",4000);
			}
		}
		
		//Request Online Status
		public void checkOnlineStatus(String contact)
		{
			ArrayList requestArray= new ArrayList();
			requestArray.add("8");
			requestArray.add(number);	
			requestArray.add(contact);
			try
			{		
				if(Debugger.testingMode())	{System.out.println("ClientToServer: Reuesting about from server... "+requestArray);}				
				oos.writeObject(requestArray);
				oos.flush();
			}
			catch(Exception e)
			{
				if(Debugger.testingMode())	
				{
					System.out.println("ClientToServer: Exception caught while requesting online status...");
					e.printStackTrace();
				}
				FrameBorder.getInstance().setStatus("problem while checking online status from server",4000);
			}
		}
	
}
