package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class PersonCreationTests extends TestBase {

  @Test
  public void testPersonCreation() {
    app.goTo().homePage();
    Persons before = app.person().all();
    PersonData person = new PersonData().withFirstname("Теодор").withMiddlename("Джеймс")
            .withLastname("Уотсон").withAddress("г. Вязьма, ул. Ланского, д.6")
            .withMobilephone("+7(923)123-43-21").withWorkname("Лесничество им. Каракозова")
            .withEmail("teodorJW@mail.bk").withBday("7").withBmonth("July").withByear("1970").withGroup("test1");
    app.person().create(person);
    app.goTo().homePage();
    Persons after = app.person().all();
    assertEquals(after.size(), before.size()+1);
    assertThat(after, equalTo(
            before.whithAdded(person.withId(after.stream().mapToInt((p) -> p.getId()).max().getAsInt()))));
  }

}
