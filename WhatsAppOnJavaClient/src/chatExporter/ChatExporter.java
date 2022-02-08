package chatExporter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Queue;

import debugger.Debugger;
import gui.FrameBorder;
import userMessageQueue.MessageQueueFunctions;
import utility.FileBrowser;
import utility.FileManager;

public class ChatExporter 
{
	private FileWriter fw;
	private MessageQueueFunctions mqf;
	public void setMessageQueueFunctionsReference(MessageQueueFunctions mqf)
	{
		this.mqf= mqf;
	}
	
	private static ChatExporter singleInstance;
	public static ChatExporter getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance = new ChatExporter();
			return singleInstance;					
		}
		return singleInstance;
	}
	
	public void exportChatsToFile(String destination,String sender)
	{
		try
		{
			if(Debugger.testingMode())	{System.out.println("ChatExporter: Fetching user message queue");}
			
			File file = new File("exportFile.txt");
			fw= new FileWriter(file);
			Queue<ArrayList> myQueue = mqf.pullMessage(destination,sender);
			
			for(ArrayList a: myQueue)
			{	
				if(a.get(5)==null)
				{
					fw.append(a.get(0)+": "+a.get(2)+" "+a.get(3)+"\n");
				}
				else
				{
					fw.append(a.get(0)+": File=["+a.get(4)+"] "+a.get(3)+"\n");
				}
				
			}
			fw.close();
			FileBrowser.saveFile(FileManager.convertToByte(file), "txt");			
			if(Debugger.testingMode())	{System.out.println("ChatExporter: Export Complete");}
			
		}
		catch(Exception e)
		{
			if(Debugger.testingMode())	{System.out.println("ChatExporter: Exception Caught while exporting" + e.getStackTrace());}
			FrameBorder.getInstance().setStatus("File Exporting failed",2000);
		}
		
	}
}
