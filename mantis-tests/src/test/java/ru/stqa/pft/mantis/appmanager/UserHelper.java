package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase{

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void selectUserForChange(int id) {
    wd.get(app.getProperty("web.baseUrl")+ "/manage_user_page.php");
    wd.findElement(By.xpath(String.format("//a[@href='manage_user_edit_page.php?user_id=%s']", id))).click();
  }

  public void resetPassword() {
    click(By.cssSelector("form#manage-user-reset-form input[type='submit']"));
  }

  public String modifyPassword(String confirmationLink, String username) {
    wd.get(confirmationLink);
    String newpassword = "newpassword";
    type(By.name("realname"),username);
    type(By.name("password"),newpassword);
    type(By.name("password_confirm"),newpassword);
    click(By.cssSelector("button[type='submit']"));
    return newpassword;
  }
}
