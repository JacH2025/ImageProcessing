package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import model.ImageModel;
import model.ImageUtil;

/**
 *
 */
public class ImageViewFeat extends JFrame implements ImageView {

  private JMenuBar operations;

  // JMenu
  private JMenu file;
  private JMenu editImage;
  private JMenu filters;
  private JMenu greyscale;
  private JMenu flip;


  // Menu items
  private static JMenuItem brighten, horz, vert, intensity, transGreyscale, luma, red, blue, green, value, blur, sharpen, sepia;
  private JMenuItem load;

  private JMenuItem save;
  private JButton exit;


  private JPanel mainPanel;
  private JPanel imagePanel;
  private JPanel histogramPanel;
  private JPanel histogram;
  private JPanel inputPanel;
  private JLabel imageLabel;
  private JTextArea message;

  //inputs for menu comands
  private JTextField imageName;
  private JTextField addAs;
  private JTextField imagePath;
  private JTextField savePath;
  private JTextField brightIncrement;

  /**
   * Constructs the interactive view of the GUI by setting up the framework of the view through
   * methods created.
   */
  public ImageViewFeat() {
    super();
    this.setTitle("Image Processor");
    this.setSize(750, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    addMainLayout();
    addImageViewer();
    initInputPanel();
    addImageHistogramViewer();
    addMessageOutput();
    addIOFileButtons();
    initMenuLayout();
    setComponentName();

    pack();
    setVisible(true);
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
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));

    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(200, 200));

    imagePanel.add(imageScrollPane, BorderLayout.CENTER);
    mainPanel.add(imagePanel, BorderLayout.WEST);
  }

  private void addIOFileButtons() {
    JPanel selectFile = new JPanel();
    selectFile.setBorder(BorderFactory.createTitledBorder("load File"));
    selectFile.setLayout(new BoxLayout(selectFile, BoxLayout.PAGE_AXIS));
    inputPanel.add(selectFile);

    JPanel saveFile = new JPanel();
    selectFile.setBorder(BorderFactory.createTitledBorder("save File"));
    selectFile.setLayout(new BoxLayout(selectFile, BoxLayout.PAGE_AXIS));
    inputPanel.add(saveFile);

    JButton fileOpenButton = new JButton("load path");
    selectFile.add(fileOpenButton);
    fileOpenButton.addActionListener(evt -> fileOpener("load"));

    JButton saveButton = new JButton("save path");
    selectFile.add(saveButton);
    saveButton.addActionListener(evt -> fileOpener("save"));

  }

  /**
   * Creates the input panel that contains the text-box and other components that will allow a user
   * to actively use the program.
   */
  private void initInputPanel() {

    inputPanel = new JPanel();
    inputPanel.setBorder(BorderFactory.createTitledBorder("Images Loaded"));
    inputPanel.setLayout(new FlowLayout());
    mainPanel.add(inputPanel);

    imageName = new JTextField(9);
    imageName.setBorder(BorderFactory.createTitledBorder("image name"));

    addAs = new JTextField(9);
    addAs.setBorder(BorderFactory.createTitledBorder("new name"));

    imagePath = new JTextField(15);
    imagePath.setBorder(BorderFactory.createTitledBorder("load path"));

    savePath = new JTextField(15);
    savePath.setBorder(BorderFactory.createTitledBorder("save path"));

    brightIncrement = new JTextField(5);
    brightIncrement.setBorder(BorderFactory.createTitledBorder("brighten"));


    inputPanel.add(imageName);
    inputPanel.add(addAs);
    inputPanel.add(brightIncrement);
    inputPanel.add(imagePath);
    inputPanel.add(savePath);
    mainPanel.add(inputPanel);
  }

  private void initMenuLayout() {
    operations = new JMenuBar();
    editImage = new JMenu("Edit Image");
    operations.add(editImage);


    //io menu items
    file = new JMenu("file");
    load = new JMenuItem("load");
    save = new JMenuItem("save");

    file.add(load);
    file.add(save);
    operations.add(file);

    brighten = new JMenuItem("brighten");

    filters = new JMenu("filters");
    blur = new JMenuItem("blur");
    sharpen = new JMenuItem("sharpen");
    sepia = new JMenuItem("sepia");

    filters.add(blur);
    filters.add(sharpen);
    filters.add(sepia);


    flip = new JMenu("flip");
    horz = new JMenuItem("horizontal");
    vert = new JMenuItem("vertical");
    flip.add(horz);
    flip.add(vert);

    greyscale = new JMenu("greyscale");
    intensity = new JMenuItem("intensity greyscale");
    value = new JMenuItem("value greyscale");
    luma = new JMenuItem("luma greyscale");
    transGreyscale = new JMenuItem("trans greyscale");
    red = new JMenuItem("red component");
    green = new JMenuItem("green component");
    blue = new JMenuItem("blue component");

    greyscale.add(intensity);
    greyscale.add(value);
    greyscale.add(luma);
    greyscale.add(transGreyscale);
    greyscale.add(red);
    greyscale.add(green);
    greyscale.add(blue);


    editImage.add(brighten);
    editImage.add(filters);
    editImage.add(greyscale);
    editImage.add(flip);




    exit = new JButton("exit");
    operations.add(exit);
    this.setJMenuBar(operations);

  }

  private void addMessageOutput() {
    message = new JTextArea();
    message.setBorder(BorderFactory.createTitledBorder("Messages"));
    message.setEditable(false);
    message.setWrapStyleWord(true);
    inputPanel.add(message);
  }


  /**
   * Refreshes the ImageView in order to keep and update the view when changes are made.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Allows access into the view by taking in a message given and rendering onto the view.
   *
   * @param message String of what needs to be rendered
   */
  @Override
  public void renderMessage(String message) {
    this.message.setText(message);

  }

  /**
   * Allows the view to link actions set to send responses to the controller.
   *
   * @param features Features
   */
  @Override
  public void addFeatures(Features features) {
    exit.addActionListener(evt -> features.exitProgram());
    save.addActionListener(
        evt -> features.saveImageAs(savePath.getText(), imageName.getText()));
    load.addActionListener(
        evt -> features.loadImage(imagePath.getText(), addAs.getText()));

    brighten.addActionListener(
        evt -> features.brighten(Integer.parseInt(brightIncrement.getText()),
            imageName.getText(), addAs.getText()));
    horz.addActionListener(
        evt -> features.flipHorizontal(imageName.getText(), addAs.getText()));
    vert.addActionListener(
        evt -> features.flipVertical(imageName.getText(), addAs.getText()));
    intensity.addActionListener(
        evt -> features.intensityGrey(
            imageName.getText(), addAs.getText()));
    transGreyscale.addActionListener(
        evt -> features.transGrey(imageName.getText(), addAs.getText()));
    luma.addActionListener(
        evt -> features.lumaGrey(imageName.getText(), addAs.getText()));
    red.addActionListener(
        evt -> features.redGrey(imageName.getText(), addAs.getText()));
    green.addActionListener(
        evt -> features.greenGrey(imageName.getText(), addAs.getText()));
    blue.addActionListener(
        evt -> features.blueGrey(imageName.getText(), addAs.getText()));
    value.addActionListener(
        evt -> features.valueGrey(imageName.getText(), addAs.getText()));
    blur.addActionListener(
        evt -> features.blur(imageName.getText(), addAs.getText()));
    sharpen.addActionListener(
        evt -> features.sharpen(imageName.getText(), addAs.getText()));
    sepia.addActionListener(
        evt -> features.sepia(imageName.getText(), addAs.getText()));
  }

  /**
   * Used by the Controller, sets the current ImageModel that was loaded, changed, or saved in order
   * to display the ImageModel onto the view.
   *
   * @param model ImageModel
   */
  @Override
  public void setCurrentImage(ImageModel model) {
    BufferedImage bufferedImage = ImageUtil.getBufferedImage(model);
    imageLabel.setIcon(new ImageIcon(bufferedImage));
    histogramPanel.remove(histogram);
    histogram = new ImageHistogram(model);
    histogramPanel.add(histogram, BorderLayout.CENTER);
  }

  /**
   * Clears the input text on the text box for user inputs.
   */
  @Override
  public void clearInputString() {
    imageName.setText(addAs.getText());
    addAs.setText("");
    imagePath.setText("");
    savePath.setText("");

  }

  private void fileOpener(String s) {
    final JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG pgn ppm bmp", "jpg", "png", "ppm", "bmp");
    fileChooser.setFileFilter(filter);
    int retValue = fileChooser.showOpenDialog(null);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();

      if (s.equalsIgnoreCase("load")) {
        imagePath.setText(f.getAbsolutePath());
      }
      if (s.equalsIgnoreCase("save")) {
        savePath.setText(f.getAbsolutePath());
      }
    }
  }

  //for testing purposes
  private void setComponentName() {
    file.setName("fileMenu");
    load.setName("load");
    blur.setName("blur");
    horz.setName("horizontalFlip");
    addAs.setName("addAs");
    imageName.setName("imageName");
    imagePath.setName("imagePath");
    imageLabel.setName("imageLabel");
    histogram.setName("histogram");
  }
}

