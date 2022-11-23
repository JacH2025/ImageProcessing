package controller;

/**
 * Features interface allows for the communication between two classes by passing
 * each other information in order to do an action.
 */
public interface Features {

  void loadImage(String imagePath, String imageName);
  void saveImageAs(String imagePath, String imageName);

  void flipHorizontal(String imageName, String destImageName);
  void flipVertical(String imageName, String destImageName);

  void brighten(int i,String imageName, String destImageName);

  void intensityGrey(String imageName, String destImageName);
  void lumaGrey(String imageName, String destImageName);
  void valueGrey(String imageName, String destImageName);
  void redGrey(String imageName, String destImageName);
  void greenGrey(String imageName, String destImageName);
  void blueGrey(String imageName,String destImageName);
  void transGrey(String imageName, String destImageName);

  void blur(String imageName, String destImageName);
  void sharpen(String imageName, String destImageName);
  void sepia(String imageName, String destImageName);

  void exitProgram();
}
