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
  public void loadImage(String imagePath, String imageName) {
    try {
      this.appendable.append(imagePath + " " + imageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void saveImageAs(String imagePath, String imageName) {
    try {
      this.appendable.append(imagePath + " " + imageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void flipHorizontal(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void flipVertical(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void brighten(int i, String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void intensityGrey(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void lumaGrey(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void valueGrey(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void redGrey(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void greenGrey(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void blueGrey(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void transGrey(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void blur(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void sharpen(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void sepia(String imageName, String destImageName) {
    try {
      this.appendable.append(imageName + " " + destImageName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void exitProgram() {
    throw new IllegalStateException();
  }
}
