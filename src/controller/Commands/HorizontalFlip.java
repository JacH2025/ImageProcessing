package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;

/**
 *
 */
public class HorizontalFlip implements ImageCommands {
  String imageName;
  String destImageName;

  /**
   *
   * @param imageName
   * @param destImageName
   */
  public HorizontalFlip(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void execute(ImageModel m) {
    IPixel[][] newImage = new IPixel[m.getHeight()][m.getWidth()];
    for (int i = 0; i < m.getHeight(); i++) {
      int x = 1;
      for (int j = 0; j < m.getWidth(); j++) {
        newImage[i][j] = m.getImage()[i][m.getWidth() - x];
        x++;
      }
    }
    m.loadImage(newImage.toString(), destImageName);
  }
}
