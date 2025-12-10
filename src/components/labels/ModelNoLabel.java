package components.labels;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class ModelNoLabel extends JLabel{
    public ModelNoLabel(String text) {
        super(text);

        setAlignmentX(Component.LEFT_ALIGNMENT);

        setFont(new Font("SansSerif", Font.PLAIN, 20));
    }
}
