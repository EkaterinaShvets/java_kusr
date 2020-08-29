package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Groups;
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
    type(By.name("mobile"), personData.getMobilephone());
    type(By.name("work"), personData.getWorkname());
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
    wd.findElement(By.xpath("//a[@href=\"edit.php?id="+id+"\"]")).click();
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

  public int getPersonCount() {
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
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      personCash.add(new PersonData().withId(id).withFirstname(firstName).withLastname(lastName));
    }
    return new Persons(personCash);
  }

}
