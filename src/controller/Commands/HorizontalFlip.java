package controller.Commands;

import controller.ImageCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;

/**
 *
 */
public class HorizontalFlip implements ImageCommand {
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
    ImageModel model = m.getImage(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      int x = 1;
      for (int j = 0; j < model.getWidth(); j++) {
        newImage[i][j] = model.getImage()[i][model.getWidth() - x];
        x++;
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
