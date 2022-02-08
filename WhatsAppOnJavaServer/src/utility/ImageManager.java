package utility;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


 

public class ImageManager 
{    
       
	//Circular crop method
    public static BufferedImage circleCroper(BufferedImage inputImage,Integer w,Integer height)
    {    	
    	Image newImage = inputImage.getScaledInstance(w, height, Image.SCALE_DEFAULT);
    	int width = newImage.getWidth(null);
    	BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);    	
    	Graphics2D g2 = circleBuffer.createGraphics();
    	g2.setClip(new Ellipse2D.Float(0, 0, width, width));
    	g2.drawImage(newImage, 0, 0, width, width, null);    	
    	return circleBuffer;
    }
    // Resize image method
    public static Image resize(BufferedImage inputImage,Integer width,Integer height) 
    {    
    	try
    	{
    		Image newImage = inputImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        	return newImage;
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return null;
    	
    }
    
    // Choose image from disk---//
    public static BufferedImage chooseImage()
    {
    	JFileChooser jf= new JFileChooser();
		jf.showOpenDialog(null);
		File file = jf.getSelectedFile();
		try 
		{		
			return ImageIO.read(file);	
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		return null;
    }
    
    
}