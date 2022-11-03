package model;

/**
 * Represents a Pixel with r g b values.
 */
public class PixelImpl implements IPixel {
  private final int r;
  private final int g;
  private final int b;

  /**
   * Constructs the RGB values for a single Pixel. values less than 0 are set to 0 and values
   * greater then 255 are set to 255;
   *
   * @param r red color value
   * @param g green color value
   * @param b blue color value
   */
  public PixelImpl(int r, int g, int b) {
    this.r = cap(r);
    this.g = cap(g);
    this.b = cap(b);
  }

  @Override
  public int getRed() {
    return this.r;
  }

  @Override
  public int getGreen() {
    return this.g;
  }

  @Override
  public int getBlue() {
    return this.b;
  }

  @Override
  public int getIntensity() {
    return cap((this.r + this.g + this.b) / 3);
  }

  @Override
  public int getValue() {
    return Math.max(this.r, Math.max(this.g, this.b));
  }

  @Override
  public int getLuma() {
    return cap(Math.toIntExact(Math.round((r * 0.2126) + (g * 0.7152) + (b * 0.0722))));
  }

  /**
   * ensures values are between 0 and 255.
   */
  private int cap(int value) {
    if (value < 0) {
      value = 0;
    } else if (value > 255) {
      value = 255;
    }
    return value;
  }
}
