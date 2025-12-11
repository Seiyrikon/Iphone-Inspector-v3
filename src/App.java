import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import components.frame.MainFrame;

public class App {
    public static void main(String[] args) throws Exception {
        new MainFrame();

        // SwingController controller = new SwingController();

        // SwingViewBuilder factory = new SwingViewBuilder(controller);

        // // Build ONLY the document viewer panel
        // JPanel viewerPanel = factory.buildViewerPanel();
        
        // JFrame frame = new JFrame("PDF Viewer");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new BorderLayout());
        // frame.add(viewerPanel, BorderLayout.CENTER);

        // frame.setSize(800, 700);
        // frame.setVisible(true);

        // controller.openDocument("sample_converted.pdf");
    }
}
