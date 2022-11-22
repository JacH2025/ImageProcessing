import org.junit.Test;

import model.ImageModel;
import model.ImageModelImpl;
import view.ImageHistogram;

public class ImageHistogramTest {

  @Test
  public void testGetRedValues() {
    ImageModel model = new ImageModelImpl("res/6Color.png");
    ImageHistogram histogram = new ImageHistogram(model);
  }
}
