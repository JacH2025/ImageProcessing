package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import controller.ImageCommand;

import static model.ImageUtil.createPPMFile;
import static model.ImageUtil.readPPM;
import static model.ImageUtil.imageData;
import static model.ImageUtil.getPPMHeight;
import static model.ImageUtil.getPPMWidth;

/**
 *
 */
public class ImageModelImpl implements ImageModel {
  private final IPixel[][] image;
  private final int width;
  private final int height;
  private final HashMap<String, ImageModel> loadedImages = new HashMap<>();

  /**
   * Constructor for an Empty model that contains no images or data.
   */
  public ImageModelImpl() {
    this.image = null;
    this.width = 0;
    this.height = 0;
  }

  /**
   * Constructor that takes in a file and takes the data from the file.
   *
   * @param filename file name or path
   */
  public ImageModelImpl(String filename) {
    this.image = imageData(readPPM(filename));
    this.height = getPPMHeight(readPPM(filename));
    this.width = getPPMWidth(readPPM(filename));
  }

  /**
   * Constructor that takes in an image's data and constructs a model
   * based on the given data
   *
   * @param image array of pixels
   */
  public ImageModelImpl(IPixel[][] image) {
    this.image = image;
    this.height = image.length;
    this.width = image[0].length;
  }

  @Override
  public IPixel[][] getImage() {
    return this.image;
  }

  @Override
  public ImageModel getImageModel(String name) {
    ImageModel image = loadedImages.getOrDefault(name, null);
    if (image == null) {
      throw new IllegalArgumentException(String.format("image names %s has not been loaded", name));
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
  public void loadImage(ImageModel image, String name) {
    this.loadedImages.put(name, image);
  }

  @Override
  public void save(String location, String name) throws IllegalArgumentException {
    if (!(location.substring(location.length() - 4)).equalsIgnoreCase(".ppm")) {
      throw new IllegalArgumentException("File Name does not end with .ppm");
    }
    ImageModel model = loadedImages.get(name);
    try {
      FileOutputStream fos = new FileOutputStream(location);
      fos.write(createPPMFile(model.getImage(), model.getHeight(), model.getWidth()).getBytes());
      fos.close();
    } catch (IOException e) {
      System.out.println("File could not be Saved");
    }
  }

  @Override
  public void execute(ImageCommand c) {
    c.execute(this);
  }
}
