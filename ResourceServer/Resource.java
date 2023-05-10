
import java.util.*;



class Resource
{
	private int numResources;
	private final int MAX = 5;
			
	public Resource(int startLevel)
	{
		numResources = startLevel;
	}
	
	public int getLevel()
	{
		return numResources;
	}
	
	public synchronized void addOne()
	{
		try
		{
			while (numResources >= MAX)	wait();
			
			numResources++;			
			System.out.println("Produced = " + numResources);			
			
			//'Wake up' any waiting consumer...
			notifyAll();
		}
		catch (InterruptedException interruptEx)
		{
			System.out.println(interruptEx);
		}
	}
	
	public synchronized int takeOne()
	{
		
		try
		{			
			while (numResources == 0) wait();
			
			
			
			numResources--;
			System.out.println("Consumed = " + numResources);
			
			//'Wake up' waiting producer...
			notify();
		}
		catch (InterruptedException interruptEx)
		{
			System.out.println(interruptEx);
		}
		return numResources;
	}
}