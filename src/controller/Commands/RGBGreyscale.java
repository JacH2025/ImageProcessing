package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;
import model.PixelImpl;

public class RGBGreyscale implements ImageCommands {
  String color;
  String imageName;
  String destImageName;

  /**
   *
   * @param color
   * @param imageName
   * @param destImageName
   */
  public RGBGreyscale(String color, String imageName, String destImageName) {
    this.color = color;
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void execute(ImageModel m) {
    IPixel[][] newImage = new IPixel[m.getHeight()][m.getWidth()];
    for (int i = 0; i < m.getHeight(); i++) {
      for (int j = 0; j < m.getWidth(); j++) {
        int value = 0;
        switch (color) {
          case "red":
            value = m.getPixels(i, j).getRed();
            break;
          case "green":
            value = m.getPixels(i, j).getGreen();
            break;
          case "blue":
            value = m.getPixels(i, j).getBlue();
            break;
        }
        newImage[i][j] = new PixelImpl(value, value, value);
      }
    }
  }
}
