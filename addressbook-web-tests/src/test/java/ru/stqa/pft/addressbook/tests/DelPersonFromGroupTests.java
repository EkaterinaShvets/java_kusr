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

public class DelPersonFromGroupTests extends TestBase {
  PersonData modifiedPerson;
  GroupData groupForDelete;
  Groups before;

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
    groupForDelete = app.db().groups().iterator().next();
      if (modifiedPerson.getGroups().size() == 0) {
      app.person().AddToGroup(modifiedPerson, groupForDelete);
      app.goTo().homePage();
      before = modifiedPerson.getGroups().whithAdded(groupForDelete);
    } else {
        groupForDelete = modifiedPerson.getGroups().iterator().next();
        before = modifiedPerson.getGroups();
      }
  }

  @Test
  public void testAddPersonToGroup() {
    int modifiedPersonId = modifiedPerson.getId();
    app.person().delFromGroup(modifiedPerson, groupForDelete);

    PersonData personAfterDelete = new PersonData();
    for (PersonData person : app.db().persons()){
      if (person.getId() == modifiedPersonId) {
        personAfterDelete = person;
        break;
      }
    }

    Groups after = personAfterDelete.getGroups();
    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalToObject(before.whithout(groupForDelete)));
 }

}
