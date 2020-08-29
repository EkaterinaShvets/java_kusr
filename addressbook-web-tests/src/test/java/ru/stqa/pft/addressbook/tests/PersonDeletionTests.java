package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.List;

public class PersonDeletionTests extends TestBase {

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
  public void testPersonDeletionTest() {
    List<PersonData> before = app.person().list();
    int index = before.size() - 1;
    app.person().delete(index);
    app.goTo().homePage();
    List<PersonData> after = app.person().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(after, before);
  }

}
