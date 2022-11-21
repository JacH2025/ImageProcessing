import controller.ImageControllerView;
import view.ImageView;
import view.ImageViewFrame;

public class ImageProgramInteractive {
  public static void main(String[] args) {
    ImageView frame = new ImageViewFrame();
    ImageControllerView controller = new ImageControllerView(frame);
    controller.run();
  }
}
