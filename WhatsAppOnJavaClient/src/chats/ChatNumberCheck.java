package chats;

import contacts.ContactDetails;
import contacts.ContactFunctions;
import debugger.Debugger;
import gui.FrameBorder;

public class ChatNumberCheck 
{
	ContactFunctions cf;
	public ChatNumberCheck(ContactFunctions cf)
	{
		this.cf=cf;
	}
	public String chatNumberCheck(String number)
	{
		if(Debugger.testingMode())	{System.out.println("ChatNumberCheck: Checking numbers in contact list");}		
		for(ContactDetails cd:cf.getAllContacts())
		{
			if(cd.getNumber().equals(number))
			{
				return cd.getName();
			}
			
		}
		return number;
		
	}

}
