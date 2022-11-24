import java.awt.Component;
import java.awt.Container;

import javax.swing.JMenu;

/**
 * Test Utilities that gets components from a GUI object in order to test and validate actions that
 * are acted upon within the GUI object.
 */
public class TestUtils {

  /**
   * Returns the component given the parent component and the name of the component.
   *
   * @param parent component
   * @param name   name of component
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
}
