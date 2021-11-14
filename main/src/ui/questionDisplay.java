package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class questionDisplay {
    MainFrame editor;
    String content;
    JTextArea imageDisplay;

    public questionDisplay(MainFrame e) {
        this.editor = e;
        content = e.getQuestionsAsked().get(editor.getSequence()).getContent();
        initializeGraphics();
        initializeInputs();
    }

    private void initializeGraphics() {
        imageDisplay = new JTextArea(content);
        imageDisplay.setEditable(false);
        editor.add(imageDisplay,BorderLayout.CENTER);
        imageDisplay.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT-20));
    }

    private void displayNewImage() {
        editor.setSequence(editor.getSequence() + 1);
        content = editor.getQuestionsAsked().get(editor.getSequence()).getContent();
        imageDisplay.setText(content);
    }

    private void initializeInputs() {
        InputMap inputs = imageDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = imageDisplay.getActionMap();
        inputs.put(KeyStroke.getKeyStroke("LEFT"), "swipeLeft");

        actions.put("swipeLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNewImage();
            }
        });

        inputs.put(KeyStroke.getKeyStroke("RIGHT"), "swipeRight");
        actions.put("swipeRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.displayUserAsked();
            }
        });
    }
}
