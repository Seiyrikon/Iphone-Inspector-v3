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
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
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
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.zebra.sdk.graphics.ZebraImageFactory;

import components.frame.PdfViewerFrame;
import components.labels.Size120x80IconLabel;
import components.labels.Size120x80TextLabel;
import fr.w3blog.zpl.model.ZebraLabel;
import model.IphoneLabelInformation;
import model.PdfDocument;
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
    PdfDocument pdf;

    public Size120x80Panel(PdfViewerFrame pdfViewer, IphoneLabelInformation informationLabel, PdfDocument pdf) {
        this.pdfViewer = pdfViewer;
        this.informationLabel = informationLabel;
        this.pdf = pdf;

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
                // generatePdf();
                viewPdf();
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
        //Option 1
        String zplLabel = "^XA^MMT^PW945^LL1417^LS0^BY2,3,24^FT33,1184^BCN,,N,N^FD>;"
        + informationLabel.getEid().toString() +
        "^FS^BY2,3,24^FT33,1235^BCN,,N,N^FD>:"
        + informationLabel.getSerialNo() + "^FS^BY2,3,24^FT33,1287^BCN,,N,N^FD>;" +
        informationLabel.getImei()
        +
        "^FS^BY2,2,24^FT720,1176^BUN,,Y,N^FD19425337371^FS^BY2,3,24^FT605,1235^BCN,,N,N^FD>;"
        + informationLabel.getImei2() + "^FS^FT33,1157^A0N,17,16^FH\\^FDEID " +
        informationLabel.getEid()
        + "^FS^FT33,1207^A0N,17,16^FH\\^FD(S) Serial No. " +
        informationLabel.getSerialNo()
        + "^FS^FT33,1261^A0N,17,16^FH\\^FDIMEI/MEID " + informationLabel.getImei()
        + "^FS^FT686,1166^A0N,17,16^FH\\^FDUPC^FS^FT605,1207^A0N,17,16^FH\\^FDIMEI2 "
        + informationLabel.getImei2()
        + "^FS^FT259,371^A@N,174,174,85620388.TTF^FH\\^CI28^FDText^FS^CI27FT33,1116^A0N,17,16^FH\\^FDOther items as marked thereon Model A2886^FS^FT33,1095^A0N,17,16^FH\\^FD"
        + informationLabel.getModelRegion() + " " + informationLabel.getProductType()
        + ", "
        + informationLabel.getProductColor() + ", " +
        informationLabel.getStorageType() + "^FS^PQ1,0,1,Y^XZ";
        generatePdf(zplLabel);
        //Option 1

        //Option 2
        // generatePdfImage();
        //Option 2
        
        pdfViewer.is120x80 = true;
        pdfViewer.setSize(600, 700);
        pdfViewer.setVisible(true);
        pdfViewer.controller.openDocument("C:/txt/example.pdf");
    }

    private void generatePdf(String zplLabel) {
        try {
            File dir = new File("C:/txt");
            if (!dir.exists()) {
                dir.mkdirs(); // Creates directory if missing
            }
            System.out.println("File created!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        File pdfFile = new File("C:/txt/example.pdf");
        pdfFile.delete();

        FileWriter writer = null;
        try {
            writer = new FileWriter("C:/txt/example.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            writer.write(zplLabel);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        CommandResult result = CommandExecutor.runTool(Constants.STRING_ZPL2PDF.get(),
                Constants.STRING_ZPL_I.get(), "C:/txt/example.txt",
                Constants.STRING_ZPL_O.get(),
                "C:/txt/", Constants.STRING_ZPL_N.get() +
                        Constants.PDF_120X80.get());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // return result;
    }

    private void generatePdfImage() {
        try {
            File dir = new File("C:/txt");
            if (!dir.exists()) {
                dir.mkdirs(); // Creates directory if missing
            }
            System.out.println("File created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    "EID " + informationLabel.getEid(),
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    44, 1280,
                    "(S) Serial No. " + informationLabel.getSerialNo(),
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    44, 1333,
                    "IMEI/MEID " + informationLabel.getImei(),
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    44, 1154,
                    informationLabel.getModelRegion() + " " + informationLabel.getProductType() + ", "
                            + informationLabel.getProductColor() + ", " + informationLabel.getStorageType(),
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
            drawFccLogo(cs, doc,
                    30, 73);
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
                    informationLabel.getEid(),
                    height, 11, 38);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* -------- BARCODE 2 -------- */
        try {
            drawBarcode(
                    cs, doc,
                    informationLabel.getSerialNo(),
                    height, 11, 25);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* -------- BARCODE 3 -------- */
        try {
            drawBarcode(
                    cs, doc,
                    informationLabel.getImei(),
                    height, 11, 13, 1.2f);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* -------- UPC A -------- */
        try {
            drawUpcABarcode(
                    cs, doc, 142, 27);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    565, 1260,
                    "UPC",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // informationLabel.setImei2("abcd");

        if (informationLabel.getImei2().equals("N/A") || !informationLabel.getImei2().isBlank()) {

            try {
                drawText(cs, font, 4,
                        581, 1324,
                        "IMEI2 " + informationLabel.getImei2(),
                        height);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                drawBarcode(
                        cs, doc,
                        informationLabel.getImei2(),
                        height, 140, 15, 1.2f);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // try {
        // drawText(cs, font, 4,
        // 640, 1300,
        // "019425705186",
        // height);
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        try {
            cs.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            File pngFile = new File("C:/images/rendered.png");
            File pdfFile = new File("C:/txt/example.pdf");

            pngFile.delete();
            pdfFile.delete();

            doc.save("C:/txt/example.pdf");
            pdf.setPdf(doc);
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImageWithDPI(0, 300f);
            File outputFile = new File("C:/images/rendered.png");
            ImageIO.write(image, "PNG", outputFile);
            System.out.println("Doc has been set");
        } catch (Exception e) {
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
            float pageHeight, int imageXPosition, int imageYPosition) throws Exception {

        Code128Writer writer = new Code128Writer();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);

        BitMatrix matrix = writer.encode(
                data,
                BarcodeFormat.CODE_128,
                1,
                1,
                hints);

        BufferedImage img = MatrixToImageWriter.toBufferedImage(matrix);

        float moduleWidthMm = 0.17f;
        float barHeightMm = 2.20f;

        float barcodeWidthMm = matrix.getWidth() * moduleWidthMm;

        cs.drawImage(
                LosslessFactory.createFromImage(doc, img),
                imageXPosition,
                imageYPosition,
                mmToPt(barcodeWidthMm),
                mmToPt(barHeightMm));
    }

    private void drawBarcode(PDPageContentStream cs,
            PDDocument doc,
            String data,
            float pageHeight, int imageXPosition, int imageYPosition, float dots) throws Exception {

        Code128Writer writer = new Code128Writer();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);

        BitMatrix matrix = writer.encode(
                data,
                BarcodeFormat.CODE_128,
                1,
                1,
                hints);

        BufferedImage img = MatrixToImageWriter.toBufferedImage(matrix);

        float baseModuleMm = 0.17f;
        float barHeightMm = 2.20f;

        float moduleWidthMm = baseModuleMm * dots;

        float barcodeWidthMm = matrix.getWidth() * moduleWidthMm;

        cs.drawImage(
                LosslessFactory.createFromImage(doc, img),
                imageXPosition,
                imageYPosition,
                mmToPt(barcodeWidthMm),
                mmToPt(barHeightMm));
    }

    private void drawUpcABarcode(PDPageContentStream cs,
            PDDocument doc,
            int imageXPosition, int imageYPosition) throws Exception {

        PDImageXObject img = PDImageXObject.createFromFile(
                "C:/images/upc_barcode.png",
                doc);

        cs.drawImage(img, imageXPosition,
                imageYPosition,
                mmToPt(27),
                mmToPt(5));
    }

    private void drawFccLogo(PDPageContentStream cs,
            PDDocument doc, int imageXPosition, int imageYPosition) throws Exception {

        PDImageXObject img = PDImageXObject.createFromFile(
                "C:/images/fcc_logo.png",
                doc);

        cs.drawImage(img, imageXPosition,
                imageYPosition,
                mmToPt(5),
                mmToPt(5));
    }
}
