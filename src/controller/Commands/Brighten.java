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
    ImageModel model = m.getModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int newR = cap(model.getPixel(i, j).getRed() + increment);
        int newG = cap(model.getPixel(i, j).getGreen() + increment);
        int newB = cap(model.getPixel(i, j).getBlue() + increment);
        newImage[i][j] = new PixelImpl(newR, newG, newB);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }

  private int cap(int value) {
    if (value < 0) {
      value = 0;
    } else if (value > 255) {
      value = 255;
    }
    return value;
  }
}
