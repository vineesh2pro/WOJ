package notificationHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;



public class NotificationPuller 
{
	private Socket sock;
	private BufferedReader br;
	private PrintWriter pr;
	
	public NotificationPuller()
	{
		setUpNetworking();
		setUpThread();
		registerNotificationSocket();
		
	}
	public void setUpNetworking()
	{
		try
		{
			sock = new Socket("127.0.0.1",5000);
			InputStreamReader ipr = new InputStreamReader(sock.getInputStream());
			br = new BufferedReader(ipr);			
			pr = new PrintWriter(sock.getOutputStream());			
			JOptionPane.showMessageDialog(null, "Connection Established");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Connection Failed. Cannot \n connect to Server");
		}
	}
	public void setUpThread()
	{
		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();
	}
	public class IncomingReader implements Runnable 
	{
		public void run() 
		{
			String message;
			try 
			{
				System.out.println("Setting up Incoming Reader");
				while((message=br.readLine())!= null) 
				{
					System.out.println(message);
					String splitedMSG[]=message.split("~");
					new NotificationPopUp(splitedMSG[0],splitedMSG[1]);
				}
			} 
			catch(Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "Unable to pull message notification");
				ex.printStackTrace();
			}
		}
	}
	private void registerNotificationSocket()
	{
		String requestMessage = "1"+"~"+"0";
		try
		{			
			pr.println(requestMessage);
			pr.flush();			
		}
		catch(Exception e)
		{
			
		}
	}
	public static void main(String[] args) 
	{
		new NotificationPuller();		
	}
}
