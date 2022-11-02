package model;

import java.io.FileNotFoundException;

/**
 *
 */
public interface ImageModel extends CommandImage {

  /**
   * Returns a 2D array of IPixels that represents the
   *
   * @return
   */
  IPixel[][] getImage();

  /**
   * Returns the IPixel (Representation of the
   *
   * @return
   */
  IPixel getPixels(int x, int y);

  /**
   * Returns the height of the image. (Measured through the amount of pixels that
   * spans the height).
   *
   * @return int
   */
  int getHeight();

  /**
   * Returns the width of the image. (Measured through the amount of pixels that
   * spans the width).
   *
   * @return int
   */
  int getWidth();

  /**
   *
   * @param filename
   * @param name
   */
  void loadImage(String filename, String name);

  /**
   *
   * @param location
   * @param name
   * @throws FileNotFoundException
   */
  void save(String location, String name);
}
