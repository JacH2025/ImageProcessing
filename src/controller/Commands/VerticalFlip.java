package controller.Commands;

import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;

/**
 * command to generate a vertically flipped image.
 */
public class VerticalFlip extends AbstractCommand {

  /**
   * default constructor for a command.
   *
   * @param imageName     name of image you want to brighten
   * @param destImageName name of result image
   */
  public VerticalFlip(String imageName, String destImageName) {
    super(imageName, destImageName);
  }


  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
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
