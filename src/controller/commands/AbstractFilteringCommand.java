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
public abstract class AbstractFilteringCommand extends AbstractCommand {
  protected double[][] kernel;

  /**
   * Default constructor for a command.
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
       // System.out.println("absfilter "+ newImage[r][c].toString());
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
  protected abstract IPixel applyKernel(ImageModel model, int r, int c);

  /**
   * returns true if a pixel exists at location rc
   *
   * @param r
   * @param c
   * @return
   */
  protected boolean pixelExists(ImageModel model, int r, int c) {
    try {
      model.getPixel(r, c);
      return true;
    } catch (IndexOutOfBoundsException e) {
      return false;

    }
  }


}
