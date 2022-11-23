import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Locale;

import controller.ImageControllerFeat;
import controller.ImageControllerImpl;
import view.ImageView;
import view.ImageViewFeat;

/**
 * Image Program created as the entry point to the Image Processing program. Allows you to use
 * various commands to load, alter, and save various images that is given to the program.
 */
public class ImageProgram {

  /**
   * Run the Image Processing Program in the console. Script inputs are allowed.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    boolean text = false;
    Readable in = new InputStreamReader(System.in);

    for (int i = 0; i < args.length; i++) {
      String s = args[i].toLowerCase(Locale.ROOT);

      switch (s) {
        case "-file":
        case "-f":
          //text = true;
          if (i + 1 < args.length) {
            in = setInput(args[i + 1]);
            if (in == null) {
              System.out.printf("could not find file: %S%nProgram will exit%n", args[i + 1]);
              return;
            }
            i++;
          } else {
            System.out.print("file argument must be followed by a filepath" + System.lineSeparator());
          }
        case "-text":
        case "-t":
          text = true;
          break;
        default:
          System.out.printf("unrecognized command: %S%nProgram will exit%n", args[i]);
          return;
      }
    }
    startProgram(in, text);
  }


  private static void startProgram(Readable in, boolean text) {
    if (text) {
      new ImageControllerImpl(in, System.out).run();
      return;
    }
    ImageView frame = new ImageViewFeat();
    new ImageControllerFeat(frame);
  }

  private static InputStreamReader setInput(String arg) {
    try {
      File f = new File(arg);
      if (f.exists() && f.isFile()) {
        return new InputStreamReader(new FileInputStream(f));
      }
    } catch (FileNotFoundException e) {
      System.out.append(e.getMessage());
      return null;
    }
    return null;
  }
}
