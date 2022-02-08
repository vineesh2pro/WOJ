package serverUser;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class ServerUserLoader 
{
	private ServerUserFunctions uf;
	public ServerUserLoader(ServerUserFunctions uf)
	{
		this.uf=uf;
	}
	public void addDummyUsers()
	{
		uf.addUser("9839107972","Vineesh","vineesh2pro@yahoo.co.in","I am Sexy");
		uf.addUser("9839107973","Sam","sam@yahoo.co.in","I am Intelligent");
		uf.addUser("9839107974","Aman","aman@yahoo.co.in","I am Single");
		uf.addUser("9839107975","Nikhil","nikhil@yahoo.co.in","I am Frustated");
		uf.addUser("9839107976","Kheti","kheti@yahoo.co.in","I am Gobar");
	}
	
	public void displayUsers()
	{
		System.out.println("\n\n Displaying serverUserList\n");
		for(HashMap.Entry<String,ServerUserDetails> entry : uf.getAllUsers().entrySet())
		{
			ServerUserDetails ud =entry.getValue();
			System.out.println(ud.getNumber()+"\t"+ud.getName()+"\t\t"+ud.getEmail()+"\t\t"+ud.getAbout());
		}
	}	
}