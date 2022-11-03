package controller.Commands;


import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 * command to generate a greyscale image based on the intensity value of each pixel.
 */
public class IntensityGreyscale extends AbstractCommand {

  /**
   * default constructor for a command.
   *
   * @param imageName     name of image you want to brighten
   * @param destImageName name of result image
   */
  public IntensityGreyscale(String imageName, String destImageName) {
    super(imageName, destImageName);
  }


  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int intensity = model.getPixel(i, j).getIntensity();
        newImage[i][j] = new PixelImpl(intensity, intensity, intensity);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
