package org.rapidpm.vaadin.karibu

import com.vaadin.annotations.VaadinServletConfiguration
import com.vaadin.server.VaadinServlet
import io.undertow.Handlers
import io.undertow.Undertow
import io.undertow.servlet.Servlets
import org.rapidpm.vaadin.MyVaadinApp
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet

class MyKaribuApp {

  private var build: Undertow? = null

  @WebServlet(urlPatterns = arrayOf("/*"), name = "MyUIServlet", asyncSupported = false)
  @VaadinServletConfiguration(ui = KaribuUI::class, productionMode = false)
  class MyUIServlet : VaadinServlet()

  @Throws(ServletException::class)
  fun startup() {
    val servletBuilder = Servlets.deployment()
        .setClassLoader(MyVaadinApp::class.java.classLoader)
        .setContextPath("/")
        .setDeploymentName("ROOT.war")
        .setDefaultEncoding("UTF-8")
        .addServlets(
            Servlets.servlet(
                MyUIServlet::class.simpleName,
                MyUIServlet::class.java
            ).addMapping("/*")
                .setAsyncSupported(true)
        )

    val manager = Servlets
        .defaultContainer()
        .addDeployment(servletBuilder)
    manager.deploy()
    val path = Handlers.path(Handlers.redirect("/"))
        .addPrefixPath("/", manager.start())
    build = Undertow.builder()
        .addHttpListener(8899, "0.0.0.0")
        .setHandler(path)
        .build()
    build!!
        .start()
  }


  fun stop() {
    build!!.stop()
  }

  companion object {

    @JvmStatic
    fun main(args: Array<String>) {
      try {
        MyVaadinApp().startup()
      } catch (e: ServletException) {
        e.printStackTrace()
      }

    }
  }


}
