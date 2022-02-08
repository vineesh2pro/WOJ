package fileTypeSelector;

import java.awt.Color;

import javax.swing.JPanel;

import debugger.Debugger;
import gui.FrameBorder;

public class Selector 
{
	
	public JPanel fileTypeSelector(String fileName,String extension,Color panelColor)
	{
		if(Debugger.testingMode())	{System.out.println("Selector: Selecting file type...");}
		
		if(extension.equals("pdf"))
		{
			PdfFile pdf= new PdfFile(fileName,panelColor);
			return pdf.getPanel();
		}		
		if(extension.equals("txt"))
		{
			TxtFile txt= new TxtFile(fileName,panelColor);
			return txt.getPanel();
		}	
		if(extension.equals("doc") || extension.equals("docx"))
		{
			DocFile doc= new DocFile(fileName,panelColor);
			return doc.getPanel();
		}
		if(extension.equals("ppt") || extension.equals("pptx"))
		{
			PptFile ppt= new PptFile(fileName,panelColor);
			return ppt.getPanel();
		}
		if(extension.equals("xls") || extension.equals("xlsx"))
		{
			XlsFile xls= new XlsFile(fileName,panelColor);
			return xls.getPanel();
		}
		if(extension.equals("mp4") || extension.equals("mkv"))
		{
			Mp4File mp4= new Mp4File(fileName,panelColor);
			return mp4.getPanel();
		}
		if(extension.equals("mp3") )
		{
			Mp3File mp3= new Mp3File(fileName,panelColor);
			return mp3.getPanel();
		}	
		if(extension.equals("exe"))
		{
			ExeFile exe= new ExeFile(fileName,panelColor);
			return exe.getPanel();
		}
		if(extension.equals("zip") || extension.equals("rar"))
		{
			ZipFile zip= new ZipFile(fileName,panelColor);
			return zip.getPanel();
		}
		if(extension.equals("xml"))
		{
			XmlFile xml= new XmlFile(fileName,panelColor);
			return xml.getPanel();
		}
		else
		{
			UnknownFile unknown= new UnknownFile(fileName,panelColor);
			return unknown.getPanel();
		}
		
	}

}
