package components.textfields;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.IphoneLabelInformation;
import utils.Constants;

public class SerialNoTextField extends JTextField {
    public SerialNoTextField(IphoneLabelInformation iphoneLabel) {
        super(iphoneLabel.getSerialNo().isBlank() ? Constants.EMPTY_STRING.get() : iphoneLabel.getSerialNo());

        setAlignmentX(Component.LEFT_ALIGNMENT);
        setFont(new Font("SansSerif", Font.PLAIN, 20));

        int height = 40;

        setPreferredSize(new Dimension(150, height));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        setMinimumSize(new Dimension(50, height));

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saveValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveValue();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveValue();
            }

            private void saveValue() {
                iphoneLabel.setSerialNo(getText());
            }
        });
    }
}
