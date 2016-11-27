package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerClear implements ActionListener {
    private final Model m;

    ControllerClear(Model m) {
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        m.clearDisplay();
    }
}
