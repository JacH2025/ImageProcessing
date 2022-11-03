import org.junit.Test;

import model.IPixel;
import model.ImageUtil;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link ImageUtil}.
 */
public class ImageUtilsTest {

  @Test
  public void testReadPPM() {
    String ppmString = ImageUtil.readPPM("res/6color.ppm");
    String expected = "P3\n" + "3 2\n" + "255\n" +
        "255   0   0     0 255   0     0   0 255\n" +
        "255 255   0   255 255 255     0   0   0\n";
    assertEquals(expected, ppmString);
  }

  @Test
  public void testImageData() {
    String ppmString = ImageUtil.readPPM("res/6color.ppm");
    IPixel[][] pixels = ImageUtil.imageData(ppmString);
    assertEquals(new PixelImpl(255, 0, 0), pixels[0][0]);
    assertEquals(new PixelImpl(0, 255, 0), pixels[0][1]);
    assertEquals(new PixelImpl(0, 0, 255), pixels[0][2]);
    assertEquals(new PixelImpl(255, 255, 0), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testGetPPMHeight() {
    String ppmString = ImageUtil.readPPM("res/6color.ppm");
    int height = ImageUtil.getPPMHeight(ppmString);
    assertEquals(2, height);
  }

  @Test
  public void testGetPPMWidth() {
    String ppmString = ImageUtil.readPPM("res/6color.ppm");
    int width = ImageUtil.getPPMWidth(ppmString);
    assertEquals(3, width);
  }

  @Test
  public void testCreatePPMFile() {
    IPixel pixel1 = new PixelImpl(255, 0, 0);
    IPixel pixel2 = new PixelImpl(255, 255, 0);
    IPixel pixel3 = new PixelImpl(255, 0, 255);
    IPixel pixel4 = new PixelImpl(0, 0, 0);
    IPixel[][] pixels = new PixelImpl[2][2];
    pixels[0][0] = pixel1;
    pixels[0][1] = pixel2;
    pixels[1][0] = pixel3;
    pixels[1][1] = pixel4;

    String fileAsString = ImageUtil.createPPMFile(pixels, pixels.length, pixels[0].length);
    String expected = "P3\n" + "2 2\n" + "255\n" +
        "255 0 0 255 255 0\n" +
        "255 0 255 0 0 0";
    assertEquals(expected, fileAsString);
  }
}
