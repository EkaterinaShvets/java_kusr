package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.PersonData;

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

  public void selectPerson() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input"));
  }

  public void deleteSelectedPerson() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  };

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
      Assert.assertFalse(isElementPresent (By.name("new_group")));
    }

    type(By.name("byear"), personData.getByear());
  }

  public void gotoNewPersonPage() {
    click(By.linkText("add new"));
  }

  public void initPersonModification () {
    click(By.xpath("//img[@alt='Edit']"));
  }
}
