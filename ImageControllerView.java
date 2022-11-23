package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

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
 * Controller for an interactive view that communicates between the ImageModel and the
 * ImageView by taking in user input from the view and commanding changes within the ImageModel.
 */
public class ImageControllerView implements Features, ImageController {
  private final ImageModel model;
  private final ImageView view;
  private final Map<String, Function<Scanner, ImageCommand>> commands;

  /**
   * Constructs the controller that takes in the view (GUI) and passes the controller to
   * the view.
   *
   * @param view ImageView
   */
  public ImageControllerView(ImageView view) {
    this.model = new ImageModelImpl();
    this.view = Objects.requireNonNull(view);
    this.commands = new HashMap<>();
    this.initializeCommands();
    view.addFeatures(this);
  }

  //adds currently implemented commands to our command map.
  private void initializeCommands() {
    commands.put("load", (Scanner s) -> new Load(s.next(), s.next()));
    commands.put("save", (Scanner s) -> new Save(s.next(), s.next()));
    commands.put("horizontal-flip"
        , (Scanner s) -> new HorizontalFlip(s.next(), s.next()));
    commands.put("vertical-flip"
        , (Scanner s) -> new VerticalFlip(s.next(), s.next()));
    commands.put("brighten"
        , (Scanner s) -> new Brighten(s.nextInt(), s.next(), s.next()));
    commands.put("intensity-greyscale"
        , (Scanner s) -> new IntensityGreyscale(s.next(), s.next()));
    commands.put("luma-greyscale"
        , (Scanner s) -> new LumaGreyscale(s.next(), s.next()));
    commands.put("value-greyscale"
        , (Scanner s) -> new ValueGreyscale(s.next(), s.next()));
    commands.put("red-component"
        , (Scanner s) -> new RGBGreyscale("red", s.next(), s.next()));
    commands.put("blue-component"
        , (Scanner s) -> new RGBGreyscale("blue", s.next(), s.next()));
    commands.put("green-component"
        , (Scanner s) -> new RGBGreyscale("green", s.next(), s.next()));
    commands.put("blur"
        , (Scanner s) -> new Blur(s.next(), s.next()));
    commands.put("sharpen"
        , (Scanner s) -> new Sharpen(s.next(), s.next()));
    commands.put("greyscale"
        , (Scanner s) -> new Greyscale(s.next(), s.next()));
    commands.put("sepia"
        , (Scanner s) -> new SepiaTone(s.next(), s.next()));
  }

  @Override
  public void run() {
    welcomeMessage();
    view.makeVisible();
  }

  private void process(String input) throws IllegalStateException {
    Scanner sc = new Scanner(input);
    boolean quit = false;

    while (!quit && sc.hasNext()) {
      String instruction = sc.next();
      if (instruction.equals("quit") || instruction.equals("q")) {
        quit = true;
        view.renderMessage("Bye Bye");
      } else if (instruction.equalsIgnoreCase("help") ||
          instruction.equalsIgnoreCase("h")) {
        printHelp();
      } else {
        try {
          processCommand(instruction, sc);
        } catch (Exception e) {
          view.renderMessage(e.getMessage());
        }
      }
    }
  }

  /**
   * Attempts to run a command.
   *
   * @param instruction name of command to run
   * @param sc scanner
   */
  private void processCommand(String instruction, Scanner sc) {
    ImageCommand c;
    Function<Scanner, ImageCommand> cmd = commands.getOrDefault(instruction, null);
    if (cmd == null) {
      view.renderMessage("unrecognized input" + System.lineSeparator());
    } else {
      try {
        c = cmd.apply(sc);
        model.execute(c);
        view.renderMessage(instruction + " executed" + System.lineSeparator());
      } catch (Exception e) {
        System.out.println(e.getMessage());
        view.renderMessage(instruction + " failed");
      }
    }
  }

  private void printHelp() {
    StringBuilder helpMenu = new StringBuilder();
    helpMenu.append("load: imagePath, imageName")
        .append(System.lineSeparator());
    helpMenu.append("save: imagePath, imageName")
        .append(System.lineSeparator());
    helpMenu.append("horizontal-flip: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("vertical-flip: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("brighten: int increment, imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("intensity-greyscale: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("luma-greyscale: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("value-greyscale: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("red-component: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("blue-component: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("green-component: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("blur: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("sharpen: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("greyscale: imageName, destinationImageName")
        .append(System.lineSeparator());
    helpMenu.append("sepia: imageName, destinationImageName")
        .append(System.lineSeparator());
    view.renderMessage(helpMenu.toString());
  }

  private void welcomeMessage() {
    StringBuilder welcomeMessage = new StringBuilder();
    welcomeMessage.append("Welcome to our Image processor :)").append(System.lineSeparator());
    welcomeMessage.append("Enter help or h for a list of commands.").append(System.lineSeparator());
    welcomeMessage.append("Enter quit or q to exit.").append(System.lineSeparator());
    view.renderMessage(welcomeMessage.toString());
  }

//  @Override
//  public void getInput(String input) {
//    this.process(input);
//    view.setCurrentImage(model.getImageModel(input.substring(input.lastIndexOf(" ") + 1)));
//    view.clearInputString();
//    view.refresh();
//  }

  @Override
  public void loadImage(String imagePath, String imageName) {

  }

  @Override
  public void saveImageAs(String imagePath, String imageName) {

  }

  @Override
  public void flipHorizontal(String imageName, String destImageName) {

  }

  @Override
  public void flipVertical(String imageName, String destImageName) {

  }

  @Override
  public void brighten(int i, String imageName, String destImageName) {

  }

  @Override
  public void intensityGrey(String imageName, String destImageName) {

  }

  @Override
  public void lumaGrey(String imageName, String destImageName) {

  }

  @Override
  public void valueGrey(String imageName, String destImageName) {

  }

  @Override
  public void redGrey(String imageName, String destImageName) {

  }

  @Override
  public void greenGrey(String imageName, String destImageName) {

  }

  @Override
  public void blueGrey(String imageName, String destImageName) {

  }

  @Override
  public void transGrey(String imageName, String destImageName) {

  }

  @Override
  public void blur(String imageName, String destImageName) {

  }

  @Override
  public void sharpen(String imageName, String destImageName) {

  }

  @Override
  public void sepia(String imageName, String destImageName) {

  }

  @Override
  public void exitProgram() {

  }
}
