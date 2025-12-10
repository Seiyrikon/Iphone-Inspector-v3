package components.panels;

import java.awt.Color;

import javax.swing.JPanel;

public class BodyContainerPanel extends JPanel {
    InformationContainerPanel infoContainer;

    public BodyContainerPanel() {
        setLayout(null);
        // setBackground(Color.BLUE);
        setBackground(new Color(20, 21, 22));

        infoContainer = new InformationContainerPanel();

        // Set bounds with 20px margin on all sides
        infoContainer.setBounds(
                20, 20,
                getWidth() - 40, // width minus left+right margin
                getHeight() - 40 // height minus top+bottom margin
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
