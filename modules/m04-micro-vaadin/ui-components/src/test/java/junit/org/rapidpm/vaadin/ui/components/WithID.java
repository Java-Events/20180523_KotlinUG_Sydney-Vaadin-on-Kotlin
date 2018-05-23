package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.elementsbase.AbstractElement;

/**
 *
 */
@FunctionalInterface
public interface WithID<T extends AbstractElement> {
  T id(String id);
}
