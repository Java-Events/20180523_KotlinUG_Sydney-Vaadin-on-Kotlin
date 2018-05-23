package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.TextFieldElement;
import org.openqa.selenium.WebDriver;
import org.rapidpm.vaadin.ui.components.CustomerForm;

import static org.rapidpm.vaadin.ui.components.CustomerForm.*;

public class CustomerFormPageObject extends AbstractVaadinPageObject {

  public CustomerFormPageObject(WebDriver webDriver) {
    super(webDriver);
  }


  public TextFieldElement lastNameTF() {
    return textField().id(CustomerForm.TF_LAST_NAME_ID);
  }

  public String getLastName() {
    return lastNameTF().getValue();
  }

  public void setLastName(String lastName) {
    lastNameTF().setValue(lastName);
  }

  public String getFirstName() {
    return firstnameTF().getValue();
  }

  public void setFirstName(String firstName) {
    firstnameTF().setValue(firstName);
  }

  public TextFieldElement firstnameTF() {
    return textField().id(TF_FIRST_NAME_ID);
  }

  public void saveEntry() {
    saveButton().click();
  }

  public void deleteEntry() {
    deleteButton().click();
  }

  public ButtonElement deleteButton() {
    return btn().id(BTN_DELETE_ID);
  }

  public ButtonElement saveButton() {
    return btn().id(BTN_SAVE_ID);
  }

  public ComboBoxElement statusSelect() {
    return comboBox().id(CB_STATUS_ID);
  }


  public void clickSwitchButton() {
    btn().id(TestUI.TEST_SWITCH_BUTTON).click();
  }

  public void clickRegisterButton() {
    btn().id(TestUI.REGISTER_BUTTON).click();
  }

}
