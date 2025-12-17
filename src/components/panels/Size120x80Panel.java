package components.panels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import components.frame.PdfViewerFrame;
import components.labels.Size120x80IconLabel;
import components.labels.Size120x80TextLabel;
import model.IphoneLabelInformation;
import utils.CommandExecutor;
import utils.CommandResult;
import utils.Constants;

public class Size120x80Panel extends JPanel {
    Size120x80IconLabel iconLabel;
    Size120x80TextLabel textLabel;

    private boolean pressed = false;
    private Color normalColor = new Color(56, 57, 58);
    private Color hoverColor = new Color(240, 240, 240);

    public PdfViewerFrame pdfViewer;
    IphoneLabelInformation informationLabel;

    public Size120x80Panel(PdfViewerFrame pdfViewer, IphoneLabelInformation informationLabel) {
        this.pdfViewer = pdfViewer;
        this.informationLabel = informationLabel;

        setOpaque(false);
        setBackground(new Color(56, 57, 58));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        iconLabel = new Size120x80IconLabel();
        textLabel = new Size120x80TextLabel();

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
                System.out.println("Size 120mm x 80mm Button clicked!");
                generatePdf();
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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int offset = pressed ? 2 : 0;

        g2.setColor(getBackground());
        g2.fillRoundRect(offset, offset, getWidth() - 6, getHeight() - 6, 20, 20);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(offset, offset, getWidth() - 6, getHeight() - 6, 20, 20);

        g2.dispose();
        super.paintComponent(g);
    }

    private void viewPdf() {
        // String zplLabel = "^XA^MMT^PW945^LL1417^LS0^BY2,3,24^FT33,1184^BCN,,N,N^FD>;"
        // + informationLabel.getEid().toString() +
        // "^FS^BY2,3,24^FT33,1235^BCN,,N,N^FD>:"
        // + informationLabel.getSerialNo() + "^FS^BY2,3,24^FT33,1287^BCN,,N,N^FD>;" +
        // informationLabel.getImei()
        // +
        // "^FS^BY2,2,24^FT720,1176^BUN,,Y,N^FD19425337371^FS^BY2,3,24^FT605,1235^BCN,,N,N^FD>;"
        // + informationLabel.getImei2() + "^FS^FT33,1157^A0N,17,16^FH\\^FDEID " +
        // informationLabel.getEid()
        // + "^FS^FT33,1207^A0N,17,16^FH\\^FD(S) Serial No. " +
        // informationLabel.getSerialNo()
        // + "^FS^FT33,1261^A0N,17,16^FH\\^FDIMEI/MEID " + informationLabel.getImei()
        // + "^FS^FT686,1166^A0N,17,16^FH\\^FDUPC^FS^FT605,1207^A0N,17,16^FH\\^FDIMEI2 "
        // + informationLabel.getImei2()
        // +
        // "^FS^FT259,371^A@N,174,174,85620388.TTF^FH\\^CI28^FDText^FS^CI27FT33,1116^A0N,17,16^FH\\^FDOther
        // items as marked thereon Model A2886^FS^FT33,1095^A0N,17,16^FH\\^FD"
        // + informationLabel.getModelRegion() + " " + informationLabel.getProductType()
        // + ", "
        // + informationLabel.getProductColor() + ", " +
        // informationLabel.getStorageType() + "^FS^PQ1,0,1,Y^XZ";

        // String zplLabel = "^XA\r\n" + //
        // "~TA000\r\n" + //
        // "~JSN\r\n" + //
        // "^LT0\r\n" + //
        // "^MNW\r\n" + //
        // "^MTT\r\n" + //
        // "^PON\r\n" + //
        // "^PMN\r\n" + //
        // "^LH0,0\r\n" + //
        // "^JMA\r\n" + //
        // "^PR2,2\r\n" + //
        // "~SD25\r\n" + //
        // "^JUS\r\n" + //
        // "^LRN\r\n" + //
        // "^CI27\r\n" + //
        // "^PA0,1,1,0\r\n" + //
        // "^XZ\r\n" + //
        // "^XA\r\n" + //
        // "^MMT\r\n" + //
        // "^PW945\r\n" + //
        // "^LL1417\r\n" + //
        // "^LS0\r\n" + //
        // "^BY5,3,189^FT239,710^BCN,,Y,N\r\n" + //
        // "^FH\\^FD>;123456789012^FS\r\n" + //
        // "^FT259,371^A@N,174,174,85620388.TTF^FH\\^CI28^FDText^FS^CI27\r\n" + //
        // "^FT259,1033^A0N,174,221^FH\\^CI28^FDText^FS^CI27\r\n" + //
        // "^LRY^FO171,835^GB630,0,332^FS\r\n" + //
        // "^LRN\r\n" + //
        // "^PQ1,0,1,Y\r\n" + //
        // "^XZ";

        String zplLabel = "";
        generatePdf();
        pdfViewer.is120x80 = true;
        pdfViewer.setSize(600, 700);
        pdfViewer.setVisible(true);
        pdfViewer.controller.openDocument("C:/txt/example.pdf");
    }

    private void generatePdf() {
        // try {
        // File dir = new File("C:/txt");
        // if (!dir.exists()) {
        // dir.mkdirs(); // Creates directory if missing
        // }

        // FileWriter writer = new FileWriter("C:/txt/example.txt");
        // writer.write(zplLabel);
        // writer.close();
        // System.out.println("File created!");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // CommandResult result =
        // CommandExecutor.runTool(Constants.STRING_ZPL2PDF.get(),
        // Constants.STRING_ZPL_I.get(), "C:/txt/example.txt",
        // Constants.STRING_ZPL_O.get(),
        // "C:/txt/", Constants.STRING_ZPL_N.get() +
        // Constants.PDF_120X80.get());
        // try {
        // Thread.sleep(2000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // return result;

        float height = mmToPt(120);
        float width = mmToPt(80);

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(width, height));

        doc.addPage(page);
        PDType0Font font = null;
        try {
            font = PDType0Font.load(doc, new File("fonts/SF-Pro.ttf"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PDPageContentStream cs = null;
        try {
            cs = new PDPageContentStream(doc, page);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    44, 1229,
                    "EID 89049032007008882600086562427831",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    44, 1280,
                    "(S) Serial No. YW1J40M992",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    44, 1333,
                    "IMEI/MEID 357140962882770",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    581, 1324,
                    "IMEI2 357140952066630",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    565, 1266,
                    "UPC",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    44, 1154,
                    "MNGK3ZD/A iPhone 13, Green, 128GB",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    44, 1175,
                    "Other items as marked thereon Model A2482",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    211, 1070,
                    "FCC ID: BCG-E4000A",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    211, 1098,
                    "IC: 579C-E4000A",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawBarcode(
                    cs, doc,
                    "89049032007008882600086562427831",
                    44, 1262,
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* -------- BARCODE 2 -------- */
        try {
            drawBarcode(
                    cs, doc,
                    "35282448939503",
                    44, 1314,
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            cs.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            doc.save("label.pdf");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            doc.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private float dotToPt(float dots) {
        return dots * 72f / 300f;
    }

    private float mmToPt(float mm) {
        return mm * 72f / 25.4f;
    }

    private int mmToDots(float mm) {
        return Math.round(mm * 300f / 25.4f);
    }

    private void drawText(PDPageContentStream cs,
            PDType0Font font,
            float fontSize,
            float xDot,
            float yDot,
            String text,
            float pageHeight) throws Exception {

        cs.beginText();
        cs.setFont(font, fontSize);
        cs.newLineAtOffset(
                dotToPt(xDot),
                pageHeight - dotToPt(yDot));
        cs.showText(text);
        cs.endText();
    }

    private void drawBarcode(PDPageContentStream cs,
            PDDocument doc,
            String data,
            float xDot,
            float yDot,
            float pageHeight) throws Exception {

        int barcodeWidth = mmToDots(0.17f);
        int barcodeHeight = mmToDots(2.20f);

        Code128Writer writer = new Code128Writer();
        BitMatrix matrix = writer.encode(
                data, BarcodeFormat.CODE_128, data.length() * 11, barcodeHeight);

        int scaledWidth = matrix.getWidth() * barcodeWidth;
        int scaledHeight = matrix.getHeight();

        BufferedImage img = new BufferedImage(
                scaledWidth,
                scaledHeight,
                BufferedImage.TYPE_BYTE_BINARY);

        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                int color = matrix.get(x, y) ? 0x000000 : 0xFFFFFF;

                for (int dx = 0; dx < barcodeWidth; dx++) {
                    img.setRGB(x * barcodeWidth + dx, y, color);
                }
            }
        }

        cs.drawImage(
                LosslessFactory.createFromImage(doc, img),
                dotToPt(xDot),
                pageHeight - dotToPt(yDot) - dotToPt(scaledHeight));
    }
}
