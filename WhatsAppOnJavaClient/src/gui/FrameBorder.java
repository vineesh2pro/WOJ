package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import debugger.Debugger;
import theme.Theme;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameBorder 
{
	private JPanel mainPanel;
	private JPanel notificationPanel;
	
	//---Singleton Pattern---//	
	private static FrameBorder singleInstance;		
	private JLabel status;
	public static FrameBorder getInstance()
	{
		if(singleInstance==null)
		{
			singleInstance= new FrameBorder();
		}
		return singleInstance;
	}
	public JPanel getFrameBorder() 
	{
		return mainPanel;
	}
	public FrameBorder() 
	{
		mainPanel = new JPanel();
		mainPanel.setBounds(0,0,340,20);
		mainPanel.setBackground(new Color(0, 115, 97));
		mainPanel.setVisible(true);
		mainPanel.setLayout(null);
		
		JLabel close = new JLabel("X");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				MainFrame.getInstance().disposeFrame();
			}
		});
		close.setFont(new Font("Tahoma", Font.BOLD, 15));
		close.setForeground(Color.RED);
		close.setBounds(314, 0, 20, 20);
		//mainPanel.add(close);
		
		JLabel minimize = new JLabel("-");
		minimize.setForeground(Color.WHITE);
		minimize.setFont(new Font("Tahoma", Font.BOLD, 15));
		minimize.setBounds(295, 0, 20, 20);
		//mainPanel.add(minimize);
		
		notificationPanel = new JPanel();
		notificationPanel.setBounds(0, 0, 285, 20);
		notificationPanel.setBackground(new Color(0, 115, 97));
		mainPanel.add(notificationPanel);
		notificationPanel.setLayout(null);
		
		status = new JLabel("Active");
		status.setFont(new Font("Tahoma", Font.BOLD, 13));
		status.setForeground(Color.WHITE);
		status.setBounds(0, 0, 285, 20);
		notificationPanel.add(status);
	}
	public void setStatus(String s)
	{
		status.setText(s);
	}
	public void setStatus(String s,int time)
	{
		status.setText(s);
		try 
		{
			Thread.sleep(time);
			status.setText("Active");
		} 
		catch (InterruptedException e) 
		{
			if(Debugger.testingMode())	{System.out.println("FrameBorder: Exception in thread");}
    		FrameBorder.getInstance().setStatus("Exception in frame border",2000);
		}
	}
	public void setTheme()
	{
		mainPanel.setBackground(Theme.getFrameBorder());
		notificationPanel.setBackground(Theme.getFrameBorder());
	}
}
