package org.rapidpm.vaadin

import com.vaadin.annotations.VaadinServletConfiguration
import com.vaadin.server.VaadinServlet
import io.undertow.Undertow
import io.undertow.server.handlers.PathHandler
import io.undertow.servlet.Servlets
import io.undertow.servlet.api.DeploymentInfo
import io.undertow.servlet.api.DeploymentManager

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet

import io.undertow.Handlers.path
import io.undertow.Handlers.redirect
import io.undertow.servlet.Servlets.servlet
import org.rapidpm.vaadin.karibu.KaribuUI

class MyVaadinApp {

  private var build: Undertow? = null

  @WebServlet(urlPatterns = arrayOf("/*"), name = "MyUIServlet", asyncSupported = false)
//  @VaadinServletConfiguration(ui = MyUI::class, productionMode = false)
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
            servlet(
                MyUIServlet::class.java.simpleName,
                MyUIServlet::class.java
            ).addMapping("/*")
                .setAsyncSupported(true)
        )

    val manager = Servlets
        .defaultContainer()
        .addDeployment(servletBuilder)
    manager.deploy()
    val path = path(redirect("/"))
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
