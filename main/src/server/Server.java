package server;


import study_tinder.Question;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server {
    public static final int SERVER_PORT_NUM = 4442;
    List<Question> questionList;
    Set<ServerThread> serverThreads;
    ServerSocket socket;

    // EFFECTS: Create a new server with an empty question list and no server threads
    public Server() {
        questionList = new ArrayList<>();
        serverThreads = new HashSet<>();

        try {
            socket = new ServerSocket(SERVER_PORT_NUM);
        } catch (IOException e) {
            throw new RuntimeException("Could not connect to port " + SERVER_PORT_NUM + ".");
        }

        listenForNewClient();
    }


    // MODIFIES: this
    // EFFECTS: Listens for new connections to server and assigns a thread to new client
    private void listenForNewClient() {
        try {
            while (true) {
                ServerThread thread = new ServerThread(socket.accept(), this);
                thread.start();
                serverThreads.add(thread);
            }
        } catch (IOException e) {
            throw new RuntimeException("IO exception has occurred.");
        }
    }

    // EFFECTS: Sends all questions to every client
    public void updateQuestions() {
        for (ServerThread thread: serverThreads) {
            thread.sendQuestions();
        }
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    // EFFECTS: Run a new server
    public static void main(String[] args) {
        new Server();
    }
}
