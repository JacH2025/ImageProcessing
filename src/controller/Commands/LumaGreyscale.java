package controller.Commands;

import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 * Command to generate a greyscale image based on the Luma value of each pixel.
 */
public class LumaGreyscale extends AbstractCommand {

  /**
   * Constructor for Luma Greyscale command.
   *
   * @param imageName     name of image to greyscale
   * @param destImageName name of result image
   */
  public LumaGreyscale(String imageName, String destImageName) {
    super(imageName, destImageName);
  }

  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
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
