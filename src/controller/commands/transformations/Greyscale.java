package controller.commands.transformations;

/**
 * Greyscale command using Color transform.
 */
public class Greyscale extends AbstractColorTransform {
  /**
   * Constructs a Greyscale Command. Executing will search for ImageName in given model and add a
   * new greyscale image with destImageName.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public Greyscale(String imageName, String destImageName) {
    super(imageName, destImageName, new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}
    });
  }
}
