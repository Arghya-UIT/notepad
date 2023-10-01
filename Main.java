import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public Main() {
        setTitle("Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        fileChooser = new JFileChooser();

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu formatMenu = new JMenu("Format");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As");
        JMenuItem fontItem = new JMenuItem("Font");

        openItem.addActionListener(new OpenListener());
        saveItem.addActionListener(new SaveListener());
        saveAsItem.addActionListener(new SaveAsListener());
        fontItem.addActionListener(new FontListener());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        formatMenu.add(fontItem);
        menuBar.add(fileMenu);
        menuBar.add(formatMenu);

        setJMenuBar(menuBar);

        add(new JScrollPane(textArea));

        setVisible(true);
    }

    class OpenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    textArea.setText("");
                    String line;
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int returnVal = fileChooser.showSaveDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(textArea.getText());
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class SaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int returnVal = fileChooser.showSaveDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(textArea.getText());
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class FontListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Font selectedFont = FontChooser.showDialog(Main.this, "Choose Font", textArea.getFont());
            if (selectedFont != null) {
                textArea.setFont(selectedFont);
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
