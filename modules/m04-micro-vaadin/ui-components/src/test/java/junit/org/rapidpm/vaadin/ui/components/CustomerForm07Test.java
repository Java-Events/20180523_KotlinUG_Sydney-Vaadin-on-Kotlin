package junit.org.rapidpm.vaadin.ui.components;

import org.junit.jupiter.api.Test;

/**
 *
 */
//@VaadinUnitTest
public class CustomerForm07Test {

  @Test
  public void test001(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    pageObject.clickRegisterButton();


  }
}