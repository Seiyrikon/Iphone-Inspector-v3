package components.textfields;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

public class EidTextField extends JTextField {
    public EidTextField() {
        super(5);

        setAlignmentX(Component.LEFT_ALIGNMENT);
        setFont(new Font("SansSerif", Font.PLAIN, 20));

        int height = 40; // <-- your desired height


        setPreferredSize(new Dimension(150, height));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        setMinimumSize(new Dimension(50, height));
    }
}
