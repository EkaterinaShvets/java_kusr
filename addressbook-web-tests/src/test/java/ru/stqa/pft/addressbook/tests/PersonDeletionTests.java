package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class PersonDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
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
              .withBday(7).withBmonth("July").withByear("1970")
              .withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testPersonDeletionTest() {
    Persons before = app.db().persons();
    PersonData deletedPerson = before.iterator().next();
    app.person().delete(deletedPerson);
    app.goTo().homePage();
    assertEquals(app.person().count(), before.size()-1);
    Persons after = app.db().persons();
    assertThat(after, equalToObject(before.whithout(deletedPerson)));
  }

}
