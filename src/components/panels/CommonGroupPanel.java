package components.panels;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.combobox.ColorTypeComboBox;
import components.combobox.StorageTypeComboBox;
import components.labels.Imei2Label;
import components.labels.ImeiLabel;
import components.labels.IphoneInfoLabel;
import components.labels.ModelNoLabel;
import components.labels.ProductNameLabel;
import components.labels.ProductTypeLabel;
import components.labels.ProductVersionLabel;
import components.labels.SerialNoLabel;
import components.textfields.EidTextField;

import java.awt.Color;
import java.awt.Component;

public class CommonGroupPanel extends JPanel {

    Color backgroundColor = new Color(210, 210, 210);

    public CommonGroupPanel(JLabel label, EidTextField component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);
        setBorder(new EmptyBorder(0, 0, 0, 10));

        add(label);
        add(Box.createHorizontalStrut(116));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(JLabel label, ColorTypeComboBox component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);
        setBorder(new EmptyBorder(0, 0, 0, 10));

        add(label);
        add(Box.createHorizontalStrut(101));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(JLabel label, StorageTypeComboBox component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);
        setBorder(new EmptyBorder(0, 0, 0, 10));

        add(label);
        add(Box.createHorizontalStrut(80));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(IphoneInfoLabel label, ImeiLabel component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);

        add(label);
        add(Box.createHorizontalStrut(53));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(IphoneInfoLabel label, Imei2Label component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);

        add(label);
        add(Box.createHorizontalStrut(97));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(IphoneInfoLabel label, SerialNoLabel component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);

        add(label);
        add(Box.createHorizontalStrut(35));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(IphoneInfoLabel label, ModelNoLabel component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);

        add(label);
        add(Box.createHorizontalStrut(20));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(IphoneInfoLabel label, ProductNameLabel component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);

        add(label);
        add(Box.createHorizontalStrut(24));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(IphoneInfoLabel label, ProductTypeLabel component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);

        add(label);
        add(Box.createHorizontalStrut(33));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public CommonGroupPanel(IphoneInfoLabel label, ProductVersionLabel component) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backgroundColor);
        setOpaque(false);

        add(label);
        add(Box.createHorizontalStrut(10));
        add(component);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
