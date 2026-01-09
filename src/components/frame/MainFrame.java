package components.frame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import components.panels.BodyContainerPanel;
import components.panels.InformationContainerPanel;
import components.panels.MainPanel;
import components.panels.SideBarPanel;
import components.panels.TitleBarPanel;
import model.IphoneLabelInformation;
import model.IphoneModel;
import model.PdfDocument;
import services.device.DeviceService;

public class MainFrame extends JFrame {
    TitleBarPanel titleBar;
    SideBarPanel sideBar;
    BodyContainerPanel bodyContainer;
    MainPanel mainPanel;
    InformationContainerPanel infoContainer;
    IphoneModel iphone;
    PdfViewerFrame pdfViewer;
    IphoneLabelInformation informationLabel;
    PdfDocument pdf;

    public MainFrame() {
        DeviceService deviceService = new DeviceService(null);
        setUndecorated(true);
        setSize(800, 720);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));

        mainPanel = new MainPanel();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        titleBar = new TitleBarPanel();
        bodyContainer = new BodyContainerPanel();

        infoContainer = bodyContainer.getInfoContainer();
        iphone = new IphoneModel();

        pdfViewer = new PdfViewerFrame();

        informationLabel = new IphoneLabelInformation();

        pdf = new PdfDocument();

        sideBar = new SideBarPanel(infoContainer, deviceService, iphone, pdfViewer, informationLabel, pdf);

        setContentPane(mainPanel);

        mainPanel.add(titleBar, BorderLayout.NORTH);
        mainPanel.add(sideBar, BorderLayout.WEST);
        mainPanel.add(bodyContainer, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
