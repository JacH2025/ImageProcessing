import org.junit.Test;

import controller.ImageCommand;
import controller.commands.transformations.Greyscale;
import controller.commands.transformations.SepiaTone;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@see src.controller.commands.transformations}.
 * Tests for {@link ImageCommand}
 */
public class ImageTransformCommandTest {

  @Test
  public void testCommandGreyscale() {
    ImageModel storage = new ImageModelImpl();
    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
    ImageCommand c = new Greyscale("6Color", "6Color-greyscale");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-greyscale").getImage();

    assertEquals(new PixelImpl(54, 54, 54), pixels[0][0]);
    assertEquals(new PixelImpl(182, 182, 182), pixels[0][1]);
    assertEquals(new PixelImpl(18, 18, 18), pixels[0][2]);
    assertEquals(new PixelImpl(236, 236, 236), pixels[1][0]);
    assertEquals(new PixelImpl(254, 254, 254), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testCommandSepiaTone() {
    ImageModel storage = new ImageModelImpl();
    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
    ImageCommand c = new SepiaTone("6Color", "6Color-sepia");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-sepia").getImage();

    assertEquals(new PixelImpl(100, 88, 69), pixels[0][0]);
    assertEquals(new PixelImpl(196, 174, 136), pixels[0][1]);
    assertEquals(new PixelImpl(48, 42, 33), pixels[0][2]);
    assertEquals(new PixelImpl(255, 255, 205), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 238), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }
}
