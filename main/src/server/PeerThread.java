package server;

import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/*
 * Represents the connection between the Peer and a given socket
 */

public class PeerThread extends Thread {
    private BufferedReader socketReader;
    private Socket socket;
    private JsonReader jsonReader;

    public PeerThread(Socket socket) throws IOException {
        this.socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
    }

    // reading
    public void run() {
        boolean running = true;
        while (running) {
            String jsonString = socketReader.lines().collect(Collectors.joining());
            JSONObject jsonListOfAskedQuestions = new JSONObject(jsonString);
            jsonListOfAskedQuestions.


        }
    }

    public void send() throws IOException {
        try (OutputStreamWriter out = new OutputStreamWriter(
                socket.getOutputStream(), StandardCharsets.UTF_8)) {
            out.write(/*jsonfile*/.toString());
        }
    }
}
