package ui;

import study_tinder.Question;
import study_tinder.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 400;
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
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMainPanel();
        addTools();
        name = new JTextArea();
    }

    private void addTools() {
        JPanel toolArea = new JPanel();
        toolArea.setVisible(true);
        toolArea.setLayout(new GridLayout(2,1));
        add(toolArea, BorderLayout.SOUTH);

        JButton b1 = new JButton("Filter Categories");
        toolArea.add(b1);
        JButton b2 = new JButton("Super Solver");
        toolArea.add(b2);
    }

    private void addMainPanel() {
        new questionDisplay(this);
    }

    public void displayUserAsked() {
        new UserPrompt(this, questionsAsked.get(sequence));
    }

    private Question getQuestion() {
        return questionsAsked.get(sequence);
    }

    public static void main(String[] args) {
        new MainFrame("Studii");
    }

}