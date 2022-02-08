package messageQueue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue implements Serializable
{
	Queue<ArrayList> queue = new LinkedList<ArrayList>();
	
	public void addMessageToQueue(ArrayList msg)
	{
		queue.add(msg);
	}
	
	public void pullMessageFromQueue()
	{
		queue.poll();
	}
	public void printAllMessage()
	{
		System.out.println(queue);
	}
	public Queue<ArrayList> getCompleteQueue()
	{
		return queue;
	}
}
