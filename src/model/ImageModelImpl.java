package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static model.ImageUtil.readPPM;
import static model.ImageUtil.imageData;
import static model.ImageUtil.getHeight;
import static model.ImageUtil.getWidth;

/**
 *
 */
public class ImageModelImpl implements ImageModel {
  private int[][][] image;
  private final int width;
  private final int height;
  private HashMap<String, File> load;

  /**
   * @param filename
   * @IOException
   */
  public ImageModelImpl(String filename) throws IOException {
    this.image = imageData(readPPM(filename));
    this.width = getWidth(readPPM(filename));
    this.height = getHeight(readPPM(filename));
    this.load = new HashMap<>();
  }

  @Override
  public void toGreyscale(Components c) {
    //Intensity -> image[i][j][0] + image[i][j][1] + image[i][j][2] / 3;
    //Value -> Math.max(image[i][j][0], image[i][j][1], image[i][j][2]);
    //Luma -> Math.round((image[i][j][0] * 0.2126) + (image[i][j][1] * 0.7152) + (image[i][j][2] * 0.0722))
    //Red -> image[i][j][0]
    //Green -> image[i][j][1]
    //Blue -> image[i][j][2]
    int[][][] newImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newImage[i][j][0] = value(i, j);
        newImage[i][j][1]
        newImage[i][j][2]
        for (int k = 0; k < 3; k++) {
          newImage[i][j][k] = image[i][j][k];
        }
      }
    }
    this.image = newImage;
  }

//  private int value(int x, int y) {
//    return Math.max(image[x][y][0], image[x][y][1], image[x][y][2]);
//  }
//
//  private int intensity(int i, int j) {
//    return image[i][j][0] + image[i][j][1] + image[i][j][2] / 3;
//  }
//
//  private int luma(int i, int j) {
//    return cap(Math.toIntExact(Math.round((image[i][j][0] * 0.2126)
//        + (image[i][j][1] * 0.7152)
//        + (image[i][j][2] * 0.0722))));
//  }
//
//  private int getRed(int i, int j) {
//    return image[i][j][0];
//  }
//
//  private int getGreen(int i, int j) {
//    return image[i][j][1];
//  }
//
//  private int getBlue(int i, int j) {
//    return image[i][j][2];
//  }





  @Override
  public void verticalFlip() {
    int[][][] newImage = new int[height][width][3];
    int x = 1;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImage[i][j][k] = image[height - x][j][k];
        }
      }
      x++;
    }
    this.image = newImage;
  }

  @Override
  public void horizontalFlip() {
    int[][][] newImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      int x = 1;
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImage[i][j][k] = image[i][width - x][k];
        }
        x++;
      }
    }
    this.image = newImage;
  }


  @Override
  public void brighten(int increment) {
    int[][][] newImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImage[i][j][k] = cap(image[i][j][k] + increment);
        }
      }
    }
    this.image = newImage;
  }

  @Override
  public void loadImage() {

  }

  @Override
  public void save() {

  }

  /**
   * @param value
   * @return
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
