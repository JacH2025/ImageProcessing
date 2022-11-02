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
  void loadImage();

  /**
   *
   */
  void save(String dest);

  /**
   *
   * @param c
   */
  void execute(ImageCommands c);
}
