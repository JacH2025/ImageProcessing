package controller;

/**
 * Features interface allows for the communication between two classes by passing
 * each other information in order to do an action.
 */
public interface Features {

  /**
   * Takes in an input and gives it to the controller.
   *
   * @param input String command
   */
  void getInput(String input);
}
