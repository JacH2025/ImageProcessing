package controller.commands.colorTransformations;

import java.util.Objects;

import controller.commands.AbstractCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 * overide trans if the transformation you are implementing is not linear.
 */
public abstract class AbstractColorTransform extends AbstractCommand {
  private double[][] transMatrix;

  /**
   * Default constructor for a command.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public AbstractColorTransform(String imageName, String destImageName, double[][] transMatrix) {
    super(imageName, destImageName);
    this.transMatrix = Objects.requireNonNull(transMatrix);
  }

  /**
   * Executes command on image model m.
   *
   * @param m ImageModel
   */
  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];

    for (int r = 0; r < model.getHeight(); r++) {
      for (int c = 0; c < model.getWidth(); c++) {
        newImage[r][c] = trans(model.getPixel(r, c));
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }

  /**
   * linear matrix multiplication of pixel rgb values by the transformation matrix. override to do
   * transformations that dont uniformly apply to every color channel.
   *
   * @param p pixel to transform
   * @return new pixel representing p with the transformation matrix applied.
   */
  protected IPixel trans(IPixel p) {
    double[] rgb = new double[3];
    double[] prgb = new double[]{p.getRed(), p.getGreen(), p.getBlue()};
    for (int i = 0; i < transMatrix.length; i++) {
      for (int j = 0; j < transMatrix.length; j++) {
        rgb[i] += prgb[j] * transMatrix[i][j];
      }
    }
    return new PixelImpl((int) rgb[0], (int) rgb[1], (int) rgb[2]);
  }
}
