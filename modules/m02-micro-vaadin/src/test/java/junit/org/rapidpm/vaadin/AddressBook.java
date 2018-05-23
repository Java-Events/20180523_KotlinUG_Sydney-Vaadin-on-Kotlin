package junit.org.rapidpm.vaadin;

import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.CssLayoutElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.TextFieldElement;


public class AddressBook extends BaseVaadinTestClass {


  public String getLastNameAtIndex(int index) {
    return $(GridElement.class).first()
                               .getCell(index, 1)
                               .getText();
  }

  public String getFirstNameAtIndex(int index) {
    return $(GridElement.class).first()
                               .getCell(index, 0)
                               .getText();
  }

  public CustomerFormPageObject selectEntryAtIndex(int index) {
    $(GridElement.class).first()
                        .getCell(index, 0).click();
    final CustomerFormPageObject result = new CustomerFormPageObject();
    result.setDriver(getDriver());
    return result;
  }

  public CustomerFormPageObject createNewEntry() {
    newCustomerButton().click();
    final CustomerFormPageObject result = new CustomerFormPageObject();
    result.setDriver(getDriver());
    return result;
  }

  private ButtonElement newCustomerButton() {
    return $(ButtonElement.class).caption("Add new customer").first();
  }

  public CustomerFormPageObject activeCustomerForm() {
    final CustomerFormPageObject result = new CustomerFormPageObject();
    result.setDriver(getDriver());
    return result;
  }


  public TextFieldElement filterTextField() {
    return $(CssLayoutElement.class).$(TextFieldElement.class).first();
  }

  public GridElement dataGrid() {
    return $(GridElement.class).first();
  }

  public ButtonElement clearFilterBTN() {
    return $(CssLayoutElement.class).$(ButtonElement.class).first();
  }

}
