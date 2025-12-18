package components.panels;

import java.awt.Color;

import javax.swing.JPanel;

import components.frame.PdfViewerFrame;
import components.labels.Size20x80IconLabel;
import components.labels.Size20x80TextLabel;
import model.IphoneLabelInformation;

public class Size20x80Black extends JPanel {
    Size20x80IconLabel iconLabel;
    Size20x80TextLabel textLabel;

    private boolean pressed = false;
    private Color normalColor = new Color(56, 57, 58);
    private Color hoverColor = new Color(240, 240, 240);

    PdfViewerFrame pdfViewer;
    IphoneLabelInformation informationLabel;

    
}
