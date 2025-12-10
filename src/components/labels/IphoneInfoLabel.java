package components.labels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class IphoneInfoLabel extends JLabel{
    public IphoneInfoLabel(String text) {
        super(text);

        setFont(new Font("SansSerif", Font.PLAIN, 20));

        setForeground(Color.BLACK);
        setAlignmentX(Component.LEFT_ALIGNMENT);

        setBorder(new EmptyBorder(0, 10, 0, 0));
    }
}
