package view;

import controller.Features;

/**
 *
 */
public interface ImageView {

  /**
   *
   * @param message
   */
  void renderMessage(String message);

  void addFeatures(Features f);

  void clearInputString();

  void makeVisible();

  /**
   *
   */
  void refresh();
}
