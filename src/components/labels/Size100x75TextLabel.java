package components.labels;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class Size100x75TextLabel extends JLabel{
    public Size100x75TextLabel() {
        super(Constants.SIZE100X75.get());

        setForeground(Color.WHITE);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
