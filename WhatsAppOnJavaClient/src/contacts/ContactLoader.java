package contacts;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;

import javax.imageio.ImageIO;

import clientSocket.ClientToServer;
import debugger.Debugger;
import gui.ContactContainer;
import gui.FrameBorder;
import gui.RegistrationPanel;
import utility.FileManager;

public class ContactLoader 
{
	ContactFunctions cf;
	public ContactLoader(ContactFunctions cf)
	{
		this.cf = cf;
	}
	
	//---CTS Object
		ClientToServer cts;
		public void setClientToServerReference(ClientToServer cts)
		{
			this.cts=cts;
		}
	
	
	
	public void displayContacts()
	{
		for(ContactDetails cd: cf.getAllContacts())
		{
			if(Debugger.testingMode())	
			{
				System.out.println("ContactLoader: ");				
				System.out.println(cd.getNumber()+"\t"+cd.getName()+"\t"+cd.getAbout());
			}
		}
	}
	
	public void requestAllContactsOnGUI()
	{
		cf.sortContactList();
		ContactContainer.getInstance().resetContactContainer();
		for(ContactDetails cd: cf.getAllContacts())
		{
			cts.requestProfilePicture(cd.getNumber());	
			cts.requestAbout(cd.getNumber());
			
			ContactContainer.getInstance().setContactsOnGUI(cd.getNumber(),cd.getName(),cd.getAbout(),cd.getImage());
		}
	}
	
	
}
