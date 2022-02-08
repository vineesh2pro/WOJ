package contacts;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collections;

import javax.swing.JOptionPane;

import chats.ChatDetails;
import debugger.Debugger;
import gui.FrameBorder;
import serializeDeSerialize.SerializeDeSerialize;


public class ContactFunctions implements Serializable
{
	private ArrayList<ContactDetails> contactList;
	private SerializeDeSerialize sds = new SerializeDeSerialize();
	
	public ContactFunctions()
	{
		initiateDeSerialization();	
		if(contactList==null || contactList.isEmpty())
		{
			initializeContactList();
			if(Debugger.testingMode())	{System.out.println("ContactFunctions: Creating new contact list...");}
			
		}	
		
				
	}
	void initializeContactList()
	{
		contactList = new ArrayList<ContactDetails>();
	}
	public void initiateDeSerialization()
	{
		contactList = (ArrayList<ContactDetails>) sds.deSerializeObject(1);
	}
	
	public void initiateSerialization()
	{
		sds.serializeObject(contactList,1);
	}
	
	public void addContact(String number,String name,String about,byte[] imageFile)
	{
		if(checkDuplicacy(number))
		{
			if(Debugger.testingMode())	{System.out.println("ContactFunctions: This contact already exist...");}
			JOptionPane.showMessageDialog(null, "Contact already exist","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			if(Debugger.testingMode())	{System.out.println("ContactFunctions: Adding new contact...");}
			
			contactList.add(new ContactDetails(number,name,about,imageFile));
			updateContactList();
		}
		
	}
	
	public void deleteContact(String number)
	{
		for(ContactDetails cd:contactList)
		{
			if(cd.getNumber().equals(number))
			{
				contactList.remove(cd);
				updateContactList();
			}
		}
	}
	public ArrayList<ContactDetails> getAllContacts()
	{
		return contactList;
	}
	public void updateContactList()
	{
		if(Debugger.testingMode())	{System.out.println("ContactFunctions: updating contact list...");}		
		initiateSerialization();
	}	
	public void updateAbout(String number,String about)
	{
		if(Debugger.testingMode())	{System.out.println("ContactFunctions: updating about...");}
		
		for(ContactDetails cd:contactList)
		{
			if(cd.getNumber().equals(number))
			{
				cd.setAbout(about);
				updateContactList();
			}
		}
	}
	public void sortContactList()
	{
		if(Debugger.testingMode())	{System.out.println("ContactFunctions: Sorting contacts...");}				
		Collections.sort(contactList, (o1,o2)->o1.getName().compareTo(o2.getName()));		
	}
	
	public void updateProfilePicture(String number,byte[] image)
	{
		for(ContactDetails cd:contactList)
		{
			if(cd.getNumber().equals(number))
			{
				cd.setImage(image);
				updateContactList();
			}
		}
	}
	public BufferedImage getProfilePicture(String number)
	{
		for(ContactDetails cd:contactList)
		{
			if(cd.getNumber().equals(number))
			{
				return cd.getImage();			
			}
		}
		return null;
	}
	public Boolean checkDuplicacy(String number)
	{	
		for(ContactDetails cd: contactList)
		{
			if(cd.getNumber().equals(number))
			{
				return true;
			}
		}
		return false;		
	}
	
}
