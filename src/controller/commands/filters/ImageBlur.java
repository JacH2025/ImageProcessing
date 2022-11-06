package controller.commands.filters;

/**
 * Gaussian blur Command.
 */
public class ImageBlur extends AbstractFilteringCommand {
  /**
   * Constructor for image Blur. Initialized Filtering kernel with a 3x3 matrix which produces a
   * blurring effect.
   *            {1 / 16.0, 1 / 8.0, 1 / 16.0}
   *            {1 / 8.0, 1 / 4.0, 1 / 8.0}
   *            {1 / 16.0, 1 / 8.0, 1 / 16.0}
   *
   * @param imageName     name of image to blur
   * @param destImageName name of result image
   */
  public ImageBlur(String imageName, String destImageName) {
    super(imageName, destImageName,
        new double[][]{
            {1 / 16.0, 1 / 8.0, 1 / 16.0},
            {1 / 8.0, 1 / 4.0, 1 / 8.0},
            {1 / 16.0, 1 / 8.0, 1 / 16.0}
        }
    );
  }


}
