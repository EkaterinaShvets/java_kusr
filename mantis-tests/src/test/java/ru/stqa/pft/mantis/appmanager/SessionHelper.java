package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAsAdminUI() {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), app.getProperty("web.adminLogin"));
    click(By.cssSelector("input[type='submit']"));
    type(By.id("password"), app.getProperty("web.adminPassword"));
    click(By.cssSelector("input[type='submit']"));
  }
}
