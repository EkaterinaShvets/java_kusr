package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class PersonModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().persons().size() == 0) {
      app.person().create(new PersonData()
              .withFirstname("Теодор")
              .withMiddlename("Джеймс")
              .withLastname("Уотсон")
              .withPhoto(new File("src/test/resources/photo.png"))
              .withAddress("г. Вязьма, ул. Ланского, д.6")
              .withHomePhone("45-64-3325")
              .withMobilePhone("+7(923)123-43-21")
              .withWorkPhone("12-13-14")
              .withEmail("teodorJW@mail.bk")
              .withEmail2("testJW@mail.bk")
              .withEmail3("testJW@gmail.bk")
              .withBday(7).withBmonth("July").withByear("1970"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testPersonModification() {
    Persons before = app.db().persons();
    PersonData modifiedPerson = before.iterator().next();
    PersonData person = new PersonData()
            .withId(modifiedPerson.getId())
            .withFirstname("Питер")
            .withMiddlename("Джеймс")
            .withLastname("Уотсон")
            .withPhoto(new File("src/test/resources/photo.png"))
            .withAddress("г. Пенза, ул. Ланского, д.6")
            .withHomePhone("45-64-3325")
            .withMobilePhone("+7(555)123-43-21")
            .withWorkPhone("435-34-345")
            .withEmail("teodorJW@mail.com")
            .withEmail2("testJW@mail.com")
            .withEmail3("testJW@gmail.com")
            .withBday(9).withBmonth("July").withByear("1976");
    app.person().modify(person);
    app.goTo().homePage();
    assertEquals(app.person().count(), before.size());
    Persons after = app.db().persons();
    assertThat(after, equalTo(before.whitModified(modifiedPerson,person)));
    verifyPersonListInUI();
  }

}
