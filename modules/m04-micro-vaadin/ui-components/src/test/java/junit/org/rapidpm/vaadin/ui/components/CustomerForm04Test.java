package junit.org.rapidpm.vaadin.ui.components;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.shared.CustomerStatus;

/**
 *
 */
//@VaadinUnitTest
public class CustomerForm04Test {

  @Test
  public void test001(CustomerFormPageObject pageObject) {
    pageObject.loadPage();

    Assert.assertNotNull(pageObject.statusSelect());
//    Assert.assertEquals(CustomerStatus.values().length + 1, pageObject.statusSelect()..size());

    pageObject.statusSelect().selectByText(CustomerStatus.Contacted.name());
    pageObject.saveEntry();
    pageObject.clickSwitchButton();
    Assert.assertEquals(pageObject.statusSelect().getValue(), CustomerStatus.Contacted.name());

  }
}