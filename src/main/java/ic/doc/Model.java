package ic.doc;

import java.util.*;

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

//  infix to reverse polish notation
        ops = infixToRPN(Arrays.copyOfRange(ops, 1, ops.length));

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

    private String[] infixToRPN(String[] input) {

        Map<String, Integer> prededence = new HashMap<>();
        prededence.put("/", 5);
        prededence.put("*", 5);
        prededence.put("+", 4);
        prededence.put("-", 4);
        prededence.put("(", 0);

        Queue<String> rpn = new LinkedList<>();
        Stack<String> ops = new Stack<>();

        for (String s: input) {
            if (Character.isSpaceChar(s.charAt(0))) continue;
            else if (isNumeric(s)) rpn.add(s);
            else if (s.equals("(")) ops.add(s);

            else if (s.equals(")")) {
                while (!ops.peek().equals("(")) rpn.add(ops.pop());
                ops.pop();
            }
            else if(isOperator(s)) {
                /*operator rule checks */
                while (!ops.isEmpty() && prededence.get(ops.peek()) >= prededence.get(s)) {
                    rpn.add(ops.pop());
                }
                ops.push(s);
            }
        }

//        ops.forEach(op -> rpn.add(op));
        for (String op: ops) rpn.add(op);

        String[] rpnOutput = new String[rpn.size()];
        for (int i = 0; i < rpnOutput.length; i++) rpnOutput[i] = rpn.remove();

        return rpnOutput;
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

//    TODO: Replace all uses with isDigit
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
