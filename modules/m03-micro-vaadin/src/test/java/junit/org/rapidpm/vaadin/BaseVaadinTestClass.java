package junit.org.rapidpm.vaadin;

import com.vaadin.testbench.TestBenchTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.rapidpm.frp.functions.CheckedExecutor;
import org.rapidpm.vaadin.CustomerService;
import org.rapidpm.vaadin.MyVaadinApp;

/**
 *
 */
public abstract class BaseVaadinTestClass extends TestBenchTestCase {

  protected String      url = "http://127.0.0.1:8899/";
  private   MyVaadinApp app = new MyVaadinApp();

  @BeforeEach
  public void setUp()
      throws Exception {

    app.startup();


    System.setProperty("webdriver.chrome.driver", "_data/webdrivers/chromedriver-mac-64bit");
    System.setProperty("webdriver.gecko.driver", "_data/webdrivers/geckodriver-mac-64bit");
    setDriver(new ChromeDriver());
    getDriver().manage().window().setSize(new Dimension(1920, 1080));

    //data init -> depending on the Singleton
    CustomerService.getInstance().resetData();
  }

  @AfterEach
  public void tearDown()
      throws Exception {
    ((CheckedExecutor) () -> getDriver().quit()).execute();

    app.stop();
  }

}
