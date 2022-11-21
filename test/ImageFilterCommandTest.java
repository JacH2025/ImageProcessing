//import org.junit.Test;
//
//import controller.ImageCommand;
//import controller.commands.filters.Blur;
//import controller.commands.filters.Sharpen;
//import model.IPixel;
//import model.ImageModel;
//import model.ImageModelImpl;
//import model.PixelImpl;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Tests for {@see src.controller.commands.fitlers}.
// * Tests for {@link ImageCommand}
// */
//public class ImageFilterCommandTest {
//
//  @Test
//  public void testCommandBlur() {
//    ImageModel storage = new ImageModelImpl();
//    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
//    ImageCommand c = new Blur("6Color", "6Color-blur");
//    c.execute(storage);
//    IPixel[][] pixels = storage.getImageModel("6Color-blur").getImage();
//
//    assertEquals(new PixelImpl(111, 79, 15), pixels[0][0]);
//    assertEquals(new PixelImpl(79, 111, 63), pixels[0][1]);
//    assertEquals(new PixelImpl(15, 47, 79), pixels[0][2]);
//    assertEquals(new PixelImpl(127, 111, 31), pixels[1][0]);
//    assertEquals(new PixelImpl(111, 127, 79), pixels[1][1]);
//    assertEquals(new PixelImpl(31, 47, 63), pixels[1][2]);
//  }
//
//  @Test
//  public void testCommandBlurTwoTimes() {
//    ImageModel storage = new ImageModelImpl();
//    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
//    ImageCommand c1 = new Blur("6Color", "6Color-blur1");
//    c1.execute(storage);
//    ImageCommand c2 = new Blur("6Color-blur1", "6Color-blur2");
//    c2.execute(storage);
//    IPixel[][] pixels = storage.getImageModel("6Color-blur2").getImage();
//
//    assertEquals(new PixelImpl(60, 55, 20), pixels[0][0]);
//    assertEquals(new PixelImpl(59, 69, 43), pixels[0][1]);
//    assertEquals(new PixelImpl(24, 39, 40), pixels[0][2]);
//    assertEquals(new PixelImpl(64, 60, 23), pixels[1][0]);
//    assertEquals(new PixelImpl(65, 73, 45), pixels[1][1]);
//    assertEquals(new PixelImpl(28, 40, 39), pixels[1][2]);
//  }
//
//  @Test
//  public void testCommandSharpen() {
//    ImageModel storage = new ImageModelImpl();
//    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
//    ImageCommand c = new Sharpen("6Color", "6Color-sharpen");
//    c.execute(storage);
//    IPixel[][] pixels = storage.getImageModel("6Color-sharpen").getImage();
//
//    assertEquals(new PixelImpl(255, 191, 31), pixels[0][0]);
//    assertEquals(new PixelImpl(191, 255, 127), pixels[0][1]);
//    assertEquals(new PixelImpl(0, 95, 255), pixels[0][2]);
//    assertEquals(new PixelImpl(255, 255, 31), pixels[1][0]);
//    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
//    assertEquals(new PixelImpl(0, 95, 127), pixels[1][2]);
//  }
//
//  @Test
//  public void testCommandSharpenTwoTimes() {
//    ImageModel storage = new ImageModelImpl();
//    storage.loadImage(new ImageModelImpl("res/6Color.ppm"), "6Color");
//    ImageCommand c1 = new Sharpen("6Color", "6Color-sharpen1");
//    c1.execute(storage);
//    ImageCommand c2 = new Sharpen("6Color-sharpen1", "6Color-sharpen2");
//    c2.execute(storage);
//    IPixel[][] pixels = storage.getImageModel("6Color-sharpen2").getImage();
//
//    assertEquals(new PixelImpl(255, 255, 86), pixels[0][0]);
//    assertEquals(new PixelImpl(255, 255, 255), pixels[0][1]);
//    assertEquals(new PixelImpl(47, 190, 255), pixels[0][2]);
//    assertEquals(new PixelImpl(255, 255, 86), pixels[1][0]);
//    assertEquals(new PixelImpl(255, 255, 255), pixels[1][1]);
//    assertEquals(new PixelImpl(47, 190, 255), pixels[1][2]);
//  }
//}
