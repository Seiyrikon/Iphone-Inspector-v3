package components.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
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
import components.labels.Size120x80BlackIconLabel;
import components.labels.Size120x80IconLabel;
import components.labels.Size120x80TextLabel;
import model.IphoneLabelInformation;
import model.PdfDocument;
import utils.CommandExecutor;
import utils.CommandResult;
import utils.Constants;

public class Size120x80BlackPanel extends JPanel {
    Size120x80BlackIconLabel iconLabel;
    Size120x80TextLabel textLabel;

    private boolean pressed = false;
    private Color normalColor = new Color(56, 57, 58);
    private Color hoverColor = new Color(240, 240, 240);

    public PdfViewerFrame pdfViewer;
    IphoneLabelInformation informationLabel;
    PdfDocument pdf;

    public Size120x80BlackPanel(PdfViewerFrame pdfViewer, IphoneLabelInformation informationLabel, PdfDocument pdf) {
        this.pdfViewer = pdfViewer;
        this.informationLabel = informationLabel;
        this.pdf = pdf;

        setOpaque(false);
        setBackground(new Color(56, 57, 58));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        iconLabel = new Size120x80BlackIconLabel();
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
                System.out.println("Size 120mm x 80mm Black Button clicked!");
                // generatePdf();
                informationLabel.set120x80(true);
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
    //     String zplLabel = null;
    //     if(informationLabel.getImei2().isBlank() || informationLabel.getImei2().equals("N/A")) {
    //         zplLabel = "^XA\r\n" + //
    //                     "^MMT\r\n" + //
    //                     "^PW945\r\n" + //
    //                     "^LL1417\r\n" + //
    //                     "^LS0\r\n" + //
    //                     "^FO32,1312^GFA,03072,03072,00048,:Z64:\n" + //
    //                     "eJxjYBgFo2AUjIJRQBfALyHBkMDD+PiAxAH5HsbHcgwH5PvleyqYP/bLNxg+4GF8UMdmYC/HDpT4ACJG1Y+qH1U/qn7IqB8FQwoAANXIlUw=:F707\n" + //
    //                     "^FO576,1248^GFA,02304,02304,00036,:Z64:\n" + //
    //                     "eJzt0LFqwzAQBuA7BNLQgNcLtM4rePRgmleRCbirIYuHkihLniFT+xodNXns3NFdmtWjCyb0ZJqAsEspoZv+4XRIHyckgJBfU8SUHz8PcTV//igk6U3+fnwiXQQTTDDBXGnOrdTn7v9MhS3YJTzKh06ZGAibselFh2xObCJeU9GOzLyWhDaBWuqE9AoqRWOzBxK2RCO1Jp2bN+kb4e6S0Eq7wZdvcwBnsotRrsyc2WIPYCO7NgSJm38xkSu3kM7savc6mCWb0jM3rqQ83OJuz4b0HZvGM8KVUtKiydkgGzT8SN7MfGNVumjWpgY+57fTsOnPwZMY/rlnE9ktkOomDDZgY6jYKHM/aaaT/XgS8sd8AYJwoL4=:1730\n" + //
    //                     "^FO96,1056^GFA,00768,00768,00012,:Z64:\n" + //
    //                     "eJytkjFqQzEMhuWa4kKh7hKyFJyT9F0hS+ZcoQcoWEfpUdwbZOnuMaPHR/F7qmzZjUMzVqCH+fifZP8SwOZw2MNmDyUsUYTpu54dUbjnbBw558bhSLQIX4E4GldEvv5gETR93HEx5ghbFvgk3HKRkuACOGYmC59Yq1fR+6BArcI9Pp6h6Qk8wZvw0hh3Va8yNz49V65mTbUt63UyRKneE3Wys5X7o0kumc7jhND0JnoAeS+a+AoSrA++nS3asHb+dQzvnbNPuXOiz3zhOPCQb+nHOmP9MPa9uk/yqMKN+ycXL++y5/5ePbtfH0Z/qm+tzpWf7PPSfR799yzucxnnVea4zX/na4a5j/ugaKG+J2V/6pMdJ+9VteUJ2i5yvHDyHp7K+aF82n7+T/wArGvM/g==:CFDD\n" + //
    //                     "^BY2,3,26^FT44,1262^BCN,,N,N\r\n" + //
    //                     "^FD>;" + informationLabel.getEid() + "^FS\r\n" + //
    //                     "^BY2,3,26^FT44,1314^BCN,,N,N\r\n" + //
    //                     "^FD>;" + informationLabel.getImei() + "^FS\r\n" + //
    //                     "^FT44,1229^ADN,18,10^FH\\^FDEID " + informationLabel.getEid() + "^FS\r\n" + //
    //                     "^FT565,1266^ADN,18,10^FH\\^FDUPC^FS\r\n" + //
    //                     "^FT44,1280^ADN,18,10^FH\\^FD(S) Serial No. " + informationLabel.getSerialNo() + "^FS\r\n" + //
    //                     "^FT44,1333^ADN,18,10^FH\\^FDIMEI/MEID " + informationLabel.getImei() + "^FS\r\n" + //
    //                     "^FT44,1154^ADN,18,10^FH\\^FD" + informationLabel.getModelRegion() + " " + informationLabel.getProductType() + ", " + informationLabel.getProductColor() + ", " + informationLabel.getStorageType() + "^FS\r\n" + //
    //                     "^FT44,1182^ADN,18,10^FH\\^FDOther items as marked thereon Model A2482^FS\r\n" + //
    //                     "^FT211,1070^ADN,18,10^FH\\^FDFCC ID:BCG-E4000A^FS\r\n" + //
    //                     "^FT211,1098^ADN,18,10^FH\\^FDIC: 579C-E4000A^FS\r\n" + //
    //                     "^PQ1,0,1,Y^XZ";
    //     } else {
    //     zplLabel = "^XA\r\n" + //
    //                     "^MMT\r\n" + //
    //                     "^PW945\r\n" + //
    //                     "^LL1417\r\n" + //
    //                     "^LS0\r\n" + //
    //                     "^FO32,1312^GFA,03072,03072,00048,:Z64:\n" + //
    //                     "eJxjYBgFo2AUjIJRQBfALyHBkMDD+PiAxAH5HsbHcgwH5PvleyqYP/bLNxg+4GF8UMdmYC/HDpT4ACJG1Y+qH1U/qn7IqB8FQwoAANXIlUw=:F707\n" + //
    //                     "^FO576,1248^GFA,02304,02304,00036,:Z64:\n" + //
    //                     "eJzt0LFqwzAQBuA7BNLQgNcLtM4rePRgmleRCbirIYuHkihLniFT+xodNXns3NFdmtWjCyb0ZJqAsEspoZv+4XRIHyckgJBfU8SUHz8PcTV//igk6U3+fnwiXQQTTDDBXGnOrdTn7v9MhS3YJTzKh06ZGAibselFh2xObCJeU9GOzLyWhDaBWuqE9AoqRWOzBxK2RCO1Jp2bN+kb4e6S0Eq7wZdvcwBnsotRrsyc2WIPYCO7NgSJm38xkSu3kM7savc6mCWb0jM3rqQ83OJuz4b0HZvGM8KVUtKiydkgGzT8SN7MfGNVumjWpgY+57fTsOnPwZMY/rlnE9ktkOomDDZgY6jYKHM/aaaT/XgS8sd8AYJwoL4=:1730\n" + //
    //                     "^FO576,1312^GFA,02816,02816,00044,:Z64:\n" + //
    //                     "eJxjYBgFo2AUDHXA3sN/4IAE8/EHFQf+JFTI9x/vYez/+CCBsZ/5g2H/xx8fDH/I9/AzH5DvqRhVO6p2VO2oWuqoHQWjYCgDAA5GF3U=:42E0\n" + //
    //                     "^FO96,1056^GFA,00768,00768,00012,:Z64:\n" + //
    //                     "eJytkjFqQzEMhuWa4kKh7hKyFJyT9F0hS+ZcoQcoWEfpUdwbZOnuMaPHR/F7qmzZjUMzVqCH+fifZP8SwOZw2MNmDyUsUYTpu54dUbjnbBw558bhSLQIX4E4GldEvv5gETR93HEx5ghbFvgk3HKRkuACOGYmC59Yq1fR+6BArcI9Pp6h6Qk8wZvw0hh3Va8yNz49V65mTbUt63UyRKneE3Wys5X7o0kumc7jhND0JnoAeS+a+AoSrA++nS3asHb+dQzvnbNPuXOiz3zhOPCQb+nHOmP9MPa9uk/yqMKN+ycXL++y5/5ePbtfH0Z/qm+tzpWf7PPSfR799yzucxnnVea4zX/na4a5j/ugaKG+J2V/6pMdJ+9VteUJ2i5yvHDyHp7K+aF82n7+T/wArGvM/g==:CFDD\n" + //
    //                     "^BY2,3,26^FT44,1262^BCN,,N,N\r\n" + //
    //                     "^FD>;" + informationLabel.getEid() + "^FS\r\n" + //
    //                     "^BY2,3,26^FT44,1314^BCN,,N,N\r\n" + //
    //                     "^FD>;" + informationLabel.getImei() + "^FS\r\n" + //
    //                     "^FT44,1229^ADN,18,10^FH\\^FDEID " + informationLabel.getEid() + "^FS\r\n" + //
    //                     "^FT565,1266^ADN,18,10^FH\\^FDUPC^FS\r\n" + //
    //                     "^FT44,1280^ADN,18,10^FH\\^FD(S) Serial No. " + informationLabel.getSerialNo() + "^FS\r\n" + //
    //                     "^FT44,1333^ADN,18,10^FH\\^FDIMEI/MEID " + informationLabel.getImei() + "^FS\r\n" + //
    //                     "^FT581,1324^ADN,18,10^FH\\^FDIMEI2 " + informationLabel.getImei2() + "^FS\r\n" + //
    //                     "^FT44,1154^ADN,18,10^FH\\^FD" + informationLabel.getModelRegion() + " " + informationLabel.getProductType() + ", " + informationLabel.getProductColor() + ", " + informationLabel.getStorageType() + "^FS\r\n" + //
    //                     "^FT44,1182^ADN,18,10^FH\\^FDOther items as marked thereon Model A2482^FS\r\n" + //
    //                     "^FT211,1070^ADN,18,10^FH\\^FDFCC ID:BCG-E4000A^FS\r\n" + //
    //                     "^FT211,1098^ADN,18,10^FH\\^FDIC: 579C-E4000A^FS\r\n" + //
    //                     "^PQ1,0,1,Y^XZ";
    // }
    //     generatePdf(zplLabel);
        //Option 1

        //Option 2
        generatePdfImage();
        //Option 2
        
        //Option 1
        // pdfViewer.is120x80 = true;
        // pdfViewer.setSize(600, 700);
        // pdfViewer.setVisible(true);
        // pdfViewer.controller.openDocument("C:/txt/example.pdf");
        //Option 1

        //Option 2
        try {
            BufferedImage finalImage = ImageIO.read(new File("C:/images/rendered.png"));
            JFrame frame = new JFrame("Label Preview");
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Image scaled = finalImage.getScaledInstance(400, -1, Image.SCALE_FAST);

            JLabel label = new JLabel(new ImageIcon(scaled));
            frame.add(label);

            frame.pack();
            frame.setVisible(true);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Option 2
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
        File txtFile = new File("C:/txt/example.txt");
        txtFile.delete();

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
            drawText(cs, font, 5,
                    65, 1229,
                    "EID " + informationLabel.getEid(),
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 1280,
                    "(S) Serial No. " + informationLabel.getSerialNo(),
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 1333,
                    "IMEI/MEID " + informationLabel.getImei(),
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 1175,
                    informationLabel.getModelRegion() + " " + informationLabel.getProductType() + ", "
                            + informationLabel.getProductColor() + ", " + informationLabel.getStorageType(),
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 5,
                    65, 1200,
                    "Other items as marked thereon Model A2482",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    211, 1100,
                    "FCC ID: BCG-E4000A",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawBcLogo(cs, doc,
                    15, 67);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawFccLogo(cs, doc,
                    30, 65);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    211, 1125,
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
                    height, 16, 38);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* -------- BARCODE 2 -------- */
        try {
            drawBarcode(
                    cs, doc,
                    informationLabel.getSerialNo(),
                    height, 16, 25);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* -------- BARCODE 3 -------- */
        try {
            drawBarcode(
                    cs, doc,
                    informationLabel.getImei(),
                    height, 16, 13, 1.2f);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* -------- UPC A -------- */
        try {
            drawUpcABarcode(
                    cs, doc, 152, 29);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawText(cs, font, 4,
                    623, 1255,
                    "UPC",
                    height);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // informationLabel.setImei2("abcd");

        if (informationLabel.getImei2().equals("N/A") || !informationLabel.getImei2().isBlank()) {

            try {
                drawText(cs, font, 5,
                        623, 1325,
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
                        height, 150, 14, 1.2f);
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
            BufferedImage image = renderer.renderImageWithDPI(0, 600f);
            File outputFile = new File("C:/images/rendered.png");
            // ImageIO.write(image, "PNG", outputFile);
            BufferedImage bw = binarize(image, 160);
            BufferedImage inverted = invertImage(bw);
            ImageIO.write(inverted, "PNG", outputFile);
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

    private BufferedImage invertImage(BufferedImage image) {
        BufferedImage inverted = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y) & 0xFFFFFF;
                inverted.setRGB(x, y, rgb == 0x000000 ? 0xFFFFFF : 0x000000);
            }
        }

        return inverted;
    }

    private BufferedImage binarize(BufferedImage src, int threshold) {
        BufferedImage bw = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        for(int y = 0; y < src.getHeight(); y++) {
            for(int x = 0; x < src.getWidth(); x++) {
                int rgb = src.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int gray = (r + g + b) / 3;

                bw.setRGB(x, y, gray < threshold ? 0x000000 : 0xFFFFFF);
            }
        }

        return bw;
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
                mmToPt(6),
                mmToPt(7));
    }

    private void drawBcLogo(PDPageContentStream cs,
        PDDocument doc, int imageXPosition, int imageYPosition) throws Exception {

    PDImageXObject img = PDImageXObject.createFromFile(
            "C:/images/bc_logo.png",
            doc);

    cs.drawImage(img, imageXPosition,
            imageYPosition,
            mmToPt(5),
            mmToPt(5));
}
}
