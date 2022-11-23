import org.junit.Test;

import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ImageModelImpl}.
 */
public class ImageModelTest {
  ImageModel colorsPPM = new ImageModelImpl("res/6color.ppm");
  ImageModel colorPNG = new ImageModelImpl("res/6Color.png");
  ImageModel candles = new ImageModelImpl("res/Candles.png");
  ImageModel clouds = new ImageModelImpl("res/Clouds.jpg");
  ImageModel mountains = new ImageModelImpl("res/MountainWaves.bmp");

  @Test
  public void testConstructorExceptions() {
    assertThrows(IllegalArgumentException.class,
        () -> new ImageModelImpl("res/ImageDoesNotExist"));
    IPixel[][] empty = null;
    assertThrows(IllegalArgumentException.class,
        () -> new ImageModelImpl(empty));
  }

  @Test
  public void testInitializedPixelsForGivenFile() {
    assertEquals(new PixelImpl(255, 0, 0), colorsPPM.getImage()[0][0]);
    assertEquals(new PixelImpl(0, 255, 0), colorsPPM.getImage()[0][1]);
    assertEquals(new PixelImpl(0, 0, 255), colorsPPM.getImage()[0][2]);
    assertEquals(new PixelImpl(255, 255, 0), colorsPPM.getImage()[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), colorsPPM.getImage()[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), colorsPPM.getImage()[1][2]);

    assertEquals(new PixelImpl(255, 0, 0), colorPNG.getImage()[0][0]);
    assertEquals(new PixelImpl(0, 255, 0), colorPNG.getImage()[0][1]);
    assertEquals(new PixelImpl(0, 0, 255), colorPNG.getImage()[0][2]);
    assertEquals(new PixelImpl(255, 255, 0), colorPNG.getImage()[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), colorPNG.getImage()[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), colorPNG.getImage()[1][2]);
  }

  @Test
  public void testGetImageModelHashMap() {
    ImageModel storage = new ImageModelImpl();
    assertTrue(storage.getImageStored().isEmpty());
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    assertTrue(!storage.getImageStored().isEmpty());
  }

  @Test
  public void testGetImageModelFromHashMap() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageModel model = storage.getImageModel("6Color");

    assertEquals(2, model.getHeight());
    assertEquals(3, model.getWidth());
    assertArrayEquals(new ImageModelImpl("res/6color.ppm").getImage(), model.getImage());
  }

  @Test
  public void testGetImageHeight() {
    assertEquals(2, colorsPPM.getHeight());
    assertEquals(2, colorPNG.getHeight());
    assertEquals(72, candles.getHeight());
    assertEquals(60, clouds.getHeight());
    assertEquals(55, mountains.getHeight());
  }

  @Test
  public void testGetImageWidth() {
    assertEquals(3, colorsPPM.getWidth());
    assertEquals(3, colorPNG.getWidth());
    assertEquals(90, candles.getWidth());
    assertEquals(90, clouds.getWidth());
    assertEquals(85, mountains.getWidth());
  }
}
