import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Text extends JFrame {
    private JTextArea textArea1;
    private JPanel miPanel;
    private JButton backButton;
    private JButton forwardButton;
    private JButton newWebPageButton;
    private Stack<String> webSites = new Stack<>();
    private Stack<String> webSitesAhead = new Stack<>();

    public Text() {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undoAction();
                actualizarTextArea();
            }
        });

        newWebPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertarWebToStack();
                actualizarTextArea();;
            }
        });

        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                forwardWebToStack();
                actualizarTextArea();
            }
        });

        backButton.setEnabled(false);
        forwardButton.setEnabled(false);

        setTitle("Web Browser");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(miPanel);
        setVisible(true);
    }

    private void forwardWebToStack() {
        if (webSitesAhead.empty()) {
            forwardButton.setEnabled(false);
            return;
        }
        webSites.push(webSitesAhead.pop());
    }

    private void undoAction() {
        if (webSites.empty()) {
            return;
        }

        webSitesAhead.push(webSites.pop());
        forwardButton.setEnabled(true);

        if (webSites.empty()) {
            backButton.setEnabled(false);
        }
    }

    private void actualizarTextArea() {
        textArea1.setText(webSites.toString());
    }

    private void insertarWebToStack() {
        webSites.push("Website #" + webSites.size());
        webSitesAhead.clear();

        backButton.setEnabled(true);
        forwardButton.setEnabled(false);
    }
}

