package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;

import java.util.Objects;
import java.util.stream.Stream;

import model.IPixel;
import model.ImageModel;

/**
 * Represents an Image's Histogram that creates line graphs that showcases the amount of red, green,
 * blur, and intense components an image has by matching their value from 0 to 255. Creates a visual
 * graph that can then be added to a main frame.
 */
public class ImageHistogram extends JPanel {
  private final ImageModel model;
  private int[] redValues;
  private int[] greenValues;
  private int[] blueValues;
  private int[] intensityValues;
  private final int startX = 0;
  private final int startY = 0;
  private final int endX = 510;
  private final int endY = 300;

  /**
   * Constructs the ImageHistogram by taking in an image's data and creating the visual panel of the
   * graph.
   *
   * @param model ImageModel
   */
  public ImageHistogram(ImageModel model) {
    super();
    this.setPreferredSize(new Dimension(endX, endY));
    if (Objects.isNull(model)) {
      this.model = null;
    } else {
      this.model = model;
      this.redValues = this.getRedValues();
      this.greenValues = this.getGreenValues();
      this.blueValues = this.getBlueValues();
      this.intensityValues = this.getIntensityValues();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphic = (Graphics2D) g;
    if (Objects.isNull(model)) {
      graphic.drawString("No Image to Create Histogram", endX / 2, endY / 2);
    } else {
      graphic.setColor(Color.BLACK);
      graphic.drawLine(startX, startY, startX, endY);
      graphic.drawLine(startX, endY, endX, endY);

      graphic.setColor(Color.RED);
      drawLineGraph(graphic, redValues);
      graphic.setColor(Color.GREEN);
      drawLineGraph(graphic, greenValues);
      graphic.setColor(Color.BLUE);
      drawLineGraph(graphic, blueValues);
      graphic.setColor(Color.GRAY);
      drawLineGraph(graphic, intensityValues);
    }
  }

  private void drawLineGraph(Graphics2D graphic, int[] component) {
    int prevX = startX;
    int prevY = endY;
    for (int c : component) {
      graphic.drawLine(prevX, prevY, prevX += 2, prevY = endY - c);
    }
  }

  private IPixel[] convertPixels(ImageModel model) {
    IPixel[] pixels;
    pixels = Stream.of(model.getImage()).flatMap(Stream::of).toArray(IPixel[]::new);
    return pixels;
  }

  private int[] getRedValues() {
    int[] redValues = new int[256];
    IPixel[] pixels = convertPixels(model);
    for (int i = 0; i < redValues.length; i++) {
      int count = 0;
      for (IPixel pixel : pixels) {
        if (pixel.getRed() == i) {
          count++;
        }
      }
      redValues[i] = count;
    }
    return redValues;
  }

  private int[] getGreenValues() {
    int[] greenValues = new int[256];
    IPixel[] pixels = convertPixels(model);
    for (int i = 0; i < greenValues.length; i++) {
      int count = 0;
      for (IPixel pixel : pixels) {
        if (pixel.getGreen() == i) {
          count++;
        }
      }
      greenValues[i] = count;
    }
    return greenValues;
  }

  private int[] getBlueValues() {
    int[] blueValues = new int[256];
    IPixel[] pixels = convertPixels(model);
    for (int i = 0; i < blueValues.length; i++) {
      int count = 0;
      for (IPixel pixel : pixels) {
        if (pixel.getBlue() == i) {
          count++;
        }
      }
      blueValues[i] = count;
    }
    return blueValues;
  }

  private int[] getIntensityValues() {
    int[] intensityValues = new int[256];
    IPixel[] pixels = convertPixels(model);
    for (int i = 0; i < intensityValues.length; i++) {
      int count = 0;
      for (IPixel pixel : pixels) {
        if (pixel.getIntensity() == i) {
          count++;
        }
      }
      intensityValues[i] = count;
    }
    return intensityValues;
  }
}
