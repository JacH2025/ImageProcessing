package controller;

import model.ImageModel;

/**
 * Interface for a Command that can be executed on an Image Model.
 */
public interface ImageCommand {

  /**
   * executes command on image model m.
   *
   * @param m ImageModel
   */
  void execute(ImageModel m);
}
