package ui;

import study_tinder.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPrompt extends JFrame implements ActionListener {
    MainFrame editor;
    Question question;
    JTextField textField;
    JTextArea chatBox;

    public UserPrompt(MainFrame mainFrame, Question q) {
        this.editor = mainFrame;
        this.question = q;
        initializeGraphics();
    }

    protected void initializeGraphics() {
        setLayout(new BorderLayout());
        JPanel main = new JPanel(new BorderLayout());
        add(main, BorderLayout.CENTER);
        setSize(new Dimension(400, 400));
        setVisible(true);
        setDefaultCloseOperation(UserPrompt.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        addPrompts(main);
}

    private void addPrompts(JPanel main) {
        JTextArea questionDisplay = new JTextArea(question.getContent());
        questionDisplay.setSize(400, 150);
        add(questionDisplay, BorderLayout.NORTH);
        questionDisplay.setEditable(false);

        JPanel chat = new JPanel(new BorderLayout());
        chat.setSize(400, 250);
        main.add(chat, BorderLayout.CENTER);

        JTextArea name = new JTextArea("Asked by: " + question.getUser().getName());
        name.setEditable(false);
        name.setSize(400, 50);
        chat.add(name, BorderLayout.NORTH);

        chatBox = new JTextArea();
        chat.setSize(400, 150);
        chat.add(chatBox, BorderLayout.CENTER);

        textField = new JTextField();
        textField.addActionListener(this);
        textField.setSize(400, 50);
        chat.add(textField, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = textField.getText();
        textField.setText("");
        chatBox.append("\n" + msg);
    }
}
