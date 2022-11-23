import org.junit.Test;

import java.util.Objects;

import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ImageControllerView;
import controller.Features;
import view.ImageView;
import view.ImageViewFrame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link ImageViewFrame}.
 * Tests for {@link ImageControllerView}.
 */
public class ImageControllerAndViewTest {

  @Test
  public void testControllerReceivingInputFromView() {
    ImageView view = new ImageViewFrame();
    Appendable appendable = new StringBuilder();
    Features controller = new ImageControllerMock(appendable, view);
    JTextField inputTest = Objects.requireNonNull((JTextField)
        TestUtils.getChildNamed((JFrame) view, "input"));
    //set tex box to input
    inputTest.setText("load res/6Color.png 6Color");
    String input = "load res/6Color.png 6Color";
    //check input in text box
    assertEquals(input, inputTest.getText());
    view.addFeatures(controller);
    //enter action for text box
    inputTest.postActionEvent();
    //check input received by mock controller
    assertEquals(input, appendable.toString());
  }

  @Test
  public void testUpdatedImageAfterLoad() {
    ImageView view = new ImageViewFrame();
    Features controller = new ImageControllerView(view);
    JLabel imageLabel = Objects.requireNonNull((JLabel)
        TestUtils.getChildNamed((JFrame) view, "image"));
    //check to see if an image icon is set
    assertNull(imageLabel.getIcon());
    controller.getInput("load res/6Color.png 6Color");
    //check to see if an image icon is set based on the width and height of the loaded image
    assertEquals(2, imageLabel.getIcon().getIconHeight());
    assertEquals(3, imageLabel.getIcon().getIconWidth());
  }
}
