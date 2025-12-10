package components.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utils.Constants;

public class CloseButton extends JButton {
    public CloseButton() {
        super(Constants.CLOSE_BUTTON.get());
        addActionListener(e -> System.exit(0));

        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);

        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(true);

        setPreferredSize(new Dimension(45, 30));

        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setOpaque(true);
                setBackground(Color.RED);
            }

            public void mouseExited(MouseEvent evt) {
                setBackground(new Color(56, 57, 58));
            }
        });
    }
}
