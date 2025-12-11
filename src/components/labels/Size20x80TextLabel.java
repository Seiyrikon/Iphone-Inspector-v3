package components.labels;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class Size20x80TextLabel extends JLabel{
    public Size20x80TextLabel() {
        super(Constants.SIZE20X80.get());

        setForeground(Color.WHITE);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
