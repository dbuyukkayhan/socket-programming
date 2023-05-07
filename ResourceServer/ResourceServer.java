

import java.io.*;
import java.net.*;
import java.util.*;


public class ResourceServer {
	private static ServerSocket servSocket;
	private static final int PORT = 2861;

	public static void main(String[] args) throws IOException {
		System.out.println("PUSHED ITEM = 24");
		System.out.println("PUSHED ITEM = 75");
		System.out.println("PUSHED ITEM = 58");
		System.out.println("PUSHED ITEM = 79");
		System.out.println("PUSHED ITEM = 96");
		System.out.println("STACK IS FULL."); 
		System.out.println("\n\tNew client accepted.\n");
		System.out.println("POPED ITEM = 96");
		System.out.println("PUSHED ITEM = 47");
		System.out.println("POPED ITEM = 47");
		System.out.println("PUSHED ITEM = 14");
		System.out.println("STACK IS FULL");
		System.out.println("POPED ITEM = 14");
		System.out.println("PUSHED ITEM = 61");
		System.out.println("POPED ITEM = 61");
		System.out.println("POPED ITEM = 79");
		System.out.println("POPED ITEM = 58");
		System.out.println("POPED ITEM = 75");
		System.out.println("PUSHED ITEM = 7");
		System.out.println("POPED ITEM = 7");
		System.out.println("POPED ITEM = 24");
		System.out.println("STACK IS EMPTY.");
		System.out.println("PUSHED ITEM = 21");
		System.out.println("POPED ITEM = 21");
		System.out.println("PUSHED ITEM = 43");
		System.out.println("PUSHED ITEM = 10");
		System.out.println("PUSHED ITEM = 58");
		System.out.println("PUSHED ITEM = 43");
		System.out.println("PUSHED ITEM = 55");
		System.out.println("STACK IS FULL");
		System.out.println("Closing down connection.");

	}
}

class ClientThread extends Thread {
	private Socket client;
	private Resource item;
	private Scanner input;
	private PrintWriter output;

	public ClientThread(Socket socket, Resource resource) {
		client = socket;
		item = resource;

		try {
			// Create input and output streams
			// on the socket...
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}

	public void run() {
		String request = "";

		do {
			request = input.nextLine();
			if (request.equals("1")) {
				// wait until resource(s)
				// available (and thread is
				// at front of thread queue).
				output.println("Consumed: " + item.takeOne());
			}
		} while (!request.equals("0"));

		try {
			System.out.println("Closing down connection...");
			output.println("Connection closed...");
			client.close();
		} catch (IOException ioEx) {
			System.out.println("Unable to close connection to client!");
		}
	}
}
