package controller.Commands;


import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;

/**
 * Command to Flip an image horizontally.
 */
public class HorizontalFlip extends AbstractCommand  {


  /**
   * constructor for a Brighten command.
   *
   * @param imageName     name of image you want to brighten
   * @param destImageName name of result image
   */
  public HorizontalFlip(String imageName, String destImageName) {
    super(imageName, destImageName);
  }

  @Override
  public void execute(ImageModel m) {
    ImageModel model = m.getImageModel(imageName);
    IPixel[][] newImage = new IPixel[model.getHeight()][model.getWidth()];
    for (int i = 0; i < model.getHeight(); i++) {
      int x = 1;
      for (int j = 0; j < model.getWidth(); j++) {
        newImage[i][j] = model.getImage()[i][model.getWidth() - x];
        x++;
      }
    }
    m.loadImage(new ImageModelImpl(newImage), destImageName);
  }
}
