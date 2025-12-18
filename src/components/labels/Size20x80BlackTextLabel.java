package components.labels;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class Size20x80BlackTextLabel extends JLabel {
    public Size20x80BlackTextLabel() {
        super(Constants.SIZE20X80_BLACK.get());

        setForeground(Color.WHITE);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
