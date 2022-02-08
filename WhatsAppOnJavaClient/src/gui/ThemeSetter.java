package gui;

import speechBubble.SpeechBubbleLoader;

public class ThemeSetter 
{
	public void refreshTheme()
	{
		System.out.println("Refreshing theme...");
		Ribbon.getInstance().setTheme();
		CallsContainer.getInstance().setTheme();
		ChatContainer.getInstance().setTheme();
		ContactContainer.getInstance().setTheme();
		ContactDetailPanel.getInstance().setTheme();
		FrameBorder.getInstance().setTheme();
		
		SpeechBubbleLoader.getInstance().setTheme();
		ConversationPanel.getInstance().setTheme();		
		//SettingsPanel.getInstance().setTheme();
	}

}
