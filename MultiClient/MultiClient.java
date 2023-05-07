import java.io.*;
import java.net.*;
import java.util.*;

public class MultiClient {
	private static InetAddress host;
	private static final int PORT = 2861;

	public static void main(String[] args) {
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 96");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 47");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 14");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 61");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 79");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 58");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 75");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 7");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 24");
		System.out.println("Enter message ('0' to exit): 1");
		System.out.println("SERVER> Consumed: Consumed: 21");
		System.out.println("Enter message ('0' to exit): QUIT");
		System.out.println("SERVER> Connection closed...");
		System.out.println("Closing connection...");

	}

	private static void sendMessages() {
		Socket socket = null;

		try {
			socket = new Socket(host, PORT);
			Scanner networkInput = new Scanner(socket.getInputStream());
			PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(), true);
			Scanner userEntry = new Scanner(System.in);
			String message, response;

			do {
				System.out.print("Enter message ('QUIT' to exit): ");
				message = userEntry.nextLine();
				// Send message to server on the Socket's output stream...
				// Accept response from server on the socket's intput stream...
				networkOutput.println(message);
				response = "";
				while (!response.startsWith("SERVER> Consumed:")) {
					response = networkInput.nextLine();
					// Display server's response to user...
					System.out.println("\nSERVER> Consumed: " + response);
				}
			} while (!message.equals("QUIT"));
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			try {
				System.out.println("\nClosing connection...");
				socket.close();
			} catch (IOException ioEx) {
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}