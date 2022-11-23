import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.ImageUtil;
import model.PixelImpl;

import static model.ImageUtil.getImageHeight;
import static model.ImageUtil.getImageWidth;
import static model.ImageUtil.readImage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
  public void testImageNotFoundException() {
    assertThrows(IllegalArgumentException.class,
        () -> readImage("ImageDoesNotExist"));
    assertThrows(IllegalArgumentException.class,
        () -> getImageWidth("ImageDoesNotExist"));
    assertThrows(IllegalArgumentException.class,
        () -> getImageHeight("ImageDoesNotExist"));
  }

  @Test
  public void testReadImage() {
    IPixel[][] pixels = readImage("res/6Color.png");
    assertEquals(new PixelImpl(255, 0, 0), pixels[0][0]);
    assertEquals(new PixelImpl(0, 255, 0), pixels[0][1]);
    assertEquals(new PixelImpl(0, 0, 255), pixels[0][2]);
    assertEquals(new PixelImpl(255, 255, 0), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testReadImageIfPPMFile() {
    IPixel[][] pixels = readImage("res/6Color.ppm");
    assertEquals(new PixelImpl(255, 0, 0), pixels[0][0]);
    assertEquals(new PixelImpl(0, 255, 0), pixels[0][1]);
    assertEquals(new PixelImpl(0, 0, 255), pixels[0][2]);
    assertEquals(new PixelImpl(255, 255, 0), pixels[1][0]);
    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
    assertEquals(new PixelImpl(0, 0, 0), pixels[1][2]);
  }

  @Test
  public void testGetImageHeight() {
    int height = getImageHeight("res/6Color.png");
    assertEquals(2, height);
  }

  @Test
  public void testGetImageHeightGivenPPMFile() {
    int height = getImageHeight("res/6Color.ppm");
    assertEquals(2, height);
  }

  @Test
  public void testGetImageWidth() {
    int width = getImageWidth("res/6Color.png");
    assertEquals(3, width);
  }

  @Test
  public void testGetImageWidthGivenPPMFile() {
    int width = getImageWidth("res/6Color.ppm");
    assertEquals(3, width);
  }

  @Test
  public void testGetBufferedImage() {
    ImageModel model = new ImageModelImpl("res/6Color.png");
    BufferedImage image = ImageUtil.getBufferedImage(model);
    BufferedImage imageFromRes;
    try {
      imageFromRes = ImageIO.read(new FileInputStream("res/6Color.png"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        assertEquals(imageFromRes.getRGB(j, i), image.getRGB(j, i));
      }
    }
  }
}
