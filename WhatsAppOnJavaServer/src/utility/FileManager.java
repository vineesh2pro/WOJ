package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class FileManager 
{
	//convert method for file
	public static byte[] convertToByte(File file) 
	{
		try 
		{		
				FileInputStream in = new FileInputStream(file);
				byte b[] = new byte[in.available()];
				in.read(b);
				return b;
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
         
	}
	
	//Overloaded convert method for image
	public static byte[] convertToByte(BufferedImage image) 
	{
		try 
		{
			
				File f = new File("new");
				ImageIO.write(image,"png",f);		
				FileInputStream in = new FileInputStream(f);
				byte b[] = new byte[in.available()];
				in.read(b);
				return b;
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
         
	}
	
	public static BufferedImage byteToImage(byte[] file)
	{
		try
		{
			File imageFile= new File("new");
			FileOutputStream fos= new FileOutputStream(imageFile);			
			fos.write(file);	
			//JOptionPane.showMessageDialog(null,ImageIO.read(imageFile));
			return ImageIO.read(imageFile);
		}
		catch(Exception e)
		{
			
		}
		
		return null;
	}
	
	public static File byteToFile(byte[] file)
	{
		try
		{
			File myFile= new File("new");
			FileOutputStream fos= new FileOutputStream(myFile);			
			fos.write(file);	
			//JOptionPane.showMessageDialog(null,ImageIO.read(imageFile));
			return myFile;
		}
		catch(Exception e)
		{
			
		}
		
		return null;
	}

}
