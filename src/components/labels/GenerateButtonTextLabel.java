package components.labels;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class GenerateButtonTextLabel extends JLabel {
    public GenerateButtonTextLabel() {
        super(Constants.GENERATE.get());

        setForeground(Color.WHITE);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
