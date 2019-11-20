import java.io.*;
import java.net.*;
import java.util.Scanner;
/*
 * Ricardo Ramos
 * Steven Lim
 * CS 327
 */
public class Server
{
    public static void main(String[] args)
    {
		Scanner scanner;
		String serverPortString;
		int _serverPort;

		try
		{
			//Check for piped arguments
			if(args.length == 0) {
				System.out.println("Argument for Server Port(int) was null, please enter a number");
		    	scanner = new Scanner(System.in);
		    	serverPortString = scanner.nextLine(); 	
			}
			else {
				serverPortString = args[0]; //getting the server port number from the CL arguments as string
			}
			
			//Parse the input into integer
			_serverPort = Integer.parseInt(serverPortString); //set the server port # from the CL and parse as integer
			
	        //Create an object aSocket of instance class DatagramSocket
	        DatagramSocket aSocket = new DatagramSocket(_serverPort);
	
	        //Creating a byte array called buffer to store the message from the client
	        byte [] buffer = new byte[1000];
	        
	        while(true)
	        {
	            buffer = new byte[1000];    
	            
	            //Create an object called "request" of instance type DatagramPacket
	            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
	            System.out.println("\nHosting at port: " + _serverPort);
	            System.out.println("Waiting for the message from the client...");
	
	            //Use the receive method from the DatagramSocket class to get the request packet
	            aSocket.receive(request);

	            System.out.println("Message from client has been received: ");
	
	            System.out.println("Creating echo response...");
	
	            System.out.println();
	
	            //Create an object reply of instance type DatagramPacket - this is our reply packet
	            DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
	
	            //Using the send method from the DatagramSocket class we send the reply packet back to the client
	            aSocket.send(reply);
	        }
	    }
        catch(SocketException e)
        {
            System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println("IO: " + e.getMessage());
        }
        catch(NumberFormatException e)
        {
        	System.out.println("NumberFormat Exception: " + e.getMessage());
        }
    }
}
