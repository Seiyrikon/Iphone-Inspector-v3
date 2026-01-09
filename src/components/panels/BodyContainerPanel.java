package components.panels;

import java.awt.Color;

import javax.swing.JPanel;

public class BodyContainerPanel extends JPanel {
    InformationContainerPanel infoContainer;

    public BodyContainerPanel() {
        setLayout(null);
        setBackground(new Color(20, 21, 22));

        infoContainer = new InformationContainerPanel();

        infoContainer.setBounds(
                20, 20,
                getWidth() - 40,
                getHeight() - 40 
        );

        add(infoContainer);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);

        // Update infoContainer size whenever BodyContainerPanel is resized
        if (infoContainer != null) {
            infoContainer.setBounds(
                    20, 20,
                    width - 40,
                    height - 40);
        }
    }

    public InformationContainerPanel getInfoContainer() {
        return infoContainer;
    }
}
