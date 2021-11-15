package ui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class questionDisplay {
    MainFrame editor;
    String content;
    JTextPane imageDisplay;
    JComponent parent;

    public questionDisplay(MainFrame e, JComponent parent) {
        this.editor = e;
        this.parent = parent;
        content = e.getQuestionsAsked().get(editor.getSequence()).getContent();
        initializeGraphics();
        initializeInputs();
    }

    public MainFrame getEditor() {
        return editor;
    }

    public String getContent() {
        return content;
    }

    public JTextPane getImageDisplay() {
        return imageDisplay;
    }

    public JComponent getParent() {
        return parent;
    }

    private void initializeGraphics() {
        imageDisplay = new JTextPane();
        StyledDocument doc = imageDisplay.getStyledDocument();
        SimpleAttributeSet centre = new SimpleAttributeSet();

        StyleConstants.setAlignment(centre, StyleConstants.ALIGN_CENTER);
        imageDisplay.setText("Question\n" + content);
        doc.setParagraphAttributes(0,doc.getLength(),centre,false);

        imageDisplay.setEditable(false);
        parent.add(imageDisplay, BorderLayout.CENTER);
        imageDisplay.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT-20));
        imageDisplay.setBackground(new Color(235, 232, 243));
    }

    private void displayNewImage() {
        editor.setSequence(editor.getSequence() + 1);
        content = editor.getQuestionsAsked().get(editor.getSequence()).getContent();
        imageDisplay.setText("Question\n" + content);
    }

    private void initializeInputs() {
        JButton left = new JButton("Next");
        parent.add(left, BorderLayout.WEST);
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNewImage();
            }
        });
        left.setForeground(new Color(139, 0,0));
        left.setMnemonic(KeyEvent.VK_LEFT);

        JButton right = new JButton("Help");
        parent.add(right, BorderLayout.EAST);
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.displayUserAsked();
            }
        });

        right.setForeground(new Color(0,100,0));
        right.setMnemonic(KeyEvent.VK_RIGHT);

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
