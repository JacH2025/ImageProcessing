import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImageController;
import controller.ImageControllerImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests for {@link ImageControllerImpl}.
 */
public class ImageControllerTest {
  @Test
  public void testControllerExceptions() {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    assertThrows(IllegalArgumentException.class,
        () -> new ImageControllerImpl(null, output));
    assertThrows(IllegalArgumentException.class,
        () -> new ImageControllerImpl(input, null));
    assertThrows(IllegalArgumentException.class,
        () -> new ImageControllerImpl(null, null));
  }

  @Test
  public void testControllerQuit() {
    Appendable instruct = new StringBuilder("quit");
    Readable input = new StringReader(instruct.toString());
    Appendable output = new StringBuilder();

    ImageController controller = new ImageControllerImpl(input, output);
    String expected = "Welcome to our Image processor :)\n" +
        "Enter help or h for a list of commands.\n" +
        "Enter quit or q to exit.\n" +
        "Bye Bye";
    controller.run();

    assertEquals(expected, output.toString());
  }

  @Test
  public void testControllerHelp() {
    Appendable instruct = new StringBuilder("help");
    Readable input = new StringReader(instruct.toString());
    Appendable output = new StringBuilder();

    ImageController controller = new ImageControllerImpl(input, output);
    String expected = "Welcome to our Image processor :)\n" +
        "Enter help or h for a list of commands.\n" +
        "Enter quit or q to exit.\n" +
        "This image processor supports the following operations\n" +
        "Commands: Arguments\n" +
        "load: imagePath, imageName\n" +
        "save: imagePath, imageName\n" +
        "horizontal-flip: imageName, destinationImageName\n" +
        "vertical-flip: imageName, destinationImageName\n" +
        "brighten: int increment, imageName, destinationImageName\n" +
        "intensity-greyscale: imageName, destinationImageName\n" +
        "luma-greyscale: imageName, destinationImageName\n" +
        "value-greyscale: imageName, destinationImageName\n" +
        "red-component: imageName, destinationImageName\n" +
        "blue-component: imageName, destinationImageName\n" +
        "green-component: imageName, destinationImageName\n" +
        "blur: imageName, destinationImageName\n" +
        "sharpen: imageName, destinationImageName\n" +
        "greyscale: imageName, destinationImageName\n" +
        "sepia: imageName, destinationImageName\n";
    controller.run();

    assertEquals(expected, output.toString());
  }

  @Test
  public void testCheckStatementAfterCommand() {
    Appendable instruct = new StringBuilder("load res/6color.ppm 6Color");
    Readable input = new StringReader(instruct.toString());
    Appendable output = new StringBuilder();
    ImageController controller = new ImageControllerImpl(input, output);
    String expected = "Welcome to our Image processor :)\n" +
        "Enter help or h for a list of commands.\n" +
        "Enter quit or q to exit.\n" +
        "load executed\n";

    controller.run();
    assertEquals(expected, output.toString());
  }

  @Test
  public void testInvalidInputException() {
    Appendable instruct = new StringBuilder("load res/6color.ppm 6Color\n" +
        "blur ImageDoesNotExist 6ColorBlur\n");
    Readable input = new StringReader(instruct.toString());
    Appendable output = new StringBuilder();
    ImageController controller = new ImageControllerImpl(input, output);
    String expected = "Welcome to our Image processor :)\n" +
        "Enter help or h for a list of commands.\n" +
        "Enter quit or q to exit.\n" +
        "load executed\n" +
        "blur failed";
    controller.run();
    assertEquals(expected, output.toString());
  }
}
