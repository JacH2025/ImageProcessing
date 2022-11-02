package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import controller.Commands.Brighten;
import controller.Commands.HorizontalFlip;
import controller.Commands.IntensityGreyscale;
import controller.Commands.Load;
import controller.Commands.LumaGreyscale;
import controller.Commands.RGBGreyscale;
import controller.Commands.Save;
import controller.Commands.ValueGreyScale;
import controller.Commands.VerticalFlip;
import model.ImageModel;
import model.ImageModelImpl;

/**
 *
 */
public class ImageControllerImpl implements ImageController {
  private final ImageModel model;
  private final Readable input;
  private final Appendable output;

  private Map<String, Function<Scanner, ImageCommands>> commands = new HashMap<>();;

  public ImageControllerImpl(Readable input, Appendable output) {
    this.model = new ImageModelImpl();
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
    commands.put("load", (Scanner s) -> {
      return new Load(s.next(), s.next());});
    commands.put("save", (Scanner s) -> {
      return new Save(s.next(), s.next());});
    commands.put("horizontal-flip", (Scanner s) -> {
      return new HorizontalFlip(s.next(), s.next());});
    commands.put("vertical-flip", (Scanner s) -> {
      return new VerticalFlip(s.next(), s.next());});
    commands.put("brighten", (Scanner s) -> {
      return new Brighten(s.nextInt(), s.next(), s.next());});
    commands.put("intensity-greyscale", (Scanner s) -> {
      return new IntensityGreyscale(s.next(), s.next());});
    commands.put("luma-greyscale", (Scanner s) -> {
      return new LumaGreyscale(s.next(), s.next());});
    commands.put("value-greyscale", (Scanner s) -> {
      return new ValueGreyScale(s.next(), s.next());});
    commands.put("rgb-greyscale", (Scanner s) -> {
      return new RGBGreyscale(s.next(), s.next(), s.next());});
  }

  @Override
  public void run() throws IOException {
    Scanner sc = new Scanner(input);
    boolean quit = false;

    while (!quit && sc.hasNext()) {
      String instruction = sc.next();
      if (instruction.equals("quit") || instruction.equals("q")) {
        quit = true;
      }
      else {
        processCommand(instruction, sc);
      }
    }
  }

  /**
   *
   * @param instruction
   * @param sc
   */
  private void processCommand(String instruction, Scanner sc) {
    ImageCommands c;
    Function<Scanner, ImageCommands> cmd = commands.getOrDefault(instruction, null);
    if (cmd == null) {
      throw new IllegalArgumentException();
    } else {
      c = cmd.apply(sc);
      model.execute(c);
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

  }
}
