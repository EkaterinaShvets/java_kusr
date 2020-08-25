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
      app.person().create(new PersonData("Теодор", "Джеймс", "Уотсон", "г. Вязьма, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", "test1"));
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
