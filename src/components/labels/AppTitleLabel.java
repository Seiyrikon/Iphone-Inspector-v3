package components.labels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import utils.Constants;

public class AppTitleLabel extends JLabel{
    public AppTitleLabel() {
        super(Constants.APP_TITLE.get());
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
}
