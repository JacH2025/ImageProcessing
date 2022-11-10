package model;

/**
 * Interface for our Image Processor. Extends CommandImage so that commands can be executed on
 * ImageModel implementations.
 */
public interface ImageModel extends CommandImage {

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

  /**
   * Attempts to save an image to the location given. Possible to save PPM file formats
   * and Image file formats supported by ImageIO such as PNG, JPEG, and BMP.
   *
   * @param location path to save file to
   * @param name     to save as
   */
  void save(String location, String name);
}
