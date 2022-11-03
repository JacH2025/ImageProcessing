import org.junit.Test;

import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link ImageModelImpl}.
 */
public class ImageModelTest {
  ImageModel model = new ImageModelImpl("res/6color.ppm");
  ImageModel koala = new ImageModelImpl("res/Koala.ppm");

  @Test
  public void testInitializedPixels() {
    assertEquals(255, model.getImage()[0][0].getRed());
    assertEquals(0, model.getImage()[0][0].getGreen());
    assertEquals(0, model.getImage()[0][0].getBlue());

    assertEquals(0, model.getImage()[0][1].getRed());
    assertEquals(255, model.getImage()[0][1].getGreen());
    assertEquals(0, model.getImage()[0][1].getBlue());

    assertEquals(0, model.getImage()[0][2].getRed());
    assertEquals(0, model.getImage()[0][2].getGreen());
    assertEquals(255, model.getImage()[0][2].getBlue());

    assertEquals(255, model.getImage()[1][0].getRed());
    assertEquals(255, model.getImage()[1][0].getGreen());
    assertEquals(0, model.getImage()[1][0].getBlue());

    assertEquals(255, model.getImage()[1][1].getRed());
    assertEquals(255, model.getImage()[1][1].getGreen());
    assertEquals(255, model.getImage()[1][1].getBlue());

    assertEquals(0, model.getImage()[1][2].getRed());
    assertEquals(0, model.getImage()[1][2].getGreen());
    assertEquals(0, model.getImage()[1][2].getBlue());
  }

  @Test
  public void testGetImageModelFromHashMap() {
    ImageModel storage = new ImageModelImpl();
    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
    ImageModel model = storage.getImageModel("6Color");

  }

  @Test
  public void testImageHeight() {
    assertEquals(2, model.getHeight());
    assertEquals(768, koala.getHeight());
  }

  @Test
  public void testImageWidth() {
    assertEquals(3, model.getWidth());
    assertEquals(1024, koala.getWidth());
  }

  @Test
  public void testLoadImageFromModel() {

  }

  @Test
  public void testSaveImageFromModel() {

  }
}
