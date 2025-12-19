package components.images;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.Constants;

public class DisconnectedIcon extends ImageIcon {
    public DisconnectedIcon() {
        super(DisconnectedIcon.class.getResource(Constants.DISCONNECTED_ICON.get()));

        Image img = getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setImage(img);
    }
}
