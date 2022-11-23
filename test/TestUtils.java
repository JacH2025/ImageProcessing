import java.awt.Component;
import java.awt.Container;
import java.awt.Window;

import javax.swing.JMenu;

/**
 * Test Utilities that gets components from a GUI object in order to test and validate
 * actions that are acted upon within the GUI object.
 */
public class TestUtils {
  static int counter;

  /**
   * Returns the component given the parent component and the name of the component.
   *
   * @param parent component
   * @param name name of component
   * @return Component
   */
  public static Component getChildNamed(Component parent, String name) {
    if (name.equals(parent.getName())) {
      return parent;
    }
    if (parent instanceof Container) {
      Component[] children = (parent instanceof JMenu) ?
          ((JMenu) parent).getMenuComponents() : ((Container) parent).getComponents();

      for (Component component : children) {
        Component child = getChildNamed(component, name);
        if (child != null) {
          return child;
        }
      }
    }
    return null;
  }

  /**
   * Returns the component given the parent component, name of the component, and index
   * whether more than one exists.
   *
   * @param parent component
   * @param name name of component
   * @param index int corresponding to component
   * @return Component
   */
  public static Component getChildIndexed(Component parent, String name, int index) {
    counter = 0;
    if (parent instanceof Window) {
      Component[] children = ((Window) parent).getOwnedWindows();
      for (Component component : children) {
        if (component instanceof Window && !((((Window) component).isActive()))) {
          continue;
        }
        Component child = getChildIndexed(component, name, index);
        if (child != null) {
          return child;
        }
      }
    }
    return null;
  }

  /**
   * Returns the internal component given the parent component, name of the component, and index
   * whether more than one exists.
   *
   * @param parent component
   * @param name name of component
   * @param index int corresponding to component
   * @return Component
   */
  public static Component getChildIndexedInternal(Component parent, String name, int index) {
    if (parent.getClass().toString().endsWith(name)) {
      if (counter == index) {
        return parent;
      }
      counter++;
    }
    if (parent instanceof Container) {
      Component[] children = (parent instanceof JMenu) ?
          ((JMenu) parent).getMenuComponents() : ((Container) parent).getComponents();

      for (Component component : children) {
        Component child = getChildIndexedInternal(component, name, index);
        if (child != null) {
          return child;
        }
      }
    }
    return null;
  }
}
