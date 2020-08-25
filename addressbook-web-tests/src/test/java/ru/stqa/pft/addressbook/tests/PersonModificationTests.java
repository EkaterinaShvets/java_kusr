package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class PersonModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.person().list().size() == 0) {
      app.person().create(new PersonData("Теодор", "Джеймс", "Уотсон", "г. Вязьма, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", "test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testPersonModification() {
    List<PersonData> before = app.person().list();
    int index = before.size() - 1;
    PersonData person = new PersonData(before.get(index).getId(), "Питер", "Джеймс", "Уотсон", "г. Пенза, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", null);
    app.person().modify(index, person);
    app.goTo().homePage();
    List<PersonData> after = app.person().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(person);
    Comparator<? super PersonData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
