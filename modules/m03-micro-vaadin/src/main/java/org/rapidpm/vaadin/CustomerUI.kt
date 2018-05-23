package org.rapidpm.vaadin

import java.time.LocalDate

enum class CustomerStatus {
  ImportedLead,
  NotContacted,
  Contacted,
  Customer,
  ClosedLost
}
//
//data class Customer(
//    var id: Long? = null,
//    var firstName: String = "",
//    var lastName: String = "",
//    var birthDate: LocalDate? = null,
//    var status: CustomerStatus? = null,
//    var email: String = "") {
//
//  val isPersisted: Boolean
//    get() = id != null
//
//  override fun toString(): String {
//    return "$firstName $lastName"
//  }
//}