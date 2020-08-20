package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class PersonModificationTests extends TestBase {

  @Test
  public void testPersonModification() {
    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Теодор", "Джеймс", "Уотсон", "г. Вязьма, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    List<PersonData> before = app.getPersonHelper().getPersonList();
    app.getPersonHelper().initPersonModification(before.size() - 1);
    PersonData person = new PersonData(before.get(before.size() - 1).getId(), "Питер", "Джеймс", "Уотсон", "г. Пенза, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", null);
    app.getPersonHelper().fillPersonForm(person, false);
    app.getPersonHelper().submitPersonModification();
    app.getNavigationHelper().gotoHomePage();
    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(person);
    Comparator<? super PersonData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
