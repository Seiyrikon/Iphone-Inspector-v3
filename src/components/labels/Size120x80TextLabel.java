package components.labels;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class Size120x80TextLabel extends JLabel {
    public Size120x80TextLabel() {
        super(Constants.SIZE120X80.get());

        setForeground(Color.WHITE);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
