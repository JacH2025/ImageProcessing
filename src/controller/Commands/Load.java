package controller.Commands;

import controller.ImageCommand;
import model.ImageModel;
import model.ImageModelImpl;

/**
 *
 */
public class Load implements ImageCommand {
  String imagePath;
  String imageName;

  /**
   *
   * @param imagePath
   * @param imageName
   */
  public Load(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public void execute(ImageModel m) {
    m.loadImage(new ImageModelImpl(imagePath), imageName);
  }
}
