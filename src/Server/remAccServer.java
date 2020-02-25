package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class remAccServer {
    final static int PORTNUMBER = 5439;

    public static void main(String[] args) throws IOException{
        int threadID = 1;
        ArrayList<Thread> clients = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(PORTNUMBER);
            while(true){
                System.err.println("Listening on "+PORTNUMBER);
                Socket client =  serverSocket.accept();
                System.err.println("Connection #" + threadID + " established with IP:"+client.getInetAddress().getHostAddress());
                Thread thread = new remAccHandler(client, threadID);
                thread.start();
                threadID ++;
                clients.add(thread);
            }
        }catch (IOException ie){
            ie.printStackTrace();
        }
    }
}