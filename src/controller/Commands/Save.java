package controller.Commands;

import java.util.Objects;

import controller.ImageCommand;
import model.ImageModel;

/**
 * Command object to save an image.
 */
public class Save implements ImageCommand {
  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a Save command.
   *
   * @param imagePath path to image file
   * @param imageName the image to save from models
   */
  public Save(String imagePath, String imageName) {
    this.imagePath = Objects.requireNonNull(imagePath);
    this.imageName = Objects.requireNonNull(imageName);
  }

  @Override
  public void execute(ImageModel m) {
    m.save(imagePath, imageName);
  }
}
