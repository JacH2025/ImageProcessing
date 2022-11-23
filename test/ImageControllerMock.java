import java.io.IOException;
import java.util.Objects;

import controller.Features;
import controller.ImageController;
import view.ImageView;

/**
 * Mock Controller to test if the controller receives inputs that are taken from the View.
 */
public class ImageControllerMock implements Features, ImageController {
  private final Appendable appendable;
  private final ImageView view;

  /**
   * Constructs a Mock Controller in order to receive and check input.
   *
   * @param appendable the destination for input
   */
  public ImageControllerMock(Appendable appendable, ImageView view) {
    this.appendable = Objects.requireNonNull(appendable);
    this.view = Objects.requireNonNull(view);
  }

  @Override
  public void run() throws IllegalStateException {
    throw new IllegalStateException();
  }

  @Override
  public void getInput(String input) {
    try {
      this.appendable.append(input);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
