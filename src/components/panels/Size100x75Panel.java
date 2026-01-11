package components.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.MouseAdapter;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import components.frame.PdfViewerFrame;
import components.labels.Size100x75IconLabel;
import components.labels.Size100x75TextLabel;
import components.labels.Size20x80IconLabel;
import components.labels.Size20x80TextLabel;
import model.IphoneLabelInformation;
import utils.Constants;

public class Size100x75Panel extends JPanel {
    Size100x75IconLabel iconLabel;
    Size100x75TextLabel textLabel;

    private boolean pressed = false;
    private Color normalColor = new Color(56, 57, 58);
    private Color hoverColor = new Color(240, 240, 240);

    PdfViewerFrame pdfViewer;
    IphoneLabelInformation informationLabel;

    public Size100x75Panel(PdfViewerFrame pdfViewer, IphoneLabelInformation informationLabel) {
        this.pdfViewer = pdfViewer;
        this.informationLabel = informationLabel;

        setOpaque(false);
        setBackground(new Color(56, 57, 58));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        iconLabel = new Size100x75IconLabel();
        textLabel = new Size100x75TextLabel();

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
                System.out.println("Size 100mm x 75mm Button clicked!");
                informationLabel.set120x80(false);
                informationLabel.set30x90(false);
                informationLabel.set100x75(true);
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
        generatePdfImage();

        try {
            BufferedImage finalImage = ImageIO.read(new File("./images/rendered.png"));
            JFrame frame = new JFrame("Label Preview");

            Image scaled = finalImage.getScaledInstance(400, -1, Image.SCALE_FAST);

            JLabel label = new JLabel(new ImageIcon(scaled));
            frame.add(label);

            Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource(Constants.CELLWEGO_ICON.get()));
            frame.setIconImage(icon);

            frame.pack();
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Option 2
    }

    private void generatePdfImage() {
        try {
            // File dir = new File("C:/txt");
            // if (!dir.exists()) {
            // dir.mkdirs(); // Creates directory if missing
            // }
            File txtDir = new File("./txt");
            if (!txtDir.exists()) {
                txtDir.mkdirs(); // Creates directory if missing
            }
            File imagesDir = new File("./images");
            if (!imagesDir.exists()) {
                imagesDir.mkdirs(); // Creates directory if missing
            }
            System.out.println("File created!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        float height = mmToPt(100);
        float width = mmToPt(75);

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(width, height));

        doc.addPage(page);
        PDType0Font font = null;
        try {
            font = PDType0Font.load(doc, new File("fonts/SF-Pro.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PDPageContentStream cs = null;
        try {
            cs = new PDPageContentStream(doc, page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 1000,
                    "EID " + informationLabel.getEid(),
                    height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 1055,
                    "(S) Serial No. " + informationLabel.getSerialNo(),
                    height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 1115,
                    "IMEI/MEID " + informationLabel.getImei(),
                    height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 930,
                    informationLabel.getModelRegion() + " " + informationLabel.getProductType() + ", "
                            + informationLabel.getProductColor() + ", " + informationLabel.getStorageType(),
                    height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 955,
                    "Other items as marked thereon Model A2481",
                    height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawBcLogo(cs, doc,
                    15, 67);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawFccLogo(cs, doc,
                    30, 65);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawBarcode(
                    cs, doc,
                    informationLabel.getEid(),
                    height, 16, 37);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* -------- BARCODE 2 -------- */
        try {
            drawBarcode(
                    cs, doc,
                    informationLabel.getSerialNo(),
                    height, 16, 23);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* -------- BARCODE 3 -------- */
        try {
            drawBarcode(
                    cs, doc,
                    informationLabel.getImei(),
                    height, 16, 9, 1.2f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* -------- UPC A -------- */
        try {
            drawUpcABarcode(
                    cs, doc, 152, 29);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    623, 1015,
                    "UPC",
                    height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!informationLabel.getImei2().isBlank()) {

            try {
                drawText(cs, font, 5,
                        655, 1098,
                        "IMEI2 " + informationLabel.getImei2(),
                        height);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                drawBarcode(
                        cs, doc,
                        informationLabel.getImei2(),
                        height, 158, 13, 1.2f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(!informationLabel.getIcc().isBlank()) {
            try {
                drawText(cs, font, 5,
                        655, 1153,
                        "()ICCID" + informationLabel.getIcc(),
                        height);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                drawBarcode(
                        cs, doc,
                        informationLabel.getIcc(),
                        height, 158, 0, 1.2f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            drawText(cs, font, 4,
                    211, 865,
                    "FCC ID: BCG-E4000A",
                    height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    211, 885,
                    "IC: 579C-E4000A",
                    height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File pngFile = new File("./images/rendered.png");
            File pdfFile = new File("./txt/example.pdf");

            pngFile.delete();
            pdfFile.delete();

            doc.save("./txt/example.pdf");
            // pdf.setPdf(doc);
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImageWithDPI(0, 600f);
            File outputFile = new File("./images/rendered.png");
            ImageIO.write(image, "PNG", outputFile);
            System.out.println("Doc has been set");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private float dotToPt(float dots) {
        return dots * 72f / 300f;
    }

    private float mmToPt(float mm) {
        return mm * 72f / 25.4f;
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
                "./static-images/upc_barcode.png",
                doc);

        cs.drawImage(img, imageXPosition,
                imageYPosition,
                mmToPt(27),
                mmToPt(5));
    }

    private void drawFccLogo(PDPageContentStream cs,
            PDDocument doc, int imageXPosition, int imageYPosition) throws Exception {

        PDImageXObject img = PDImageXObject.createFromFile(
                "./static-images/fcc_logo.png",
                doc);

        cs.drawImage(img, imageXPosition,
                imageYPosition,
                mmToPt(6),
                mmToPt(7));
    }

    private void drawBcLogo(PDPageContentStream cs,
            PDDocument doc, int imageXPosition, int imageYPosition) throws Exception {

        PDImageXObject img = PDImageXObject.createFromFile(
                "./static-images/bc_logo.png",
                doc);

        cs.drawImage(img, imageXPosition,
                imageYPosition,
                mmToPt(5),
                mmToPt(5));
    }
}
