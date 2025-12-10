package components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import components.buttons.CloseButton;
import components.buttons.MaximizeButton;
import components.buttons.MinimizeButton;

public class TitleBarButtonsPanel extends JPanel{
    MinimizeButton minimizeButton;
    MaximizeButton maximizeButton;
    CloseButton closeButton;

    public TitleBarButtonsPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        setOpaque(false);

        minimizeButton = new MinimizeButton();
        maximizeButton = new MaximizeButton();
        closeButton = new CloseButton();

        add(minimizeButton);
        add(maximizeButton);
        add(closeButton);
    }
}
