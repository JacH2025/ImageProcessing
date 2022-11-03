package model;

import controller.ImageCommand;

/**
 * Interface to allow for executing ImageCommands.
 */
public interface CommandImage {

  /**
   * executes ImageCommand c on this.
   *
   * @param c ImageCommand
   */
  void execute(ImageCommand c);
}
