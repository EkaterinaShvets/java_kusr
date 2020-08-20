package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class PersonCreationTests extends TestBase {

  @Test
  public void testPersonCreation() {
    List<PersonData> before = app.getPersonHelper().getPersonList();
    PersonData person = new PersonData("Теодор", "Джеймс", "Уотсон", "г. Вязьма, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", "test1");
    app.getPersonHelper().createPerson(person);
    app.getNavigationHelper().gotoHomePage();
    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(person);
    Comparator<? super PersonData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
