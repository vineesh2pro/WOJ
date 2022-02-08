package clientUser;


import java.io.File;
import java.io.Serializable;

import debugger.Debugger;
import gui.FrameBorder;

@SuppressWarnings("serial")
public class ClientUserDetails implements Serializable
{
	String number;
	String name;
	String email;
	String about;
	byte[] image;	
	

	public ClientUserDetails(String number,String name,String email,String about,byte[] image)
	{
		setNumber(number);
		setName(name);
		setEmail(email);
		setAbout(about);
		setImage(image);
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public byte[] getImage() 
	{
		return image;
	}

	public void setImage(byte[] image) 
	{		
		this.image = image;
		if(Debugger.testingMode())	{System.out.println("ClientUserDetails: Image saved");}
		
	}
}
