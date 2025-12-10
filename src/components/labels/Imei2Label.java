package components.labels;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class Imei2Label extends JLabel {
    public Imei2Label(String text) {
        super(text);

        setAlignmentX(Component.LEFT_ALIGNMENT);

        setFont(new Font("SansSerif", Font.PLAIN, 20));
    }
}
