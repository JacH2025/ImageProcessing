package controller.Commands;

import java.util.Objects;

import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 * command to generate a greyscale image based on either the Red Blue or Green value of each pixel.
 */
public class RGBGreyscale extends AbstractCommand {
  private final String color;

  /**
   * default constructor for a command.
   *
   * @param color         must be either red blue or green
   * @param imageName     name of image you want to brighten
   * @param destImageName name of result image
   */
  public RGBGreyscale(String color, String imageName, String destImageName) {
    super(imageName, destImageName);
    this.color = Objects.requireNonNull(color);
  }


  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int value = 0;
        switch (color) {
          case "red":
            value = model.getPixel(i, j).getRed();
            break;
          case "green":
            value = model.getPixel(i, j).getGreen();
            break;
          case "blue":
            value = model.getPixel(i, j).getBlue();
            break;
          default:
            throw new IllegalArgumentException("color must be one of: red, blue , green");

        }
        newImage[i][j] = new PixelImpl(value, value, value);
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
