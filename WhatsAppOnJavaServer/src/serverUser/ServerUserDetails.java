package serverUser;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class ServerUserDetails implements Serializable
{
	String number;
	String name;
	String email;
	String about;
	//BufferedImage image;
	
	

	public ServerUserDetails(String number,String name,String email,String about)
	{
		setNumber(number);
		setName(name);
		setEmail(email);
		setAbout(about);
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
	/*public BufferedImage getImage() 
	{
		return image;
	}

	public void setImage(BufferedImage image) 
	{
		this.image = image;
	}*/
}
