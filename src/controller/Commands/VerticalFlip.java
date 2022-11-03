package controller.Commands;

import controller.ImageCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;

/**
 *
 */
public class VerticalFlip implements ImageCommand {
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
    ImageModel model = m.getImage(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    int x = 1;
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
          newImage[i][j] = model.getImage()[model.getHeight() - x][j];
      }
      x++;
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
