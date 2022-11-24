import org.junit.Test;

import controller.commands.Load;
import controller.commands.Save;
import controller.ImageCommand;
import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

/**
 * Tests for {@link ImageCommand}.
 */
public class ImageCommandTest {

  @Test
  public void testCommandLoad() {
    ImageModel storage = new ImageModelImpl();

    try {
      storage.getImageModel("6Color");
      fail();
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    ImageCommand c;
    c = new Load("res/6Color.ppm", "6ColorPPM");
    c.execute(storage);

    try {
      storage.getImageModel("6ColorPPM");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    c = new Load("res/6Color.png", "6ColorPNG");
    c.execute(storage);

    try {
      storage.getImageModel("6ColorPNG");
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

  /**
   * Everytime this test runs, a new file is created that is overriding the previous file within the
   * res folder/directory, shows that files are overridden if the file location name is the same as
   * the one that already exists.
   */
  @Test
  public void testCommandSaveAndOverride() {
    ImageModel storage = new ImageModelImpl();
    ImageCommand c;
    storage.getImageStored().put("6Color", new ImageModelImpl("res/6Color.ppm"));
    c = new Save("res/test-6Color.ppm", "6Color");
    c.execute(storage);
    c = new Save("res/test-6Color.png", "6Color");
    c.execute(storage);

    assertArrayEquals(new ImageModelImpl("res/test-6Color.ppm").getImage(),
        new ImageModelImpl("res/6Color.ppm").getImage());
    assertArrayEquals(new ImageModelImpl("res/test-6Color.png").getImage(),
        new ImageModelImpl("res/6Color.png").getImage());
    assertArrayEquals(new ImageModelImpl("res/test-6Color.png").getImage(),
        new ImageModelImpl("res/6Color.ppm").getImage());
  }
}
