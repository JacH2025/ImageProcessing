package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.basic.Brighten;
import controller.commands.basic.HorizontalFlip;
import controller.commands.colorTransformations.Greyscale;
import controller.commands.colorTransformations.Sepiatone;
import controller.commands.filters.ImageBlur;
import controller.commands.basic.IntensityGreyscale;
import controller.commands.Load;
import controller.commands.basic.LumaGreyscale;
import controller.commands.basic.RGBGreyscale;
import controller.commands.Save;
import controller.commands.basic.ValueGreyscale;
import controller.commands.basic.VerticalFlip;
import controller.commands.filters.imageSharpening;
import model.ImageModel;
import model.ImageModelImpl;

/**
 * Controller for our Image processor. Uses a command pattern.
 */
public class ImageControllerImpl implements ImageController {
  private final ImageModel model;
  private final Readable input;
  private final Appendable output;
  private final Map<String, Function<Scanner, ImageCommand>> commands;

  /**
   * Basic constructor for a Image controller. starts with a new image model.
   *
   * @param input  Readable input stream to give commands to controller
   * @param output Appendable output stream to give user feedback
   * @throws IllegalArgumentException given invalid input or output streams
   */
  public ImageControllerImpl(Readable input, Appendable output) throws IllegalArgumentException {
    this.model = new ImageModelImpl();
    try {
      this.input = Objects.requireNonNull(input);
      this.output = Objects.requireNonNull(output);
    } catch (Exception e) {
      throw new IllegalArgumentException("input or output cannot be null");
    }
    this.commands = new HashMap<>();
    initializeCommands();

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
        , (Scanner s) -> new ImageBlur(s.next(), s.next()));
    commands.put("sharpen"
        , (Scanner s) -> new imageSharpening(s.next(), s.next()));
    commands.put("greyscale"
        , (Scanner s) -> new Greyscale(s.next(), s.next()));
    commands.put("sepia"
        , (Scanner s) -> new Sepiatone(s.next(), s.next()));
  }

  @Override
  public void run() throws IllegalStateException {
    Scanner sc = new Scanner(input);
    boolean quit = false;

    welcomeMessage();
    while (!quit && sc.hasNext()) {
      String instruction = sc.next();
      if (instruction.equals("quit") || instruction.equals("q")) {
        quit = true;
        writeMessage("Bye Bye");
      } else if (instruction.equalsIgnoreCase("help") ||
          instruction.equalsIgnoreCase("h")) {
        printHelp();

      } else {
        try {
          processCommand(instruction, sc);
        } catch (Exception e) {
          writeMessage(e.getMessage());
        }
      }
    }
  }


  /**
   * Attempts to run a command.
   *
   * @param instruction name of command to be ran
   * @param sc          scanner
   */
  private void processCommand(String instruction, Scanner sc) {
    ImageCommand c;
    Function<Scanner, ImageCommand> cmd = commands.getOrDefault(instruction, null);
    if (cmd == null) {
      writeMessage("unrecognized input" + System.lineSeparator());
    } else {
      try {
        c = cmd.apply(sc);
        model.execute(c);
        writeMessage(instruction + " executed" + System.lineSeparator());
      } catch (Exception e) {
        System.out.println(e.getMessage());
        writeMessage(instruction + " failed");
      }
    }
  }

  private void writeMessage(String message) throws IllegalStateException {
    try {
      output.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void printHelp() {
    StringBuilder helpMenu = new StringBuilder();
    helpMenu.append("This image processor supports the following operations")
        .append(System.lineSeparator());
    helpMenu.append("Commands: Arguments")
        .append(System.lineSeparator());
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
    writeMessage(helpMenu.toString());
  }

  private void welcomeMessage() {
    StringBuilder welcomeMessage = new StringBuilder();
    welcomeMessage.append("Welcome to our Image processor :)").append(System.lineSeparator());
    welcomeMessage.append("Enter help or h for a list of commands.").append(System.lineSeparator());
    welcomeMessage.append("Enter quit or q to exit.").append(System.lineSeparator());
    writeMessage(welcomeMessage.toString());
  }
}
