package junit.org.rapidpm.kotlin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.kotlin.MainJava;

public class MainJavaTest {


  @Test
  void test001() {
    int add = new MainJava().add(1, 1);
    System.out.println("add = " + add);
    Assertions.assertEquals(0,add );
  }

  @Test
  void test002() {
    int add = new MainJava().add(2, 2);
    System.out.println("add = " + add);
    Assertions.assertEquals(4,add );
  }


}
