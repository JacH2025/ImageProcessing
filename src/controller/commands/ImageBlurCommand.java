package controller.commands;

import model.IPixel;
import model.ImageModel;
import model.PixelImpl;

public class ImageBlurCommand extends AbtractFilteringCommand {
  /**
   * Default constructor for a command.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public ImageBlurCommand(String imageName, String destImageName) {
    super(imageName, destImageName,
            new double[][]{
                    {1 / 16.0, 1 / 8.0, 1 / 16.0},
                    {1 / 8.0, 1 / 4.0, 1 / 8.0},
                    {1 / 16.0, 1 / 8.0, 1 / 16.0}
            }
    );
  }


  /**
   * uses a filter kernel on a model at pixel at posn r,c , and returns a new pixel with the filter
   * applied to it.
   *
   * @param model model to
   * @param r     pixel row
   * @param c     pixel col
   * @return new IPixel with filter applied to it.
   */
  @Override
  protected IPixel applyKernel(ImageModel model, int r, int c) {
    double red = 0;
    double blue = 0;
    double green = 0;
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        red += model.getPixel(r - 1, c - 1).getRed() * kernel[i][j];
        green += model.getPixel(r - 1, c - 1).getGreen() * kernel[i][j];
        blue += model.getPixel(r - 1, c - 1).getBlue() * kernel[i][j];
      }
    }
    return new PixelImpl((int) red, (int) green, (int) blue);
  }
}
