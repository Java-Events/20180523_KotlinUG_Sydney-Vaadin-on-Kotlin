package junit.org.rapidpm.vaadin;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.CustomerService;

/**
 *
 */
public class AddressBook05Test extends AddressBook {


  @Test
  public void test001() throws Exception {
    getDriver().get(url);
    Assert.assertEquals(dataGrid().getRowCount(), CustomerService.getInstance().findAll().size());

  }


}
