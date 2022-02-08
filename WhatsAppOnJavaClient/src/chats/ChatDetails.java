package chats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.imageio.ImageIO;

import utility.FileManager;

public class ChatDetails implements Serializable
{
	private String number;
	private String chat;
	private String time;
	private String about;	
	private byte[] imageFile;
	
	
	public ChatDetails(String number,String chat,String about,byte[] imageFile)
	{
		setNumber(number);
		setChat(chat);
		setAbout(about);
		setImage(imageFile);
	}
	public void setNumber(String number)
	{
		this.number=number;
	}
	public void setChat(String chat)
	{
		this.chat=chat;
		setTime();
	}
	public void setTime()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalDateTime now = LocalDateTime.now();
		time=dtf.format(now);
	}
	
	public String getNumber()
	{
		return number;
	}
	public String getChat()
	{
		return chat;
	}
	public String getTime()
	{
		return time;
	}
	public String getAbout() 
	{
		return about;
	}
	public void setAbout(String about) 
	{
		this.about = about;
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
