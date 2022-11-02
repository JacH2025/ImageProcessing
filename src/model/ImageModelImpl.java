package model;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import controller.ImageCommands;

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
  private final HashMap<String, ImageModel> load = new HashMap<>();

  public ImageModelImpl() {
    this.image = null;
    this.width = 0;
    this.height = 0;
  }

  /**
   *
   * @param filename
   */
  public ImageModelImpl(String filename) {
    this.image = imageData(readPPM(filename));
    this.height = getPPMHeight(readPPM(filename));
    this.width = getPPMWidth(readPPM(filename));
  }

  /**
   *
   * @param image
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
  public ImageModel getModel(String name) {
    return load.get(name);
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
  public void loadImage(ImageModel model, String name) {
    load.put(name, model);
  }

  @Override
  public void save(String location, String name) {
    if (!(location.substring(location.length() - 4)).equalsIgnoreCase(".ppm")) {
      throw new IllegalArgumentException("File Name does not end with .ppm");
    }
    ImageModel model = load.get(name);
    try {
      FileOutputStream fos = new FileOutputStream(location);
      fos.write(createPPMFile(model.getImage(), model.getHeight(), model.getWidth()).getBytes());
      fos.close();
    } catch (IOException e) {
      System.out.println("File could not be Saved");
    }
  }

  @Override
  public void execute(ImageCommands c) {
    c.execute(this);
  }
}
