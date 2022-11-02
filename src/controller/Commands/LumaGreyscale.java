package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 *
 */
public class LumaGreyscale implements ImageCommands {
  String imageName;
  String destImageName;

  /**
   *
   * @param imageName
   * @param destImageName
   */
  public LumaGreyscale(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int luma = model.getPixel(i, j).getLuma();
        newImage[i][j] = new PixelImpl(luma, luma, luma);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
