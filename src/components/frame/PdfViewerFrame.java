package components.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import utils.Constants;

public class PdfViewerFrame extends JFrame{
    public SwingController controller;
    SwingViewBuilder factory;
    JPanel viewerPanel;

    public boolean is120x80 = true;

    public PdfViewerFrame() {
        super(Constants.PDF_VIEWER_TITLE.get());
        controller = new SwingController();
        factory = new SwingViewBuilder(controller);
        viewerPanel = factory.buildViewerPanel();

        setLayout(new BorderLayout());
        add(viewerPanel, BorderLayout.CENTER);
    }
}
