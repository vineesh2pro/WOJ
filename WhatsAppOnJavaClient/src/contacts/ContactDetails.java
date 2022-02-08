package contacts;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import utility.FileManager;

public class ContactDetails implements Serializable
{
	private String name;
	private String number;
	private String about;
	private byte[] imageFile;
	
	
	public ContactDetails(String name,String number)
	{
		setName(name);
		setNumber(number);
		setAbout("Hey! there I m using whatsapp");
	}
	public ContactDetails(String number,String name,String about,byte[] imageFile)
	{
		setNumber(number);
		setName(name);
		setAbout(about);
		setImage(imageFile);
	}
	private void setName(String name)
	{
		this.name=name;
	}
	private void setNumber(String number)
	{
		this.number=number;
	}
	public void setAbout(String about)
	{
		this.about=about;
	}
	public String getName()
	{
		return name;
	}
	public String getNumber()
	{
		return number;
	}
	public String getAbout()
	{
		return about;
	}
	public BufferedImage getImage() 
	{
		return FileManager.byteToImage(imageFile);	
	}
	public void setImage(byte[] imageFile) 
	{
		this.imageFile = imageFile;
	}

}
