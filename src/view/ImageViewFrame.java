package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.util.Objects;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import model.ImageModel;

/**
 *
 */
public class ImageViewFrame extends JFrame implements ImageView, ActionListener {
  private ImageModel model;
  private JPanel mainPanel;
  private JPanel imagePanel;
  private JPanel histogramPanel;
  private JPanel inputPanel;
  private JScrollPane mainScrollPane;
  private JTextArea commands;
  private JTextField input;
  private JTextArea message;

  private JList<String> listOfStrings;

  public ImageViewFrame() {
    super();
    this.setTitle("Image Processor");
    this.setSize(750, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    addMainLayout();
    addImageViewer();
    addImageHistogramViewer();
    addInputPanel();
    addTextInputBox();
    addOpenFileButton();
    addMessageOutput();
  }

  private void addMainLayout() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);
  }

  private void addImageHistogramViewer() {
    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.setLayout(new FlowLayout());
    mainPanel.add(histogramPanel, BorderLayout.EAST);
    ImageHistogram histogram = new ImageHistogram();
    histogramPanel.add(histogram, BorderLayout.CENTER);
  }

  private void addImageViewer() {
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Images Loaded"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10) );
    mainPanel.add(imagePanel, BorderLayout.WEST);

    String[] images = {"res/Clouds.jpg", "res/Candles.png"};
    JLabel[] imageLabel = new JLabel[images.length];
    JScrollPane[] imageScrollPane = new JScrollPane[images.length];

    for (int i = 0; i < imageLabel.length; i++) {
      imageLabel[i] = new JLabel();
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
      imageLabel[i].setIcon(new ImageIcon(images[i]));
      imageScrollPane[i].setPreferredSize(new Dimension(200, 200));
      imagePanel.add(imageScrollPane[i]);
    }
  }

  private void addInputPanel() {
    inputPanel = new JPanel();
    inputPanel.setBorder(BorderFactory.createTitledBorder("Images Loaded"));
    inputPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(2, 2, 2, 2);
    mainPanel.add(inputPanel);
  }

  private void addTextInputBox() {
    input = new JTextField(25);
    input.setBorder(BorderFactory.createTitledBorder("Input Command Text"));
    input.setText("Input help (h) for commands or quit (q) to quit");
    input.setActionCommand("\n");
    inputPanel.add(input);
  }

  private void addMessageOutput() {
    message = new JTextArea();
    message.setEditable(false);
    inputPanel.add(message);
  }

  private void addOpenFileButton() {
    JPanel selectFile = new JPanel();
    selectFile.setBorder(BorderFactory.createTitledBorder("Select File"));
    selectFile.setLayout(new BoxLayout(selectFile, BoxLayout.PAGE_AXIS));
    inputPanel.add(selectFile);

    JPanel fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new FlowLayout());
    selectFile.add(fileOpenPanel);

    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileOpenPanel.add(fileOpenButton);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file": {
        final JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "png", "ppm", "bmp");
        fileChooser.setFileFilter(filter);
        int retValue = fileChooser.showOpenDialog(ImageViewFrame.this);
        if (retValue == JFileChooser.APPROVE_OPTION) {
          File f = fileChooser.getSelectedFile();
          input.setText(input.getText() + " " + f.getAbsolutePath());
        }
        break;
      }
    }
  }

  @Override
  public void renderMessage(String message) {
    this.message.setText(message);
  }

  @Override
  public void addFeatures(Features f) {
    input.addActionListener(evt -> f.getInput(input.getText()));
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }

  @Override
  public void refresh() {
    this.repaint();
  }
}
