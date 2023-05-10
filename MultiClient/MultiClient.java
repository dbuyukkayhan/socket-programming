import java.io.*;
import java.net.*;
import java.util.*;

public class MultiClient
{
	private static InetAddress host;
	private static final int PORT = 2861;
	
	public static void main(String[] args)
	{
		try
		{
			//host = InetAddress.getByName("ktuceng-pc"); //  "ServerName" : Server bilgisayarýn yerel aðdaki adý
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("\nHost ID not found!\n");
			System.exit(1);
		}
	
		sendMessages();
	}

	private static void sendMessages()
	{
		Socket socket = null;
		
		try
		{
			socket 						= new Socket(host,PORT);
			Scanner networkInput 		= new Scanner(socket.getInputStream());
			PrintWriter networkOutput 	= new PrintWriter(socket.getOutputStream(),true);
		    Scanner userEntry			= new Scanner(System.in);
			String message, response;
			
			do
			{
				System.out.print("Enter message ('QUIT' to exit): ");
				message=userEntry.nextLine();
				//Send message to server on the Socket's output stream...
				//Accept response from server on the socket's intput stream...
				networkOutput.println(message);
				response = networkInput.nextLine();
				//Display server's response to user...
				System.out.println("\nSERVER> " + response);
			}
			while (!message.equals("QUIT"));
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				System.out.println("\nClosing connection...");
				socket.close();
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}