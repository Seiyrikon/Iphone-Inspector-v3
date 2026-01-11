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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import components.combobox.ColorTypeComboBox;
import components.combobox.StorageTypeComboBox;
import components.images.DisconnectedIcon;
import components.images.PhoneFailedIcon;
import components.labels.IphoneInfoLabel;
import components.labels.ScanButtonIconLabel;
import components.labels.ScanButtonTextLabel;
import components.textfields.EidTextField;
import components.textfields.IccTextField;
import components.textfields.Imei2TextField;
import components.textfields.ImeiTextField;
import components.textfields.ModelNoTextField;
import components.textfields.ProductNameTextField;
import components.textfields.ProductTypeTextField;
import components.textfields.ProductVersionTextField;
import components.textfields.SerialNoTextField;
import model.IphoneLabelInformation;
import model.IphoneModel;
import services.device.DeviceService;
import utils.CommandResult;
import utils.Constants;

public class ScanButtonPanel extends JPanel {
    ScanButtonIconLabel iconLabel;
    ScanButtonTextLabel textLabel;

    private boolean pressed = false;
    private Color normalColor = new Color(56, 57, 58);
    private Color hoverColor = new Color(240, 240, 240);

    private InformationContainerPanel infoContainer;
    private DeviceService deviceService;

    IphoneModel iphone;
    ColorTypeComboBox colorDropdown = null;
    StorageTypeComboBox storageDropdown = null;

    IphoneLabelInformation informationLabel;

    public ScanButtonPanel(InformationContainerPanel infoContainer, DeviceService deviceService, IphoneModel iphone,
            IphoneLabelInformation informationLabel) {
        this.infoContainer = infoContainer;
        this.deviceService = deviceService;
        this.iphone = iphone;
        this.informationLabel = informationLabel;

        setOpaque(false);
        setBackground(normalColor);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        iconLabel = new ScanButtonIconLabel();
        textLabel = new ScanButtonTextLabel();

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
                System.out.println("Scan Button clicked!");
                generateInfo();
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

    private void generateInfo() {
        infoContainer.removeAll();
        infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));
        informationLabel.setEid(
                iphone.getSerialNo().equals(informationLabel.getSerialNo())
                        ? informationLabel.getEid()
                        : Constants.EMPTY_STRING.get());

        // CommandResult device = deviceService.detect();

        // if (device.output.isBlank()) {
        //     UIManager.put("OptionPane.background", new Color(30, 30, 30));
        //     UIManager.put("Panel.background", new Color(30, 30, 30));
        //     UIManager.put("OptionPane.messageForeground", Color.WHITE);
        //     UIManager.put("Button.background", new Color(56, 57, 58));
        //     UIManager.put("Button.foreground", Color.WHITE);

        //     JOptionPane.showMessageDialog(
        //             SwingUtilities.getWindowAncestor(this),
        //             "No device detected! Please reconnect the cable.",
        //             "Scan Failed",
        //             JOptionPane.WARNING_MESSAGE,
        //             new DisconnectedIcon());
        //     return;
        // }

        // iphone = deviceService.extractInfo();

        // if(iphone.isFailed == true) {
        //     UIManager.put("OptionPane.background", new Color(30, 30, 30));
        //     UIManager.put("Panel.background", new Color(30, 30, 30));
        //     UIManager.put("OptionPane.messageForeground", Color.WHITE);
        //     UIManager.put("Button.background", new Color(56, 57, 58));
        //     UIManager.put("Button.foreground", Color.WHITE);

        //     JOptionPane.showMessageDialog(
        //             SwingUtilities.getWindowAncestor(this),
        //             "Device Failure! Select \"Trust\" on the device.",
        //             "Device Failed",
        //             JOptionPane.WARNING_MESSAGE,
        //             new PhoneFailedIcon());
        //     return;
        // }

        // --- EID ---
        infoContainer.add(
                new CommonGroupPanel(new IphoneInfoLabel(Constants.EID.get()), new EidTextField(informationLabel)));

        // --- COLOR ---
        if (Constants.IPHONE_8.get().equals(iphone.getProductType())
                || Constants.IPHONE_8_PLUS.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone8And8PlusColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Gold");
        } else if (Constants.IPHONE_X.get().equals(iphone.getProductType())
                || Constants.IPHONE_XS.get().equals(iphone.getProductType())
                || Constants.IPHONE_XS_MAX.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphoneXAndXsAndXsMaxColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Gold");
        } else if (Constants.IPHONE_XR.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphoneXrColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Black");
        } else if (Constants.IPHONE_11.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone11Colors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Black");
        } else if (Constants.IPHONE_11_PRO.get().equals(iphone.getProductType())
                || Constants.IPHONE_11_PRO_MAX.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone11ProAnd11ProMaxColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Gold");
        } else if (Constants.IPHONE_12.get().equals(iphone.getProductType())
                || Constants.IPHONE_12_MINI.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone12And12MiniColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Black");
        } else if (Constants.IPHONE_12_PRO.get().equals(iphone.getProductType())
                || Constants.IPHONE_12_PRO_MAX.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone12ProAnd12ProMaxColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Gold");
        } else if (Constants.IPHONE_13.get().equals(iphone.getProductType())
                || Constants.IPHONE_13_MINI.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone13Ad13MiniColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Blue");
        } else if (Constants.IPHONE_13_PRO.get().equals(iphone.getProductType())
                || Constants.IPHONE_13_PRO_MAX.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone13ProAnd13ProMaxColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Alpine Green");
        } else if (Constants.IPHONE_SE.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphoneSeColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Midnight");
        } else if (Constants.IPHONE_14.get().equals(iphone.getProductType())
                || Constants.IPHONE_14_PLUS.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone14And14PlusColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Blue");
        } else if (Constants.IPHONE_14_PRO.get().equals(iphone.getProductType())
                || Constants.IPHONE_14_PRO_MAX.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone14ProAnd14ProMaxColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Deep Purple");
        } else if (Constants.IPHONE_15.get().equals(iphone.getProductType())
                || Constants.IPHONE_15_PLUS.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone15And15PlusColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Black");
        } else if (Constants.IPHONE_15_PRO.get().equals(iphone.getProductType())
                || Constants.IPHONE_15_PRO_MAX.get().equals(iphone.getProductType())) {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone15ProAnd15ProMaxColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
            informationLabel.setProductColor("Black Titanium");
        } else {
            colorDropdown = new ColorTypeComboBox(informationLabel, iphone.getIphone8And8PlusColors());
            colorDropdown.setChoice(informationLabel.getProductColor());
        }
        infoContainer.add(new CommonGroupPanel(new IphoneInfoLabel(Constants.COLOR_TYPE.get()), colorDropdown));

        // --- STORAGE ---
        storageDropdown = new StorageTypeComboBox(informationLabel, iphone.getStorageTypes());
        storageDropdown.setChoice(informationLabel.getStorageType());
        informationLabel.setStorageType("64GB");
        infoContainer.add(new CommonGroupPanel(new IphoneInfoLabel(Constants.STORAGE_TYPE.get()), storageDropdown));

        // --- IMEI ---
        informationLabel.setImei(iphone.getImei());
        infoContainer.add(
                new CommonGroupPanel(new IphoneInfoLabel(Constants.IMEI.get()), new ImeiTextField(informationLabel)));

        // --- IMEI2 ---
        informationLabel.setImei2(iphone.getImei2());
        infoContainer.add(
                new CommonGroupPanel(new IphoneInfoLabel(Constants.IMEI2.get()), new Imei2TextField(informationLabel)));

        // --- SERIAL NO ---
        informationLabel.setSerialNo(iphone.getSerialNo());
        infoContainer.add(new CommonGroupPanel(new IphoneInfoLabel(Constants.SERIAL_NUMBER.get()),
                new SerialNoTextField(informationLabel)));

        // --- MODEL NO ---
        informationLabel.setModelRegion(iphone.getModel() + iphone.getRegion());
        infoContainer.add(new CommonGroupPanel(new IphoneInfoLabel(Constants.MODEL_NUMBER.get()),
                new ModelNoTextField(informationLabel)));

        // --- PRODUCT NAME ---
        informationLabel.setProductName(iphone.getProductName());
        infoContainer.add(new CommonGroupPanel(new IphoneInfoLabel(Constants.PRODUCT_NAME.get()),
                new ProductNameTextField(informationLabel)));

        // --- PRODUCT TYPE ---
        informationLabel.setProductType(iphone.getProductType());
        infoContainer.add(new CommonGroupPanel(new IphoneInfoLabel(Constants.PRODUCT_TYPE.get()),
                new ProductTypeTextField(informationLabel)));

        // --- PRODUCT VERSION ---
        informationLabel.setProductVersion(iphone.getProductVersion());
        infoContainer.add(new CommonGroupPanel(new IphoneInfoLabel(Constants.PRODUCT_VERSION.get()),
                new ProductVersionTextField(informationLabel)));

        if(!iphone.getIcc().isBlank()) {
            informationLabel.setIcc(iphone.getIcc());
            infoContainer.add(new CommonGroupPanel(new IphoneInfoLabel(Constants.ICC.get()), 
                new IccTextField(informationLabel)));
        }

        infoContainer.revalidate();
        infoContainer.repaint();
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
