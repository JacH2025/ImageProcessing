package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 *
 */
public class Brighten implements ImageCommands {
  int increment;
  String imageName;
  String destImageName;

  /**
   *
   * @param imageName
   * @param destImageName
   * @param increment
   */
  public Brighten(int increment, String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
    this.increment = increment;
  }

  @Override
  public void execute(ImageModel m) {
    IPixel[][] newImage = new IPixel[m.getHeight()][m.getWidth()];
    for (int i = 0; i < m.getHeight(); i++) {
      for (int j = 0; j < m.getWidth(); j++) {
        int newR = m.getPixel(i, j).getRed() + increment;
        int newG = m.getPixel(i, j).getGreen() + increment;
        int newB = m.getPixel(i, j).getBlue() + increment;
        newImage[i][j] = new PixelImpl(newR, newG, newB);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
