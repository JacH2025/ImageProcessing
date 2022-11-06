package controller.commands.filters;

import java.util.Objects;

import controller.ImageCommand;
import controller.commands.AbstractCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 * Abstract base class for implementations of ImageCommand that apply an image filter kernel to a
 * image {@link ImageCommand}.
 */
public abstract class AbstractFilteringCommand extends AbstractCommand {
  protected double[][] kernel;

  /**
   * Default constructor for a AbstractFilteringCommand.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public AbstractFilteringCommand(String imageName, String destImageName, double[][] kernel) {
    super(imageName, destImageName);
    this.kernel = Objects.requireNonNull(kernel, "kernel cannot be null");

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
        newImage[r][c] = applyKernel(model, r, c);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
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
  private IPixel applyKernel(ImageModel model, int r, int c) {
    double[] rgb = new double[]{0, 0, 0};
    int loopOffset = kernel.length / 2; //this shall center the kernel on the pixel at r c

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        if (pixelExists(model, r + i - loopOffset, c + j - loopOffset)) {
          IPixel p = model.getPixel(r + i - loopOffset, c + j - loopOffset);
          rgbAccumulator(p, rgb, kernel[i][j]);
        }

      }
    }
    return new PixelImpl((int) rgb[0], (int) rgb[1], (int) rgb[2]);
  }

  /**
   * this method should increment rgb( with rgb[0] representing red value rgb[1] : green and rgb[2]
   * blue) rgb shall be incremented based on the pixel and the kernel value that overlaps with the
   * pixel
   *
   * @param p      pixel to increment based on
   * @param rgb    rgb values being accumulated for a new pixel
   * @param kernel kernel value that overlaps with pixel p
   */
  private void rgbAccumulator(IPixel p, double[] rgb, double kernel) {
    rgb[0] += kernel * p.getRed();
    rgb[1] += kernel * p.getGreen();
    rgb[2] += kernel * p.getBlue();
  }

  /**
   * returns true if a pixel exists at location rc
   */
  private boolean pixelExists(ImageModel model, int r, int c) {
    return r < model.getHeight() && c < model.getWidth() && r >= 0 && c >= 0;
  }


}
