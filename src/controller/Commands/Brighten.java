package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;
import model.PixelImpl;

/**
 *
 */
public class Brighten implements ImageCommands {
  String imageName;
  String destImageName;
  int increment;

  /**
   *
   * @param imageName
   * @param destImageName
   * @param increment
   */
  public Brighten(String imageName, String destImageName, int increment) {
    this.imageName = imageName;
    this.destImageName = destImageName;
    this.increment = increment;
  }

  @Override
  public void execute(ImageModel m) {
    IPixel[][] newImage = new IPixel[m.getHeight()][m.getWidth()];
    for (int i = 0; i < m.getHeight(); i++) {
      for (int j = 0; j < m.getWidth(); j++) {
        int newR = m.getImage()[i][j].getRed() + increment;
        int newG = m.getImage()[i][j].getGreen() + increment;
        int newB = m.getImage()[i][j].getBlue() + increment;
        newImage[i][j] = new PixelImpl(newR, newG, newB);
      }
    }
  }
}
