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
import java.util.List;

public class Server {

    public static void main(String[] args) {
        List<Question> questionList = new ArrayList<>();
        JsonReader jsonReader = new JsonReader("");

        int portNum = 4441;
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
                    JSONObject jsonObject = new JSONObject(clientText);
                    questionList.addAll(jsonReader.parseQuestionList(jsonObject));
                }

                output.println(questionsToString(questionList));

            }


        } catch (IOException e) {
            System.out.println("fail");
        }
    }

    public static String questionsToString(List<Question> questionList) {
        String string = "";
        JSONArray jsonQuestions = new JSONArray();
        for (Question q: questionList) {
           jsonQuestions.put(q.toJson());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questions", jsonQuestions);

        return jsonObject.toString();
    }

}
