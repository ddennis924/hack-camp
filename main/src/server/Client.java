package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Represents a user that can send and receive messages
 */
public class Client {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        boolean running = true;
        try {
            String clientName = consoleReader.readLine();
        //    int portNum = Integer.parseInt(consoleReader.readLine());

            Socket socket = new Socket((String) null, 4441);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);


            while (running) {
                if (input.ready()) {
                    String serverText = input.readLine();
                    if (serverText != null) {
                        System.out.println("Received from server:" + serverText);
                    }
                }

                if (consoleReader.ready()) {
                String userText = consoleReader.readLine();
                System.out.println(userText);
                    if (userText != null) {
                        System.out.println("Sent to server:" + userText);
                        output.println(userText);
                        if (userText.equals("quit")) {
                            running = false;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
    }
}
