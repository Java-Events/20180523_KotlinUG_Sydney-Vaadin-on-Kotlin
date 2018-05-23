package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@WebServlet(urlPatterns = "/*", name = "MyUIServlet")
@VaadinServletConfiguration(ui = TestUI.class, productionMode = false)
public class TestServlet extends VaadinServlet {
}
