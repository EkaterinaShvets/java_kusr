package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class PersonDeletionTests extends TestBase {

  @Test
  public void testPersonDeletionTest() {
    app.getPersonHelper().selectPerson();
    app.getPersonHelper().deleteSelectedPerson();
  }
}
