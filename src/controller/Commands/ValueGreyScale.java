package controller.Commands;

import controller.ImageCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 *
 */
public class ValueGreyScale implements ImageCommand {
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
    ImageModel model = m.getImage(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int value = model.getPixel(i, j).getValue();
        newImage[i][j] = new PixelImpl(value, value, value);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
