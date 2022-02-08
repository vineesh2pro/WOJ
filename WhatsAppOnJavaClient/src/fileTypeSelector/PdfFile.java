package fileTypeSelector;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

import java.awt.FlowLayout;


public class PdfFile 
{
private JPanel panel;
	
	public JPanel getPanel()
	{
		return panel;
	}
	public PdfFile(String fileName,Color panelColor) 
	{
		panel = new JPanel();
		panel.setBackground(panelColor);
		panel.setBounds(0,0,150,60);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PdfFile.class.getResource("/img/pdf.png")));
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
