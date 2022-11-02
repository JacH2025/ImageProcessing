import java.io.IOException;
import java.io.InputStreamReader;

import controller.ImageControllerImpl;

public class ImageProgram {
  public static void main(String []args) throws IOException {
    String filename;
    if (args.length > 0) {
      filename = args[0];
    }
    else {
      filename = "sample.ppm";
    }

    ImageControllerImpl controller = new ImageControllerImpl(new InputStreamReader(System.in), System.out);
    controller.run();
  }
}
