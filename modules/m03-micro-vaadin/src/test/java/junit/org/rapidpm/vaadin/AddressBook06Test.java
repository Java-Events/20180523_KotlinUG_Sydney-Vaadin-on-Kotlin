package junit.org.rapidpm.vaadin;

import com.vaadin.testbench.elements.GridElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.CustomerService;

/**
 *
 */
public class AddressBook06Test extends AddressBook {

  @Test
  public void test001() throws Exception {
    final int firstCount = CustomerService.getInstance().findAll().size();
    getDriver().get(url);

    final CustomerFormPageObject newEntry = createNewEntry();
    newEntry.setLastName("X");
    newEntry.setFirstName("Y");
    newEntry.saveEntry();
    final GridElement gridElement = dataGrid();
    final long        rowCount    = gridElement.getRowCount();
    Assert.assertEquals(rowCount, firstCount + 1);

  }
}
