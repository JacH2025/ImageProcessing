package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;

/**
 *
 */
public class VerticalFlip implements ImageCommands {
  String imageName;
  String destImageName;

  /**
   *
   * @param imageName
   * @param destImageName
   */
  public VerticalFlip(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void execute(ImageModel m) {
    IPixel[][] newImage = new IPixel[m.getHeight()][m.getWidth()];
    int x = 1;
    for (int i = 0; i < m.getHeight(); i++) {
      for (int j = 0; j < m.getWidth(); j++) {
          newImage[i][j] = m.getImage()[m.getHeight() - x][j];
      }
      x++;
    }
  }
}
