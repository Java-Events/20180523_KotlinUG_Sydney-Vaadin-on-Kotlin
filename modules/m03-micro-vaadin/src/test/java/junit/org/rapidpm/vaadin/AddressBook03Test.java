package junit.org.rapidpm.vaadin;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class AddressBook03Test extends AddressBook {

  @Test
  public void test001() {
    getDriver().get(url);

    //filter
    filterTextField().setValue("Lara");
    Assert.assertEquals(1L, dataGrid().getRowCount());

    selectEntryAtIndex(0).deleteEntry();
    Assert.assertEquals(0L, dataGrid().getRowCount());
  }

}
