package Client;

import Server.ThreadReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class remAccClient {

    final static String IPADDRESS = "129.21.68.69";
    final static int PORTNUM = 5439;

    public static void main(String [] args)throws IOException{
        Socket socket = new Socket(IPADDRESS,PORTNUM);
        System.err.println("Connection Established with Server on IP:"+IPADDRESS+" Port:" +PORTNUM);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner userinp = new Scanner(System.in);
        Thread reader = new ThreadReader(input);
        reader.start();

        boolean running = true;
        while(running){
            String line = userinp.nextLine();
            writer.println(line);
            writer.flush();
            if(line.equals("QUIT")){
                running = false;
                System.err.println("Closing Connection to Server.");
            }
        }
        reader.stop();
        input.close();
        writer.close();
        socket.close();
        System.err.println("Connection with IP:" + IPADDRESS + " closed Successfully.");
    }
}
