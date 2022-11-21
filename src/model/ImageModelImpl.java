package model;

import java.util.HashMap;
import java.util.Objects;

import controller.ImageCommand;

import static model.ImageUtil.getImageHeight;
import static model.ImageUtil.getImageWidth;
import static model.ImageUtil.readImage;

/**
 * ImageModelImpl implements ImageModel. This model contains a 2D array of IPixels which is an image
 * representation. It also contains HashMap of images associated with String names. Images can be
 * loaded into this HashMap, and have Commands executed on them.
 */
public class ImageModelImpl implements ImageModel {
  private final IPixel[][] image;
  private final int width;
  private final int height;
  private final HashMap<String, ImageModel> loadedImages;

  /**
   * Constructor for an Empty model that contains no images or data.
   * Implemented to store loaded images into HashMap for the user to easily get.
   */
  public ImageModelImpl() {
    this.image = null;
    this.width = 0;
    this.height = 0;
    this.loadedImages = new HashMap<>();
  }

  /**
   * Constructor that takes in a file and takes the data from the file.
   *
   * @param filename file name or path
   */
  public ImageModelImpl(String filename) {
    this.image = readImage(filename);
    this.height = getImageHeight(filename);
    this.width = getImageWidth(filename);
    this.loadedImages = null;
  }

  /**
   * Constructor that takes in an image's data and constructs a model based on the given data.
   *
   * @param image array of pixels
   */
  public ImageModelImpl(IPixel[][] image) {
    try {
      this.image = Objects.requireNonNull(image);
    } catch (Exception e) {
      throw new IllegalArgumentException("image cannot be null");
    }
    this.height = image.length;
    this.width = image[0].length;
    this.loadedImages = null;
  }

  @Override
  public IPixel[][] getImage() {
    return this.image;
  }

  public HashMap<String, ImageModel> getImageStored() {
    return this.loadedImages;
  }

  @Override
  public ImageModel getImageModel(String name) {
    ImageModel image = loadedImages.getOrDefault(name, null);
    if (image == null) {
      throw new IllegalArgumentException(String.format("image named %s has not been loaded", name));
    }
    return image;
  }

  @Override
  public IPixel getPixel(int x, int y) {
    return image[x][y];
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public void execute(ImageCommand c) {
    c.execute(this);
  }
}
