package model;

/**
 * Represents the operations that can be performed on a Pixel
 */
public interface IPixel {

  /**
   * Returns the red color value of the Pixel. (Min = 0, Max = 255).
   *
   * @return int
   */
  int getRed();

  /**
   * Returns the green color value of the Pixel. (Min = 0, Max = 255).
   *
   * @return
   */
  int getGreen();

  /**
   * Returns the green color value of the Pixel. (Min = 0, Max = 255).
   *
   * @return int
   */
  int getBlue();

  /**
   * Returns the intensity of the Pixel, the average of the three components:
   * Red, Green, and Blue.
   *
   * @return int
   */
  int getIntensity();

  /**
   * Returns the value of the Pixel, the maximum color value between the three
   * components: Red, Green, and Blue.
   *
   * @return int
   */
  int getValue();

  /**
   * Returns the luma of the Pixel, the weighted sum through between the three
   * components: Red, Green, and Blue.
   *
   * @return int
   */
  int getLuma();

}
