package junit.org.rapidpm.vaadin;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class AddressBook04Test extends AddressBook {

  @Test
  public void test001() {
    getDriver().get(url);

    final CustomerFormPageObject customerFormPageObject = selectEntryAtIndex(1);

    final CustomerFormPageObject newEntry = createNewEntry();

    Assert.assertFalse(dataGrid().isSelected());

  }


}
