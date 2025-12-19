package components.images;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.Constants;

public class PhoneFailedIcon extends ImageIcon {
    public PhoneFailedIcon() {
        super(PhoneFailedIcon.class.getResource(Constants.PHONE_FAILED.get()));

        Image img = getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setImage(img);
    }
}
