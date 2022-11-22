import org.junit.Test;

import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
  public void testGetImageModelFromHashMap() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageModel model = storage.getImageModel("6Color");

    assertEquals(2, model.getHeight());
    assertEquals(3, model.getWidth());
    assertArrayEquals(new ImageModelImpl("res/6color.ppm").getImage(), model.getImage());
  }

  @Test
  public void testImageHeight() {
    assertEquals(2, colorsPPM.getHeight());
    assertEquals(2, colorPNG.getHeight());
    assertEquals(72, candles.getHeight());
    assertEquals(60, clouds.getHeight());
    assertEquals(55, mountains.getHeight());
  }

  @Test
  public void testImageWidth() {
    assertEquals(3, colorsPPM.getWidth());
    assertEquals(3, colorPNG.getWidth());
    assertEquals(90, candles.getWidth());
    assertEquals(90, clouds.getWidth());
    assertEquals(85, mountains.getWidth());
  }

//  @Test
//  public void testLoadPPMImageFromModel() {
//    ImageModel storage = new ImageModelImpl();
//    try {
//      storage.getImageModel("6Color");
//      fail();
//    } catch (IllegalArgumentException e) {
//      e.getMessage();
//    }
//
//    storage.loadImage(new ImageModelImpl("res/6color.ppm"), "6ColorPPM");
//    storage.loadImage(new ImageModelImpl("res/6color.png"), "6ColorPNG");
//
//    try {
//      storage.getImageModel("6ColorPPM");
//    } catch (IllegalArgumentException e) {
//      e.getMessage();
//    }
//    try {
//      storage.getImageModel("6ColorPNG");
//    } catch (IllegalArgumentException e) {
//      e.getMessage();
//    }
//    try {
//      storage.getImageModel("ImageDoesNotExist");
//      fail();
//    } catch (IllegalArgumentException e) {
//      e.getMessage();
//    }
//  }
//
//  @Test
//  public void testSaveImageFromModel() {
//    ImageModel storage = new ImageModelImpl();
//    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
//    storage.save("res/test-6Color.png", "6Color");
//    storage.loadImage(new ImageModelImpl("res/test-6Color.png"), "6ColorPNG");
//    storage.save("res/test-6Color.ppm", "6ColorPNG");
//
//    assertArrayEquals(new ImageModelImpl("res/test-6Color.png").getImage(),
//        new ImageModelImpl("res/6Color.ppm").getImage());
//    assertArrayEquals(new ImageModelImpl("res/test-6Color.ppm").getImage(),
//        new ImageModelImpl("res/6Color.ppm").getImage());
//  }
//
//  @Test
//  public void testSavePPMImageFromModel() {
//    ImageModel storage = new ImageModelImpl();
//    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
//    storage.save("res/test-6color.ppm", "6Color");
//
//    assertArrayEquals(new ImageModelImpl("res/test-6Color.ppm").getImage(),
//        new ImageModelImpl("res/6Color.ppm").getImage());
//  }
}
