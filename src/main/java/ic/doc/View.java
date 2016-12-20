package ic.doc;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

class View implements Updatable {

    private final JButton zero = new JButton("0");
    private final JButton one = new JButton("1");
    private final JButton two = new JButton("2");
    private final JButton three = new JButton("3");
    private final JButton four = new JButton("4");
    private final JButton five = new JButton("5");
    private final JButton six = new JButton("6");
    private final JButton seven = new JButton("7");
    private final JButton eight = new JButton("8");
    private final JButton nine = new JButton("9");

    private final JButton add = new JButton("+");
    private final JButton minus = new JButton("-");
    private final JButton multiply = new JButton("*");
    private final JButton divide = new JButton("/");

    private final JButton equals = new JButton("=");
    private final JButton clear = new JButton("C");


    private final JButton[] opsButtons = new JButton[]{
            add, one, two, three,
            minus, four, five, six,
            multiply, seven, eight, nine,
            divide, zero
    };

    private final JTextField outputField = new JTextField(17);

    View() {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(200, 160);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());

        contentPane.add(outputField, BorderLayout.NORTH);

        for (JButton button : opsButtons) {
            contentPane.add(button, BorderLayout.WEST);
        }

        contentPane.add(clear, BorderLayout.WEST);
        contentPane.add(equals, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void setControllerOps(List<ActionListener> controllerOps) {
        for (int i = 0; i < opsButtons.length; i++) {
            opsButtons[i].addActionListener(controllerOps.get(i));
        }
    }

    void setControllerEqual(ActionListener controller) {
        equals.addActionListener(controller);
    }

    void setControllerClear(ActionListener controller) {
        clear.addActionListener(controller);
    }

    public void clearDisplay() {
        outputField.setText("");
    }


    public void updateDisplay(String s) {
        outputField.setText(outputField.getText() + " " + s);
    }

    public String getOutput() {
        return outputField.getText();
    }


}
