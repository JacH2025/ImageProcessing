import org.junit.Test;

import model.IPixel;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link IPixel}.
 */
public class PixelTest {
  IPixel white = new PixelImpl(255, 255, 255);
  IPixel red = new PixelImpl(255, 0, 0);
  IPixel green = new PixelImpl(0, 255, 0);
  IPixel blue = new PixelImpl(0, 0, 255);
  IPixel black = new PixelImpl(0, 0, 0);
  IPixel color1 = new PixelImpl(234, 10, 30);
  IPixel color2 = new PixelImpl(12, 212, 95);
  IPixel color3 = new PixelImpl(123, 43, 84);

  @Test
  public void testConstructorAdjustments() {
    IPixel color = new PixelImpl(300, -10, 100);
    assertEquals(255, color.getRed());
    assertEquals(0, color.getGreen());
    assertEquals(100, color.getBlue());
  }

  @Test
  public void testGetRed() {
    assertEquals(255, red.getRed());
    assertEquals(234, color1.getRed());
    assertEquals(12, color2.getRed());
    assertEquals(123, color3.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(255, green.getGreen());
    assertEquals(10, color1.getGreen());
    assertEquals(212, color2.getGreen());
    assertEquals(43, color3.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(255, blue.getBlue());
    assertEquals(30, color1.getBlue());
    assertEquals(95, color2.getBlue());
    assertEquals(84, color3.getBlue());
  }

  @Test
  public void testGetIntensity() {
    assertEquals(255, white.getIntensity());
    assertEquals(85, red.getIntensity());
    assertEquals(85, green.getIntensity());
    assertEquals(85, blue.getIntensity());
    assertEquals(0, black.getIntensity());
    assertEquals(91, color1.getIntensity());
    assertEquals(106, color2.getIntensity());
    assertEquals(83, color3.getIntensity());
  }

  @Test
  public void testGetValue() {
    assertEquals(255, white.getValue());
    assertEquals(255, red.getValue());
    assertEquals(255, green.getValue());
    assertEquals(255, blue.getValue());
    assertEquals(0, black.getValue());
    assertEquals(234, color1.getValue());
    assertEquals(212, color2.getValue());
    assertEquals(123, color3.getValue());
  }

  @Test
  public void testGetLuma() {
    assertEquals(255, white.getLuma());
    assertEquals(54, red.getLuma());
    assertEquals(182, green.getLuma());
    assertEquals(18, blue.getLuma());
    assertEquals(0, black.getLuma());
    assertEquals(59, color1.getLuma());
    assertEquals(161, color2.getLuma());
    assertEquals(63, color3.getLuma());
  }
}
