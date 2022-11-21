package controller.commands;

import java.util.HashMap;
import java.util.Objects;

import controller.ImageCommand;
import model.ImageModel;

/**
 * Abstract base class for implementations of {@link ImageCommand}.
 */
public abstract class AbstractCommand implements ImageCommand {
  protected final String imageName;
  protected final String destImageName;

  /**
   * Default constructor for a command.
   *
   * @param imageName     name of image to instruct
   * @param destImageName name of result image
   */
  public AbstractCommand(String imageName, String destImageName) {
    this.imageName = Objects.requireNonNull(imageName, "name cannot be null");
    this.destImageName = Objects.requireNonNull(destImageName, "name cannot be null");
  }


}
