package components.images;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.Constants;

public class SizeIcon extends ImageIcon{
    public SizeIcon() {
        super(SizeIcon.class.getResource(Constants.SIZE_ICON.get()));

        Image img = getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setImage(img);
    }
}
