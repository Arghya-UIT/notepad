import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FontChooser extends JDialog {
    private Font selectedFont;
    private JComboBox<String> fontComboBox;
    private JComboBox<Integer> sizeComboBox;

    public FontChooser(JFrame parent, Font initialFont) {
        super(parent, "Choose Font", true);
        selectedFont = initialFont;

        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontComboBox = new JComboBox<>(fontNames);

        Integer[] fontSizes = { 8, 10, 12, 14, 16, 18, 20, 24, 28, 32, 36, 48, 72 };
        sizeComboBox = new JComboBox<>(fontSizes);

        fontComboBox.setSelectedItem(initialFont.getFamily());
        sizeComboBox.setSelectedItem(initialFont.getSize());

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fontName = (String) fontComboBox.getSelectedItem();
                int fontSize = (int) sizeComboBox.getSelectedItem();
                selectedFont = new Font(fontName, Font.PLAIN, fontSize);
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Font:"));
        panel.add(fontComboBox);
        panel.add(new JLabel("Size:"));
        panel.add(sizeComboBox);
        panel.add(okButton);
        panel.add(cancelButton);

        add(panel);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    public Font getSelectedFont() {
        return selectedFont;
    }

    public static Font showDialog(JFrame parent, String title, Font initialFont) {
        FontChooser chooser = new FontChooser(parent, initialFont);
        chooser.setVisible(true);
        return chooser.getSelectedFont();
    }
}
