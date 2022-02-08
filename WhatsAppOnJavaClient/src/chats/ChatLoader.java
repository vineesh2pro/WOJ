package chats;

import debugger.Debugger;
import gui.ChatContainer;
import gui.FrameBorder;

public class ChatLoader
{
	ChatFunctions cf;
	ChatNumberCheck cnc;
	
	public ChatLoader(ChatFunctions cf,ChatNumberCheck cnc)
	{
		this.cf = cf;
		this.cnc=cnc;
	}
	
	public void displayChats()
	{
		for(ChatDetails cd: cf.getAllChats())
		{
			if(Debugger.testingMode())
				{
				System.out.println(cd.getNumber()+"\t"+cd.getChat()+"\t\t"+cd.getTime());
				System.out.println(cnc.chatNumberCheck(cd.getNumber())+"\t"+cd.getChat()+"\t\t"+cd.getTime());
				}
			
		}
	}
	
	public void requestAllChatsOnGUI()
	{
		if(Debugger.testingMode())	{System.out.println("ChatLoader: Loading chats on GUI...");}
		
		cf.sortChatList();
		ChatContainer.getInstance().resetChatContainer();
		for(ChatDetails cd: cf.getAllChats())
		{
			ChatContainer.getInstance().setChatOnGUI(cnc.chatNumberCheck(cd.getNumber()),cd.getTime(),cd.getChat(),cd.getNumber(),cd.getImage(),cd.getAbout());
			
		}
	}
}
