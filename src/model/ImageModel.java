package model;

/**
 *
 */
public interface ImageModel extends CommandImage {

  /**
   * returns an image with given name if such an image is loaded into the model.
   *
   * @param name name of image to get from model.
   * @return ImageModel
   */
  ImageModel getImageModel(String name);

  /**
   * Returns a 2D array of IPixels that represents the
   *
   * @return IPixel[][]
   */
  IPixel[][] getImage();

  /**
   * Returns the Pixel at posn x y;
   *
   * @param x x axis of pixel
   * @param y y axis of pixel
   */
  IPixel getPixel(int x, int y);

  /**
   * Returns the height of the image. (Measured through the amount of pixels that spans the
   * height).
   *
   * @return int
   */
  int getHeight();

  /**
   * Returns the width of the image. (Measured through the amount of pixels that spans the width).
   *
   * @return int
   */
  int getWidth();

  /**
   * Loads an image into the image model.
   *
   * @param image image to load.
   * @param name  name to give to loaded image in this model.
   */
  void loadImage(ImageModel image, String name);

  /** attempts to save an image to the location given.
   *
   *
   * @param location path to save file to
   * @param name     to save as
   */
  void save(String location, String name);
}
