package ui;

import study_tinder.User;

public class NoConnectionMain {
    public static void main(String[] args) {
        User user = new User("alan");

        // Questions that show up on Study page
        user.addQuestion("What is a for loop", "CPSC");
        user.addQuestion("((1+2)*0)/12985y1892519", "MATH");

        new MainFrame("Studii (w/ No Connection)", user);
    }
}
