package serializeDeSerialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import debugger.Debugger;

public class SerializeDeSerialize 
{
	String[] fileName = new String[9];
	{
		fileName[0] = "UserInfo";
		fileName[1] = "ContactList";
		fileName[2] = "ChatList";
		fileName[3] = "ClientUserList";
		fileName[4] = "ChatFile";	
		fileName[5]= "MessageQueue";
	}
	
	public Object deSerializeObject(int num)
	{
		if(Debugger.testingMode()) {System.out.println("Initiating DeSerialization for: "+fileName[num]);}
		Object temp=null;
		try
		{
			FileInputStream fis = new FileInputStream(fileName[num]);
			ObjectInputStream ois =  new ObjectInputStream(fis);
			
			temp = ois.readObject();
			ois.close();
			fis.close();
			if(Debugger.testingMode()) {System.out.println("DeSerialization successful for "+ fileName[num] );}
		}
		catch(Exception ex)
		{
			if(Debugger.testingMode()) {System.out.println("DeSerialization failed for "+ fileName[num] +".No File Found");}
			return temp;
		}
		return temp;
	}
	
	public void serializeObject(Object temp,int num)
	{
		if(Debugger.testingMode()) {System.out.println("Initiating Serialization for: "+fileName[num]);}
		try 
		{
			FileOutputStream fos = new FileOutputStream(fileName[num]);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(temp);
			
			oos.close();
			fos.close();
			
			if(Debugger.testingMode()) {System.out.println("Serialization Done!! "+fileName[num]);}
			
		}
		catch(Exception ex)
		{
			if(Debugger.testingMode())
			{
				ex.printStackTrace();
				System.out.println("Serailization Failed");
			}
		}
	}
}
