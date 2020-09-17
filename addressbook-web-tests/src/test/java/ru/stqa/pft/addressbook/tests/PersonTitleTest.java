package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTitleTest extends TestBase {

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
              .withBday(7).withBmonth("July").withByear("1970"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testPersonPhone() {
    app.goTo().homePage();
    PersonData person = app.person().all().iterator().next();
    PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

    assertThat(person.getAllPhones(), equalTo(mergePhones(personInfoFromEditForm)));
    assertThat(person.getAddress(),
            equalTo(personInfoFromEditForm.getAddress().replaceAll("\\s+", " ").trim()));
    assertThat(person.getAllEmails(), equalTo(mergeEmails(personInfoFromEditForm)));
  }

  private String mergePhones(PersonData person) {
    return Arrays.asList(person.getHomePhone(), person.getMobilePhone(), person.getWorkPhone(), person.getPhoneSecondary())
            .stream().filter((s) -> !s.equals("")).map(PersonTitleTest::cleanedPhones)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(PersonData person) {
    return Arrays.asList(person.getEmail().trim(), person.getEmail2().trim(), person.getEmail3().trim())
            .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  public static String cleanedPhones(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
