package org.rapidpm.kotlin

class MainKotlin {
  fun add(a: Int, b: Int): Int {
    return if (a < 2) {
      a + b * -1
    } else {
      a + b
    }
  }
}
