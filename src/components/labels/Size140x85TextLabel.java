package components.labels;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class Size140x85TextLabel extends JLabel{
    public Size140x85TextLabel() {
        super(Constants.SIZE140X85.get());

        setForeground(Color.WHITE);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
