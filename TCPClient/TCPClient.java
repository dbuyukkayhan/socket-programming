import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient {

	private static InetAddress host;
	private static final int PORT = 2861;

	public static void main(String[] args) {
		try {
			// host = InetAddress.getByName("ktuceng-pc");
			host = InetAddress.getLocalHost();
			// byte[] b=new byte[]{(byte)127, (byte)0, (byte)0, (byte)1};
			// host=Inet4Address.getByAddress(b);
			System.out.println("Host name: " + host.getHostName());
			System.out.println("Class :" + host.getClass());
			System.out.println("Host IP Address: " + host.getHostAddress());
			System.out.println("\nSunucuya baglandi.");
		} catch (UnknownHostException uhEx) {
			System.out.println("Host ID degeri bulunamadi!");
			System.exit(1);
		}
		accessServer();
	}

	private static void accessServer() {
		Socket link = null;

		try {
			link = new Socket(host, PORT);
			Scanner input = new Scanner(link.getInputStream());
			PrintWriter output = new PrintWriter(link.getOutputStream(), true);
			Scanner userEntry = new Scanner(System.in);
			String message, response;
			do {
				System.out.print("Enter message : ");
				message = userEntry.nextLine();
				output.println(message);
			} while (!message.equals("BYE BYE"));

			response = input.nextLine();
			System.out.println("\nSERVER > " + response);
		}

		catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			try {
				System.out.println("* Baglanti sonlandiriliyor... *");
				link.close();
			} catch (IOException ioEx) {
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}