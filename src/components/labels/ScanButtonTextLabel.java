package components.labels;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class ScanButtonTextLabel extends JLabel{
    public ScanButtonTextLabel() {
        super(Constants.SCAN.get());

        setForeground(Color.WHITE);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
