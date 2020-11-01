package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.PersonData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddPersonToGroupTests extends TestBase {
  PersonData modifiedPerson;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
      app.goTo().homePage();
    }
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
    modifiedPerson = app.db().persons().iterator().next();
    if (app.db().groups().size() == modifiedPerson.getGroups().size()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testAddPersonToGroup() {
    int modifiedPersonId = modifiedPerson.getId();
    GroupData groupForAdd = new GroupData();
    for (GroupData group : app.db().groups()) {
      if (!modifiedPerson.getGroups().contains(group)) {
        groupForAdd = group;
        break;
      }
    }

    Groups before = modifiedPerson.getGroups();
    app.person().AddToGroup(modifiedPerson, groupForAdd);
    app.goTo().homePage();

    PersonData personAfterAdd = new PersonData();
    for (PersonData person : app.db().persons()) {
      if (person.getId() == modifiedPersonId) {
        personAfterAdd = person;
        break;
      }
    }
    Groups after = personAfterAdd.getGroups();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalToObject(before.whithAdded(groupForAdd)));
   }

}
