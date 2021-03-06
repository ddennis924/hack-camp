package ui;

import study_tinder.Question;
import study_tinder.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 500;
    private User user;
    private List<Question> questionsAsked;
    private int sequence;
    private JTextArea name;
    private JPanel main;


    public MainFrame(String title, User user) {
        super(title);

        this.user = user;
        sequence = 0;
        questionsAsked = user.getQList();
        initializeGraphics();
    }

    private void test() {
        user = new User("alan");
        user.uploadMyQuestion(new Question(user, "What is a for loop", "CPSC"));
        user.uploadMyQuestion(new Question(user, "((1+2)*0)/12985y1892519", "MATH"));

        user.addQuestion("What is a for loop", "CPSC");
        user.addQuestion("((1+2)*0)/12985y1892519", "MATH");
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
        setLayout(new GridBagLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main = new JPanel(new BorderLayout());
        main.setVisible(true);
        add(main);
        addMenu();
    }

    private void addMainOne() {
        addMainPanel();
        addTools();
    }

    private void addMenu() {
        JPanel topMenu = new JPanel(new GridLayout(0, 2));
        add(topMenu);

        JToggleButton c1 = new JToggleButton("Study");
        topMenu.add(c1);

        JToggleButton c2 = new JToggleButton("Messages");
        topMenu.add(c2);

        c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (c1.isSelected()) {
                    c2.setSelected(false);
                    remove(main);
                    main = new JPanel(new BorderLayout());
                    main.setVisible(true);
                    add(main);
                    addMainOne();
                    main.setFocusable(true);
                    revalidate();
                    repaint();
                }
            }
        });

        c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (c2.isSelected()) {
                    c1.setSelected(false);
                    remove(main);
                    main = new JPanel(new BorderLayout());
                    main.setVisible(true);
                    add(main);
                    addMainTwo();
                    main.setFocusable(true);
                    revalidate();
                    repaint();
                }
            }
        });
    }

    private void addMainTwo() {
        main.removeAll();
        DefaultListModel<String> friendslistBack = new DefaultListModel<>();
        JList friendslist = new JList(friendslistBack);
        friendslistBack.addElement("Alan");
        friendslistBack.addElement("Adrian");
        friendslistBack.addElement("Dennis");
        main.add(friendslist, BorderLayout.CENTER);
    }

    private void addTools() {
        JPanel toolArea = new JPanel();
        toolArea.setVisible(true);
        toolArea.setLayout(new GridLayout(2,1));
        main.add(toolArea, BorderLayout.SOUTH);

        JButton b1 = new JButton("Filter Categories");
        toolArea.add(b1);
        JButton b2 = new JButton("Super Solver");
        toolArea.add(b2);
    }

    private void addMainPanel() {
        new questionDisplay(this, main);
    }

    public void displayUserAsked() {
        new UserPrompt(this, questionsAsked.get(sequence));
    }

    private Question getQuestion() {
        return questionsAsked.get(sequence);
    }
}