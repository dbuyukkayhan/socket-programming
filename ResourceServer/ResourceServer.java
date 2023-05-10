import java.io.*;
import java.net.*;
import java.util.*;

public class ResourceServer
{
	private static ServerSocket servSocket;
	private static final int PORT = 2861;
	
	public static void main(String[] args)	throws IOException
	{
		try
		{
			servSocket = new ServerSocket(PORT);
		}
		catch (IOException e)
		{
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}
		
		//Create a Resource object with
		//a starting resource level of 1...
		Resource item = new Resource(0);
		//Create a Producer thread, passing a reference
		//to the Resource object as an argument to the
		//thread constructor...
		Producer producer = new Producer(item);
		//Start the Producer thread running...
		producer.start();
		
		do
		{
			//Wait for a client to make connection...
			Socket client = servSocket.accept();
			System.out.println("\n New client accepted.\n");
			//Create a ClientThread thread to handle all
			//subsequent dialogue with the client, passing
			//references to both the client's socket and
			//the Resource object...
			ClientThread handler = new ClientThread(client,item);
			//Start the ClientThread thread running...
			handler.start();
		}
		while (true); //Server will run indefinitely.
	}
}


class ClientThread extends Thread
{
	private Socket client;
	private Resource item;
	private Scanner input;
	private PrintWriter output;
	
	public ClientThread(Socket socket, Resource resource)
	{
		client = socket;
		item = resource;
		
		try
		{
			//Create input and output streams
			//on the socket...
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(client.getOutputStream(),true);
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
	}
	public void run()
	{
		String request = "";
		
		do
		{
			request = input.nextLine();
			if (request.equals("1"))
			{
			        //wait until resource(s)
				//available (and thread is
				//at front of thread queue).
				output.println("Consumed: " + item.takeOne());
			}
		}
		while (!request.equals("0"));
		
		try
		{
			System.out.println("Closing down connection...");
			output.println("Connection closed...");
			client.close();
		}
		catch(IOException ioEx)
		{
			System.out.println("Unable to close connection to client!");
		}
	}
}
