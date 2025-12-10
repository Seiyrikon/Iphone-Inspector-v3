package components.images;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.Constants;

public class ScanButtonIcon extends ImageIcon{
    public ScanButtonIcon() {
        super(ScanButtonIcon.class.getResource(Constants.SCAN_ICON.get()));

        Image img = getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setImage(img);
    }
}
