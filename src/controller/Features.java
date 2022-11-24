package controller;

/**
 * Features interface allows for the communication between two classes by passing
 * each other information in order to do an action.
 */
public interface Features {

  /**
   * Load Image Command.
   *
   * @param imagePath file image path
   * @param imageName image name
   */
  void loadImage(String imagePath, String imageName);

  /**
   * Save Image Command.
   *
   * @param imagePath file image path
   * @param imageName image name
   */
  void saveImageAs(String imagePath, String imageName);

  /**
   * Flip Horizontal Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void flipHorizontal(String imageName, String destImageName);

  /**
   * Flip Vertical Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void flipVertical(String imageName, String destImageName);

  /**
   * Brighten Command.
   *
   * @param i increment
   * @param imageName image name
   * @param destImageName new image name
   */
  void brighten(int i,String imageName, String destImageName);

  /**
   * Intensity Greyscale Command
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void intensityGrey(String imageName, String destImageName);

  /**
   * Luma Greyscale Command
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void lumaGrey(String imageName, String destImageName);

  /**
   * Value Greyscale Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void valueGrey(String imageName, String destImageName);

  /**
   * Red Component Greyscale Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void redGrey(String imageName, String destImageName);

  /**
   * Green Component Greyscale Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void greenGrey(String imageName, String destImageName);

  /**
   * Blue Component Greyscale Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void blueGrey(String imageName,String destImageName);

  /**
   * Greyscale Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void transGrey(String imageName, String destImageName);

  /**
   * Blur Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void blur(String imageName, String destImageName);

  /**
   * Sharpen Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void sharpen(String imageName, String destImageName);

  /**
   * Sepia Command.
   *
   * @param imageName image name
   * @param destImageName new image name
   */
  void sepia(String imageName, String destImageName);

  /**
   * Exits the Image Program and ends the operations.
   */
  void exitProgram();
}
