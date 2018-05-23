package junit.org.rapidpm.kotlin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.rapidpm.kotlin.MainKotlin

class MainKotlinTest {

  @Test
  internal fun test001() {
    val add = MainKotlin().add(1, 1)
    Assertions.assertEquals(0, add)
  }

  @Test
  internal fun test002() {
    val add = MainKotlin().add(2, 2)
    Assertions.assertEquals(4, add)
  }
}
