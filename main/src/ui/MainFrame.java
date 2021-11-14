package ui;

import study_tinder.Category;
import study_tinder.Question;
import study_tinder.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MainFrame extends JFrame {
    private User user;
    private List<Question> questionsAsked;
    private int sequence;
    private JTextArea name;


    public MainFrame(String title) {
        super(title);
        sequence = 0;
        test();
        initializeGraphics();
    }

    private void test() {
        User user1 = new User("alan");
        user1.addQuestion("poonus", "MATH");
        user1.addQuestion("poopcock", "CPSC");
        questionsAsked = user1.getQList();
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Question> getQuestionsAsked() {
        return questionsAsked;
    }

    public void setQuestionsAsked(List<Question> questionsAsked) {
        this.questionsAsked = questionsAsked;
    }

    private void initializeGraphics() {
        setVisible(true);
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(400, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMainPanel();
        name = new JTextArea();
        add(name, BorderLayout.SOUTH);
    }

    private void addTools() {
    }

    private void addMainPanel() {
        new questionDisplay(this);
    }

    public void displayUserAsked() {
        name.setText(getQuestion().getUser().getName());
    }

    private Question getQuestion() {
        return questionsAsked.get(sequence);
    }

    public static void main(String[] args) {
        new MainFrame("Studii");
    }

}