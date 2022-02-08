package fileTypeSelector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UnknownFile 
{
	
	
	private JPanel panel;

	public JPanel getPanel()
	{
		return panel;
	}
	public UnknownFile(String fileName,Color panelColor) 
	{
		panel = new JPanel();
		panel.setBackground(panelColor);
		panel.setBounds(0,0,150,60);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UnknownFile.class.getResource("/img/unknown.png")));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(fileName);
		lblNewLabel_1.setPreferredSize(new Dimension(93, 10));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Mp3File.class.getResource("/img/download.png")));
		panel.add(lblNewLabel_2);
	}
}


