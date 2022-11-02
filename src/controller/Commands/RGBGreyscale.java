package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
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
    ImageModel model = m.getModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int value = 0;
        switch (color) {
          case "red":
            value = model.getPixel(i, j).getRed();
            break;
          case "green":
            value = model.getPixel(i, j).getGreen();
            break;
          case "blue":
            value = model.getPixel(i, j).getBlue();
            break;
        }
        newImage[i][j] = new PixelImpl(value, value, value);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
