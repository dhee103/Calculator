package ic.doc;

import java.util.Stack;

public class Model {

    private final Updatable view;
    private final String invalid = "Invalid";

    Model(Updatable view) {
        this.view = view;
    }

    void sendToDisplay(String s) {
        view.updateDisplay(s);
    }

    void clearDisplay() {
        view.clearDisplay();
    }

    void calculate() {
        String input = view.getOutput();
        clearDisplay();
        sendToDisplay(calculateAnswer(input));
    }

    public String calculateAnswer(String input) {
        Stack<String> stack = new Stack<>();
        String[] ops = input.split("\\s+");

        for (String x: ops) {
            if (x.equals("")) {
                continue;
            }
            if (isOperator(x)) {
                String secondOp = getOperand(stack);
                String firstOp  = getOperand(stack);
                stack.push(applyOperand(firstOp, secondOp, x));
            } else {
                stack.push(x);
            }
        }
        return (stack.size() == 1) ? stack.pop() : invalid;

    }

    private String getOperand(Stack<String> stack) {
        if (!stack.isEmpty() && isNumeric(stack.peek())) {
            return stack.pop();
        } else {
            return invalid;
        }

    }

    private Boolean isOperator(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private boolean checkInvalid(String input) {
        return input.equals(invalid);
    }

    private String applyOperand(String inp1, String inp2, String op) {
        if (checkInvalid(inp1) || checkInvalid(inp2)) {
            return invalid;
        }

        switch (op) {
            case "+":
                return String.valueOf((Integer.parseInt(inp1) + Integer.parseInt(inp2)));
            case "-":
                return String.valueOf((Integer.parseInt(inp1) - Integer.parseInt(inp2)));
            case "*":
                return String.valueOf((Integer.parseInt(inp1) * Integer.parseInt(inp2)));
            case "/":
                if (Integer.parseInt(inp2) == 0) {
                    return invalid + ": Divide By Zero";
                }
                return String.valueOf((Integer.parseInt(inp1) / Integer.parseInt(inp2)));
        }
        return "Invalid Operator";
    }






}
