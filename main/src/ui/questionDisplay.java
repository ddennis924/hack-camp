package ui;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;

public class questionDisplay {
    MainFrame editor;
    String content;
    JTextPane imageDisplay;

    public questionDisplay(MainFrame e) {
        this.editor = e;
        content = e.getQuestionsAsked().get(editor.getSequence()).getContent();
        initializeGraphics();
        initializeInputs();
    }

    private void initializeGraphics() {
        imageDisplay = new JTextPane();
        StyledDocument doc = imageDisplay.getStyledDocument();
        SimpleAttributeSet centre = new SimpleAttributeSet();

        StyleConstants.setAlignment(centre, StyleConstants.ALIGN_CENTER);
        imageDisplay.setText(content);
        doc.setParagraphAttributes(0,doc.getLength(),centre,false);

        imageDisplay.setEditable(false);
        editor.add(imageDisplay, BorderLayout.CENTER);
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
