package controller;

/**
 * Features interface allows for the communication between two classes by passing each other
 * information in order to do an action. Includes methods for the features that our image processor
 * is expected to be able to implement.
 */
public interface Features {

  /**
   * should be able to load an image.
   *
   * @param imagePath path to load from
   * @param imageName name to give image
   */
  void loadImage(String imagePath, String imageName);

  /**
   * should be able to save an image.
   *
   * @param imagePath path to save to
   * @param imageName name to give image
   */
  void saveImageAs(String imagePath, String imageName);

  /**
   * should be able to flip an image horizontally.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void flipHorizontal(String imageName, String destImageName);

  /**
   * should be able to flip an image vertically.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void flipVertical(String imageName, String destImageName);

  /**
   * should be able to brighten image.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void brighten(int i, String imageName, String destImageName);

  /**
   * should be able to use intensity of pixels to make image greyscale.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void intensityGrey(String imageName, String destImageName);

  /**
   * should be able to use luma value of pixels to make image greyscale.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void lumaGrey(String imageName, String destImageName);

  /**
   * should be able to use value of image pixels to make image greyscale.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void valueGrey(String imageName, String destImageName);

  /**
   * should be able to use red component of image to make image greyscale.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void redGrey(String imageName, String destImageName);

  /**
   * should be able to use green component of image to make image greyscale.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void greenGrey(String imageName, String destImageName);

  /**
   * should be able to use blue component of image to make image greyscale.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void blueGrey(String imageName, String destImageName);

  /**
   * should be able to use color transformations to make image greyscale.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void transGrey(String imageName, String destImageName);

  /**
   * should be able to blur image.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void blur(String imageName, String destImageName);

  /**
   * should be able to sharpen image.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void sharpen(String imageName, String destImageName);

  /**
   * should be able to make image sepia tone.
   *
   * @param imageName     name to give image
   * @param destImageName name for altered image
   */
  void sepia(String imageName, String destImageName);

  /**
   * should be able to exit.
   */
  void exitProgram();
}
