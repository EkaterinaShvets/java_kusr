package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.PersonData;

public class PersonHelper extends HelperBase {

  public PersonHelper(FirefoxDriver wd) {
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

  public void fillPersonForm(PersonData personData) {
    type(By.name("firstname"), personData.getFirstname());
    type(By.name("middlename"), personData.getMiddlename());
    type(By.name("lastname"), personData.getLastname());
    type(By.name("address"), personData.getAddress());
    type(By.name("mobile"), personData.getMobilephone());
    type(By.name("work"), personData.getWorkname());
    type(By.name("email"), personData.getEmail());
    clickSelect(By.name("bday"), personData.getBday());
    clickSelect(By.name("bmonth"), personData.getBmonth());
    type(By.name("byear"), personData.getByear());
  }

  public void gotoNewPersonPage() {
    click(By.linkText("add new"));
  }

  public void initPersonModification () {
    click(By.xpath("//img[@alt='Edit']"));
  }
}
