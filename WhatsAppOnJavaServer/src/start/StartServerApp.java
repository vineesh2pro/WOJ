package start;

import gui.MainFrame;
import mapper.ClientSocketMapper;
import messageQueue.MessageQueueFunctions;
import selector.TypeSelector;
import serverSocket.ChatServerSocket;
import serverUser.ServerUserFunctions;
import serverUser.UserProfilePicture;

public class StartServerApp 
{
	private ServerUserFunctions suf;
	private MessageQueueFunctions mqf;
	private TypeSelector tf;
	private ChatServerSocket css;
	private ClientSocketMapper csm;
	private UserProfilePicture upp;
	
	
	
	private MainFrame mf;
	
	public static void main(String[] args)
	{
		StartServerApp sa = new StartServerApp();
		sa.loadClientSocketMapper();
		sa.loadMessageQueue();
		sa.loadServerUser();
		sa.loadUserProfilePicture();		
		sa.startTypeSelector();
		sa.startChatServerSocket();		
		sa.startGUI();
	}

	private void loadServerUser() 
	{
		suf = new ServerUserFunctions();
		suf.setMessageQueueFunctionReference(mqf);
	}
	private void loadUserProfilePicture()
	{
		upp= new UserProfilePicture();
	}
	private void loadMessageQueue()
	{
		mqf = new MessageQueueFunctions();
		mqf.setServerUserFunctionsReference(suf);
	}
	private void startTypeSelector()
	{
		tf = new TypeSelector();
		tf.setServerUserFunctionsReference(suf);
		tf.setMessageQueueFunctionsReference(mqf);
		tf.setClientSocketMapperReference(csm);	
		tf.setUserProfilePictureReference(upp);
	}
	private void startChatServerSocket() 
	{
		css = new ChatServerSocket();
		css.setTypeSelectorReference(tf);
		css.setMessageQueueReference(mqf);
	}
	private void startGUI()
	{
		mf = MainFrame.getInstance();
		mf.setChatServerSocketReference(css);
		
	}
	private void loadClientSocketMapper()
	{
		csm= new ClientSocketMapper();			
	}
}
