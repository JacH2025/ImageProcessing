package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and construct an array that contains
   * the image's data.
   *
   * @param filename the path of the file
   * @return int[][][] an array that contains the coordinates of each pixel
   *                   and the RGB values of each pixel
   */
  public static String readPPM(String filename) {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    return builder.toString();
  }

  /**
   *
   * @param data
   * @return
   */
  public static IPixel[][] imageData(String data) {
    Scanner sc;
    sc = new Scanner(data);
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
   *
   * @param data
   * @return
   */
  public static int getPPMHeight(String data) {
    Scanner sc;
    sc = new Scanner(data);
    checkPPMFile(sc);
    sc.nextInt();
    int height = sc.nextInt();
    return height;
  }

  /**
   *
   * @param data
   * @return
   */
  public static int getPPMWidth(String data) {
    Scanner sc;
    sc = new Scanner(data);
    checkPPMFile(sc);
    int width = sc.nextInt();
    return width;
  }

  public static void createPPMFile() {

  }

  /**
   *
   * @param sc
   */
  private static void checkPPMFile(Scanner sc) {
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
  }
}

