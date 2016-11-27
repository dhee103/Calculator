package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class EqualController implements ActionListener {

    private final Model m;

    EqualController(Model model) {
        this.m = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        m.calculate();
    }
}
