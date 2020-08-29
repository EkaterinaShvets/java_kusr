package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class PersonModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.person().all().size() == 0) {
      app.person().create(new PersonData().withFirstname("Теодор").withMiddlename("Джеймс")
              .withLastname("Уотсон").withAddress("г. Вязьма, ул. Ланского, д.6")
              .withMobilephone("+7(923)123-43-21").withWorkname("Лесничество им. Каракозова")
              .withEmail("teodorJW@mail.bk").withBday("7").withBmonth("July").withByear("1970").withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testPersonModification() {
    Persons before = app.person().all();
    PersonData modifiedPerson = before.iterator().next();
    PersonData person = new PersonData()
            .withId(modifiedPerson.getId()).withFirstname("Питер").withMiddlename("Джеймс")
            .withLastname("Уотсон").withAddress("г. Пенза, ул. Ланского, д.6")
            .withMobilephone("+7(923)123-43-21").withWorkname("Лесничество им. Каракозова")
            .withEmail("teodorJW@mail.bk").withBday("7").withBmonth("July").withByear("1972");
    app.person().modify(person);
    app.goTo().homePage();
    Persons after = app.person().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.whitModified(modifiedPerson,person)));
  }

}
