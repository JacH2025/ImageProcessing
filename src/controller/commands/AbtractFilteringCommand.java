package controller.commands;

import java.util.Objects;

import controller.ImageCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;

/**
 * Abstract base class for implementations of ImageCommand that apply an image filter kernel to a
 * image {@link ImageCommand}.
 */
public abstract class AbtractFilteringCommand extends AbstractCommand {
  protected final double[][] kernel;

  /**
   * Default constructor for a command.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public AbtractFilteringCommand(String imageName, String destImageName, double[][] kernel) {
    super(imageName, destImageName);
    this.kernel = Objects.requireNonNull(kernel,"kernel cannot be null");
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

    for (int r = 0; r < m.getHeight(); r++) {
      for (int c = 0; c < m.getWidth(); c++) {
        newImage[r][c] = applyKernel(model,r,c);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }

  /**
   * uses a filter kernel on a model at pixel at posn r,c , and returns a new pixel with the filter
   * applied to it.
   *
   * @param model model to
   * @param r pixel row
   * @param c pixel col
   * @return new IPixel with filter applied to it.
   */
  protected abstract IPixel applyKernel(ImageModel model, int r, int c);
}
