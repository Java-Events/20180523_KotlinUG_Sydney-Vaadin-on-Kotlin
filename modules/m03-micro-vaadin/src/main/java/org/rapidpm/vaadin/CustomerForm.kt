package org.rapidpm.vaadin

import com.github.vok.karibudsl.*
import com.vaadin.data.Binder
import com.vaadin.event.ShortcutAction.KeyCode.ENTER
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import org.rapidpm.dependencies.core.logger.HasLogger

class CustomerForm(private val myUI: MyUI) : FormLayout(), HasLogger {

  private val firstName = textField { caption = "First name" }
  private val lastName = textField { caption = "Last name" }
  private val email = textField { caption = "Email" }

  private val status = nativeSelect<CustomerStatus> {
    caption = "Status"
    setItems(*CustomerStatus.values())
  }

  private val birthdate = dateField { caption = "Birthday" }

  private val save = button {
    caption = "Save"
    styleName = ValoTheme.BUTTON_PRIMARY
    clickShortcut = KeyShortcut(ENTER)
    addClickListener { save() }
  }

  private val delete = button {
    caption = "Delete"
    addClickListener { delete() }
  }

  private val beanBinder = Binder(Customer::class.java)
  private val service = CustomerService.getInstance()
  private var customer: Customer? = null

  init {
    setSizeUndefined()

    addComponents(firstName,
        lastName,
        email,
        status,
        birthdate,
        HorizontalLayout(save, delete))

    beanBinder.bindInstanceFields(this)

  }

  fun setCustomer(customer: Customer?) {
    this.customer = customer
    beanBinder.bean = customer

    // Show delete button for only customers already in the database
    delete.isVisible = customer != null && customer.isPersisted
    isVisible = true
    firstName.selectAll()
  }

  private fun delete() {
    service.delete(customer)
    myUI.updateList()
    isVisible = false
  }

  private fun save() {
    service.save(customer)
    myUI.updateList()
    isVisible = false
  }
}
