package ic.doc;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CalculatorTest {


    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    Updatable view = context.mock(Updatable.class);
    Model model = new Model(view);

    Random rand = new Random();
    private int getRandom(boolean isDiv) {
        return isDiv ? rand.nextInt(9) + 1 : rand.nextInt(10);
    }


    @Test
    public void testSimpleAddition() throws Exception {
        String answer = model.calculateAnswer("3 4 +");
        assertThat(answer, is("7"));
    }

    @Test
    public void testSimpleMultiplication() throws Exception {
        String answer = model.calculateAnswer("3 5 *");
        assertThat(answer, is("15"));
    }

    @Test
    public void testSimpleDivision() throws Exception {
        String answer = model.calculateAnswer("6 3 /");
        assertThat(answer, is("2"));
    }

    @Test
    public void testDivisionByZero() throws Exception {
        String answer = model.calculateAnswer("3 0 /");
        assertThat(answer, is("Invalid: Divide By Zero"));
    }

    @Test
    public void testManyAddition() throws Exception {
        testOperator("+");
    }

    @Test
    public void testManyMultiplication() throws  Exception {
        testOperator("*");
    }

    @Test
    public void testManySubtraction() throws Exception {
        testOperator("-");
    }

    @Test
    public void testManyDivision() throws Exception {
        testOperator("/");
    }

    @Test
    public void testCompositeExpression() throws Exception {
        String answer = model.calculateAnswer("5 1 2 + 4 * + 3 -");
        assertThat(answer, is("14"));
    }

    @Test
    public void testErrorExpressionMultipleOps() throws Exception {
        String answer = model.calculateAnswer("+ +");
        assertThat(answer, is("Invalid"));
    }

    @Test
    public void testInvalidExpression() throws Exception {
        String answer = model.calculateAnswer("1 2 + 3");
        assertThat(answer, is("Invalid"));
    }

    @Test
    public void testInvalidOnlyNumbers() throws Exception {
        String answer = model.calculateAnswer("1 1 1");
        assertThat(answer, is("Invalid"));
    }



    private int getValue(int input1, int input2, String op) {
        switch (op) {
            case "+":
                return input1 + input2;
            case "-":
                return input1 - input2;
            case "*":
                return input1 * input2;
            case "/":
                return input1 / input2;
            default:
                throw new UnsupportedOperationException(op);
        }
    }

    private void testOperator(String op) {
        boolean isDiv = op.equals("/");

        String answer;
        for (int i = 0; i < 100; i++) {
            int op1 = getRandom(isDiv);
            int op2 = getRandom(isDiv);
            answer = model.calculateAnswer(op1 + " " + op2 + " " + op);
            assertThat(answer, is(String.valueOf(getValue(op1, op2, op))));
        }

    }



}