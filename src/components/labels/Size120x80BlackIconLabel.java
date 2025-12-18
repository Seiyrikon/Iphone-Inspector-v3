package components.labels;

import javax.swing.JLabel;

import components.images.SizeIconBlack;

public class Size120x80BlackIconLabel extends JLabel {
    public Size120x80BlackIconLabel() {
        super(new SizeIconBlack());

        setAlignmentY(CENTER_ALIGNMENT);
    }
}
