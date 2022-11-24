import org.junit.Test;

import java.util.Objects;

import javax.swing.*;

import controller.Features;
import controller.ImageControllerFeat;
import view.ImageView;
import view.ImageViewFeat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link ImageViewFeat}.
 * Tests for {@link ImageControllerFeat}.
 */
public class ImageControllerAndViewTest {

  @Test
  public void testControllerReceivingInputFromView() {
    ImageView view = new ImageViewFeat();
    Appendable appendable = new StringBuilder();
    Features controller = new ImageControllerMock(appendable, view);
    JMenuItem load = Objects.requireNonNull((JMenuItem)
        TestUtils.getChildNamed((JFrame) view, "load"));
    JMenuItem blur = Objects.requireNonNull((JMenuItem)
        TestUtils.getChildNamed((JFrame) view, "blur"));
    JMenuItem horizontalFlip = Objects.requireNonNull((JMenuItem)
        TestUtils.getChildNamed((JFrame) view, "horizontalFlip"));
    JTextField addAs = Objects.requireNonNull((JTextField)
        TestUtils.getChildNamed((JFrame) view, "addAs"));
    JTextField imagePath = Objects.requireNonNull((JTextField)
        TestUtils.getChildNamed((JFrame) view, "imagePath"));
    JTextField imageName = Objects.requireNonNull((JTextField)
        TestUtils.getChildNamed((JFrame) view, "imageName"));
    //set tex box to input and process action
    addAs.setText("6Color");
    imagePath.setText("res/6Color.png");
    view.addFeatures(controller);
    load.doClick();
    //check input received by mock controller
    String loadInput = "res/6Color.png 6Color";
    assertEquals(loadInput, appendable.toString());

    imageName.setText("6Color");
    addAs.setText("6Color-blur");
    blur.doClick();
    String blurInput = "6Color 6Color-blur";
    assertEquals(blurInput, "6Color 6Color-blur");

    imageName.setText("6Color-blur");
    addAs.setText("6Color-blur-horizontal");
    horizontalFlip.doClick();
    String flipHorizontalInput = "6Color-blur 6Color-blur-horizontal";
    assertEquals(flipHorizontalInput, "6Color-blur 6Color-blur-horizontal");
  }

  @Test
  public void testUpdatedImageAfterLoad() {
    ImageView view = new ImageViewFeat();
    Features controller = new ImageControllerFeat(view);
    JLabel imageLabel = Objects.requireNonNull((JLabel)
        TestUtils.getChildNamed((JFrame) view, "imageLabel"));
    //check to see if an image icon is set
    assertNull(imageLabel.getIcon());
    controller.loadImage("res/6Color.png", "6Color");
    //check to see if an image icon is set based on the width and height of the loaded image
    assertEquals(2, imageLabel.getIcon().getIconHeight());
    assertEquals(3, imageLabel.getIcon().getIconWidth());
  }
}
