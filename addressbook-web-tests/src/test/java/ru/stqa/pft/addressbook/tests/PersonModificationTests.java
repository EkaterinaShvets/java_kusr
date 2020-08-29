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
      app.person().create(new PersonData().withFirstname("Теодор").withMiddlename("Джеймс")
              .withLastname("Уотсон").withAddress("г. Вязьма, ул. Ланского, д.6")
              .withMobilephone("+7(923)123-43-21").withWorkname("Лесничество им. Каракозова")
              .withEmail("teodorJW@mail.bk").withBday("7").withBmonth("July").withByear("1970").withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testPersonModification() {
    List<PersonData> before = app.person().list();
    int index = before.size() - 1;
    PersonData person = new PersonData()
            .withId(before.get(index).getId()).withFirstname("Питер").withMiddlename("Джеймс")
            .withLastname("Уотсон").withAddress("г. Пенза, ул. Ланского, д.6")
            .withMobilephone("+7(923)123-43-21").withWorkname("Лесничество им. Каракозова")
            .withEmail("teodorJW@mail.bk").withBday("7").withBmonth("July").withByear("1972");
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
