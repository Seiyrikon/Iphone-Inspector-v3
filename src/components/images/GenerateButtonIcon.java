package components.images;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.Constants;

public class GenerateButtonIcon extends ImageIcon{
    public GenerateButtonIcon() {
        super(GenerateButtonIcon.class.getResource(Constants.GENERATE_ICON.get()));

        Image img = getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setImage(img);
    }
}
