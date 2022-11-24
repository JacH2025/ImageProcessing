package controller;


import  java.util.Objects;

import controller.commands.Load;
import controller.commands.Save;
import controller.commands.basic.Brighten;
import controller.commands.basic.HorizontalFlip;
import controller.commands.basic.IntensityGreyscale;
import controller.commands.basic.LumaGreyscale;
import controller.commands.basic.RGBGreyscale;
import controller.commands.basic.ValueGreyscale;
import controller.commands.basic.VerticalFlip;
import controller.commands.filters.Blur;
import controller.commands.filters.Sharpen;
import controller.commands.transformations.Greyscale;
import controller.commands.transformations.SepiaTone;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageView;

/**
 *
 */
public class ImageControllerFeat implements Features {
  private ImageView view;
  private ImageModel model;

  /**
   *
   *
   * @param v
   */
  public ImageControllerFeat(ImageView v) {
    this.view = Objects.requireNonNull(v);
    this.model = new ImageModelImpl();
    view.addFeatures(this);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    try {
      new Load(imagePath, imageName).execute(model);
      view.setCurrentImage(model.getImageModel(imageName));
    } catch (Exception e) {
      view.renderMessage("failed to load image");
    }

    view.clearInputString();

  }

  @Override
  public void saveImageAs(String imagePath, String imageName) {
    new Save(imagePath, imageName).execute(model);
  }

  @Override
  public void flipHorizontal(String imageName, String destImageName) {
    try {
      new HorizontalFlip(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute horizontal flip");
    }
    view.clearInputString();
  }

  @Override
  public void flipVertical(String imageName, String destImageName) {
    try {
      new VerticalFlip(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute vertical flip");
    }
    view.clearInputString();

  }

  @Override
  public void brighten(int i, String imageName, String destImageName) {
    try {
      new Brighten(i, imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to Brighten");
    }
    view.clearInputString();


  }

  @Override
  public void intensityGrey(String imageName, String destImageName) {
    try {
      new IntensityGreyscale(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute intensity greyscale");
    }
    view.clearInputString();

  }

  @Override
  public void lumaGrey(String imageName, String destImageName) {
    try {
      new LumaGreyscale(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute luma greyscale");
    }
    view.clearInputString();

  }

  @Override
  public void valueGrey(String imageName, String destImageName) {
    try {
      new ValueGreyscale(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute value greyscale");
    }
    view.clearInputString();

  }

  @Override
  public void redGrey(String imageName, String destImageName) {
    try {
      new RGBGreyscale("red", imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute red component greyscale");
    }
    view.clearInputString();

  }

  @Override
  public void greenGrey(String imageName, String destImageName) {
    try {
      new RGBGreyscale("green", imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute green component greyscale");
    }
    view.clearInputString();

  }


  @Override
  public void blueGrey(String imageName, String destImageName) {
    try {
      new RGBGreyscale("blue", imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute blue component greyscale");
    }
    view.clearInputString();

  }

  @Override
  public void transGrey(String imageName, String destImageName) {
    try {
      new Greyscale(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute greyscale");
    }
    view.clearInputString();

  }

  @Override
  public void blur(String imageName, String destImageName) {
    try {
      new Blur(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute blur filter");
    }
    view.clearInputString();

  }

  @Override
  public void sharpen(String imageName, String destImageName) {
    try {
      new Sharpen(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute greyscale");
    }
    view.clearInputString();

  }

  @Override
  public void sepia(String imageName, String destImageName) {
    try {
      new SepiaTone(imageName, destImageName).execute(model);
      view.setCurrentImage(model.getImageModel(destImageName));
    } catch (Exception e) {
      view.renderMessage("failed to execute sepia filter");
    }
    view.clearInputString();

  }


}
