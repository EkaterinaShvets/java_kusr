package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.List;

public class PersonDeletionTests extends TestBase {

  @Test
  public void testPersonDeletionTest() {
    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Теодор", "Джеймс", "Уотсон", "г. Вязьма, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    List<PersonData> before = app.getPersonHelper().getPersonList();
    app.getPersonHelper().selectPerson(before.size() - 1);
    app.getPersonHelper().deleteSelectedPerson();
    app.getNavigationHelper().gotoHomePage();
    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }
}
