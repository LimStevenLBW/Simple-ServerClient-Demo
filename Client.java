import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String[] args)
    {
        DatagramSocket aSocket = null;
        try
        {
            String hostname = args[0]; //Getting the host name or IP address from the CL arguments
            String serverPortString = args[1]; //Getting the port number from the CL arguments
            int serverPort = Integer.parseInt(serverPortString);

            //Declaring buffer so that we can read input from the user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            //Creating object aSocket of instance type DatagramSocket
            aSocket = new DatagramSocket();

            //Creating object aHost of instance type InetAddress taking in the hostname
            InetAddress aHost = InetAddress.getByName(hostname);
            
            System.out.println("Connecting to " + aHost + " via Port: " + serverPort);

            //Prompting the user to enter a message to send to the server
            System.out.print("Enter a message to send: ");
            String messageToSend = userInput.readLine();

            //Creating an array of bytes from our message which is converted to bytes
            byte [] message  = messageToSend.getBytes();

            System.out.println("Sending message of " + message.length + " bytes to the server.");

            //Creating object request of instance class DatagramPacket to send our data to the server
            DatagramPacket request = new DatagramPacket(message, message.length, aHost, serverPort);

            //Using the send method from the DatagramPacket class to send our message to the server
            aSocket.send(request);

            //Creating an array of bytes called "buffer" to store the response message from the server
            byte [] buffer = new byte[1000];

            //Creating object reply of instance class DatagramPacket to recevie a response from the server
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            System.out.println("Waiting from the echo response message from the server");
            aSocket.setSoTimeout(10000);

            try
            {
                //Using the receive method from the DatagramPacket class to receive our echo message from the server
                aSocket.receive(reply);

                //String variable to hold the echo message from the server
                String echoFromServer = new String(reply.getData());

                //Creating serverName object of instance type InetAddress 
                InetAddress serverName = reply.getAddress();

                //Creating port variable and using the getPort method from the DatagramPacket class to get the port number
                int port = reply.getPort();

                System.out.println("Echo response from the server at " + serverName + " at port: " + port);
                System.out.println("Echo response: " + echoFromServer);
            }catch(SocketTimeoutException e)
            {
                System.out.println("Timeout occurred: Packet lost");
            }

            aSocket.close();

        }catch(UnknownHostException e)
        {
            System.err.println(e);
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
}