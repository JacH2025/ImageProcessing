import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;

/**
 * Image Program created as the entry point to the Image Processing program.
 * Allows you to use various commands to load, alter, and save various images
 * that is given to the program.
 */
public class ImageProgram {

  /**
   * Run the Image Processing Program in the console.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    ImageController c = new ImageControllerImpl(new InputStreamReader(System.in), System.out);
    c.run();
  }
}
