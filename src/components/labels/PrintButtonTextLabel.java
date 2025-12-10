package components.labels;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class PrintButtonTextLabel extends JLabel{
    public PrintButtonTextLabel() {
        super(Constants.PRINT.get());

        setForeground(Color.WHITE);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
