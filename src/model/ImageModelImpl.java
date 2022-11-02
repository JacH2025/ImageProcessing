package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
  private HashMap<String, ImageModel> load = new HashMap<>();

  /**
   *
   * @param filename
   */
  public ImageModelImpl(String filename) {
    this.image = imageData(readPPM(filename));
    this.width = getPPMHeight(readPPM(filename));
    this.height = getPPMWidth(readPPM(filename));
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
  public void loadImage(String filename, String name) {
    ImageModel model = new ImageModelImpl(filename);
    load.put(name, model);
  }

  @Override
  public void save(String location, String name) {
    ImageModel model = load.get(name);
    Path path = Paths.get(location);
    String file = createPPM(model);
    try {
      Files.writeString(path, file, StandardCharsets.UTF_8);
    } catch (IOException e) {

    }
  }

  private String createPPM(ImageModel m) {
    StringBuilder build = new StringBuilder();
    build.append("P3\n");
    build.append(width + " " + height + "\n");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        build.append(image[i][j].getRed() + " ");
        build.append(image[i][j].getGreen() + " ");
        build.append(image[i][j].getBlue() + " ");
      }
      build.append("\n");
    }
    return build.toString();
  }

  @Override
  public void execute(ImageCommands c) {
    c.execute(this);
  }
}
