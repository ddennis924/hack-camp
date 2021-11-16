package server;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import study_tinder.Question;
import study_tinder.User;
import ui.MainFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

/*
 * Represents a user that can send and receive messages
 */
public class Client {
    MainFrame mainFrame;
    User user;
    JsonReader jsonReader;
    BufferedReader input;
    PrintWriter output;

    // EFFECTS: Creates a new client with a connection to the server
    public Client(User user, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.user = user;
        this.jsonReader = new JsonReader("");

        try {
            // ** Change localhost to your IP address for connection through Wi-Fi **
            Socket socket = new Socket("localhost", Server.SERVER_PORT_NUM);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            sendUserQuestions();
            listenForServer();

        } catch (IOException e) {
            throw new RuntimeException("Could not connect to server.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Sends the user's questions to the server
    private void sendUserQuestions() {
        Set<Question> questionList = user.getMyQuestions();
        JSONArray jsonQuestions = new JSONArray();
        for (Question q: questionList) {
            jsonQuestions.put(q.toJson());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questions", jsonQuestions);
        output.println(jsonObject);
    }

    // MODIFIES: this
    // EFFECTS: Listens for new questions from the server
    private void listenForServer() {
        try {
            while (true) {
                String serverText = input.readLine();
                if (serverText != null) {
                    System.out.println("Received from server:" + serverText);
                    JSONObject jsonQuestionList = new JSONObject(serverText);
                    mainFrame.getQuestionsAsked().addAll(jsonReader.parseQuestionList(jsonQuestionList));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("IO Exception has occured.");
        }
    }
}
