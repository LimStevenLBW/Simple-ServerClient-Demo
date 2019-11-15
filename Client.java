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

            //Creating object request of instance class DatagramPacket to send our data to the server
            DatagramPacket request = new DatagramPacket(message, message.length, aHost, serverPort);

            //Using the send method from the DatagramPacket class to send our message to the server
            aSocket.send(request);

            //Creating an array of bytes called "buffer" to store the response message from the server
            byte [] buffer = new byte[1000];

            //Creating object reply of instance class DatagramPacket to recevie a response from the server
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            System.out.println("Waiting for the echo response message from the server");
            aSocket.setSoTimeout(10000); //Timeout the socket if no response

            try
            {
                //Using the receive method from the Datagramver
                aSocket.receive(reply);

                //Getting the ehco from the server from the reply object using the getData() method
                String echoFromServer = new String(reply.getData());

                //Getting the server address from the reply object using the getAddress() method
                InetAddress serverName = reply.getAddress();

                //Getting the server port number from the reply object using the getPort() method
                int port = reply.getPort();

                //Displaying the echo response from the server
                System.out.println("Echo response from the server at " + serverName + " at port: " + port);
                System.out.println(echoFromServer);

            }catch(SocketTimeoutException e)
            {
                System.out.println("Socket: " + e.getMessage());
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