package components.labels;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class ProductVersionLabel extends JLabel{
    public ProductVersionLabel(String text) {
        super(text);

        setAlignmentX(Component.LEFT_ALIGNMENT);

        setFont(new Font("SansSerif", Font.PLAIN, 20));
    }
}
