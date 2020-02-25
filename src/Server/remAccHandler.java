package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class remAccHandler extends Thread {
    Socket socket;
    int threadID;
    Scanner scanner;

    remAccHandler(Socket s, int id) {
        socket = s;
        threadID = id;
        scanner = new Scanner(System.in);
    }

    public void run() {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Thread reader = new ThreadReader(input);
            reader.start();

            while (true) {
                String line = scanner.nextLine();
                if(line.equals("QUIT")){
                    break;
                }
                output.println(line);
                output.flush();
            }
            System.err.println("Closing Connection #" + threadID + " with IP:"+ socket.getInetAddress().getHostAddress());
            reader.stop();
            input.close();
            output.close();
            socket.close();

            System.err.println("Disconnected from : " + threadID);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
