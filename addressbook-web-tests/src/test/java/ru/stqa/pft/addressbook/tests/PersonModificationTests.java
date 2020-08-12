package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

public class PersonModificationTests extends TestBase{

  @Test
  public void testPersonModification(){
    if (! app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson (new PersonData("Теодор", "Джеймс", "Уотсон", "г. Вязьма, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    app.getPersonHelper().initPersonModification();
    app.getPersonHelper().fillPersonForm(new PersonData("Питер", "Джеймс", "Уотсон", "г. Пенза, ул. Ланского, д.6", "+7(923)123-43-21", "Лесничество им. Каракозова", "teodorJW@mail.bk", "7", "July", "1970", "teodorJW@mail.bk", null), false);
    app.getPersonHelper().submitPersonModification();
    app.getNavigationHelper().gotoHomePage();
  }

}
