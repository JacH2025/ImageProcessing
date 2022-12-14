import org.junit.Test;

import controller.ImageCommand;
import controller.commands.basic.Brighten;
import controller.commands.basic.HorizontalFlip;
import controller.commands.basic.IntensityGreyscale;
import controller.commands.basic.LumaGreyscale;
import controller.commands.basic.RGBGreyscale;
import controller.commands.basic.ValueGreyscale;
import controller.commands.basic.VerticalFlip;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@see src.controller.commands.basic}. Tests for {@link ImageCommand}
 */
public class ImageBasicCommandTest {

  @Test
  public void testCommandBrighten() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new Brighten(50, "6Color", "6Color-brighten");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-brighten").getImage();

    assertEquals(new PixelImpl(255, 50, 50), pixels[0][0]);
    assertEquals(new PixelImpl(50, 255, 50), pixels[0][1]);
    assertEquals(new PixelImpl(50, 50, 255), pixels[0][2]);
    assertEquals(new PixelImpl(255, 255, 50), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(50, 50, 50), pixels[1][2]);
  }

  @Test
  public void testCommandDarken() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new Brighten(-50, "6Color", "6Color-darken");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-darken").getImage();

    assertEquals(new PixelImpl(205, 0, 0), pixels[0][0]);
    assertEquals(new PixelImpl(0, 205, 0), pixels[0][1]);
    assertEquals(new PixelImpl(0, 0, 205), pixels[0][2]);
    assertEquals(new PixelImpl(205, 205, 0), pixels[1][0]);
    assertEquals(new PixelImpl(205, 205, 205), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testCommandHorizontalFlip() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new HorizontalFlip("6Color", "6Color-horizontal");
    c.execute(storage);
    IPixel[][] oldPixels = storage.getImageModel("6Color").getImage();
    IPixel[][] newPixels = storage.getImageModel("6Color-horizontal").getImage();

    assertEquals(oldPixels[0][2], newPixels[0][0]);
    assertEquals(oldPixels[0][1], newPixels[0][1]);
    assertEquals(oldPixels[0][0], newPixels[0][2]);
    assertEquals(oldPixels[1][2], newPixels[1][0]);
    assertEquals(oldPixels[1][1], newPixels[1][1]);
    assertEquals(oldPixels[1][0], newPixels[1][2]);
  }

  @Test
  public void testCommandVerticalFlip() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new VerticalFlip("6Color", "6Color-vertical");
    c.execute(storage);
    IPixel[][] oldPixels = storage.getImageModel("6Color").getImage();
    IPixel[][] newPixels = storage.getImageModel("6Color-vertical").getImage();

    assertEquals(oldPixels[1][0], newPixels[0][0]);
    assertEquals(oldPixels[1][1], newPixels[0][1]);
    assertEquals(oldPixels[1][2], newPixels[0][2]);
    assertEquals(oldPixels[0][0], newPixels[1][0]);
    assertEquals(oldPixels[0][1], newPixels[1][1]);
    assertEquals(oldPixels[0][2], newPixels[1][2]);
  }

  @Test
  public void testCommandLumaGreyscale() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new LumaGreyscale("6Color", "6Color-luma");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-luma").getImage();

    assertEquals(new PixelImpl(54, 54, 54), pixels[0][0]);
    assertEquals(new PixelImpl(182, 182, 182), pixels[0][1]);
    assertEquals(new PixelImpl(18, 18, 18), pixels[0][2]);
    assertEquals(new PixelImpl(237, 237, 237), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testCommandIntensityGreyscale() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new IntensityGreyscale("6Color", "6Color-intensity");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-intensity").getImage();

    assertEquals(new PixelImpl(85, 85, 85), pixels[0][0]);
    assertEquals(new PixelImpl(85, 85, 85), pixels[0][1]);
    assertEquals(new PixelImpl(85, 85, 85), pixels[0][2]);
    assertEquals(new PixelImpl(170, 170, 170), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testCommandValueGreyscale() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new ValueGreyscale("6Color", "6Color-value");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-value").getImage();

    assertEquals(new PixelImpl(255, 255, 255), pixels[0][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[0][1]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[0][2]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testCommandRedGreyscale() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new RGBGreyscale("red", "6Color", "6Color-red");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-red").getImage();

    assertEquals(new PixelImpl(255, 255, 255), pixels[0][0]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[0][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[0][2]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testCommandGreenGreyscale() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new RGBGreyscale("green", "6Color", "6Color-green");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-green").getImage();

    assertEquals(new PixelImpl(0, 0, 0), pixels[0][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[0][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[0][2]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testCommandBlueGreyscale() {
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    ImageCommand c = new RGBGreyscale("blue", "6Color", "6Color-blue");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-blue").getImage();

    assertEquals(new PixelImpl(0, 0, 0), pixels[0][0]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[0][1]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[0][2]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testCombineHorizontalThenVerticalFlips() {
    ImageCommand c;
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    c = new HorizontalFlip("6Color", "6Color-horizontal");
    c.execute(storage);
    c = new VerticalFlip("6Color-horizontal", "6Color-horizontal-vertical");
    c.execute(storage);
    IPixel[][] oldPixels = storage.getImageModel("6Color").getImage();
    IPixel[][] newPixels = storage.getImageModel("6Color-horizontal-vertical").getImage();

    assertEquals(oldPixels[1][2], newPixels[0][0]);
    assertEquals(oldPixels[1][1], newPixels[0][1]);
    assertEquals(oldPixels[1][0], newPixels[0][2]);
    assertEquals(oldPixels[0][2], newPixels[1][0]);
    assertEquals(oldPixels[0][1], newPixels[1][1]);
    assertEquals(oldPixels[0][0], newPixels[1][2]);
  }

  @Test
  public void testCombineVerticalThenHorizontalFlips() {
    ImageCommand c;
    ImageModel storage = new ImageModelImpl();
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    c = new VerticalFlip("6Color", "6Color-vertical");
    c.execute(storage);
    c = new HorizontalFlip("6Color-vertical", "6Color-vertical-horizontal");
    c.execute(storage);
    IPixel[][] oldPixels = storage.getImageModel("6Color").getImage();
    IPixel[][] newPixels = storage.getImageModel("6Color-vertical-horizontal").getImage();

    assertEquals(oldPixels[1][2], newPixels[0][0]);
    assertEquals(oldPixels[1][1], newPixels[0][1]);
    assertEquals(oldPixels[1][0], newPixels[0][2]);
    assertEquals(oldPixels[0][2], newPixels[1][0]);
    assertEquals(oldPixels[0][1], newPixels[1][1]);
    assertEquals(oldPixels[0][0], newPixels[1][2]);
  }
}
