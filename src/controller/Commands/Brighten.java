package controller.Commands;

import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 * Command to increase the rgb values of every pixel of an image by the increment given.
 */
public class Brighten extends AbstractCommand {
  private final int increment;

  /**
   * Constructor for Brighten command.
   *
   * @param imageName     name of image you want to brighten
   * @param destImageName name of result image
   * @param increment     to brighten by
   */
  public Brighten(int increment, String imageName, String destImageName) {
    super(imageName,destImageName);
    this.increment = increment;
  }

  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int newR = model.getPixel(i, j).getRed() + increment;
        int newG = model.getPixel(i, j).getGreen() + increment;
        int newB = model.getPixel(i, j).getBlue() + increment;
        newImage[i][j] = new PixelImpl(newR, newG, newB);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }

}
