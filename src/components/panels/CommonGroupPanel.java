package components.panels;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import components.textfields.Imei2TextField;
import components.textfields.ImeiTextField;
import components.textfields.ModelNoTextField;
import components.textfields.ProductNameTextField;
import components.textfields.ProductTypeTextField;
import components.textfields.ProductVersionTextField;
import components.textfields.SerialNoTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class CommonGroupPanel extends JPanel {

    Color backgroundColor = new Color(210, 210, 210);

    private static final int LABEL_WIDTH = 170;
    private static final int FIELD_WIDTH = 320;

    public CommonGroupPanel(JLabel label, JComponent component) {
        // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // setBackground(backgroundColor);
        // setOpaque(false);
        // setBorder(new EmptyBorder(0, 0, 0, 10));

        // add(label);
        // add(Box.createHorizontalStrut(116));
        // add(component);

        // setAlignmentX(Component.LEFT_ALIGNMENT);

        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        // Normalize label width
        Dimension labelSize = new Dimension(
                LABEL_WIDTH,
                label.getPreferredSize().height);
        label.setPreferredSize(labelSize);
        label.setMinimumSize(labelSize);

        // Normalize field width
        Dimension fieldSize = new Dimension(
                FIELD_WIDTH,
                component.getPreferredSize().height);
        component.setPreferredSize(fieldSize);
        component.setMinimumSize(fieldSize);

        // Label column
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(label, gbc);

        // Field column (FIXED START POSITION)
        gbc.gridx = 1;
        add(component, gbc);

        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    // public CommonGroupPanel(JLabel label, ImeiTextField component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(54));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(JLabel label, Imei2TextField component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(98));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(JLabel label, SerialNoTextField component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(35));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(JLabel label, ModelNoTextField component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(18));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(JLabel label, ProductNameTextField component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(22));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(JLabel label, ProductTypeTextField component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(30));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(JLabel label, ProductVersionTextField component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(8));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(JLabel label, ColorTypeComboBox component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(101));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(JLabel label, StorageTypeComboBox component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);
    // setBorder(new EmptyBorder(0, 0, 0, 10));

    // add(label);
    // add(Box.createHorizontalStrut(80));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(IphoneInfoLabel label, ImeiLabel component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);

    // add(label);
    // add(Box.createHorizontalStrut(53));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(IphoneInfoLabel label, Imei2Label component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);

    // add(label);
    // add(Box.createHorizontalStrut(97));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(IphoneInfoLabel label, SerialNoLabel component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);

    // add(label);
    // add(Box.createHorizontalStrut(35));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(IphoneInfoLabel label, ModelNoLabel component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);

    // add(label);
    // add(Box.createHorizontalStrut(20));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(IphoneInfoLabel label, ProductNameLabel component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);

    // add(label);
    // add(Box.createHorizontalStrut(24));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(IphoneInfoLabel label, ProductTypeLabel component) {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);

    // add(label);
    // add(Box.createHorizontalStrut(33));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }

    // public CommonGroupPanel(IphoneInfoLabel label, ProductVersionLabel component)
    // {
    // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    // setBackground(backgroundColor);
    // setOpaque(false);

    // add(label);
    // add(Box.createHorizontalStrut(10));
    // add(component);

    // setAlignmentX(Component.LEFT_ALIGNMENT);
    // }
}
