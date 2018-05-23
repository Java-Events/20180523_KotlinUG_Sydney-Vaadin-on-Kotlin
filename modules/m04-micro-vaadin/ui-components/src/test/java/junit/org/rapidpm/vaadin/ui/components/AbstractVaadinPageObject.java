package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.*;
import org.openqa.selenium.WebDriver;

/**
 *
 */
public abstract class AbstractVaadinPageObject
    extends TestBenchTestCase
    implements VaadinPageObject {


  public AbstractVaadinPageObject(WebDriver webDriver) {
    setDriver(webDriver);
  }

  public void switchToDebugMode() {
    getDriver().get(url().get() + "?debug&restartApplication");
  }

  public void restartApplication() {
    getDriver().get(urlRestartApp().get());
  }

  public void loadPage() {
    final String url = url().get();
    getDriver().get(url);
  }

  public String getTitle() {
    return getDriver().getTitle();
  }
  
  public WithID<TextFieldElement> textField() {
    return id -> $(TextFieldElement.class).id(id);
  }

  public WithID<PasswordFieldElement> passwordField() {
    return id -> $(PasswordFieldElement.class).id(id);
  }

  public WithID<ButtonElement> btn() {
    return id -> $(ButtonElement.class).id(id);
  }

  public WithID<LabelElement> label() {
    return id -> $(LabelElement.class).id(id);
  }

  public WithID<GridElement> grid() {
    return id -> $(GridElement.class).id(id);
  }

  public WithID<ComboBoxElement> comboBox() {
    return id -> $(ComboBoxElement.class).id(id);
  }

  public WithID<DateFieldElement> dateField() {
    return id -> $(DateFieldElement.class).id(id);
  }

  public WithID<FormLayoutElement> formLayout() {
    return id -> $(FormLayoutElement.class).id(id);
  }

  public WithID<CssLayoutElement> cssLayout() {
    return id -> $(CssLayoutElement.class).id(id);
  }

  public WithID<HorizontalLayoutElement> horizontalLayout() {
    return id -> $(HorizontalLayoutElement.class).id(id);
  }

  public WithID<VerticalLayoutElement> verticalLayout() {
    return id -> $(VerticalLayoutElement.class).id(id);
  }


}
