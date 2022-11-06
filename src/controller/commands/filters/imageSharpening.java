package controller.commands.filters;

/**
 * image sharpening command.
 */
public class imageSharpening extends AbstractFilteringCommand {

  /**
   * initializes image sharpening command.
   *
   * @param imageName     name of image to sharpen
   * @param destImageName name of result image
   */
  public imageSharpening(String imageName, String destImageName) {
    super(imageName, destImageName, new double[][]{
        {-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1 / 1.0, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0},
        {-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0}});
  }
}
