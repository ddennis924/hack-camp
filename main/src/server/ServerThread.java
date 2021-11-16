package server;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import study_tinder.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread {
    Socket socket;
    Server server;
    BufferedReader input;
    PrintWriter output;
    JsonReader jsonReader;

    /*
     * Represents a server thread for handling connections with a single client
     */
    public ServerThread(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.jsonReader = new JsonReader("");
    }

    public void run() {
        try {
            receiveQuestions();
        } catch (IOException e) {
            throw new RuntimeException("IOException has occurred.");
        }
    }

    // EFFECTS: Listens for and receives questions from the client; then updates the server's question list and
    //          calls for the server to resend questions to all clients
    //          (only occurs once as of now)
    private void receiveQuestions() throws IOException {
        String clientText = input.readLine();
        if (clientText != null) {
            System.out.println("Received from client:" + clientText);
            JSONObject jsonObject = new JSONObject(clientText);
            server.getQuestionList().addAll(jsonReader.parseQuestionList(jsonObject));
        }
        server.updateQuestions();
    }

    // EFFECTS: Sends the server's question list (as a String) to this thread's client
    public void sendQuestions() {
        output.println(questionsToString(server.getQuestionList()));
    }

    // EFFECTS: Converts a list of Question to a String
    public static String questionsToString(List<Question> questionList) {
        JSONArray jsonQuestions = new JSONArray();
        for (Question q: questionList) {
            jsonQuestions.put(q.toJson());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questions", jsonQuestions);

        return jsonObject.toString();
    }

}
