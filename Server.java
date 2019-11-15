import java.io.*;
import java.net.*;

public class Server
{
    public static void main(String[]args)
    {
        //DatagramSocket aSocket = null;
        //String serverName = args[0];
        String serverPortString = args[0];
        int serverPort = Integer.parseInt(serverPortString);
        
        try
        {
            DatagramSocket aSocket = new DatagramSocket(serverPort);

            byte [] buffer = new byte[1000];
            
            while(true)
            {
        
                buffer = new byte[1000];
                
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);

                System.out.println("Waiting for the message from the client...");

                aSocket.receive(request);

                String messageFromClient = new String(request.getData());

                InetAddress clientAddress = request.getAddress();

                int clientPort = request.getPort();

                System.out.println("Message from client has been received");
                System.out.println("Client name: " + clientAddress + " at port: " + clientPort);
                System.out.println("Message from client: " + messageFromClient);

                System.out.println("Creating echo response...");

                String echoResponse = new String("Echo reponse from server: We received your message");
                System.out.println();

                byte [] echoReponseAsBytes = echoResponse.getBytes();

                DatagramPacket reply = new DatagramPacket(echoReponseAsBytes, echoReponseAsBytes.length, clientAddress, clientPort);

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