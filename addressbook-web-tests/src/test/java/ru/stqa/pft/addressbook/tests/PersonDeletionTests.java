package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

public class PersonDeletionTests extends TestBase {

  @Test
  public void testPersonDeletionTest() {
    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Теодор", "Джеймс", "Уотсон", "г. Вязьма, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    int before = app.getPersonHelper().getPersonCount();
    app.getPersonHelper().selectPerson(before-1);
    app.getPersonHelper().deleteSelectedPerson();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getPersonHelper().getPersonCount();
    Assert.assertEquals(after,before-1);
  }
}
