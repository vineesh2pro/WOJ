package serverSocket;

import java.util.Queue;

public interface ClientRegistration 
{
	public void registerClient(WhatsAppClient c);
	public void removeClient(WhatsAppClient o);
	public Queue<String> pushMessage(String number);
}
