package components.images;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.Constants;

public class PrinterFailedIcon extends ImageIcon {
    public PrinterFailedIcon() {
        super(PrinterFailedIcon.class.getResource(Constants.PRINTER_FAILED.get()));

        Image img = getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setImage(img);
    }
}
