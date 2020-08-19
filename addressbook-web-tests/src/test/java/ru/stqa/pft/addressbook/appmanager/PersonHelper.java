package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.ArrayList;
import java.util.List;

public class PersonHelper extends HelperBase {

  public PersonHelper(WebDriver wd) {
    super(wd);
  }

  public void submitPersonCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void submitPersonModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectPerson(int index) {
    wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td/input")).get(index).click();
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

  public void initPersonModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void createPerson(PersonData person) {
    gotoNewPersonPage();
    fillPersonForm(person, true);
    submitPersonCreation();
  }

  public boolean isThereAPerson() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input"));
  }

  public int getPersonCount() {
    return wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td/input")).size();
  }


  public List<PersonData> getPersonList() {
    List<PersonData> persons = new ArrayList<PersonData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      PersonData person = new PersonData(firstName, null, lastName, null, null, null, null, null, null, null, null, null);
      persons.add(person);
    }
    return persons;
  }
}
