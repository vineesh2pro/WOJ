package utility;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class FileBrowser 
{
	public static ArrayList browseFile()
	{
		ArrayList fileArray= new ArrayList();
		String extension = "";
		JFileChooser fc= new JFileChooser();
		fc.showOpenDialog(null);
		File file = fc.getSelectedFile();
		
		// fetching file extension
		String fileName=file.getName();			
		int i = fileName.lastIndexOf('.');
		if(i > 0) 
		{
		    extension = fileName.substring(i+1);
		    System.out.println(extension);
		}
		
		fileArray.add(FileManager.convertToByte(file));
		fileArray.add(file.getName());
		fileArray.add(extension);
		return fileArray;
	}
	
	public static void saveFile(Object file,String extension)
	{
		System.out.println("Saving file: "+file+"."+extension);		
		  
		JFileChooser ch = new JFileChooser();
        int c = ch.showSaveDialog(null);
        if (c == JFileChooser.APPROVE_OPTION) 
        {
            try {
                FileOutputStream out = new FileOutputStream(ch.getSelectedFile()+"."+extension);
                out.write((byte[]) file);
                out.close();
            } catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
	}
	
	

}
