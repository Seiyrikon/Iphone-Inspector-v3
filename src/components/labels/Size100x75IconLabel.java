package components.labels;

import javax.swing.JLabel;

import components.images.SizeIcon;

public class Size100x75IconLabel extends JLabel{
    public Size100x75IconLabel() {
        super(new SizeIcon());

        setAlignmentY(CENTER_ALIGNMENT);
    }
}
