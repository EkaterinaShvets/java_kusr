package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import java.util.List;

public class PersonHelper extends HelperBase {
  private Persons personCash = null;

  public PersonHelper(WebDriver wd) {
    super(wd);
  }

  public void submitPersonCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void submitPersonModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectPersonById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void deleteSelectedPerson() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void fillPersonForm(PersonData personData, boolean creation) {
    type(By.name("firstname"), personData.getFirstname());
    type(By.name("middlename"), personData.getMiddlename());
    type(By.name("lastname"), personData.getLastname());
    type(By.name("address"), personData.getAddress());
    type(By.name("mobile"), personData.getMobilePhone());
    type(By.name("work"), personData.getWorkPhone());
    type(By.name("home"), personData.getHomePhone());
    type(By.name("email"), personData.getEmail());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(personData.getBday());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(personData.getBmonth());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(personData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    type(By.name("byear"), personData.getByear());
  }

  public void gotoNewPersonPage() {
    click(By.linkText("add new"));
  }

  public void initPersonModificationById(int id) {
    wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
  }

  public void create(PersonData person) {
    gotoNewPersonPage();
    fillPersonForm(person, true);
    submitPersonCreation();
    personCash = null;
  }

  public void modify(PersonData person) {
    initPersonModificationById(person.getId());
    fillPersonForm(person, false);
    submitPersonModification();
    personCash = null;
  }

  public void delete(PersonData person) {
    selectPersonById(person.getId());
    deleteSelectedPerson();
    personCash = null;
  }

  public boolean isThereAPerson() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input"));
  }

  public int count() {
    return wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td/input")).size();
  }

  public Persons all() {
    if (personCash != null) {
      return new Persons(personCash);
    }

    personCash = new Persons();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      personCash.add(new PersonData().withId(id).withFirstname(firstName).withLastname(lastName)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return new Persons(personCash);
  }

  public PersonData infoFromEditForm(PersonData person) {
    initPersonModificationById(person.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();

    return new PersonData().withId(person.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }
}
