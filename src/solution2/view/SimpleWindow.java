package solution2.view;

import solution2.Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleWindow implements UserInterface {
    private JFrame window;
    private JTextArea text;
    private JTextField inString;
    private JButton submitButton;
    private JPanel sPanel;
    private BlockingQueue<String> mq;

    private Boundary boundary;
    private List<Double> data;
    private Stack<Double> stack;
    String input;

    public SimpleWindow(String title, Boundary boundary) {
        window = new JFrame(title);
        window.setLayout(new BorderLayout());
        text = new JTextArea();
        text.setEditable(false);
        text.setBackground(new Color(255, 220, 220));
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Sansserif", Font.BOLD, 18));
        window.add(new JScrollPane(text), BorderLayout.CENTER);
        sPanel = new JPanel();
        sPanel.setLayout(new BorderLayout());
        window.add(sPanel, BorderLayout.SOUTH);
        inString = new JTextField();
        inString.setFont(new Font("Sansserif", Font.BOLD | Font.ITALIC, 18));
        inString.requestFocusInWindow();
        submitButton = new JButton("Send");
        submitButton.setForeground(Color.RED.darker());
        submitButton.setBackground(Color.BLUE);
        mq = new ArrayBlockingQueue<String>(100);
        ActionListener goAction = new GoListener();
        submitButton.addActionListener(goAction);
        inString.registerKeyboardAction(goAction, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        sPanel.add(inString, BorderLayout.CENTER);
        sPanel.add(submitButton, BorderLayout.EAST);
        window.setSize(800, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.setVisible(true);
        data = new ArrayList<>();
        this.boundary = boundary;
        this.stack = boundary.getStack();
        run();
    }

    public void run() {
        while (true) {
            render();
            getInput();
            handleInput();
        }
    }

    private void render() {
        if (stack.isEmpty()) {
            renderReadme();
        } else {
            renderOutput();
        }
        if (boundary.isIllegalCommand()) {
            renderIllegalCommandMessage();
        }
    }

    private void renderReadme() {
        clearText();
        addString("[empty]\n");
        addString("Commands: q=quit c=clear + - * / number");
    }

    private void renderOutput() {
        clearText();
        addString(stack.toString());
    }

    private void renderIllegalCommandMessage() {
        addString("\nIllegal command");
    }

    private void getInput() {
        input = getString();
    }

    private void handleInput() {
        if (input.equalsIgnoreCase("c")) {
            clear();
        } else if (input.equalsIgnoreCase("q")) {
            exit();
        } else {
            boundary.handleInput(input);
        }
    }

    class GoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                mq.put(inString.getText());
                inString.setText("");
                inString.requestFocusInWindow();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String getString() {
        try {
            return mq.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Should not happen";
        }
    }

    @Override
    public void addString(String s) {
        text.append(s);
    }

    public void clear() {
        stack.clear();
    }

    @Override
    public void clearText() {
        text.setText("");
    }

    @Override
    public void exit() {
        System.out.println("Shutting down...");
        window.dispose();
        System.exit(0);
    }
}
