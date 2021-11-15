package server;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import study_tinder.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server {
    List<Question> questionList;
    Set<ServerThread> serverThreads;

    public Server() {
        questionList = new ArrayList<>();
        serverThreads = new HashSet<>();
        int portNum = 4441;
        try {
            ServerSocket socket = new ServerSocket(portNum);
            while (true) {
                ServerThread thread = new ServerThread(socket.accept(), this);
                serverThreads.add(thread);
            }
        } catch (IOException e) {
            System.out.println("fail");
        }
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public static void main(String[] args) {
        new Server();
    }

    public void update() {
        for (ServerThread thread: serverThreads) {
            thread.sendQuestions();
        }
    }
}
