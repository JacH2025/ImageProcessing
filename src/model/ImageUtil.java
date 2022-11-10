package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read an Image File formats (PNG, JPG, BMP, PPM),
 * and returns the image's information.
 */
public class ImageUtil {

  /**
   * Can read an Image file (PNG, JPG, BMP, PPM, etc), and returns an
   * IPixel 2D representation of it.
   *
   * @param filename the path of the file
   * @return IPixel[][] a 2D array that contains the coordinates of each pixel and the RGB
   *         values of each pixel
   * @throws IllegalArgumentException when file is not found
   */
  public static IPixel[][] readImage(String filename) throws IllegalArgumentException {
    if (isImagePPMFile(filename)) {
      return imageData(readPPM(filename));
    }
    BufferedImage image = readBufferedImage(filename);
    IPixel[][] pixels = new IPixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int color = image.getRGB(j, i);
        Color c = new Color(color);
        pixels[i][j] = new PixelImpl(c.getRed(), c.getGreen(), c.getBlue());
      }
    }
    return pixels;
  }

  /**
   * Gets height from an image file.
   *
   * @param filename the path of the file
   * @return int height of the image
   * @throws IllegalArgumentException when file is not found
   */
  public static int getImageHeight(String filename) throws IllegalArgumentException {
    if (isImagePPMFile(filename)) {
      return getPPMHeight(readPPM(filename));
    }
    BufferedImage image = readBufferedImage(filename);
    return image.getHeight();
  }

  /**
   * Gets width from an image file.
   *
   * @param filename the path of the file
   * @return int width of the image
   * @throws IllegalArgumentException when file is not found
   */
  public static int getImageWidth(String filename) throws IllegalArgumentException {
    if (isImagePPMFile(filename)) {
      return getPPMWidth(readPPM(filename));
    }
    BufferedImage image = readBufferedImage(filename);
    return image.getWidth();
  }

  /**
   * Reads the given Image file and converts to a BufferedImage.
   *
   * @param filename the path of the file
   * @return BufferedImage
   * @throws IllegalArgumentException when file is not found
   */
  private static BufferedImage readBufferedImage(String filename) throws IllegalArgumentException {
    BufferedImage image;
    try {
      image = ImageIO.read(new FileInputStream(filename));
    } catch (IOException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    return image;
  }

  /**
   * Checks if the given file is a PPM file.
   *
   * @param filename the path of the file
   * @return boolean true if the file ends in ".ppm"
   */
  private static boolean isImagePPMFile(String filename) {
    int index = filename.lastIndexOf('.');
    String fileType = filename.substring(index + 1).toLowerCase();
    return fileType.equals("ppm");
  }

  /**
   * Takes an IPixel 2D array and creates a copy as a BufferedImage.
   *
   * @param pixels 2D Pixel Array of an image
   * @param height image height
   * @param width image width
   * @return BufferedImage representation of input image
   */
  public static BufferedImage createImage(IPixel[][] pixels, int height, int width) {
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
   * Read an image file in the PPM format and creates a string that allows other methods to parse
   * through the contents.
   *
   * @param filename the path of the file
   * @return String contents of the file formatted
   */
  public static String readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    return builder.toString();
  }

  /**
   * Given the contents of a PPM file, creates a 2D array that contains the coordinates of each
   * pixel, and the RGB values represented as IPixels.
   *
   * @param contents the contents of a file formatted
   * @return IPixel[][] a 2D array that contains the coordinates of each pixel and the RGB values of
   *         each pixel
   */
  public static IPixel[][] imageData(String contents) {
    Scanner sc;
    sc = new Scanner(contents);
    checkPPMFile(sc);

    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt();

    IPixel[][] pixels = new IPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new PixelImpl(sc.nextInt(), sc.nextInt(), sc.nextInt());
      }
    }
    return pixels;
  }

  /**
   * Returns the height that is explicitly stated within the PPM File.
   *
   * @param contents the contents of a file formatted
   * @return int height of the image
   */
  public static int getPPMHeight(String contents) {
    Scanner sc;
    sc = new Scanner(contents);
    checkPPMFile(sc);
    sc.nextInt();
    return sc.nextInt();
  }

  /**
   * Returns the width that is explicitly stated within the PPM File.
   *
   * @param contents the contents of a file formatted
   * @return int width of the image
   */
  public static int getPPMWidth(String contents) {
    Scanner sc;
    sc = new Scanner(contents);
    checkPPMFile(sc);
    return sc.nextInt();
  }

  /**
   * Checks to make sure that the given file is a PPM File.
   *
   * @param sc Scanner of the file contents
   */
  private static void checkPPMFile(Scanner sc) {
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
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
  public static String createPPMFile(IPixel[][] pixels, int height, int width) {
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


