package components.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.fixup.processor.PDDocumentProcessor;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.graphics.ZebraImageFactory;
import com.zebra.sdk.graphics.ZebraImageI;
import com.zebra.sdk.printer.PrinterLanguage;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.discovery.DiscoveredUsbPrinter;
import com.zebra.sdk.printer.discovery.UsbDiscoverer;

import components.frame.PdfViewerFrame;
import components.labels.PrintButtonIconLabel;
import components.labels.PrintButtonTextLabel;
import fr.w3blog.zpl.model.element.ZebraGraficBox;
import model.IphoneLabelInformation;
import model.PdfDocument;

public class PrintButtonPanel extends JPanel {
    PrintButtonIconLabel iconLabel;
    PrintButtonTextLabel textLabel;

    private boolean pressed = false;
    private Color normalColor = new Color(56, 57, 58);
    private Color hoverColor = new Color(240, 240, 240);

    IphoneLabelInformation informationLabel;
    PdfDocument pdf;

    DiscoveredUsbPrinter usbPrinter = null;

    Connection printerConnection = null;

    public PrintButtonPanel(PdfViewerFrame pdfViewer, IphoneLabelInformation informationLabel, PdfDocument pdf)
            throws Exception {
        this.informationLabel = informationLabel;
        this.pdf = pdf;

        setOpaque(false);
        setBackground(new Color(56, 57, 58));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        iconLabel = new PrintButtonIconLabel();
        textLabel = new PrintButtonTextLabel();

        add(Box.createHorizontalGlue());
        add(iconLabel);
        add(Box.createHorizontalStrut(10));
        add(textLabel);
        add(Box.createHorizontalGlue());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                textLabel.setForeground(new Color(56, 57, 58));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalColor);
                textLabel.setForeground(Color.WHITE);
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Print Button clicked!");
                try {
                    printLabelImage();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });

        try {
            for (DiscoveredUsbPrinter p : UsbDiscoverer.getZebraUsbPrinters()) {
                usbPrinter = p;
                break;
            }

            if (usbPrinter != null) {
                // throw new Exception("No Zebra USB printer detected.");
                printerConnection = usbPrinter.getConnection();
                printerConnection.open();
            }
        } catch (ConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printLabelImage() throws Exception {
        if (usbPrinter == null) {
            try {
                for (DiscoveredUsbPrinter p : UsbDiscoverer.getZebraUsbPrinters()) {
                    usbPrinter = p;
                    break;
                }

                if (usbPrinter != null) {
                    // throw new Exception("No Zebra USB printer detected.");
                    printerConnection = usbPrinter.getConnection();
                    printerConnection.open();
                } else {
                    System.out.println("No Printer Detected");
                    return;
                }
            } catch (ConnectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        File image = new File("C:/images/rendered.png");

        if (image.exists() && image.isFile()) {

            try {
                ZebraImageI toPrint = ZebraImageFactory.getImage("C:/images/rendered.png");

                if (informationLabel.is120x80() == true) {
                    System.out.println("Printing in 80mm x 120mm");
                    ZebraPrinterFactory.getInstance(printerConnection).printImage(toPrint, 0, 0, 945, 1417, false);
                } else {
                    System.out.println("Printing in 90mm x 30mm");
                    ZebraPrinterFactory.getInstance(printerConnection).printImage(toPrint, 0, 0, 945, 354, false);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int offset = pressed ? 2 : 0;

        g2.setColor(getBackground());
        g2.fillRoundRect(offset, offset, getWidth() - 6, getHeight() - 6, 20, 20);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(offset, offset, getWidth() - 6, getHeight() - 6, 20, 20);

        g2.dispose();
    }
}
