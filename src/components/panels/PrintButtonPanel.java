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

    public PrintButtonPanel(PdfViewerFrame pdfViewer, IphoneLabelInformation informationLabel, PdfDocument pdf) {
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
                // System.out.println(pdf.getPdf().getNumberOfPages());
                // try {
                // printLabel();
                // System.out.println("Print sent!");
                // } catch (Exception err) {
                // err.printStackTrace();
                // System.out.println("Print failed!");
                // }

                //Option 1
                // try {
                //     printLabel();
                // } catch (Exception e1) {
                //     // TODO Auto-generated catch block
                //     e1.printStackTrace();
                // }
                //Option 1

                //Option 2
                try {
                    printLabelImage();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //Option 2
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
    }

    private void printLabel() throws Exception {
        DiscoveredUsbPrinter usbPrinter = null;
        for (DiscoveredUsbPrinter p : UsbDiscoverer.getZebraUsbPrinters()) {
            usbPrinter = p;
            break;
        }

        if (usbPrinter == null) {
            throw new Exception("No Zebra USB printer detected.");
        }

        Connection conn = usbPrinter.getConnection();

        PrinterLanguage printerLanguage = ZebraPrinterFactory.getInstance(conn).getPrinterControlLanguage();
        conn.write(getConfigLabel(printerLanguage));

        conn.close();
    }

    public void printLabelImage() throws Exception {

        DiscoveredUsbPrinter usbPrinter = null;
        for (DiscoveredUsbPrinter p : UsbDiscoverer.getZebraUsbPrinters()) {
            usbPrinter = p;
            break;
        }

        if (usbPrinter == null) {
            throw new Exception("No Zebra USB printer detected.");
        }

        File image = new File("C:/images/rendered.png");

        if (image.exists() && image.isFile()) {
            Connection printerConnection = null;

            try {
                printerConnection = usbPrinter.getConnection();
                printerConnection.open();

                ZebraImageI toPrint = ZebraImageFactory.getImage("C:/images/rendered.png");

                if(informationLabel.is120x80() == true) {
                    System.out.println("Printing in 80mm x 120mm");
                    ZebraPrinterFactory.getInstance(printerConnection).printImage(toPrint, 0, 0, 945, 1417, false); // for 80mm x 120mm
                } else {
                    System.out.println("Printing in 90mm x 30mm");
                    ZebraPrinterFactory.getInstance(printerConnection).printImage(toPrint, 0, 0, 945, 354, false); // for 80mm x 30mm
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                printerConnection.close();
            }
        }

        // PrinterLanguage printerLanguage =
        // ZebraPrinterFactory.getInstance(conn).getPrinterControlLanguage();
        // conn.write(getConfigLabel(printerLanguage));

        // conn.close();
    }

    private byte[] getConfigLabel(PrinterLanguage printerLanguage) {

        byte[] configLabel = null;
        if (printerLanguage == PrinterLanguage.ZPL) {
            // configLabel =
            // "^XA^FT65,255^A0N,20,40^^BY3^BCN,80,Y,N,N^FDFFXDD20N0F0X^FS^XZ".getBytes();
            // String zplLabel = "^XA^MMT^PW945^LL1417^LS0^BY2,3,24^FT33,1184^BCN,,N,N^FD>;"
            // + informationLabel.getEid().toString() +
            // "^FS^BY2,3,24^FT33,1235^BCN,,N,N^FD>:" + informationLabel.getSerialNo() +
            // "^FS^BY2,3,24^FT33,1287^BCN,,N,N^FD>;" + informationLabel.getImei() +
            // "^FS^BY2,2,24^FT720,1176^BUN,,Y,N^FD19425337371^FS^BY2,3,24^FT605,1235^BCN,,N,N^FD>;"
            // + informationLabel.getImei2() + "^FS^FT33,1157^A0N,17,16^FH\\^FDEID " +
            // informationLabel.getEid() + "^FS^FT33,1207^A0N,17,16^FH\\^FD(S) Serial No. "
            // + informationLabel.getSerialNo() + "^FS^FT33,1261^A0N,17,16^FH\\^FDIMEI/MEID
            // " + informationLabel.getImei() +
            // "^FS^FT686,1166^A0N,17,16^FH\\^FDUPC^FS^FT605,1207^A0N,17,16^FH\\^FDIMEI2 " +
            // informationLabel.getImei2() + "^FS^FT33,1116^A0N,17,16^FH\\^FDOther items as
            // marked thereon Model A2886^FS^FT33,1095^A0N,17,16^FH\\^" +
            // informationLabel.getModelRegion() + " " + informationLabel.getProductType() +
            // ", " + informationLabel.getProductColor() + ", " +
            // informationLabel.getStorageType() + "^FS";
            String zplLabel = "^XA^MMT^PW945^LL1417^LS0^BY2,3,24^FT33,1184^BCN,,N,N^FD>;"
                    + informationLabel.getEid().toString() + "^FS^BY2,3,24^FT33,1235^BCN,,N,N^FD>:"
                    + informationLabel.getSerialNo() + "^FS^BY2,3,24^FT33,1287^BCN,,N,N^FD>;"
                    + informationLabel.getImei()
                    + "^FS^BY2,2,24^FT720,1176^BUN,,Y,N^FD19425337371^FS^BY2,3,24^FT605,1235^BCN,,N,N^FD>;"
                    + informationLabel.getImei2() + "^FS^FT33,1157^A0N,17,16^FH\\^FDEID " + informationLabel.getEid()
                    + "^FS^FT33,1207^A0N,17,16^FH\\^FD(S) Serial No. " + informationLabel.getSerialNo()
                    + "^FS^FT33,1261^A0N,17,16^FH\\^FDIMEI/MEID " + informationLabel.getImei()
                    + "^FS^FT686,1166^A0N,17,16^FH\\^FDUPC^FS^FT605,1207^A0N,17,16^FH\\^FDIMEI2 "
                    + informationLabel.getImei2()
                    + "^FS^FT33,1116^A0N,17,16^FH\\^FDOther items as marked thereon Model A2886^FS^FT33,1095^A0N,17,16^FH\\^FD"
                    + informationLabel.getModelRegion() + " " + informationLabel.getProductType() + ", "
                    + informationLabel.getProductColor() + ", " + informationLabel.getStorageType()
                    + "^FS^PQ1,0,1,Y^XZ";
            configLabel = zplLabel.getBytes();

        }
        return configLabel;
    }

    private void printImage() {
        File image = new File("C:/images/rendered.png");

        if (image.exists() && image.isFile()) {
            Connection printerConnection = null;

            try {

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
