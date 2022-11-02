import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;
import model.ImageUtil;

public class ImageProgram {
  public static void main(String []args) throws IOException {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    }
    else {
      filename = "sample.ppm";
    }

    ImageModel model = new ImageModelImpl(filename);

  }
}
