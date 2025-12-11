package components.combobox;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;

import model.IphoneLabelInformation;
import utils.Constants;

public class ColorTypeComboBox extends JComboBox<String> {
    IphoneLabelInformation informationLabel;

    public ColorTypeComboBox(IphoneLabelInformation informationLabel, String[] colorTypes) {
        super(colorTypes);

        this.informationLabel = informationLabel;

        setAlignmentX(Component.LEFT_ALIGNMENT);
        setFont(new Font("SansSerif", Font.PLAIN, 20));

        int height = 40; // <-- your desired height

        setPreferredSize(new Dimension(150, height));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        setMinimumSize(new Dimension(50, height));

        addActionListener(e -> {
            Object item = getSelectedItem();
            if (item != null) {
                informationLabel.setProductColor(item.toString());
            }
        });
    }

    public void setChoice(String value) {
        if (value == null || value.isBlank())
            return;

        boolean exists = false;

        for (int i = 0; i < getItemCount(); i++) {
            if (getItemAt(i).equalsIgnoreCase(value)) {
                exists = true;
                break;
            }
        }

        if (!exists)
            return;

        setSelectedItem(value);

        informationLabel.setProductColor(value);
    }
}
