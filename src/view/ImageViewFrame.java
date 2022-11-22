package view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import model.ImageModel;
import model.ImageUtil;

/**
 *
 */
public class ImageViewFrame extends JFrame implements ImageView, ActionListener {
  private JPanel mainPanel;
  private JPanel imagePanel;
  private JPanel histogramPanel;
  private JPanel histogram;
  private JPanel inputPanel;
  private JLabel imageLabel;
  private JTextField input;
  private JTextArea message;

//  private JList<String> listOfStrings;

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
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);
  }

  private void addImageHistogramViewer() {
    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.setLayout(new FlowLayout());
    histogram = new JPanel();
    histogramPanel.add(histogram, BorderLayout.CENTER);
    mainPanel.add(histogramPanel, BorderLayout.EAST);
  }

  private void addImageViewer() {
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current Image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10) );

    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(200, 200));

    imagePanel.add(imageScrollPane, BorderLayout.CENTER);
    mainPanel.add(imagePanel, BorderLayout.WEST);
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
    input.setActionCommand("\n");
    inputPanel.add(input);
  }

  private void addMessageOutput() {
    message = new JTextArea();
    message.setBorder(BorderFactory.createTitledBorder("Messages"));
    message.setEditable(false);
    message.setWrapStyleWord(true);
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

  public void setCurrentImage(ImageModel model) {
    BufferedImage bufferedImage = ImageUtil.getBufferedImage(model);
    imageLabel.setIcon(new ImageIcon(bufferedImage));
    histogramPanel.remove(histogram);
    histogram = new ImageHistogram(model);
    histogramPanel.add(histogram, BorderLayout.CENTER);
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
