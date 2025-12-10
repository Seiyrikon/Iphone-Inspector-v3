package components.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import components.labels.AppTitleLabel;

public class TitleBarPanel extends JPanel{
    AppTitleLabel appTitle;
    TitleBarButtonsPanel titleBarButtons;

    private Point initialClick;
    public TitleBarPanel() {
        setBackground(new Color(56, 57, 58));
        setLayout(new BorderLayout());

        appTitle = new AppTitleLabel();
        appTitle.setVerticalAlignment(JLabel.CENTER);

        titleBarButtons = new TitleBarButtonsPanel();

        add(appTitle, BorderLayout.WEST);
        add(titleBarButtons, BorderLayout.EAST);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TitleBarPanel.this);
                if (frame != null) {
                    int thisX = frame.getLocation().x;
                    int thisY = frame.getLocation().y;

                    int xMoved = e.getX() - initialClick.x;
                    int yMoved = e.getY() - initialClick.y;

                    int X = thisX + xMoved;
                    int Y = thisY + yMoved;
                    frame.setLocation(X, Y);
                }
            }
        });
    }
}
