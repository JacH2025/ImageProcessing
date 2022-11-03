package controller.Commands;

import java.util.Objects;

import controller.ImageCommand;

public abstract class AbstractCommand implements ImageCommand {
  protected final String imageName;
  protected final String destImageName;

  /**
   * default constructor for a command.
   *
   * @param imageName     name of image you want to brighten
   * @param destImageName name of result image
   */
  public AbstractCommand(String imageName, String destImageName) {
    this.imageName = Objects.requireNonNull(imageName,"name cannot be null");
    this.destImageName = Objects.requireNonNull(destImageName,"name cannot be null");
  }


}
