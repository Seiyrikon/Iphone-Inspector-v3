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
        
        // Barcode Generator
        // String data = "1234567890";
        // String filePath = "barcode.png";

        // try {
        //     BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, 400, 150);

        //     Path path = FileSystems.getDefault().getPath(filePath);

        //     MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        //     System.out.println("Barcode Generated: " + filePath);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        //QR Code Generator
        // String text = "Hello Bossing!";
        // String filePath = "qrcode.png";

        // int width = 300;
        // int height = 300;

        // QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // Map<EncodeHintType, Object> hints = new HashMap<>();
        // hints.put(EncodeHintType.MARGIN, 1);

        // try {
        //     BitMatrix matrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        //     Path path = FileSystems.getDefault().getPath(filePath);
        //     MatrixToImageWriter.writeToPath(matrix, "PNG", path);

        //     System.out.println("QR Code Generated: " + filePath);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // UPC Barcode generator
        // try {
        //     String upc = "036000291452";

        //     int width = 400;
        //     int height = 220;
        //     int barcodeHeight = 160;

        //     MultiFormatWriter writer = new MultiFormatWriter();
        //     BitMatrix matrix = writer.encode(upc, BarcodeFormat.UPC_A, width, barcodeHeight);

        //     BufferedImage barcode = MatrixToImageWriter.toBufferedImage(matrix);

        //     BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //     Graphics2D g = finalImage.createGraphics();

        //     g.setColor(Color.WHITE);
        //     g.fillRect(0, 0, width, height);

        //     g.drawImage(barcode, 0, 0, null);

        //     g.setColor(Color.BLACK);
        //     g.setFont(new Font("Monospaced", Font.BOLD, 20));
        //     FontMetrics fm = g.getFontMetrics();

        //     // UPC Splitting
        //     String leftGuardDigit = upc.substring(0, 1);      // 1 digit
        //     String leftGroup = upc.substring(1, 6);            // 5 digits
        //     String rightGroup = upc.substring(6, 11);          // 5 digits
        //     String checkDigit = upc.substring(11);             // last digit

        //     int y = barcodeHeight + 20;

        //     // Bar width per module
        //     int module = width / 95; // UPC-A = 95 modules total

        //     // Digit positions based on UPC spec
        //     int xLeftGuardDigit = 0;
        //     int xLeftGroup = module * 3;
        //     int xRightGroup = module * 49;
        //     int xCheckDigit = module * 92;

        //     // Draw each aligned number
        //     g.drawString(leftGuardDigit, xLeftGuardDigit, y);
        //     g.drawString(leftGroup, xLeftGroup, y);
        //     g.drawString(rightGroup, xRightGroup, y);
        //     g.drawString(checkDigit, xCheckDigit, y);

        //     g.dispose();
        //     ImageIO.write(finalImage, "png", new File("upc_aligned.png"));

        //     System.out.println("Accurate UPC barcode generated: upc_aligned.png");

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}
