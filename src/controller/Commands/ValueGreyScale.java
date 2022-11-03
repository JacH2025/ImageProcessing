package controller.Commands;

import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 * Command to generate a greyscale image based on the highest color value of each pixel.
 */
public class ValueGreyScale extends AbstractCommand {


  /**
   * Constructor for Value Greyscale command.
   *
   * @param imageName     name of image to greyscale
   * @param destImageName name of result image
   */
  public ValueGreyScale(String imageName, String destImageName) {
    super(imageName, destImageName);
  }

  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
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
