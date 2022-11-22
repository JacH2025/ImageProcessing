package model;

import java.util.HashMap;

/**
 * Interface for our Image Processor. Extends CommandImage so that commands can be executed on
 * ImageModel implementations.
 */
public interface ImageModel extends CommandImage {

  /**
   * Returns the HashMap that stores other ImageModels.
   *
   * @return HashMap String key that links to an ImageModel
   */
  HashMap<String, ImageModel> getImageStored();

  /**
   * Returns an image with given name (key) if such an image is loaded into the model.
   *
   * @param name name of image to get from model.
   * @return ImageModel
   */
  ImageModel getImageModel(String name);

  /**
   * Returns a 2D array of IPixels that makes up an image.
   *
   * @return IPixel[][]
   */
  IPixel[][] getImage();

  /**
   * Returns the Pixel at position x y.
   *
   * @param x x-axis of pixel
   * @param y y-axis of pixel
   */
  IPixel getPixel(int x, int y);

  /**
   * Returns the height of the image. (Measured through the amount of pixels that spans the
   * height).
   *
   * @return int height
   */
  int getHeight();

  /**
   * Returns the width of the image. (Measured through the amount of pixels that spans the width).
   *
   * @return int width
   */
  int getWidth();
}
