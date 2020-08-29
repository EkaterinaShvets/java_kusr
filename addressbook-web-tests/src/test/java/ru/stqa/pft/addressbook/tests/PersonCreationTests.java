package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class PersonCreationTests extends TestBase {

  @Test
  public void testPersonCreation() {
    app.goTo().homePage();
    List<PersonData> before = app.person().list();
    PersonData person = new PersonData().withFirstname("Теодор").withMiddlename("Джеймс")
            .withLastname("Уотсон").withAddress("г. Вязьма, ул. Ланского, д.6")
            .withMobilephone("+7(923)123-43-21").withWorkname("Лесничество им. Каракозова")
            .withEmail("teodorJW@mail.bk").withBday("7").withBmonth("July").withByear("1970").withGroup("test1");
    app.person().create(person);
    app.goTo().homePage();
    List<PersonData> after = app.person().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(person);
    Comparator<? super PersonData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
