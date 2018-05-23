package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import static io.undertow.Handlers.path;
import static io.undertow.Handlers.redirect;
import static io.undertow.servlet.Servlets.servlet;

public class MyTestApp {

  @WebServlet(urlPatterns = "/*", name = "MyUIServlet")
  @VaadinServletConfiguration(ui = TestUI.class, productionMode = false)
  public static class MyUIServlet extends VaadinServlet {
  }

  public static void main(String[] args) {
    try {
      new MyTestApp().startup();
    } catch (ServletException e) {
      e.printStackTrace();
    }
  }

  public void startup() throws ServletException {
    DeploymentInfo servletBuilder
        = Servlets.deployment()
                  .setClassLoader(MyTestApp.class.getClassLoader())
                  .setContextPath("/")
                  .setDeploymentName("ROOT.war")
                  .setDefaultEncoding("UTF-8")
                  .addServlets(
                      servlet(
                          MyUIServlet.class.getSimpleName(),
                          MyUIServlet.class
                      ).addMapping("/*")
                       .setAsyncSupported(false)
                  );

    final DeploymentManager manager = Servlets
        .defaultContainer()
        .addDeployment(servletBuilder);
    manager.deploy();
    PathHandler path = path(redirect("/"))
        .addPrefixPath("/", manager.start());
    build = Undertow.builder()
                             .addHttpListener(8899, "0.0.0.0")
                             .setHandler(path)
                             .build();
    build
            .start();
  }

  private Undertow build;


  public void stop(){
    build.stop();
  }


}
