package Server;

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadReader extends Thread {
    BufferedReader reader;
    public ThreadReader(BufferedReader reader){
        this.reader = reader;
    }

    public void run(){
        boolean running = true;
        while(running){
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line != null && line.equals("QUIT")) {
                System.err.println("Host Disconnected.");
                running = false;
            }
            if(line != null) System.out.println("> "+ line);
        }
    }
}
