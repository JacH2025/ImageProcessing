package controller.commands.colorTransformations;


/**
 * sepiatone Using color transform
 */
public class SepiaTone extends AbstractColorTransform {
  /**
   * Constructor for sepiatone. Initialized transformation matrix with a 3x3 matrix which produces a
   * sepia tone effect. {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}}.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public SepiaTone(String imageName, String destImageName) {
    super(imageName, destImageName, new double[][]{
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}
    });
  }
}
