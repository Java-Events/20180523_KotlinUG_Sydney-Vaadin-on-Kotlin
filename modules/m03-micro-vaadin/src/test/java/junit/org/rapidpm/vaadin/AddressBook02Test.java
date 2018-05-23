package junit.org.rapidpm.vaadin;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class AddressBook02Test extends AddressBook {

  @Test
  public void test001() {
    getDriver().get(url);

    //filter
    filterTextField().setValue("Lara");

    assertEquals("Lara", getFirstNameAtIndex(0));
    assertEquals("Martin", getLastNameAtIndex(0));

    assertEquals(1L, dataGrid().getRowCount());

    clearFilterBTN().click();

    assertTrue(dataGrid().getRowCount() > 1);
  }

}
