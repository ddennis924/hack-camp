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
import java.util.List;

/*
 * Represents a user that can send and receive messages
 */
public class Client {
    MainFrame mainFrame;

    public Client(User user, MainFrame mainFrame) {
        JsonReader jsonReader = new JsonReader("");
//        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;
        try {
            Socket socket = new Socket((String) null, 4441);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            List<Question> questionList = user.getQList();
            String string = "";
            JSONArray jsonQuestions = new JSONArray();
            for (Question q: questionList) {
                jsonQuestions.put(q.toJson());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("questions", jsonQuestions);
            output.println(jsonObject);


            while (running) {
                if (input.ready()) {
                    String serverText = input.readLine();
                    if (serverText != null) {
                        System.out.println("Received from server:" + serverText);
                        JSONObject jsonQuestionList = new JSONObject(serverText);
                        mainFrame.getQuestionsAsked().addAll(jsonReader.parseQuestionList(jsonQuestionList));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
    }
}
