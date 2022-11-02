package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import controller.ImageCommands;

import static model.ImageUtil.readPPM;
import static model.ImageUtil.imageData;
import static model.ImageUtil.getPPMHeight;
import static model.ImageUtil.getPPMWidth;

/**
 *
 */
public class ImageModelImpl implements ImageModel {
  private IPixel[][] image;
  private final int width;
  private final int height;
  private HashMap<String, File> load;

  /**
   * @param filename
   * @IOException
   */
  public ImageModelImpl(String filename) throws IOException {
    this.image = imageData(readPPM(filename));
    this.width = getPPMHeight(readPPM(filename));
    this.height = getPPMWidth(readPPM(filename));
    this.load = new HashMap<>();
  }


  @Override
  public IPixel[][] getImage() {
    return this.image;
  }

  @Override
  public IPixel getPixels(int x, int y) {
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
  public void loadImage() {

  }

  @Override
  public void save(String dest) {
    File file = new File(dest);

  }

  @Override
  public void execute(ImageCommands c) {
    c.execute(this);
  }
}
