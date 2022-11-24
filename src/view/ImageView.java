package view;

import controller.Features;
import model.ImageModel;

/**
 * Interface for an interactive ImageView for the Image Program. Represents the
 */
public interface ImageView {

  /**
   * Refreshes the ImageView in order to keep and update the view when changes
   * are made.
   */
  void refresh();

  /**
   * Allows access into the view by taking in a message given and rendering onto
   * the view.
   *
   * @param message String of what needs to be rendered
   */
  void renderMessage(String message);

  /**
   * Allows the view to link actions set to send responses to the controller.
   *
   * @param f Features
   */
  void addFeatures(Features f);

  /**
   * Used by the Controller, sets the current ImageModel that was loaded, changed, or saved
   * in order to display the ImageModel onto the view.
   *
   * @param model ImageModel
   */
  void setCurrentImage(ImageModel model);

  /**
   * Clears the input text on the text box for user inputs.
   */
  void clearInputString();
}
