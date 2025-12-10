package components.labels;

import javax.swing.JLabel;

import components.images.GenerateButtonIcon;

public class GenerateButtonIconLabel extends JLabel{
    public GenerateButtonIconLabel() {
        super(new GenerateButtonIcon());

        setAlignmentY(CENTER_ALIGNMENT);
    }
}
