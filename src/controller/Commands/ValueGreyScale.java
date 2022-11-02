package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;
import model.PixelImpl;

/**
 *
 */
public class ValueGreyScale implements ImageCommands {

  String imageName;
  String destImageName;

  /**
   *
   * @param imageName
   * @param destImageName
   */
  public ValueGreyScale(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void execute(ImageModel m) {
    IPixel[][] newImage = new IPixel[m.getHeight()][m.getWidth()];
    for (int i = 0; i < m.getHeight(); i++) {
      for (int j = 0; j < m.getWidth(); j++) {
        int value = m.getImage()[i][j].getValue();
        newImage[i][j] = new PixelImpl(value, value, value);
      }
    }
  }
}
