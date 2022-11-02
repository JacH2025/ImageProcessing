package controller.Commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 *
 */
public class Save implements ImageCommands {
  String imagePath;
  String imageName;

  /**
   *
   * @param imagePath
   * @param imageName
   */
  public Save(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public void execute(ImageModel m) {
    m.save(imagePath, imageName);
  }
}
