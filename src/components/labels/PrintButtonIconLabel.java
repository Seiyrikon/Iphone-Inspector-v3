package components.labels;

import javax.swing.JLabel;

import components.images.PrintButtonIcon;

public class PrintButtonIconLabel extends JLabel {
    public PrintButtonIconLabel() {
        super(new PrintButtonIcon());

        setAlignmentY(CENTER_ALIGNMENT);
    }
}
