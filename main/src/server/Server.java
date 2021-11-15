package server;

import persistence.JsonWriter;
import study_tinder.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    JsonWriter jsonWriter;

    public static void main(String[] args) {
        List<Question> questionList = new ArrayList<>();

        int portNum = 4441;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;

        try {
            ServerSocket socket = new ServerSocket(portNum);
            Socket clientSocket = socket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            while (running) {
                String clientText = input.readLine();

                if (clientText != null) {
                    if (clientText.equals("quit")) {
                        running = false;
                    }
                    System.out.println("Received from client:" + clientText);
                    output.println("received");
                }

            }
        } catch (IOException e) {
            System.out.println("fail");
        }
    }

}
