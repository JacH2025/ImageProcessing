package model;

import controller.ImageCommands;

/**
 *
 */
public interface ImageModel {

  /**
   *
   * @return
   */
  IPixel[][] getImage();

  /**
   *
   * @return
   */
  IPixel getPixels(int x, int y);

  /**
   *
   */
  int getHeight();

  /**
   *
   */
  int getWidth();

  /**
   *
   */
  void loadImage(String filename, String name);

  /**
   *
   */
  void save(String location, String name);

  /**
   *
   * @param c
   */
  void execute(ImageCommands c);
}
