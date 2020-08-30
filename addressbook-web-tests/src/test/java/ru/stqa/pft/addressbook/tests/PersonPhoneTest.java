package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonPhoneTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.person().all().size() == 0) {
      app.person().create(new PersonData().withFirstname("Теодор").withMiddlename("Джеймс")
              .withLastname("Уотсон").withAddress("г. Вязьма, ул. Ланского, д.6")
              .withMobilePhone("+7(923)123-43-21").withWorkPhone("12-13-14").withHomePhone("33-33-44")
              .withEmail("teodorJW@mail.bk").withBday("7").withBmonth("July").withByear("1970").withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testPersonPhone() {
    app.goTo().homePage();
    PersonData person = app.person().all().iterator().next();
    PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

    assertThat(person.getHomePhone(), equalTo(cleaned(personInfoFromEditForm.getHomePhone())));
    assertThat(person.getMobilePhone(), equalTo(cleaned(personInfoFromEditForm.getMobilePhone())));
    assertThat(person.getWorkPhone(), equalTo(cleaned(personInfoFromEditForm.getWorkPhone())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
