package speechBubble;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

public class BubbleFormatter 
{
	 
	
	JPanel panel;
	private JLabel message;
	private JLabel time;
	
	public JPanel formatMessage(String msg,String time,Color backgroundColor,Color textColor)
	{
		if(msg.length()>=28)
		{
			message.setText("<html><p style = \"width : 150px\">"+msg+"&nbsp &nbsp</p></html>");
		}
		else
		{
			message.setText(msg);
		}	
		this.time.setText(time);
		panel.setBackground(backgroundColor);
		message.setForeground(textColor);
		this.time.setForeground(textColor);
		return panel;
	}
	public BubbleFormatter()
	{
		panel= new JPanel();
		//panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0,0,197,41);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		message = new JLabel("Message");
		message.setFont(new Font("Calibri Light", Font.BOLD, 14));
		message.setForeground(Color.BLACK);
		message.setHorizontalAlignment(SwingConstants.TRAILING);
		message.setBounds(0, 0, 197, 25);
		panel.add(message);
		
		time = new JLabel("time");
		time.setForeground(Color.BLACK);
		time.setFont(new Font("Tahoma", Font.PLAIN, 10));
		time.setHorizontalAlignment(SwingConstants.TRAILING);
		time.setBounds(0, 23, 197, 14);
		panel.add(time);		
	}
	
}
