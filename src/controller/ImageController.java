package controller;

/**
 * Controller for ImageModel. Should be able to take user input, and use them to do various
 * processes to an ImageModel.
 */
public interface ImageController {

  /**
   * Run allows you to run the operations of an Image Processing program where you
   * can run various commands to load, alter, and save images.
   *
   * @throws IllegalStateException if input cannot be read or output is not transmittable
   */
  void run() throws IllegalStateException;
}
