package user;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeDeSerialize 
{
	String[] fileName = new String[9];
	{
		fileName[0] = "UserInfo";
		fileName[1] = "ContactList";
		fileName[2] = "ChatList";
		fileName[3] = "UserList";
		
		
	}
	
	public Object deSerializeObject(int num)
	{
		Object temp=null;
		try
		{
			FileInputStream fis = new FileInputStream(fileName[num]);
			ObjectInputStream ois =  new ObjectInputStream(fis);
			
			temp = ois.readObject();
			ois.close();
			fis.close();
			System.out.println("DeSerialization successful for "+ fileName[num] );
		}
		catch(Exception ex)
		{
			System.out.println("DeSerialization failed for "+ fileName[num] +".No File Found");
			return temp;
		}
		return temp;
	}
	
	public void serializeObject(Object temp,int num)
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream(fileName[num]);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(temp);
			
			oos.close();
			fos.close();
			
			System.out.println("Serialization Done!! "+fileName[num]);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Serailization Failed");
		}
	}
}
