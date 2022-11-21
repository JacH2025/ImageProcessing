//import org.junit.Test;
//
//import controller.commands.Load;
//import controller.commands.Save;
//import controller.ImageCommand;
//import model.ImageModel;
//import model.ImageModelImpl;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
///**
// * Tests for {@link ImageCommand}.
// */
//public class ImageCommandTest {
//
//  @Test
//  public void testCommandLoad() {
//    ImageModel storage = new ImageModelImpl();
//
//    try {
//      storage.getImageModel("6Color");
//      fail();
//    } catch (IllegalArgumentException e) {
//      e.getMessage();
//    }
//
//    ImageCommand c = new Load("res/6Color.ppm", "6Color");
//    c.execute(storage);
//
//    try {
//      storage.getImageModel("6Color");
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
//  /**
//   * Everytime this test runs, a new file is created that is overriding the
//   * previous file within the res folder/directory, shows that files are overridden
//   * if the file location name is the same as the one that already exists.
//   */
//  @Test
//  public void testCommandSaveAndOverride() {
//    ImageModel storage = new ImageModelImpl();
//    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
//    ImageCommand c = new Save("res/test-6Color.ppm", "6Color");
//    c.execute(storage);
//
//    checkSamePixels(new ImageModelImpl("res/test-6Color.ppm"),
//        new ImageModelImpl("res/6Color.ppm"));
//    assertArrayEquals(new ImageModelImpl("res/test-6Color.ppm").getImage(),
//        new ImageModelImpl("res/6Color.ppm").getImage());
//  }
//
//  private void checkSamePixels(ImageModel model1, ImageModel model2) {
//    assertEquals(model1.getImage().length, model2.getImage().length);
//    assertEquals(model1.getImage()[0].length, model2.getImage()[0].length);
//    for (int i = 0; i < model1.getImage().length; i++) {
//      for (int j = 0; j < model1.getImage()[0].length; j++) {
//        assertEquals(model1.getImage()[i][j], model2.getImage()[i][j]);
//      }
//    }
//  }
//}
