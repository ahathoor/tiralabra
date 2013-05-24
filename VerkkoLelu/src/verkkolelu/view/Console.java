/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.view;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * This console displays displays the System.out stream.
 * @author mikko
 */
public class Console extends JDialog {

    public Console(MainFrame mf) {
        super(mf, "Console");
        mf.addDialog(this);
        JTextArea jt = new JTextArea(15, 50);
        jt.setEditable(false);
        JScrollPane js = new JScrollPane(jt);
        this.add(js);
        this.pack();
        this.setVisible(true);
        System.setOut(new PrintStream(new TextAreaOut(jt)));
    }
    
    /**
     * 
     */
    private class TextAreaOut extends OutputStream {

        private StringBuilder sb;
        private JTextArea textArea;

        public TextAreaOut(JTextArea textArea) {
            this.textArea = textArea;
            sb = new StringBuilder();
        }

        
        @Override
        public void write(int b) throws IOException {

            if (b == '\r') {
                return;
            }

            if (b == '\n') {
                final String text = sb.toString() + "\n";
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textArea.append(text);
                    }
                });
                sb.setLength(0);
                return;
            }

            sb.append((char) b);

        }
    }
}
