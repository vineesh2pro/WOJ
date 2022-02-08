package clientUser;

import debugger.Debugger;
import gui.FrameBorder;

public class UserAuthentication 
{
	ClientUserFunctions uf;
	
	private static UserAuthentication singleInstance;	
	public static UserAuthentication getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new UserAuthentication();
		}
		return singleInstance;
	}
	public void setUserFunctionReference(ClientUserFunctions uf)
	{
		this.uf = uf;		
	}
	public String authenticateUser(String number)
	{ 
		if(Debugger.testingMode())	{System.out.println("UserAuthentication: Inside authenticateUser function...");}
		
		for(ClientUserDetails ud: uf.getAllUsers())
		{			
			if(number.equals(ud.getNumber()))
			{
				return ud.getName();
			}				
		}
	return null;
	}
	
	public ClientUserDetails checkLoginInfo(String number)
	{ 
		if(Debugger.testingMode())	{System.out.println("UserAuthentication: Inside checkLoginInfo function...");}
		
		for(ClientUserDetails ud: uf.getAllUsers())
		{
			if(number.equals(ud.getNumber()))
			{
				return ud;
			}				
		}
	return null;
	}
}
