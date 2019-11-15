import java.io.*;
import java.net.*;

public class Server
{
    public static void main(String[]args)
    {
        String serverPortString = args[0]; //getting the server port number from the CL arguments as string
        int serverPort = Integer.parseInt(serverPortString); //set the server port # from the CL and parse as integer
        
        try
        {
            //Create an object aSocket of instance class DatagramSocket
            DatagramSocket aSocket = new DatagramSocket(serverPort);

            //Creating a byte array called buffer to store the message from the client
            byte [] buffer = new byte[1000];
            
            while(true)
            {

                buffer = new byte[1000];    
                
                //Create an object called "request" of instance type DatagramPacket
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);

                System.out.println("Waiting for the message from the client...");

                //Use the receive method from the DatagramSocket class to get the request packet
                aSocket.receive(request);

                System.out.println("Message from client has been received");

                System.out.println("Creating echo response...");

                System.out.println();

                //Create an object reply of instance type DatagramPacket - this is our reply packet
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());

                //Using the send method from the DatagramSocket class we send the reply packet back to the client
                aSocket.send(reply);
            }
        }catch(SocketException e)
        {
            System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println("IO: " + e.getMessage());
        }

    }
}