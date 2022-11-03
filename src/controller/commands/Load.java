package controller.commands;

import java.util.Objects;

import controller.ImageCommand;
import model.ImageModel;
import model.ImageModelImpl;

/**
 * Command to load an image onto an ImageModel.
 */
public class Load implements ImageCommand {
  private String imagePath;
  private String imageName;

  /**
   * Constructor for Load Command.
   *
   * @param imagePath path to image to load.
   * @param imageName name to load image with
   */
  public Load(String imagePath, String imageName) {
    this.imagePath = Objects.requireNonNull(imagePath);
    this.imageName = Objects.requireNonNull(imageName);
  }

  @Override
  public void execute(ImageModel m) {
    m.loadImage(new ImageModelImpl(imagePath), imageName);
  }
}
