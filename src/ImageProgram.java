import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;

/**
 * Image Program created as the entry point to the Image Processing program. Allows you to use
 * various commands to load, alter, and save various images that is given to the program.
 */
public class ImageProgram {

  /**
   * Run the Image Processing Program in the console.
   * Script inputs are allowed.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    Readable in = new InputStreamReader(System.in);
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-file")
          || args[i].equalsIgnoreCase("-f")) {
        try {
          File f = new File(args[i + 1]);
          if (f.exists() && f.isFile()) {
            in = new InputStreamReader(new FileInputStream(f));
            i++;
          }
        } catch (FileNotFoundException e) {
          System.out.append(e.getMessage());
          return;
        }
      }
    }
    ImageController c = new ImageControllerImpl(in, System.out);
    c.run();
  }
}
