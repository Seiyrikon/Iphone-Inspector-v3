package components.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.printer.PrinterLanguage;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.discovery.DiscoveredUsbPrinter;
import com.zebra.sdk.printer.discovery.UsbDiscoverer;

import components.labels.PrintButtonIconLabel;
import components.labels.PrintButtonTextLabel;

public class PrintButtonPanel extends JPanel {
    PrintButtonIconLabel iconLabel;
    PrintButtonTextLabel textLabel;

    private boolean pressed = false;
    private Color normalColor = new Color(56, 57, 58);
    private Color hoverColor = new Color(240, 240, 240);

    public PrintButtonPanel() {
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
                    printTestLabel();
                    System.out.println("Print sent!");
                } catch (Exception err) {
                    err.printStackTrace();
                    System.out.println("Print failed!");
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
    }

    private boolean isZplMode(Connection conn) {
        try {
            // ask printer to describe itself
            conn.write("~HI".getBytes());

            // wait a little for response
            Thread.sleep(300);

            // read identification string
            byte[] response = conn.read();
            if (response == null)
                return false;

            String info = new String(response);

            // ZPL printers always include "ZPL" in the ~HI response
            return info.contains("ZPL");

        } catch (Exception e) {
            return false; // no response → not ZPL
        }
    }

    public void printTestLabel() throws Exception {

        // ----------- 1. FIND USB PRINTER -------------
        DiscoveredUsbPrinter usbPrinter = null;
        for (DiscoveredUsbPrinter p : UsbDiscoverer.getZebraUsbPrinters()) {
            usbPrinter = p;
            break;
        }

        if (usbPrinter == null) {
            throw new Exception("No Zebra USB printer detected.");
        }

        // ----------- 2. CONNECT TO PRINTER ------------
        Connection conn = usbPrinter.getConnection();
        conn.open();

        // ----------- 3. CHECK IF PRINTER IS IN ZPL MODE ------------
        // if (!isZplMode(conn)) {
        //     conn.close();
        //     throw new Exception("Printer is NOT in ZPL mode. Switch printer language to ZPL.");
        // }

        // // ----------- 4. SEND ZPL IF OK ----------------
        // String zpl = "^XA^FO50,50^ADN,36,20^FDHELLO ZEBRA^FS^XZ";
        PrinterLanguage printerLanguage = ZebraPrinterFactory.getInstance(conn).getPrinterControlLanguage();
        conn.write(getConfigLabel(printerLanguage));



        // conn.close();
    }

    private byte[] getConfigLabel(PrinterLanguage printerLanguage) {

    /*
     * Returns the command for a test label depending on the printer control language
     * The test label is a box with the word "TEST" inside of it
     * 
     * _________________________
     * |                       |
     * |                       |
     * |        TEST           |
     * |                       |
     * |                       |
     * |_______________________|
     * 
     * 
     */


        byte[] configLabel = null;
        if (printerLanguage == PrinterLanguage.ZPL) {
            configLabel = "^XA^FT65,255^A0N,20,40^^BY3^BCN,80,Y,N,N^FDFFXDD20N0F0X^FS^XZ".getBytes();
            // configLabel = "^XA^CF0,30^FO50,30^FD123456789012^FS^FO50,60^BCN,100,Y,N,N^FD123456789012^FS".getBytes();
        } else if ((printerLanguage == PrinterLanguage.CPCL) || (printerLanguage == PrinterLanguage.LINE_PRINT)) {
            String cpclConfigLabel = "! 0 200 200 406 1\r\n" + "ON-FEED IGNORE\r\n" + "BOX 20 20 380 380 8\r\n"
                    + "T 0 6 137 177 TEST\r\n" + "PRINT\r\n";
            configLabel = cpclConfigLabel.getBytes();
        }
        return configLabel;
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
}
