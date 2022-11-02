package model;

/**
 *
 */
public interface ImageModel {



  /**
   *
   */
  void toGreyscale(Components c);

  /**
   *
   */
  void verticalFlip();

  /**
   *
   */
  void horizontalFlip();

  /**
   *
   */
  void brighten(int increment);

  /**
   *
   */
  void loadImage();

  /**
   *
   */
  void save();
}
