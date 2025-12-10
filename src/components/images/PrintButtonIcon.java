package components.images;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.Constants;

public class PrintButtonIcon extends ImageIcon {
    public PrintButtonIcon() {
        super(PrintButtonIcon.class.getResource(Constants.PRINT_ICON.get()));

        Image img = getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setImage(img);
    }
}
