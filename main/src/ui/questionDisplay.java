package ui;

import com.sun.tools.javac.Main;
import study_tinder.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class questionDisplay {
    MainFrame editor;
    Image image;
    JLabel imageDisplay;

    public questionDisplay(MainFrame e) {
        this.editor = e;
        image = e.getQuestionsAsked().get(editor.getSequence()).getImage();
        initializeGraphics();
        initializeInputs();
    }

    private void initializeGraphics() {
        imageDisplay = new JLabel(new ImageIcon(image));
        editor.add(imageDisplay,BorderLayout.CENTER);
    }

    private void displayNewImage() {
        editor.setSequence(editor.getSequence() + 1);
        image = editor.getQuestionsAsked().get(editor.getSequence()).getImage();
        imageDisplay.setIcon( new ImageIcon(image));
    }

    private void initializeInputs() {
        InputMap inputs = imageDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = imageDisplay.getActionMap();
        inputs.put(KeyStroke.getKeyStroke("A"), "swipeLeft");

        actions.put("swipeLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNewImage();
            }
        });

        inputs.put(KeyStroke.getKeyStroke("D"), "swipeRight");
        actions.put("swipeRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.displayUserAsked();
            }
        });
    }
}
