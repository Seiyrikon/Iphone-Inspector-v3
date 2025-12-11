package components.labels;

import javax.swing.JLabel;

import components.images.SizeIcon;

public class Size120x80IconLabel extends JLabel {
    public Size120x80IconLabel() {
        super(new SizeIcon());

        setAlignmentY(CENTER_ALIGNMENT);
    }
}
