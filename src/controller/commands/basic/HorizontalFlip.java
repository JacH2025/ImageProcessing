package controller.commands.basic;

import controller.commands.AbstractCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;

/**
 * Command to generate a horizontally flipped image.
 */
public class HorizontalFlip extends AbstractCommand {

  /**
   * Constructor for Horizontal Flip command.
   *
   * @param imageName     name of image to horizontally flip
   * @param destImageName name of result image
   */
  public HorizontalFlip(String imageName, String destImageName) {
    super(imageName, destImageName);
  }

  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      int x = 1;
      for (int j = 0; j < model.getWidth(); j++) {
        newImage[i][j] = model.getImage()[i][model.getWidth() - x];
        x++;
      }
    }
    m.getImageStored().put(destImageName, new ImageModelImpl(newImage));
  }
}
