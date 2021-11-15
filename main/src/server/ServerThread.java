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

    public ServerThread(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.jsonReader = new JsonReader("");


        String clientText = input.readLine();
        if (clientText != null) {
            System.out.println("Received from client:" + clientText);
            JSONObject jsonObject = new JSONObject(clientText);
            server.getQuestionList().addAll(jsonReader.parseQuestionList(jsonObject));
            sendQuestions();
        }
        server.update();
    }

    public void run() {
        jsonReader = new JsonReader("");
        try {
            if (input.ready()) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String questionsToString(List<Question> questionList) {
        JSONArray jsonQuestions = new JSONArray();
        for (Question q: questionList) {
            jsonQuestions.put(q.toJson());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questions", jsonQuestions);

        return jsonObject.toString();
    }

    public void sendQuestions() {
        output.println(questionsToString(server.getQuestionList()));
    }
}
