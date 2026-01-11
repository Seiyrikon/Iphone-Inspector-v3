package components.labels;

import javax.swing.JLabel;

import components.images.SizeIcon;

public class Size140x85IconLabel extends JLabel {
    public Size140x85IconLabel() {
        super(new SizeIcon());

        setAlignmentY(CENTER_ALIGNMENT);
    }
}
