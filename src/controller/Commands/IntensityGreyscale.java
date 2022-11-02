package controller.Commands;

import controller.ImageCommands;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 *
 */
public class IntensityGreyscale implements ImageCommands {
  String imageName;
  String destImageName;

  /**
   *
   * @param imageName
   * @param destImageName
   */
  public IntensityGreyscale(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void execute(ImageModel m) {
    IPixel[][] newImage = new IPixel[m.getHeight()][m.getWidth()];
    for (int i = 0; i < m.getHeight(); i++) {
      for (int j = 0; j < m.getWidth(); j++) {
        int intensity = m.getPixel(i, j).getIntensity();
        newImage[i][j] = new PixelImpl(intensity, intensity, intensity);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
