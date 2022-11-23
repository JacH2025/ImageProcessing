package controller.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import controller.ImageCommand;
import model.IPixel;
import model.ImageModel;

/**
 * Command object to save an image.
 */
public class Save implements ImageCommand {
  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a Save command.
   *
   * @param imagePath path to image file
   * @param imageName the image to save from models
   */
  public Save(String imagePath, String imageName) {
    this.imagePath = Objects.requireNonNull(imagePath);
    this.imageName = Objects.requireNonNull(imageName);
  }

  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
    save(imagePath, model);
  }

  /**
   * Attempts to save an image to the location given. Possible to save PPM file formats
   * and Image file formats supported by ImageIO such as PNG, JPEG, and BMP.
   *
   * @param location path to save file to
   * @param model    model to save
   */
  private void save(String location, ImageModel model) {
    int index = location.lastIndexOf('.');
    String fileType = location.substring(index + 1).toLowerCase();
    if (fileType.equals("ppm")) {
      savePPMFile(location, model);
      return;
    }
    //if file type is not specified, default to PNG File type
    try {
      BufferedImage image = createImage(model.getImage(), model.getHeight(), model.getWidth());
      File file = new File(location);
      ImageIO.write(image, fileType, file);
    } catch (IOException e) {
      System.out.println("File could not be Saved");
    }
  }

  private void savePPMFile(String location, ImageModel model) throws IllegalArgumentException {
    if (!(location.substring(location.length() - 4)).equalsIgnoreCase(".ppm")) {
      throw new IllegalArgumentException("File name does not end with .ppm");
    }
    try {
      FileOutputStream fos = new FileOutputStream(location);
      fos.write(createPPMFile(model.getImage(), model.getHeight(), model.getWidth()).getBytes());
      fos.close();
    } catch (IOException e) {
      System.out.println("File could not be Saved");
    }
  }

  /**
   * Takes an IPixel 2D array and creates a copy as a BufferedImage.
   *
   * @param pixels 2D Pixel Array of an image
   * @param height image height
   * @param width image width
   * @return BufferedImage representation of input image
   */
  private BufferedImage createImage(IPixel[][] pixels, int height, int width) {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = pixels[i][j].getRed();
        int g = pixels[i][j].getGreen();
        int b = pixels[i][j].getBlue();
        Color color = new Color(r, g, b);
        image.setRGB(j, i, color.getRGB());
      }
    }
    return image;
  }

  /**
   * Given the information of an image, format a string into the format of a PPM File that includes
   * the file type "P3", the width and height in order, the max color value, and the rest of the
   * pixels represented by a triplet of Red, Green, and Blue values in order.
   *
   * @param pixels 2D Pixel Array of an image
   * @param height height of the image
   * @param width  width of the image
   * @return String formatted as a PPM File
   */
  private String createPPMFile(IPixel[][] pixels, int height, int width) {
    StringBuilder build = new StringBuilder();
    build.append("P3\n").append(width).append(" ").append(height).append("\n").append("255\n");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        build.append(pixels[i][j].getRed()).append(" ");
        build.append(pixels[i][j].getGreen()).append(" ");
        build.append(pixels[i][j].getBlue());
        if (j < width - 1) {
          build.append(" ");
        }
      }
      if (i < height - 1) {
        build.append("\n");
      }
    }
    return build.toString();
  }
}
