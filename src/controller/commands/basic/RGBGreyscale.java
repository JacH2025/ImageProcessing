package controller.commands.basic;

import java.util.Objects;

import controller.commands.AbstractCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

/**
 * Command to generate a greyscale image based on either the Red Blue or Green value of each pixel.
 */
public class RGBGreyscale extends AbstractCommand {
  private final String color;

  /**
   * Constructor for RGB Greyscale command.
   *
   * @param color         must be either red blue or green
   * @param imageName     name of image to greyscale
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
        int value;
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
