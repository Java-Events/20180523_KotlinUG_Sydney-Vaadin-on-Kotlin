package org.rapidpm.vaadin.karibu

import com.github.vok.karibudsl.*

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

class KaribuUI : UI() {
  @Override
  override fun init(vaadinRequest: VaadinRequest?) {
    lateinit var layout: VerticalLayout
    layout = verticalLayout {
      val name = textField {
        caption = "Type your name here:"
      }
      button("Click Me") {
        onLeftClick {
          println("Thanks ${name.value}, it works!")
          layout.label("Thanks ${name.value}, it works!")
        }
      }
    }
  }
}
