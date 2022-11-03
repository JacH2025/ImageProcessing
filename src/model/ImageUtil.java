package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains utility methods to read a PPM image from file and construct the image data.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and creates a string that allows other methods to parse
   * through the contents.
   *
   * @param filename the path of the file
   * @return String contents of the file formatted
   */
  public static String readPPM(String filename) {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    while (true) {
      assert sc != null;
      if (!sc.hasNextLine()) break;
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
   * each pixel
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
      throw new IllegalArgumentException();
    }
  }

  /**
   * Given the information of an image, format a string into the format of a PPM File that includes
   * the file type "P3", the width and height in order, the max color value, and the rest of the
   * pixels represented by a triplet of Red, Green, and Blue values in order.
   *
   * @param image  image data
   * @param height height of the image
   * @param width  width of the image
   * @return String formatted as a PPM File
   */
  public static String createPPMFile(IPixel[][] image, int height, int width) {
    StringBuilder build = new StringBuilder();
    build.append("P3\n").append(width).append(" ").append(height).append("\n").append("255\n");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        build.append(image[i][j].getRed()).append(" ");
        build.append(image[i][j].getGreen()).append(" ");
        build.append(image[i][j].getBlue());
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

