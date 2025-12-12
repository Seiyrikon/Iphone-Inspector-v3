package components.panels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

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
        String zplLabel = "^XA^MMT^PW945^LL1417^LS0^BY2,3,24^FT33,1184^BCN,,N,N^FD>;"
                + informationLabel.getEid().toString() + "^FS^BY2,3,24^FT33,1235^BCN,,N,N^FD>:"
                + informationLabel.getSerialNo() + "^FS^BY2,3,24^FT33,1287^BCN,,N,N^FD>;" + informationLabel.getImei()
                + "^FS^BY2,2,24^FT720,1176^BUN,,Y,N^FD19425337371^FS^BY2,3,24^FT605,1235^BCN,,N,N^FD>;"
                + informationLabel.getImei2() + "^FS^FT33,1157^A0N,17,16^FH\\^FDEID " + informationLabel.getEid()
                + "^FS^FT33,1207^A0N,17,16^FH\\^FD(S) Serial No. " + informationLabel.getSerialNo()
                + "^FS^FT33,1261^A0N,17,16^FH\\^FDIMEI/MEID " + informationLabel.getImei()
                + "^FS^FT686,1166^A0N,17,16^FH\\^FDUPC^FS^FT605,1207^A0N,17,16^FH\\^FDIMEI2 "
                + informationLabel.getImei2()
                + "^FS^FT33,1116^A0N,17,16^FH\\^FDOther items as marked thereon Model A2886^FS^FT33,1095^A0N,17,16^FH\\^"
                + informationLabel.getModelRegion() + " " + informationLabel.getProductType() + ", "
                + informationLabel.getProductColor() + ", " + informationLabel.getStorageType() + "^FS^PQ1,0,1,Y^XZ";

        generatePdf(zplLabel);
        pdfViewer.is120x80 = true;
        pdfViewer.setSize(600, 700);
        pdfViewer.setVisible(true);
        pdfViewer.controller.openDocument("C:/txt/example.pdf");
    }

    private CommandResult generatePdf(String zplLabel) {
        try {
            File dir = new File("C:/txt");
            if (!dir.exists()) {
                dir.mkdirs(); // Creates directory if missing
            }

            FileWriter writer = new FileWriter("C:/txt/example.txt");
            writer.write(zplLabel);
            writer.close();
            System.out.println("File created!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommandResult result =
        CommandExecutor.runTool(Constants.STRING_ZPL2PDF.get(),
        Constants.STRING_ZPL_I.get(), "C:/txt/example.txt",
        Constants.STRING_ZPL_O.get(),
        "C:/txt/", Constants.STRING_ZPL_N.get() +
        Constants.PDF_120X80.get());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
