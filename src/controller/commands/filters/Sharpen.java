package controller.commands.filters;

/**
 * image sharpening command.
 */
public class Sharpen extends AbstractFilterCommand {

  /**
   * Constructor for image Blur. Initialized Filtering kernel with a 5x5 kernel which produces a
   * sharpening effect.
   * {-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0},
   * {-1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0},
   * {-1 / 8.0, 1 / 4.0, 1 / 1.0, 1 / 4.0, -1 / 8.0},
   * {-1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0},
   * {-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0}}
   *
   * @param imageName     name of image to sharpen
   * @param destImageName name of result image
   */
  public Sharpen(String imageName, String destImageName) {
    super(imageName, destImageName, new double[][]{
        {-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1 / 1.0, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0}});
  }
}
