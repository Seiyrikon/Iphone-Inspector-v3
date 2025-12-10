package components.labels;

import javax.swing.JLabel;

import components.images.ScanButtonIcon;

public class ScanButtonIconLabel extends JLabel{
    public ScanButtonIconLabel() {
        super(new ScanButtonIcon());

        setAlignmentY(CENTER_ALIGNMENT);
    }
}
