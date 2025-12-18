package components.images;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.Constants;

public class SizeIconBlack extends ImageIcon {
    public SizeIconBlack() {
        super(SizeIcon.class.getResource(Constants.SIZE_ICON_BlACK.get()));

        Image img = getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setImage(img);
    }
}
