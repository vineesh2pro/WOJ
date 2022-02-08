package speechBubble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Queue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import fileTypeSelector.Selector;
import gui.ConversationPanel;
import theme.Theme;
import userMessageQueue.MessageQueueFunctions;
import utility.FileBrowser;
import utility.FileManager;
import utility.ImageManager;

public class SpeechBubbleLoader 
{	
	Color  incomingColor = Color.WHITE;
	Color  outgoingColor = new Color(220, 255, 198);
	
	
	ConversationPanel cp = ConversationPanel.getInstance();
	Box vertical = Box.createVerticalBox();
	private JLabel l1;
	
	// Single Instance ----//
	private static SpeechBubbleLoader singleInstance;
	public static SpeechBubbleLoader getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new SpeechBubbleLoader();
			return singleInstance;
		}
		return singleInstance;
	}
	
	private MessageQueueFunctions mqf;
	private JPanel right;
	private JPanel left;
	public void setMessageQueueReference(MessageQueueFunctions mqf)
	{
		this.mqf= mqf;
	}
	
	public void setChatBubble(ArrayList messageFrame)
	{
		if(messageFrame.get(0).equals(cp.activeUser))
		{
			 JPanel p2 = outgoing(messageFrame.get(2),messageFrame.get(3),messageFrame.get(4),messageFrame.get(5));
			 try
			 {	             
		           	cp.incoming.setLayout(new BorderLayout());	            
		            right = new JPanel(new BorderLayout());		           
		            right.add(p2, BorderLayout.LINE_END);
		            right.setBackground(Theme.getIncomingBackground());
		            vertical.add(right);
		            vertical.add(Box.createVerticalStrut(5));	            
		            cp.incoming.add(vertical, BorderLayout.PAGE_START);
		            cp.incoming.revalidate();
		              
		      }
			 catch(Exception e)
			 {
		            System.out.println(e);
		     }
		}
		else if(messageFrame.get(0).equals(cp.number))
		{
				JPanel p2 = incoming(messageFrame.get(2),messageFrame.get(3),messageFrame.get(4),messageFrame.get(5));
				 
				cp.incoming.setLayout(new BorderLayout());
				
	            left = new JPanel(new BorderLayout());
	            left.setBackground(Theme.getIncomingBackground());
	            left.add(p2, BorderLayout.LINE_START);
	            vertical.add(left);
	            vertical.add(Box.createVerticalStrut(5));
	            cp.incoming.add(vertical, BorderLayout.PAGE_START);	
	            	
	            
		}
		cp.refreshScrollBar();
		
	}
	
	public void loadChats(String activeUser,String number)
	{
		cp.incoming.removeAll();
		cp.incoming.repaint();
		cp.incoming.revalidate();
		vertical = Box.createVerticalBox();
		Queue<ArrayList> messageQueue = mqf.pullMessage(activeUser,cp.number);
		if(messageQueue!=null)
		{
			for(ArrayList al: messageQueue)
			{
				setChatBubble(al);
			}
		}
		
	}	
	public JPanel outgoing(Object msg,Object time,Object fileName,Object extension)
	{
		Color backgroundColor=Theme.getOutgoingBubble();
		Color textColor= Theme.getOutgoingBubbleText();
        JPanel p3 = new OutgoingBubble();       
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));        
        if(extension==null)
        {
        	 
        	
        	p3.add(new BubbleFormatter().formatMessage(msg.toString(), time.toString(),backgroundColor,textColor));
        	p3.setBorder(new EmptyBorder(5,5,5,30));
        }
        else if(extension.equals("png"))
        {
        	l1= new JLabel();
        	l1.setIcon(new ImageIcon(ImageManager.resize(FileManager.byteToImage((byte[]) msg),170,150)));
        	l1.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        	l1.setBorder(new EmptyBorder(2,2,2,20));
        	p3.add(l1);
        	l1.addMouseListener(new MouseAdapter() 
    		{
    			public void mouseClicked(MouseEvent arg0) 
    			{
    				FileBrowser.saveFile(msg,extension.toString());
    			}
    		});
        }  
        else
        {
        	Selector s = new Selector();
        	p3.add(s.fileTypeSelector(fileName.toString(),extension.toString(),outgoingColor));
        	p3.setBorder(new EmptyBorder(2,2,2,20));  
            p3.addMouseListener(new MouseAdapter() 
    		{
    			public void mouseClicked(MouseEvent arg0) 
    			{
    				FileBrowser.saveFile(msg,extension.toString());
    			}
    		});
        }  
        p3.setBackground(Color.LIGHT_GRAY);
        return p3;
    }
	
	public JPanel incoming(Object msg,Object time,Object fileName,Object extension)
	{
		Color backgroundColor=Theme.getIncomingBubble();
		Color textColor= Theme.getIncomingBubbleText();
        JPanel p3 = new IncomingBubble();       
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS)); 
        
        if(extension==null)
        {
        	//Color color= Color.WHITE;
        	
        	p3.add(new BubbleFormatter().formatMessage(msg.toString(), time.toString(),backgroundColor,textColor));
        	p3.setBorder(new EmptyBorder(5,30,5,5));
        }   
        else if(extension.equals("png") || extension.equals("jpeg"))
        {
        	l1= new JLabel();        	
        	l1.setIcon(new ImageIcon(ImageManager.resize(FileManager.byteToImage((byte[]) msg), 170,150)));        	
        	l1.setAlignmentX(JLabel.LEFT_ALIGNMENT); 
        	l1.setBorder(new EmptyBorder(2,20,2,2));
        	p3.add(l1);  
        	l1.addMouseListener(new MouseAdapter() 
    		{
    			public void mouseClicked(MouseEvent arg0) 
    			{
    				FileBrowser.saveFile(msg,extension.toString());
    			}
    		});
        }  
        else
        {
        	Selector s = new Selector();
        	p3.add(s.fileTypeSelector(fileName.toString(),extension.toString(),incomingColor));
        	p3.setBorder(new EmptyBorder(2,20,2,2));  
            p3.addMouseListener(new MouseAdapter() 
    		{
    			public void mouseClicked(MouseEvent arg0) 
    			{
    				FileBrowser.saveFile(msg,extension.toString());
    			}
    		});
        }
        p3.setBackground(Color.LIGHT_GRAY);
        return p3;
    } 
	
	public void setTheme()
	{
		incomingColor= Theme.getIncomingBubble();
		outgoingColor=Theme.getOutgoingBubble();
		
	}

}
