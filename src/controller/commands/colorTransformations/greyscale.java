package controller.commands.colorTransformations;

public class greyscale extends AbstractColorTransform {
  /**
   * Default constructor for a command.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public greyscale(String imageName, String destImageName) {
    super(imageName, destImageName, new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}
    });
  }
}
