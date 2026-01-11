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

import components.frame.PdfViewerFrame;
import components.labels.GenerateButtonIconLabel;
import components.labels.GenerateButtonTextLabel;
import model.IphoneLabelInformation;
import model.IphoneModel;
import model.PdfDocument;

public class GenerateButtonPanel extends JPanel {
    GenerateButtonIconLabel iconLabel;
    GenerateButtonTextLabel textLabel;

    private boolean pressed = false;
    private Color normalColor = new Color(56, 57, 58);
    private Color hoverColor = new Color(240, 240, 240);

    InformationContainerPanel infoContainer;
    IphoneModel iphone;

    Size120x80Panel size120x80Button;
    Size20x80Panel size20x80Button;
    Size20x80BlackPanel size20x80BlackButton;
    Size120x80BlackPanel size120x80BlackButton;

    PdfViewerFrame pdfViewer;

    IphoneLabelInformation informationLabel;

    PdfDocument pdf;

    public GenerateButtonPanel(InformationContainerPanel infoContainer, IphoneModel iphone, PdfViewerFrame pdfViewer, IphoneLabelInformation informationLabel, PdfDocument pdf) {
        this.infoContainer = infoContainer;
        this.iphone = iphone;
        this.pdfViewer = pdfViewer;
        this.informationLabel = informationLabel;
        this.pdf = pdf;

        setOpaque(false);
        setBackground(new Color(56, 57, 58));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        iconLabel = new GenerateButtonIconLabel();
        textLabel = new GenerateButtonTextLabel();

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
                pressed = false;
                System.out.println("Generate Button clicked!");
                generateSizeButtons();
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

    public void generateSizeButtons() {
        infoContainer.removeAll();
        infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));

        size120x80Button = new Size120x80Panel(pdfViewer, informationLabel, pdf);
        // size120x80BlackButton = new Size120x80BlackPanel(pdfViewer, informationLabel, pdf);
        size20x80Button = new Size20x80Panel(pdfViewer, informationLabel);
        // size20x80BlackButton = new Size20x80BlackPanel(pdfViewer, informationLabel);

        infoContainer.add(size120x80Button);
        // infoContainer.add(size120x80BlackButton);
        infoContainer.add(size20x80Button);
        // infoContainer.add(size20x80BlackButton);

        infoContainer.revalidate();
        infoContainer.repaint();
    }

}
