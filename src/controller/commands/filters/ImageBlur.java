package controller.commands.filters;

/**
 * Gaussian blur Command.
 */
public class ImageBlur extends AbstractFilteringCommand {
  /**
   * Default constructor for a command.
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
