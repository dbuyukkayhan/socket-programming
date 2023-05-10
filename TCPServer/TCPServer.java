import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer
{

	private static ServerSocket servSock;
	private static final int PORT = 2861;
	private static boolean  connection = true;
	
	public static void main(String[] args)
	{
		try
		{
		
			servSock = new ServerSocket(PORT); 
			System.out.println("Waiting for connection...\n");
		}
		catch(IOException ioEx)
		{
			System.out.println("Unable to attach to port!");
			System.exit(1);
		}
		
		do
		{
			deneme();
		}
		while (connection);
	}
	
	private static void deneme()
	{
		Socket link = null; 
		String message = null;
		
		try
		{
			link = servSock.accept();
			
			Scanner input = new Scanner(link.getInputStream());
			PrintWriter output = new PrintWriter(link.getOutputStream(),true);
			int numMessages = 1;
			
			message = input.nextLine();
			while (!message.equals("BYE"))
			{
				System.out.println("Received Message : " + message);
				numMessages++;
				message = input.nextLine();
			}
			
			output.println(numMessages + " messages received.");
		
			
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				System.out.println("Because of Received Message : " + message);
				System.out.println(" * Closing connection... *");
				link.close();
				connection = false;
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}