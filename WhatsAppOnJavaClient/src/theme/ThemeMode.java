package theme;

import java.awt.Color;

public class ThemeMode 
{
	public void setDarkTheme()
	{
		System.out.println("Setting dark theme");
		Theme.setFrameBorder(new Color(39, 52, 67));
		Theme.setIncomingBackground(new Color(33,33,33));
		Theme.setCallsContainerBackground(Color.GRAY);
		Theme.setChatsContainerBackground(Color.GRAY);
		Theme.setContactsContainerBackground(Color.GRAY);
		Theme.setChatTileBackground(new Color(33,33,33));
		Theme.setContactTileBackground(new Color(33,33,33));
		Theme.setIncomingBubble(Color.DARK_GRAY);
		Theme.setIncomingBubbleText(Color.WHITE);
		Theme.setOutgoingBubble(new Color(0, 115, 97));
		Theme.setOutgoingBubbleText(Color.WHITE);
		Theme.setContactDetailsBackground(Color.GRAY);
		Theme.setContactDetailsText(Color.WHITE);
		Theme.setSettingsPanelBackground(Color.GRAY);
		Theme.setSettingsPanelText(Color.WHITE);
		Theme.setBackground(new Color(33,33,33));
		Theme.setHeader(new Color(39, 52, 67));
		Theme.setOutgoingBackground(new Color(39, 52, 67));
		Theme.setOutgoingText(Color.WHITE);
		Theme.setTextColor(Color.WHITE);
	}
	public void setLightTheme()
	{
		System.out.println("Setting light theme");
		Theme.setFrameBorder(new Color(0, 115, 97));
		Theme.setIncomingBackground(new Color(169, 169, 169));
		Theme.setCallsContainerBackground(Color.LIGHT_GRAY);
		Theme.setChatsContainerBackground(Color.LIGHT_GRAY);
		Theme.setContactsContainerBackground(Color.LIGHT_GRAY);
		Theme.setChatTileBackground(Color.WHITE);
		Theme.setContactTileBackground(Color.WHITE);
		Theme.setIncomingBubble(Color.WHITE);
		Theme.setIncomingBubbleText(Color.BLACK);
		Theme.setOutgoingBubble(new Color(220, 255, 198));
		Theme.setOutgoingBubbleText(Color.BLACK);
		Theme.setContactDetailsBackground(Color.WHITE);
		Theme.setContactDetailsText(Color.BLACK);
		Theme.setSettingsPanelBackground(Color.WHITE);
		Theme.setSettingsPanelText(Color.BLACK);
		Theme.setBackground(Color.WHITE);
		Theme.setHeader(new Color(0, 108, 97));
		Theme.setOutgoingBackground(Color.WHITE);
		Theme.setOutgoingText(Color.BLACK);
		Theme.setTextColor(Color.BLACK);
	}

}
