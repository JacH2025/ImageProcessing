package controller.commands.colorTransformations;

public class sepiatone extends AbstractColorTransform {
  /**
   * Default constructor for a command.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public sepiatone(String imageName, String destImageName) {
    super(imageName, destImageName, new double[][]{
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}
    });
  }
}
