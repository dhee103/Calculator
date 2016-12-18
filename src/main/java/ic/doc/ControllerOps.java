package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerOps implements ActionListener {
    private final String op;
    private final Model model;

    public ControllerOps(String s, Model m) {
        this.op = s;
        this.model = m;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        model.sendToDisplay(op);
    }
}
