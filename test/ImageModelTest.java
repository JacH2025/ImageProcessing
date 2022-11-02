import org.junit.Test;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;

public class ImageModelTest {

  ImageModel model = new ImageModelImpl("Koala.ppm");

  public ImageModelTest() throws IOException {
  }

  @Test
  public void testInitializedImage() {

  }
}
