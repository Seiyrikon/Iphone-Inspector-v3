package components.labels;

import javax.swing.JLabel;

import components.images.SizeIconBlack;

public class Size20x80BlackIconLabel extends JLabel {
    public Size20x80BlackIconLabel() {
        super(new SizeIconBlack());

        setAlignmentY(CENTER_ALIGNMENT);
    }
}
