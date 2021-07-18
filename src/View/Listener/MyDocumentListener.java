package View.Listener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;

public class MyDocumentListener implements DocumentListener {

    private DefaultStyledDocument doc;
    private JLabel remainingLabel;
    private int maxChars;

    public MyDocumentListener(DefaultStyledDocument doc, JLabel remainingLabel, int maxChars){
        this.doc = doc;
        this.remainingLabel = remainingLabel;
        this.maxChars = maxChars;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateCount(remainingLabel, doc, maxChars);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateCount(remainingLabel, doc, maxChars);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateCount(remainingLabel, doc, maxChars);
    }

    private void updateCount(JLabel remainingLabel, DefaultStyledDocument doc, int maxChars)
    {
        remainingLabel.setText((maxChars - doc.getLength()) + " caratteri rimanenti");
    }
}
