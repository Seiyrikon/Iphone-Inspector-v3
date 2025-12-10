package components.combobox;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;

public class StorageTypeComboBox extends JComboBox<String>{
    public StorageTypeComboBox(String[] storageTypes) {
        super(storageTypes);

        setAlignmentX(Component.LEFT_ALIGNMENT);
        setFont(new Font("SansSerif", Font.PLAIN, 20));

        int height = 40; // <-- your desired height

        setPreferredSize(new Dimension(150, height));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        setMinimumSize(new Dimension(50, height));
    }
}
