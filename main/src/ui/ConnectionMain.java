package ui;

import server.Client;
import study_tinder.Question;
import study_tinder.User;

public class ConnectionMain {
    public static void main(String[] args) {
        User user = new User("alan");
        user.uploadMyQuestion(new Question(user, "What is a for loop", "CPSC"));
        user.uploadMyQuestion(new Question(user, "((1+2)*0)/12985y1892519", "MATH"));

        MainFrame mainFrame = new MainFrame("Studii (w/ Connection)", user);
        Thread thread = new Thread() {
            public void run() {
                new Client(user, mainFrame);
            }
        };
        thread.start();
    }
}
