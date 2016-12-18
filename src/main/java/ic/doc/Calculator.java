package ic.doc;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class Calculator {

    private final View v;
    private final Model m;

    public Calculator() {
        v = new View();
        m = new Model(v);
        v.setControllerOps(createControllerOps());
        v.setControllerEqual(new EqualController(m));
        v.setControllerClear(new ControllerClear(m));
    }

    private List<ActionListener> createControllerOps() {
        List<ActionListener> result = new LinkedList<>();
        result.add(new ControllerOps("+", m));
        result.add(new ControllerOps("1", m));
        result.add(new ControllerOps("2", m));
        result.add(new ControllerOps("3", m));
        result.add(new ControllerOps("-", m));
        result.add(new ControllerOps("4", m));
        result.add(new ControllerOps("5", m));
        result.add(new ControllerOps("6", m));
        result.add(new ControllerOps("*", m));
        result.add(new ControllerOps("7", m));
        result.add(new ControllerOps("8", m));
        result.add(new ControllerOps("9", m));
        result.add(new ControllerOps("/", m));
        result.add(new ControllerOps("0", m));
        return result;
    }

    public static void main(String[] args) {
        new Calculator();
    }

}
