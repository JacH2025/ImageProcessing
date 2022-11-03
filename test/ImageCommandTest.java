import org.junit.Test;

import controller.Commands.Brighten;
import controller.ImageCommand;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ImageCommandTest {

  @Test
  public void testCommandBrighten() {
    ImageModel storage = new ImageModelImpl();
    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
    ImageCommand c = new Brighten(50, "6Color", "6Color-brighten");
    c.execute(storage);
    IPixel[][] pixels = storage.getImageModel("6Color-brighten").getImage();

    assertEquals(255, pixels[0][0].getRed());
    assertEquals(50, pixels[0][0].getGreen());
    assertEquals(50, pixels[0][0].getBlue());

    assertEquals(50, pixels[0][1].getRed());
    assertEquals(255, pixels[0][1].getGreen());
    assertEquals(50, pixels[0][1].getBlue());

    assertEquals(50, pixels[0][2].getRed());
    assertEquals(50, pixels[0][2].getGreen());
    assertEquals(255, pixels[0][2].getBlue());

    assertEquals(255, pixels[1][0].getRed());
    assertEquals(255, pixels[1][0].getGreen());
    assertEquals(50, pixels[1][0].getBlue());

    assertEquals(255, pixels[1][1].getRed());
    assertEquals(255, pixels[1][1].getGreen());
    assertEquals(255, pixels[1][1].getBlue());

    assertEquals(50, pixels[1][2].getRed());
    assertEquals(50, pixels[1][2].getGreen());
    assertEquals(50, pixels[1][2].getBlue());


    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
      }
    }
  }

  @Test
  public void testCommandHorizontalFlip() {

  }

  @Test
  public void testCommandVerticalFlip() {

  }

  @Test
  public void testCommandLumaGreyscale() {

  }

  @Test
  public void testCommandIntensityGreyscale() {

  }

  @Test
  public void testCommandValueGreyscale() {

  }

  @Test
  public void testCommandRGBGreyscale() {

  }

  @Test
  public void testCommandLoad() {
    //Check the Hashmap
    //Check Contents
  }

  @Test
  public void testCommandSave() {
    //Create File
    //Read File
    //Compare Contents
  }

  @Test
  public void testOverrideImage() {

  }
}
