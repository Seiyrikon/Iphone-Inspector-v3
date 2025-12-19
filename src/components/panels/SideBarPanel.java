package components.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import components.frame.PdfViewerFrame;
import model.IphoneLabelInformation;
import model.IphoneModel;
import model.PdfDocument;
import services.device.DeviceService;

public class SideBarPanel extends JPanel{
    ScanButtonPanel scanButton;
    GenerateButtonPanel generateButton;
    PrintButtonPanel printButton;
    Size120x80Panel size120x80Panel;
    Size20x80Panel size20x80Panel;

    public SideBarPanel(InformationContainerPanel infoContainer, DeviceService deviceService, IphoneModel iphone, PdfViewerFrame pdfViewer, IphoneLabelInformation informationLabel, PdfDocument pdf) {

        setPreferredSize(new Dimension(200, 600));
        setLayout(null);
        setBackground(new Color(20, 21, 22));

        scanButton = new ScanButtonPanel(infoContainer, deviceService, iphone, informationLabel);
        scanButton.setBounds(10, 20, 180, 50);
        add(scanButton);

        generateButton = new GenerateButtonPanel(infoContainer, iphone, pdfViewer, informationLabel, pdf);
        generateButton.setBounds(10, 100, 180, 50);
        add(generateButton);

        try {
            printButton = new PrintButtonPanel(pdfViewer, informationLabel, pdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        printButton.setBounds(10, 180, 180, 50);
        add(printButton);
    }
}
