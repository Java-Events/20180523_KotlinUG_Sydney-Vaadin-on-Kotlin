package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.elements.FormLayoutElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

/**
 *
 */
//@VaadinUnitTest
public class CustomerForm02Test {

  @Test
  public void test001(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    final FormLayoutElement customerForm = pageObject.formLayout().id(TestUI.CUSTOMER_FORM);
    Assert.assertTrue(customerForm.isDisplayed());
    pageObject.deleteEntry();
    Assert.assertEquals(0, pageObject.getDriver().findElements(new By.ById(TestUI.CUSTOMER_FORM)).size());

    final String id = pageObject.textField().id(TestUI.ID).getValue();
    Assert.assertEquals("-1", id);
  }


  @Test
  public void test002(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    final FormLayoutElement customerForm = pageObject.formLayout().id(TestUI.CUSTOMER_FORM);
    Assert.assertTrue(customerForm.isDisplayed());
    pageObject.saveEntry();
    Assert.assertEquals(0, pageObject.getDriver().findElements(new By.ById(TestUI.CUSTOMER_FORM)).size());

    final String id = pageObject.textField().id(TestUI.ID).getValue();
    Assert.assertEquals("2", id);
  }


}