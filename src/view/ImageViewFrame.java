package view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
 * ImageViewFrame that implements ImageView interface and represents a graphical user
 * interface of the Image Program. The ImageViewFrame displays both the current image that
 * is most recently loaded, changed, or saved, and the resulting histogram (Image data that
 * shows the amount of red, green, blue, and intensity components of the image). Contains
 * a text box that takes in commands that would allow a user to load, change, and save image
 * files.
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

  /**
   * Constructs the interactive view of the GUI by setting up the framework of the
   * view through methods created.
   */
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
    setComponentsNames();
  }

  /**
   * Creates the main panel that will contain all other sub panels.
   */
  private void addMainLayout() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);
  }

  /**
   * Creates the histogram panel that contains the histogram component.
   */
  private void addImageHistogramViewer() {
    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.setLayout(new FlowLayout());
    histogram = new JPanel();
    histogramPanel.add(histogram, BorderLayout.CENTER);
    mainPanel.add(histogramPanel, BorderLayout.EAST);
  }

  /**
   * Creates the image panel that contains the image component.
   */
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

  /**
   * Creates the input panel that contains the text-box and other components
   * that will allow a user to actively use the program.
   */
  private void addInputPanel() {
    inputPanel = new JPanel();
    inputPanel.setBorder(BorderFactory.createTitledBorder("Images Loaded"));
    inputPanel.setLayout(new FlowLayout());
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

  /**
   * For the purpose of testing.
   */
  private void setComponentsNames() {
    input.setName("input");
    imageLabel.setName("image");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if ("Open file".equals(e.getActionCommand())) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "JPG & GIF Images", "jpg", "png", "ppm", "bmp");
      fileChooser.setFileFilter(filter);
      int retValue = fileChooser.showOpenDialog(ImageViewFrame.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        input.setText(input.getText() + " " + f.getAbsolutePath());
      }
    }
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void renderMessage(String message) {
    this.message.setText(message);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features f) {
    input.addActionListener(evt -> f.getInput(input.getText()));
  }

  @Override
  public void setCurrentImage(ImageModel model) {
    BufferedImage bufferedImage = ImageUtil.getBufferedImage(model);
    imageLabel.setIcon(new ImageIcon(bufferedImage));
    histogramPanel.remove(histogram);
    histogram = new ImageHistogram(model);
    histogramPanel.add(histogram, BorderLayout.CENTER);
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }
}
