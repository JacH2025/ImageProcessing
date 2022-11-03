import org.junit.Test;

import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for {@link ImageModelImpl}.
 */
public class ImageModelTest {
  ImageModel model = new ImageModelImpl("res/6color.ppm");
  ImageModel koala = new ImageModelImpl("res/Koala.ppm");

  @Test
  public void testConstructorExceptions() {

  }

  @Test
  public void testInitializedPixels() {
    assertEquals(new PixelImpl(255, 0, 0), model.getImage()[0][0]);
    assertEquals(new PixelImpl(0, 255, 0), model.getImage()[0][1]);
    assertEquals(new PixelImpl(0, 0, 255), model.getImage()[0][2]);
    assertEquals(new PixelImpl(255, 255, 0), model.getImage()[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), model.getImage()[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), model.getImage()[1][2]);
  }

  @Test
  public void testGetImageModelFromHashMap() {
    ImageModel storage = new ImageModelImpl();
    storage.loadImage(new ImageModelImpl("res/6color.ppm"), "6Color");
    ImageModel model = storage.getImageModel("6Color");

    assertEquals(2, model.getHeight());
    assertEquals(3, model.getWidth());
    assertArrayEquals(new ImageModelImpl("res/6color.ppm").getImage(), model.getImage());
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
    ImageModel storage = new ImageModelImpl();

    try {
      storage.getImageModel("6Color");
      fail();
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    storage.loadImage(new ImageModelImpl("res/6color.ppm"), "6Color");

    try {
      storage.getImageModel("6Color");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    try {
      storage.getImageModel("ImageDoesNotExist");
      fail();
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  @Test
  public void testSaveImageFromModel() {
    ImageModel storage = new ImageModelImpl();
    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
    storage.save("res/new-6color.ppm", "6Color");

    assertArrayEquals(new ImageModelImpl("res/new-6Color.ppm").getImage(),
        new ImageModelImpl("res/6Color.ppm").getImage());
  }
}
