package junit.org.rapidpm.vaadin.ui.components;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.rapidpm.frp.functions.CheckedExecutor;

/**
 *
 */
//@VaadinUnitTest
public class CustomerForm01Test {

  public static final String LAST_NAME_ONE = "AAAA";
  public static final String LAST_NAME_TWO = "BBBB";

  private CustomerFormPageObject pageObject ;
  private MyTestApp              app        = new MyTestApp();
  @BeforeEach
  public void setUp() throws Exception {

    app.startup();

    System.setProperty("webdriver.chrome.driver", "_data/webdrivers/chromedriver-mac-64bit");
    System.setProperty("webdriver.gecko.driver", "_data/webdrivers/geckodriver-mac-64bit");
    pageObject = new CustomerFormPageObject(new ChromeDriver());
//    setDriver(new ChromeDriver());
    pageObject.getDriver().manage().window().setSize(new Dimension(1920, 1080));

    //data init -> depending on the Singleton

  }

  @AfterEach
  public void tearDown()
      throws Exception {
    ((CheckedExecutor) () -> pageObject.getDriver().quit()).execute();
    app.stop();
  }


  @Test
  public void test001() {
    pageObject.loadPage();

    pageObject.setLastName(LAST_NAME_ONE);
    pageObject.saveEntry();
    Assert.assertEquals(LAST_NAME_ONE, pageObject.textField().id(TestUI.LAST_NAME).getValue());
    pageObject.clickSwitchButton();
    Assert.assertEquals(LAST_NAME_ONE, pageObject.getLastName());
    pageObject.setLastName(LAST_NAME_TWO);
    pageObject.clickRegisterButton(); //Registrations off
    pageObject.saveEntry();
    Assert.assertEquals(LAST_NAME_ONE, pageObject.textField().id(TestUI.LAST_NAME).getValue());
    pageObject.clickSwitchButton();
    Assert.assertEquals(LAST_NAME_TWO, pageObject.getLastName());
//    setLastName(LAST_NAME_TWO); is from last time -> statefull component
    pageObject.clickRegisterButton();
    pageObject.saveEntry();
    Assert.assertEquals(LAST_NAME_TWO, pageObject.textField().id(TestUI.LAST_NAME).getValue());


  }

  @Test
  public void test002(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    pageObject.saveEntry();

    Assert.assertEquals("2", pageObject.textField().id(TestUI.ID).getValue());
    pageObject.clickSwitchButton();
    pageObject.deleteEntry();
    Assert.assertEquals("-1", pageObject.textField().id(TestUI.ID).getValue());
    pageObject.clickSwitchButton();
    pageObject.saveEntry();
    Assert.assertEquals("0", pageObject.textField().id(TestUI.ID).getValue());
    pageObject.clickSwitchButton();
    pageObject.clickRegisterButton(); // registrations off
    pageObject.deleteEntry();
    Assert.assertEquals("0", pageObject.textField().id(TestUI.ID).getValue());
    pageObject.clickSwitchButton();
    pageObject.clickRegisterButton(); // registrations on
    pageObject.deleteEntry();
    Assert.assertEquals("-1", pageObject.textField().id(TestUI.ID).getValue());

  }
}