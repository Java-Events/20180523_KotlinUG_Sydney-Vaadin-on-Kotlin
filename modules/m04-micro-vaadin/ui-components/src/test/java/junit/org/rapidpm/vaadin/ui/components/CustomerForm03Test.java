package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.FormLayoutElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.srv.PropertyService;
import org.rapidpm.vaadin.srv.PropertyServiceInMemory;
import org.rapidpm.vaadin.ui.components.CustomerForm;

/**
 *
 */
//@VaadinUnitTest
public class CustomerForm03Test {


  private final PropertyService propertyService = new PropertyServiceInMemory();

  public String resolve(String key) {
    return propertyService.resolve(key);
  }

  @Test
  public void test001(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    Assert.assertTrue(pageObject.deleteButton().isDisplayed());
    pageObject.deleteEntry();
    pageObject.clickSwitchButton();
    Assert.assertTrue(pageObject
                          .$(FormLayoutElement.class)
                          .$(ButtonElement.class)
                          .caption(resolve(CustomerForm.BTN_DELETE_CAPTION))
                          .all()
                          .isEmpty());
    pageObject.saveEntry();
    pageObject.clickSwitchButton();
    Assert.assertTrue(pageObject.btn().id(CustomerForm.BTN_DELETE_ID) != null);
  }
}