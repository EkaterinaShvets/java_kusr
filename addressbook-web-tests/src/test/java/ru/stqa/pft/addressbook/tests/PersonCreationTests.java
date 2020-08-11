package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

public class PersonCreationTests extends TestBase {

  @Test
  public void testPersonCreation()  {
    app.getPersonHelper().gotoNewPersonPage();
    app.getPersonHelper().fillPersonForm(new PersonData("Теодор", "Джеймс", "Уотсон", "г. Вязьма, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", "test1"), true);
    app.getPersonHelper().submitPersonCreation();
    app.getNavigationHelper().gotoHomePage();
  }

}
