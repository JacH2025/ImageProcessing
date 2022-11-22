package view;

import controller.Features;
import model.ImageModel;

/**
 *
 */
public interface ImageView {

  /**
   *
   * @param message
   */
  void renderMessage(String message);

  /**
   *
   */
  void makeVisible();

  /**
   *
   */
  void refresh();

  /**
   *
   * @param f
   */
  void addFeatures(Features f);

  /**
   *
   * @param model
   */
  void setCurrentImage(ImageModel model);

  /**
   *
   */
  void clearInputString();
}
